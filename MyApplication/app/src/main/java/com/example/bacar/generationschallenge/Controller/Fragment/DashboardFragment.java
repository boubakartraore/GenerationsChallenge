package com.example.bacar.generationschallenge.Controller.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import com.example.bacar.generationschallenge.Controller.myDate;
import com.example.bacar.generationschallenge.Model.AllMatch;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Network.HttpHandler;
import com.example.bacar.generationschallenge.Model.Network.MatchInterface;
import com.example.bacar.generationschallenge.Model.User;
import com.example.bacar.generationschallenge.Model.Match;
import com.example.bacar.generationschallenge.R;
import com.example.bacar.generationschallenge.Test.NavMainActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Bacar on 08/06/2017.
 */

public class DashboardFragment extends Fragment {

    private int logged = 0;
    private String userLastname, userFirstname, userUsername, userPhone, userPoste, userMail, userTeam, userGoal;
    private SharedPreferences sharedPreferences;

    private String Attaquant = "Attaquant";

    private ProgressDialog pDialog;
    private String txtResponse;

    private View view;

    // Les données
    private User player;
    private Match nextMatch;
    private Match lastMatch;
    private Equipe playersTeam;
    private AllMatch lesMatchs;

    // La vue
    private ImageView profilImage;
    private ImageView profilPosteImage;
    private ImageView profilTeamImage;
    private TextView profilFirstname;
    private TextView profilLastname;
    private TextView profilPoste;
    private TextView profilPhone;
    private TextView profilMail;
    private TextView profilTextTeam;
    private TextView profilTeam;
    private TextView profilTextLastMatch;
    private TextView profilLastMatchDate;
    private TextView profilLastMatchDom;
    private TextView profilLastMatchScoreDom;
    private TextView profilSeparator;
    private TextView profilLastMatchScoreExt;
    private TextView profilLastMatchExt;
    private TextView profilTextNextMatch;
    private TextView profilNextMatchDate;
    private TextView profilNextMatchDom;
    private TextView profilNextMatchExt;
    private Button profilButton;
    private Button deconnectButton;
    private Button connectionButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        sharedPreferences = this.getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        logged = sharedPreferences.getInt("Logged", 0);
        userFirstname = sharedPreferences.getString("firstname", "");
        userLastname = sharedPreferences.getString("lastname", "");
        userUsername = sharedPreferences.getString("username", "");
        userMail = sharedPreferences.getString("mail", "");
        userPhone = sharedPreferences.getString("phone", "");
        userPoste = sharedPreferences.getString("poste", "");
        userGoal = sharedPreferences.getString("goal", "");
        userTeam = sharedPreferences.getString("team", "");

        if (logged == 1) {
            view = inflater.inflate(R.layout.profil_fragment, container, false);

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);

            profilImage = (ImageView) view.findViewById(R.id.profilImage);
            profilPosteImage = (ImageView) view.findViewById(R.id.profilPosteImage);
            profilTeamImage = (ImageView) view.findViewById(R.id.profilTeamImage);
            profilFirstname = (TextView) view.findViewById(R.id.profilFirstname);
            profilLastname = (TextView) view.findViewById(R.id.profilLastname);
            profilPoste = (TextView) view.findViewById(R.id.profilPoste);
            profilPhone = (TextView) view.findViewById(R.id.profilPhone);
            profilMail = (TextView) view.findViewById(R.id.profilMail);
            profilTextTeam = (TextView) view.findViewById(R.id.profilTextEquipe);
            profilTeam = (TextView) view.findViewById(R.id.profilTeam);
            profilTextLastMatch = (TextView) view.findViewById(R.id.profilTextLastMatch);
            profilLastMatchDate = (TextView) view.findViewById(R.id.profilLastMatchDate);
            profilLastMatchDom = (TextView) view.findViewById(R.id.profilLastMatchDom);
            profilLastMatchScoreDom = (TextView) view.findViewById(R.id.profilLastMatchScoreDom);
            profilSeparator = (TextView) view.findViewById(R.id.profilSeparator);
            profilLastMatchScoreExt = (TextView) view.findViewById(R.id.profilLastMatchScoreExt);
            profilLastMatchExt = (TextView) view.findViewById(R.id.profilLastMatchExt);
            profilTextNextMatch = (TextView) view.findViewById(R.id.profilTextNextMatch);
            profilNextMatchDate = (TextView) view.findViewById(R.id.profilNextMatchDate);
            profilNextMatchDom = (TextView) view.findViewById(R.id.profilNextMatchDom);
            profilNextMatchExt = (TextView) view.findViewById(R.id.profilNextMatchExt);
            profilButton = (Button) view.findViewById(R.id.profilButton);
            deconnectButton = (Button) view.findViewById(R.id.profilDeconnexion);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://bacar.000webhostapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            MatchInterface requestTeamInterface = retrofit.create(MatchInterface.class);

            final Call<AllMatch> requete = requestTeamInterface.getMatch();

            requete.enqueue(new Callback<AllMatch>() {

                @Override
                public void onResponse(Call<AllMatch> call, retrofit2.Response<AllMatch> response) {

                    if (response.isSuccessful()) {

                        AllMatch test = response.body();

                        lastMatch = test.getLastMatch(userTeam);

                        nextMatch = test.getNextMatch(userTeam);

                        profilLastMatchDate.setText(lastMatch.getDate());
                        profilLastMatchDom.setText(lastMatch.getEquipeDomicile());
                        profilLastMatchScoreDom.setText(lastMatch.getScoreEquipeDomicile());
                        profilLastMatchScoreExt.setText(lastMatch.getScoreEquipeExterieur());
                        profilLastMatchExt.setText(lastMatch.getEquipeExterieur());

                        profilNextMatchDate.setText(nextMatch.getDate());
                        profilNextMatchDom.setText(nextMatch.getEquipeDomicile());
                        profilNextMatchExt.setText(nextMatch.getEquipeExterieur());

                    }
                }

                @Override
                public void onFailure(Call<AllMatch> call, Throwable t) {

                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                }
            });

            profilFirstname.setText(userUsername);
            profilLastname.setText(userFirstname + " " + userLastname);
            profilMail.setText(userMail);
            profilPhone.setText(userPhone);
            profilPoste.setText(userPoste);
            profilTeam.setText(userTeam);

            Picasso.with(getActivity())
                    .load("https://bacar.000webhostapp.com/photo/no_photo.jpg")
                    .placeholder(R.drawable.team)
                    .into(profilTeamImage);

            switch (userPoste) {
                case "Attaquant":
                    profilPosteImage.setImageResource(R.drawable.poste_attaquant);
                    break;
                case "Milieu":
                    profilPosteImage.setImageResource(R.drawable.poste_milieu);
                    break;
                case "Defenseur":
                    profilPosteImage.setImageResource(R.drawable.poste_defenseur);
                    break;
                case "Gardien de but":
                    profilPosteImage.setImageResource(R.drawable.poste_gardien);
                    break;
            }

            profilButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            deconnectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPreferences = DashboardFragment.this.getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("Logged", 0);
                    editor.apply();
                    Intent act = new Intent(getActivity(), NavMainActivity.class);
                    startActivity(act);
                }
            });

            return view;


        } else {
            view = inflater.inflate(R.layout.not_logged_layout, container, false);

            connectionButton = (Button) view.findViewById(R.id.connectionButton);

            connectionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent act = new Intent(getActivity(), LoginActivity.class);
                    startActivity(act);
                }
            });
            return view;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.myProfil);
    }

}
