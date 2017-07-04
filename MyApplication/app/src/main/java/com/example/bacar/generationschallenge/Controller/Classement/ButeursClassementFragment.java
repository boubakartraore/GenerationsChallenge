package com.example.bacar.generationschallenge.Controller.Classement;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bacar.generationschallenge.Controller.Adapter.PlayersListAdapter;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Joueurs;
import com.example.bacar.generationschallenge.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bacar on 14/06/2017.
 */

public class ButeursClassementFragment extends Fragment {

    private List<Joueurs> play = new ArrayList<Joueurs>();
    private Joueurs j1;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_buteurs_classement, container, false);

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

        dataJoueur();

        TeamMatch();

        Collections.sort(play, new Joueurs.JoueursComparator());

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

    private void dataJoueur() {
        j1 = new Joueurs(1, "Player 1", "Nom", "mail@mail.com", "tester", "Attaquant", 1, "0123445566", 1);
        play.add(j1);
        j1 = new Joueurs(2, "Player 2", "Nom", "mail@mail.com", "tester", "Chef du projet", 3, "0123445566", 10);
        play.add(j1);
        j1 = new Joueurs(3, "Player 3", "Nom", "mail@mail.com", "tester", "Milieu", 1, "0123445566", 2);
        play.add(j1);
        j1 = new Joueurs(4, "Player 4", "Nom", "mail@mail.com", "tester", "Defenseur", 1, "0123445566", 34);
        play.add(j1);
        j1 = new Joueurs(5, "Player 5", "Nom", "mail@mail.com", "tester", "Defenseur", 1, "0123445566", 0);
        play.add(j1);
        j1 = new Joueurs(6, "Player 6", "Nom", "mail@mail.com", "tester", "Attaquant", 1, "0123445566", 1);
        play.add(j1);
        j1 = new Joueurs(7, "Player 7", "Nom", "mail@mail.com", "tester", "Chef du projet", 1, "0123445566", 12);
        play.add(j1);
        j1 = new Joueurs(8, "Player 8", "Nom", "mail@mail.com", "tester", "Milieu", 3, "0123445566", 200);
        play.add(j1);
        j1 = new Joueurs(9, "Player 9", "Nom", "mail@mail.com", "tester", "Defenseur", 4, "0123445566", 348);
        play.add(j1);
        j1 = new Joueurs(10, "Player 10", "Nom", "mail@mail.com", "tester", "Defenseur", 1, "0123445566", 8);
        play.add(j1);
        j1 = new Joueurs(11, "Player 11", "Nom", "mail@mail.com", "tester", "Attaquant", 1, "0123445566", 3);
        play.add(j1);
        j1 = new Joueurs(12, "Player 12", "Nom", "mail@mail.com", "tester", "Chef du projet", 1, "0123445566", 3);
        play.add(j1);
        j1 = new Joueurs(13, "Player 13", "Nom", "mail@mail.com", "tester", "Milieu", 1, "0123445566", 2);
        play.add(j1);
        j1 = new Joueurs(14, "Player 14", "Nom", "mail@mail.com", "tester", "Defenseur", 1, "0123445566", 32);
        play.add(j1);
        j1 = new Joueurs(15, "Player 15", "Nom", "mail@mail.com", "tester", "Defenseur", 1, "0123445566", 0);
        play.add(j1);
    }

    private void top5Buteurs(List<Joueurs> list) {
        Joueurs premier = list.get(0);
        Joueurs deuxieme = list.get(1);
        Joueurs troisieme = list.get(2);

        firstName.setText(premier.getFirstname() + " " + premier.getLastname());
        firstTeam.setText(maListe.get(premier.getTeam_id() - 1).getName());
        firstGoal.setText(R.string.goalScored + ": " + premier.getGoal().toString());
        firstPhoto.setImageResource(R.drawable.players);

        secondName.setText(deuxieme.getFirstname() + " " +  deuxieme.getLastname());
        secondTeam.setText(maListe.get(deuxieme.getTeam_id() - 1).getName());
        secondGoal.setText(R.string.goalScored + ": " + deuxieme.getGoal().toString());
        secondPhoto.setImageResource(R.drawable.players);

        thirdName.setText(troisieme.getFirstname() + " " + troisieme.getLastname());
        thirdTeam.setText(maListe.get(troisieme.getTeam_id() - 1).getName());
        thirdGoal.setText(R.string.goalScored + ": " + premier.getGoal().toString());
        thirdPhoto.setImageResource(R.drawable.players);
    }

    public void TeamMatch() {
        equipe = new Equipe("Equipe 1", "Test", "Vert", 2,0,1,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 2", "Test", "Vert", 4,0,4,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 3", "Test", "Vert", 2,0,3,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 4", "Test", "Vert", 2,3,11,0,0);
        maListe.add(equipe);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.classementScorer);
    }
}



