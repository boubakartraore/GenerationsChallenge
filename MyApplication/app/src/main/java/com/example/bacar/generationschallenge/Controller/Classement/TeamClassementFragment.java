package com.example.bacar.generationschallenge.Controller.Classement;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.bacar.generationschallenge.Controller.Adapter.ClassementEquipeAdapter;
import com.example.bacar.generationschallenge.Controller.Adapter.ListTeamAdapter;
import com.example.bacar.generationschallenge.Controller.Authentification.LoginActivity;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Network.RequestTeamInterface;
import com.example.bacar.generationschallenge.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.security.AccessController.getContext;

/**
 * Created by Bacar on 12/06/2017.
 */

public class TeamClassementFragment extends Fragment {

    private List<Equipe> maListe = new ArrayList<Equipe>();
    private Equipe equipe;
    private RecyclerView rv;

    private View view;
    private SharedPreferences sharedPreferences;
    private Integer logged;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        sharedPreferences = this.getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        logged = sharedPreferences.getInt("Logged", 0);

        if (logged == 0) {

            view = inflater.inflate(R.layout.not_logged_layout, container, false);

            Button connectionButton = (Button) view.findViewById(R.id.connectionButton);

            connectionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent act = new Intent(getActivity(), LoginActivity.class);
                    startActivity(act);
                }
            });

            return view;

        } else {

            view = inflater.inflate(R.layout.activity_team_classement, container, false);

            // Initialisation de la RecyclerView
            final RecyclerView rv = (RecyclerView) view.findViewById(R.id.team_classement);

            // Agencement de la RecyclerView
            LinearLayoutManager rvLayoutManager = new LinearLayoutManager(getContext());
            rv.setLayoutManager(rvLayoutManager);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://bacar.000webhostapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setTitle("Chargement");
            progressDialog.setMessage("Veuillez patienter pendant le chargement des données...");
            progressDialog.show();

            RequestTeamInterface requestTeamInterface = retrofit.create(RequestTeamInterface.class);
            final Call<ArrayList<Equipe>> requete = requestTeamInterface.team_operation();
            requete.enqueue(new Callback<ArrayList<Equipe>>() {


                @Override
                public void onResponse(Call<ArrayList<Equipe>> call, retrofit2.Response<ArrayList<Equipe>> response) {


                    for (int i = 0; i < response.body().size(); i++) {

                        Equipe eq = response.body().get(i);

                        maListe.add(eq);

                    }

                    Collections.sort(maListe, new Equipe.EquipeComparator());

                    // On créé un adapter pour la RecyclerView avec nos données
                    ClassementEquipeAdapter teamAdapter = new ClassementEquipeAdapter(maListe);

                    rv.setAdapter(teamAdapter);


                }

                @Override
                public void onFailure(Call<ArrayList<Equipe>> call, Throwable t) {

                    Log.d("FAILURE", "failed");

                    Toast.makeText(getActivity(), "Echec", Toast.LENGTH_LONG).show();

                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                }
            });

            progressDialog.dismiss();
            return view;
        }
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.classement);
    }

}