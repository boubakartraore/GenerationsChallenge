package com.example.bacar.generationschallenge.Controller.Adapter;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bacar.generationschallenge.Controller.myDate;
import com.example.bacar.generationschallenge.Model.Match;
import com.example.bacar.generationschallenge.R;

import java.util.List;

/**
 * Created by Bacar on 20/06/2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarListViewHolder> {

    private List<Match> match;
    private SharedPreferences sharedPreferences;

    public CalendarAdapter(List<Match> item) {
        match = item;
    }

    public static class CalendarListViewHolder extends RecyclerView.ViewHolder {

        TextView matchDate;
        TextView matchDay;
        TextView matchEquipeDom;
        TextView matchEquipeExt;

        public CalendarListViewHolder (View itemMatch) {
            super(itemMatch);
            matchDate = (TextView) itemMatch.findViewById(R.id.calendarDate);
            matchDay = (TextView) itemMatch.findViewById(R.id.calendarDay);
            matchEquipeDom = (TextView) itemMatch.findViewById(R.id.calendarDom);
            matchEquipeExt = (TextView) itemMatch.findViewById(R.id.calendarExt);
        }

    }

    @Override
    public CalendarListViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_items, parent, false);
        return new CalendarListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CalendarListViewHolder parent, int position) {
        Match item = match.get(position);

        String jour, heure;

        jour = new myDate().myDate(item.getDate());
        heure = new myDate().myHeure(item.getDate());

        parent.matchDay.setText("Match: " + item.getJournée().toString());
        parent.matchDate.setText("Le " + jour + " à " + heure);
        parent.matchEquipeDom.setText(item.getEquipeDomicile());
        parent.matchEquipeExt.setText(item.getEquipeExterieur());

    }

    @Override
    public int getItemCount() {
        return match.size();
    }


}




/*

public class ClassementEquipeAdapter extends RecyclerView.Adapter<ClassementEquipeAdapter.ClassementEquipeListViewHolder> {

    private int color1 = 0xaaA5D6A7;
    private int color2 = 0xaaE57373;
    private List<Equipe> team;

    public ClassementEquipeAdapter(List<Equipe> item) {
        team = item;
    }

    public static class ClassementEquipeListViewHolder extends RecyclerView.ViewHolder {

        View view;
        CardView classementCard;
        TextView classementPos;
        TextView classementName;
        TextView classementPoints;
        TextView classementPlayed;
        TextView classementVictory;
        TextView classementTie;
        TextView classementDefeat;
        TextView classementGoalScored;
        TextView classementGoalConceded;
        TextView classementDifference;

        public ClassementEquipeListViewHolder(View itemPlayer) {
            super(itemPlayer);
            classementCard = (CardView) itemPlayer.findViewById(R.id.classementCard);
            classementPos = (TextView) itemPlayer.findViewById(R.id.classementPos);
            classementName = (TextView) itemPlayer.findViewById(R.id.classementName);
            classementPoints = (TextView) itemPlayer.findViewById(R.id.classementPts);
            classementPlayed = (TextView) itemPlayer.findViewById(R.id.classementPlayed);
            classementVictory = (TextView) itemPlayer.findViewById(R.id.classementVictory);
            classementTie = (TextView) itemPlayer.findViewById(R.id.classementTie);
            classementDefeat = (TextView) itemPlayer.findViewById(R.id.classementDefeat);
            classementGoalScored = (TextView) itemPlayer.findViewById(R.id.classementGoalScored);
            classementGoalConceded = (TextView) itemPlayer.findViewById(R.id.classementGoalConceded);
            classementDifference = (TextView) itemPlayer.findViewById(R.id.classementDifference);
        }
    }


    @Override
    public ClassementEquipeListViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_classement_items, parent, false);
        return new ClassementEquipeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassementEquipeListViewHolder parent, int position) {
        Equipe equipe = team.get(position);
        Integer positions = position + 1;
        parent.classementPos.setText(positions.toString());
        parent.classementName.setText(equipe.getName());
        parent.classementPoints.setText(equipe.points().toString());
        parent.classementPlayed.setText(equipe.matchPlayed().toString());
        parent.classementVictory.setText(equipe.getVictory().toString());
        parent.classementTie.setText(equipe.getTie().toString());
        parent.classementDefeat.setText(equipe.getDefeat().toString());
        parent.classementGoalScored.setText(equipe.getGoalScored().toString());
        parent.classementGoalConceded.setText(equipe.getGoalConceded().toString());
        parent.classementDifference.setText(equipe.goalDifference().toString());

        if(position < 3)
            parent.classementCard.setBackgroundColor(color1);
        else if (position == 10)
            parent.classementCard.setBackgroundColor(color2);

    }

    @Override
    public int getItemCount() {
        return team.size();
    }

}

 */
