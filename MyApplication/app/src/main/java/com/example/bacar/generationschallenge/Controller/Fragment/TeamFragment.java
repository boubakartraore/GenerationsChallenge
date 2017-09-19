package com.example.bacar.generationschallenge.Controller.Fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bacar.generationschallenge.Controller.Adapter.ListTeamAdapter;
import com.example.bacar.generationschallenge.Controller.Authentification.LoginActivity;
import com.example.bacar.generationschallenge.Controller.Authentification.RegisterActivity;
import com.example.bacar.generationschallenge.Controller.Team.TeamInfoActivity;
import com.example.bacar.generationschallenge.Listener.ClickListener;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Network.InputStreamOperations;
import com.example.bacar.generationschallenge.Model.Network.JSONTeamResponse;
import com.example.bacar.generationschallenge.Model.Network.RequestInterface;
import com.example.bacar.generationschallenge.Model.Network.RequestTeamInterface;
import com.example.bacar.generationschallenge.Model.Network.ServerPlayerRequest;
import com.example.bacar.generationschallenge.Model.Network.ServerPlayerResponse;
import com.example.bacar.generationschallenge.Model.Network.ServerTeamRequest;
import com.example.bacar.generationschallenge.Model.Network.ServerTeamResponse;
import com.example.bacar.generationschallenge.Model.TouteEquipe;
import com.example.bacar.generationschallenge.Model.User;
import com.example.bacar.generationschallenge.Query.Requete;
import com.example.bacar.generationschallenge.R;
import com.example.bacar.generationschallenge.Test.NavMainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bacar on 07/06/2017.
 */

public class TeamFragment extends Fragment implements ListTeamAdapter.ListTeamAdapterListener {

    private ArrayList<Equipe> maListe = new ArrayList<Equipe>();
    private Equipe equipe;
    private RecyclerView rv;
    private ListTeamAdapter teamAdapter;
    private Requete req;

    private View view;
    private SharedPreferences sharedPreferences;
    private Integer logged;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


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

            view = inflater.inflate(R.layout.equipes_fragment, container, false);

            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setTitle("Chargement");
            progressDialog.setMessage("Veuillez patienter pendant le chargement des données...");
            progressDialog.show();

            // Initialisation de la RecyclerView

            rv = (RecyclerView) view.findViewById(R.id.list_team);
            rv.setRecycledViewPool(new RecyclerView.RecycledViewPool());

            // Agencement de la RecyclerView

            GridLayoutManager rvLayoutManager = new GridLayoutManager(getActivity(), 2);

            rv.setLayoutManager(rvLayoutManager);

            teamAdapter = new ListTeamAdapter(getContext(),this);

            rv.setAdapter(teamAdapter);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://bacar.000webhostapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RequestTeamInterface requestTeamInterface = retrofit.create(RequestTeamInterface.class);

            final Call<ArrayList<Equipe>> requete = requestTeamInterface.team_operation();

            requete.enqueue(new Callback<ArrayList<Equipe>>() {

                @Override
                public void onResponse(Call<ArrayList<Equipe>> call, retrofit2.Response<ArrayList<Equipe>> response) {

                    if (response.isSuccessful()) {

                        for (int i = 0; i < response.body().size(); i++) {

                            Equipe eq = response.body().get(i);

                            teamAdapter.addTeams(eq);

                        }
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<Equipe>> call, Throwable t) {

                    Log.d("FAILURE", "failed");

                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                }
            });

            progressDialog.dismiss();

            return view;
        }
    }

    @Override
    public void onClick(int position) {
        Equipe eq = teamAdapter.getSelectedTeam(position);
        Intent i = new Intent(getActivity(), TeamInfoActivity.class);
        i.putExtra("team", eq);
        startActivity(i);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.teams);

    }

}
