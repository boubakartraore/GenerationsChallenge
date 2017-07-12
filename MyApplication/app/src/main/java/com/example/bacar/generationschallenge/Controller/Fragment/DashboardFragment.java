package com.example.bacar.generationschallenge.Controller.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bacar.generationschallenge.Controller.Authentification.LoginActivity;
import com.example.bacar.generationschallenge.Controller.myDate;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Joueurs;
import com.example.bacar.generationschallenge.Model.Match;
import com.example.bacar.generationschallenge.R;
import com.example.bacar.generationschallenge.Test.NavMainActivity;

import java.util.Date;

/**
 * Created by Bacar on 08/06/2017.
 */

public class DashboardFragment extends Fragment {

    private int logged = 0;
    private SharedPreferences sharedPreferences;

    private View view;

    // Les données
    private Joueurs player;
    private Match nextMatch;
    private Match lastMatch;
    private Equipe playersTeam;

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
    private Button connectionButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        sharedPreferences = this.getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        logged = sharedPreferences.getInt("Logged", 0);

        if (logged == 1) {
            view = inflater.inflate(R.layout.profil_fragment, container, false);
        }
        else {
            view = inflater.inflate(R.layout.not_logged_layout, container, false);
        }

        // initialisation
        player = new Joueurs(1, "Player 1", "Nom", "mail@mail.com", "tester", "Attaquant", 1, "0123445566", 42);
        playersTeam = new Equipe("Equipe 1", "Player 1", "Blue", 0,0,0,0,0);
        lastMatch = new Match(1, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        nextMatch = new Match(2, new Date(), "Equipe 1", "Equipe 5", 0, 0);
        String dateLastMatch = new myDate().myDate(lastMatch.getDate());
        String heureLastMatch = new myDate().myHeure(lastMatch.getDate());

        String dateNextMatch = new myDate().myDate(nextMatch.getDate());
        String heureNextMatch = new myDate().myHeure(nextMatch.getDate());


        if (logged == 1) {
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


            switch (player.getPoste()) {
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

            // on affecte les valeurs a la vue
            // Infos du joueur
            profilFirstname.setText(player.getFirstname());
            profilLastname.setText(player.getLastname());
            profilMail.setText(player.getMail());
            profilPhone.setText(player.getTelephone());
            profilPoste.setText(player.getPoste());

            // Infos d'equipe
            profilTextTeam.setText(R.string.profilTextTeam);
            profilTeam.setText(playersTeam.getName());

            // Infos match precedent et suivant
            profilTextLastMatch.setText(R.string.profilTextLastMatch);
            profilTextNextMatch.setText(R.string.profilTextNextMatch);

            profilLastMatchDate.setText("Le " + dateLastMatch + " à " + heureLastMatch);
            profilLastMatchDom.setText(lastMatch.getEquipeDomicile());
            profilLastMatchScoreDom.setText(lastMatch.getScoreEquipeDomicile().toString());
            profilLastMatchScoreExt.setText(lastMatch.getScoreEquipeExterieur().toString());
            profilLastMatchExt.setText(lastMatch.getEquipeExterieur());

            profilNextMatchDate.setText("Le " + dateNextMatch + " à " + heureNextMatch);
            profilNextMatchDom.setText(nextMatch.getEquipeDomicile());
            profilNextMatchExt.setText(nextMatch.getEquipeExterieur());

            profilButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("Logged", 0);
                    editor.commit();
                    Intent act = new Intent(getActivity(), NavMainActivity.class);
                    startActivity(act);
                }
            });
            return view;
        } else {
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
