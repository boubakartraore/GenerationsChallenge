package com.example.bacar.generationschallenge.Model;

import android.util.Log;

import com.example.bacar.generationschallenge.Controller.myDate;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Bacar on 29/08/2017.
 */

public class AllMatch implements Serializable {

    @SerializedName("teams")
    @Expose
    private List<Match> allMatch = new ArrayList<>();

    public List<Match> getAllMatch() {
        return allMatch;
    }

    public void setAllMatch(List<Match> allMatch) {
        this.allMatch = allMatch;
    }

    public void addMatch (Match match) {
        this.allMatch.add(match);
    }

    public Match getMatch (int i) {
        return this.allMatch.get(i);
    }

    public Match getNextMatch (String team_id) {

        Date aujourdhui = new Date();
        Match currentMatch = new Match();

        for (int i = 0 ; i < allMatch.size() ; i++) {

            currentMatch = allMatch.get(i);

            Log.d("PROGRESS", currentMatch.getEquipeDomicile());

            Date currentDate = new myDate().stringToDate(currentMatch.getDate());

            if (currentMatch.getEquipeDomicile().equals(team_id) || currentMatch.getEquipeExterieur().equals(team_id))

            {
                if (currentDate.after(aujourdhui)) {

                    return currentMatch;

                }
            }

        }

        return new Match();
    }

    public Match getLastMatch (String team_id) {

        Collections.reverse(allMatch);

        Date aujourdhui = new Date();
       //Match currentMatch = new Match();

        for (Match currentMatch : allMatch) {

            //currentMatch = allMatch.get(i);

            Log.d("PROGRESS", currentMatch.getEquipeDomicile());

            Date currentDate = new myDate().stringToDate(currentMatch.getDate());

            if (currentMatch.getEquipeDomicile().equals(team_id) || currentMatch.getEquipeExterieur().equals(team_id))

            {
                if (currentDate.before(aujourdhui)) {

                    return currentMatch;

                }
            }

        }

        return new Match();
    }

    public List<Match> teamMatch (String team_id) {

        List<Match> currentList = new ArrayList<>();
        for (int i = 0; i < allMatch.size(); i++) {
            Match currentMatch = new Match();
            currentMatch = allMatch.get(i);

            if (currentMatch.getEquipeDomicile().equals(team_id) || currentMatch.getEquipeExterieur().equals((team_id))) {
                currentList.add(currentMatch);
            }
        }
        return currentList;
    }

    public List<Match> dayMatch (Integer day) {

        List<Match> currentList = new ArrayList<>();
        for (int i = 0; i < allMatch.size(); i++) {
            Match currentMatch = new Match();
            currentMatch = allMatch.get(i);

            if (currentMatch.getJournée().equals(day.toString()) || currentMatch.getJournée().equals(day.toString())) {
                currentList.add(currentMatch);
            }
        }
        return currentList;
    }


}
