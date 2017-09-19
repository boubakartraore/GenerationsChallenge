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
 * Created by Bacar on 04/07/2017.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultListViewHolder> {

    private List<Match> match;
    private SharedPreferences sharedPreferences;

    public ResultAdapter(List<Match> item) {
        match = item;
    }

    public static class ResultListViewHolder extends RecyclerView.ViewHolder {

        TextView matchDate;
        TextView matchDay;
        TextView matchEquipeDom;
        TextView matchScoreDom;
        TextView matchScoreExt;
        TextView matchEquipeExt;

        public ResultListViewHolder (View itemMatch) {
            super(itemMatch);
            matchDate = (TextView) itemMatch.findViewById(R.id.calendarDate);
            matchDay = (TextView) itemMatch.findViewById(R.id.calendarDay);
            matchScoreDom = (TextView) itemMatch.findViewById(R.id.calendarScoreDom);
            matchScoreExt = (TextView) itemMatch.findViewById(R.id.calendarScoreExt);
            matchEquipeDom = (TextView) itemMatch.findViewById(R.id.calendarDom);
            matchEquipeExt = (TextView) itemMatch.findViewById(R.id.calendarExt);
        }

    }

    @Override
    public ResultListViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_items_played, parent, false);
        return new ResultListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultListViewHolder parent, int position) {
        Match item = match.get(position);

        String jour, heure;

        jour = new myDate().myDate(new myDate().stringToDate(item.getDate()));
        heure = new myDate().myHeure(new myDate().stringToDate(item.getDate()));

        parent.matchDay.setText("Match: " + item.getJournée().toString());
        parent.matchDate.setText("Le " + jour + " à " + heure);
        parent.matchEquipeDom.setText(item.getEquipeDomicile());
        parent.matchScoreDom.setText(item.getScoreEquipeDomicile().toString());
        parent.matchScoreExt.setText(item.getScoreEquipeExterieur().toString());
        parent.matchEquipeExt.setText(item.getEquipeExterieur());

    }

    @Override
    public int getItemCount() {
        return match.size();
    }


}
