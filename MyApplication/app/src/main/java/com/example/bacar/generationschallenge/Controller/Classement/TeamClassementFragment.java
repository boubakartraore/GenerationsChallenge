package com.example.bacar.generationschallenge.Controller.Classement;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bacar.generationschallenge.Controller.Adapter.ClassementEquipeAdapter;
import com.example.bacar.generationschallenge.Controller.Adapter.ListTeamAdapter;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Bacar on 12/06/2017.
 */

public class TeamClassementFragment extends Fragment {

    private List<Equipe> maListe = new ArrayList<Equipe>();
    private Equipe equipe;
    private RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_team_classement, container, false);

        // Initialisation de la RecyclerView
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.team_classement);

        // Agencement de la RecyclerView
        LinearLayoutManager rvLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(rvLayoutManager);

        dataEquipe();

        Collections.sort(maListe, new Equipe.EquipeComparator());

        // On créé un adapter pour la RecyclerView avec nos données
        ClassementEquipeAdapter teamAdapter = new ClassementEquipeAdapter(maListe);

        rv.setAdapter(teamAdapter);

        return view;
    }

    private void dataEquipe() {
        equipe = new Equipe("Equipe 1", "Test", "Vert", 2,0,1,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 2", "Test", "Vert", 4,0,4,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 3", "Test", "Vert", 2,0,3,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 4", "Test", "Vert", 2,3,11,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 5", "Test", "Vert", 5,3,0,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 6", "Test", "Vert", 0,0,0,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 7", "Test", "Vert", 5,3,2,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 8", "Test", "Vert", 1,0,10,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 9", "Test", "Vert", 1,1,1,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 10", "Test", "Vert", 7,1,0,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 11", "Test", "Vert", 10,0,0,0,0);
        maListe.add(equipe);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.classement);
    }

}


/*public class TeamClassementFragment extends AppCompatActivity {

    private List<Equipe> maListe = new ArrayList<Equipe>();
    private Equipe equipe;
    private RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_classement);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialisation de la RecyclerView
        RecyclerView rv = (RecyclerView)findViewById(R.id.team_classement);
        // Agencement de la RecyclerView
        LinearLayoutManager rvLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(rvLayoutManager);

        equipe = new Equipe("Viltaneuse", "Test", "Vert", 2,0,1,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Boca Junior", "Test", "Vert", 4,0,4,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Fc Guess", "Test", "Vert", 2,0,3,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Inf", "Test", "Vert", 2,3,11,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Les anciens", "Test", "Vert", 5,3,0,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Hala Madrid", "Test", "Vert", 0,0,0,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Sporting", "Test", "Vert", 5,3,2,0,0);
        maListe.add(equipe);
        equipe = new Equipe("La Toho", "Test", "Vert", 1,0,10,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Dortmund", "Test", "Vert", 1,1,1,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Foyer", "Test", "Vert", 7,1,0,0,0);
        maListe.add(equipe);
        equipe = new Equipe("La Roja", "Test", "Vert", 10,0,0,0,0);
        maListe.add(equipe);

        Collections.sort(maListe, new Equipe.EquipeComparator());

        // On créé un adapter pour la RecyclerView avec nos données
        ClassementEquipeAdapter teamAdapter = new ClassementEquipeAdapter(maListe);

        rv.setAdapter(teamAdapter);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}*/