/*public class ButeursClassementFragment extends AppCompatActivity {

    private List<Joueurs> play = new ArrayList<Joueurs>();
    private Joueurs j1;

    private TextView firstName;
    private TextView firstTeam;
    private ImageView firstPhoto;
    private TextView secondName;
    private TextView secondTeam;
    private ImageView secondPhoto;
    private TextView thirdName;
    private TextView thirdTeam;
    private ImageView thirdPhoto;
    private TextView fourName;
    private TextView fourTeam;
    private ImageView fourPhoto;
    private TextView fifthName;
    private TextView fifthTeam;
    private ImageView fifthPhoto;
    private Button allButeurs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buteurs_classement);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstName = (TextView) findViewById(R.id.firstName);
        firstTeam = (TextView)findViewById(R.id.firstTeam);
        firstPhoto = (ImageView) findViewById(R.id.firstPhoto);
        secondName = (TextView)findViewById(R.id.secondName);
        secondTeam = (TextView)findViewById(R.id.secondTeam);
        secondPhoto = (ImageView)findViewById(R.id.secondPhoto);
        thirdName = (TextView)findViewById(R.id.thirdName);
        thirdTeam = (TextView)findViewById(R.id.thirdTeam);
        thirdPhoto = (ImageView)findViewById(R.id.thirdPhoto);
        fourName = (TextView)findViewById(R.id.fourName);
        fourTeam = (TextView)findViewById(R.id.fourTeam);
        fourPhoto = (ImageView)findViewById(R.id.fourPhoto);
        fifthName = (TextView)findViewById(R.id.fifthName);
        fifthTeam = (TextView)findViewById(R.id.fifthTeam);
        fifthPhoto = (ImageView)findViewById(R.id.fifthPhoto);
        allButeurs = (Button)findViewById(R.id.allButeurs);

        j1 = new Joueurs(1, "Boubakar", "Traore", "t.b@ho.com", "tester", "Attaquant", 1, "0123445566", 1);
        play.add(j1);
        j1 = new Joueurs(2, "Mehdi", "Zerfaoui", "t.b@hot.com", "tester", "Chef du projet", 1, "0123445566", 10);
        play.add(j1);
        j1 = new Joueurs(3, "Halid", "Ibrahim", "t.b@hott.com", "tester", "Milieu", 1, "0123445566", 2);
        play.add(j1);
        j1 = new Joueurs(4, "Moussa", "Kebe", "t.b@hobj.com", "tester", "Defenseur", 1, "0123445566", 34);
        play.add(j1);
        j1 = new Joueurs(5, "Christophe", "ShaoTest4", "t.b@holm.com", "tester", "Defenseur", 1, "0123445566", 0);
        play.add(j1);
        j1 = new Joueurs(6, "Boubakar", "Traore", "t.b@ho.com", "tester", "Attaquant", 1, "0123445566", 1);
        play.add(j1);
        j1 = new Joueurs(7, "Mehdi", "Zerfaoui", "t.b@hot.com", "tester", "Chef du projet", 1, "0123445566", 12);
        play.add(j1);
        j1 = new Joueurs(8, "Halid", "Ibrahim", "t.b@hott.com", "tester", "Milieu", 1, "0123445566", 200);
        play.add(j1);
        j1 = new Joueurs(9, "Moussa", "Kebe", "t.b@hobj.com", "tester", "Defenseur", 1, "0123445566", 348);
        play.add(j1);
        j1 = new Joueurs(10, "Christophe", "ShaoTest4", "t.b@holm.com", "tester", "Defenseur", 1, "0123445566", 8);
        play.add(j1);
        j1 = new Joueurs(11, "Boubakar", "Traore", "t.b@ho.com", "tester", "Attaquant", 1, "0123445566", 3);
        play.add(j1);
        j1 = new Joueurs(12, "Mehdi", "Zerfaoui", "t.b@hot.com", "tester", "Chef du projet", 1, "0123445566", 3);
        play.add(j1);
        j1 = new Joueurs(13, "Halid", "Ibrahim", "t.b@hott.com", "tester", "Milieu", 1, "0123445566", 2);
        play.add(j1);
        j1 = new Joueurs(14, "Moussa", "Kebe", "t.b@hobj.com", "tester", "Defenseur", 1, "0123445566", 32);
        play.add(j1);
        j1 = new Joueurs(15, "Christophe", "ShaoTest4", "t.b@holm.com", "tester", "Defenseur", 1, "0123445566", 0);
        play.add(j1);

        Collections.sort(play, new Joueurs.JoueursComparator());

        top5Buteurs(play);

        allButeurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tr = new Intent(ButeursClassementFragment.this, AllButeursClassementActivity.class);
                startActivity(tr);
            }
        });

    }

    private void top5Buteurs(List<Joueurs> list) {
        Joueurs premier = list.get(0);
        Joueurs deuxieme = list.get(1);
        Joueurs troisieme = list.get(2);
        Joueurs quatrieme = list.get(3);
        Joueurs cinquieme = list.get(4);

        firstName.setText(premier.getFirstname() + " " + premier.getLastname());
        firstTeam.setText(premier.getTeam_id().toString());
        firstPhoto.setImageResource(R.drawable.players);

        secondName.setText(deuxieme.getFirstname() + " " +  deuxieme.getLastname());
        secondTeam.setText(deuxieme.getTeam_id().toString());
        secondPhoto.setImageResource(R.drawable.players);

        thirdName.setText(troisieme.getFirstname() + " " + troisieme.getLastname());
        thirdTeam.setText(troisieme.getTeam_id().toString());
        thirdPhoto.setImageResource(R.drawable.players);

        fourName.setText(quatrieme.getFirstname() + " " + quatrieme.getLastname());
        fourTeam.setText(quatrieme.getTeam_id().toString());
        fourPhoto.setImageResource(R.drawable.players);

        fifthName.setText(cinquieme.getFirstname() + " " + cinquieme.getLastname());
        fifthTeam.setText(cinquieme.getTeam_id().toString());
        fifthPhoto.setImageResource(R.drawable.players);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}*/
