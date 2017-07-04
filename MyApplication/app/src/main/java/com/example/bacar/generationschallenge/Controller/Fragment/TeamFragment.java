package com.example.bacar.generationschallenge.Controller.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bacar.generationschallenge.Controller.Adapter.ListTeamAdapter;
import com.example.bacar.generationschallenge.Controller.Team.TeamInfoActivity;
import com.example.bacar.generationschallenge.Listener.ClickListener;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bacar on 07/06/2017.
 */

public class TeamFragment extends Fragment implements ClickListener{

    private List<Equipe> maListe = new ArrayList<Equipe>();
    private Equipe equipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.equipes_fragment, container, false);

        // Initialisation de la RecyclerView
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.list_team);
        // Agencement de la RecyclerView
        GridLayoutManager rvLayoutManager = new GridLayoutManager(getContext(), 2);
        rv.setLayoutManager(rvLayoutManager);

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



        // On créé un adapter pour la RecyclerView avec nos données
        ListTeamAdapter teamAdapter = new ListTeamAdapter(maListe);

        rv.setAdapter(teamAdapter);

        teamAdapter.setClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.teams);

    }

    @Override
    public void itemClicked(View view, int position) {
        Equipe eq = maListe.get(position);
        Toast.makeText(getContext(), eq.getName(), Toast.LENGTH_LONG).show();

        Intent i = new Intent(getActivity(), TeamInfoActivity.class);
        i.putExtra("team", eq.getName());
        startActivity(i);

        /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle("Equipe selectionnée");
        alertDialogBuilder.setMessage(eq.getName());
        alertDialogBuilder.show();*/
    }
}
