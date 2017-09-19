package com.example.bacar.generationschallenge.Controller.Classement;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bacar.generationschallenge.Controller.Authentification.LoginActivity;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Network.AllPlayerInterface;
import com.example.bacar.generationschallenge.Model.Network.PlayersInterface;
import com.example.bacar.generationschallenge.Model.Network.RequestTeamInterface;
import com.example.bacar.generationschallenge.Model.User;
import com.example.bacar.generationschallenge.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bacar on 14/06/2017.
 */

public class ButeursClassementFragment extends Fragment {

    private List<User> play = new ArrayList<User>();
    private User j1;
    private Equipe equipe;
    private List<Equipe> maListe = new ArrayList<Equipe>();

    private TextView firstName;
    private TextView firstTeam;
    private TextView firstGoal;
    private ImageView firstPhoto;
    private TextView secondName;
    private TextView secondTeam;
    private TextView secondGoal;
    private ImageView secondPhoto;
    private TextView thirdName;
    private TextView thirdTeam;
    private TextView thirdGoal;
    private ImageView thirdPhoto;
    private Button allButeurs;

    private View view;
    private SharedPreferences sharedPreferences;
    private Integer logged;

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

            view = inflater.inflate(R.layout.activity_buteurs_classement, container, false);

            firstName = (TextView) view.findViewById(R.id.firstName);
            firstTeam = (TextView) view.findViewById(R.id.firstTeam);
            firstPhoto = (ImageView) view.findViewById(R.id.firstPhoto);
            firstGoal = (TextView) view.findViewById(R.id.firstGoal);
            secondName = (TextView) view.findViewById(R.id.secondName);
            secondTeam = (TextView) view.findViewById(R.id.secondTeam);
            secondGoal = (TextView) view.findViewById(R.id.secondGoal);
            secondPhoto = (ImageView) view.findViewById(R.id.secondPhoto);
            thirdName = (TextView) view.findViewById(R.id.thirdName);
            thirdTeam = (TextView) view.findViewById(R.id.thirdTeam);
            thirdGoal = (TextView) view.findViewById(R.id.thirdGoal);
            thirdPhoto = (ImageView) view.findViewById(R.id.thirdPhoto);
            allButeurs = (Button) view.findViewById(R.id.allButeurs);

            //dataJoueur();

            //TeamMatch();

            Collections.sort(play, new User.JoueursComparator());

            top5Buteurs(play);

            allButeurs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent tr = new Intent(getActivity(), AllButeursClassementActivity.class);
                    startActivity(tr);
                }
            });

            return view;
        }
    }

    private void top5Buteurs(List<User> list) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bacar.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i("onResponse", "Etape 1");

        AllPlayerInterface allPlayerInterface = retrofit.create(AllPlayerInterface.class);

        Log.i("onResponse", "Etape 2");

        Toast.makeText(getActivity(), "Lancement de la recherche", Toast.LENGTH_LONG).show();

        Call<ArrayList<User>> requete = allPlayerInterface.getUse();

        Log.i("onResponse", "Etape 3");

        requete.enqueue(new Callback<ArrayList<User>>() {


            @Override
            public void onResponse(Call<ArrayList<User>> call, retrofit2.Response<ArrayList<User>> response) {

                    play = response.body();

                    Collections.sort(play, new User.JoueursComparator());

                    User user1 = play.get(0);
                    User user2 = play.get(1);
                    User user3 = play.get(2);

                    firstName.setText(user1.getUsername());
                    firstTeam.setText(user1.getTeam_id());
                    firstGoal.setText("Buts: " + user1.getGoal());
                    firstPhoto.setImageResource(R.drawable.players);

                    secondName.setText(user2.getUsername());
                    secondTeam.setText(user2.getTeam_id());
                    secondGoal.setText("Buts: " + user2.getGoal());
                    secondPhoto.setImageResource(R.drawable.players);

                    thirdName.setText(user3.getUsername());
                    thirdTeam.setText(user3.getTeam_id());
                    thirdGoal.setText("Buts: " + user3.getGoal());
                    thirdPhoto.setImageResource(R.drawable.players);

            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

                Log.d("FAILURE","failed");

                Toast.makeText(getActivity(), "Echec", Toast.LENGTH_LONG).show();

                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.classementScorer);
    }
}

