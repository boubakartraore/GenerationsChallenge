package com.example.bacar.generationschallenge.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Bacar on 06/06/2017.
 */

public class Equipe implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("captain")
    @Expose
    private String captain;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("victory")
    @Expose
    private String victory;
    @SerializedName("tie")
    @Expose
    private String tie;
    @SerializedName("defeat")
    @Expose
    private String defeat;
    @SerializedName("goalScored")
    @Expose
    private String goalScored;
    @SerializedName("goalConceded")
    @Expose
    private String goalConceded;
    @SerializedName("photo")
    @Expose
    private String photo;

    public String getPhoto() {

        String test = "https://bacar.000webhostapp.com/" + photo;
        return test;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    //private Integer goalDifference;
    //private Integer points;
    //private Integer matchPlayed;

    public Equipe() {
        this.name = "";
        this.captain = "";
        this.color = "";
        this.victory = "";
        this.defeat = "";
        this.tie = "";
        this.goalScored = "";
        this.goalConceded = "";
        this.photo = "";
        //this.goalDifference = 0;
        //this.points = 0;
        //this.matchPlayed = 0;

    }

    public Equipe(String name, String captain, String colors, String victory, String defeat, String tie, String goalScored, String goalConceded) {
        this.name = name;
        this.captain = captain;
        this.color = colors;
        this.victory = victory;
        this.defeat = defeat;
        this.tie = tie;
        this.goalScored = goalScored;
        this.goalConceded = goalConceded;
        //this.goalDifference = goalDifference;
        //this.points = points;
        //this.matchPlayed = matchPlayed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColors() {
        return color;
    }

    public void setColors(String colors) {
        this.color = colors;
    }

    public String getVictory() {
        return victory;
    }

    public void setVictory(String victory) {
        this.victory = victory;
    }

    public String getDefeat() {
        return defeat;
    }

    public void setDefeat(String defeat) {
        this.defeat = defeat;
    }

    public String getTie() {
        return tie;
    }

    public void setTie(String tie) {
        this.tie = tie;
    }

    public String getGoalScored() {
        return goalScored;
    }

    public void setGoalScored(String goalScored) {
        this.goalScored = goalScored;
    }

    public String getGoalConceded() {
        return goalConceded;
    }

    public void setGoalConceded(String goalConceded) {
        this.goalConceded = goalConceded;
    }

    public Integer goalDifference() {
        return (Integer.valueOf(goalScored) - Integer.valueOf(goalConceded));
    }


    public Integer points() {
        return ((Integer.valueOf(this.victory) * 3) + Integer.valueOf(this.tie));
    }


    public Integer matchPlayed() {
        return (Integer.valueOf(this.victory) + Integer.valueOf(this.tie) + Integer.valueOf(this.defeat));
    }

    public static class EquipeComparator implements Comparator<Equipe> {
        @Override
        public int compare(Equipe o1, Equipe o2) {
            return o2.points().compareTo(o1.points());
        }
    }
}
