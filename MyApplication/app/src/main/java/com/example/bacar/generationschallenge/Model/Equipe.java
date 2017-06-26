package com.example.bacar.generationschallenge.Model;

import java.util.Comparator;

/**
 * Created by Bacar on 06/06/2017.
 */

public class Equipe {

    private String name;
    private String captain;
    private String colors;
    private Integer victory;
    private Integer defeat;
    private Integer tie;
    private Integer goalScored;
    private Integer goalConceded;
    //private Integer goalDifference;
    //private Integer points;
    //private Integer matchPlayed;

    public Equipe() {
        this.name = "";
        this.captain = "";
        this.colors = "";
        this.victory = 0;
        this.defeat = 0;
        this.tie = 0;
        this.goalScored = 0;
        this.goalConceded = 0;
        //this.goalDifference = 0;
        //this.points = 0;
        //this.matchPlayed = 0;

    }

    public Equipe(String name, String captain, String colors, Integer victory, Integer defeat, Integer tie, Integer goalScored, Integer goalConceded) {
        this.name = name;
        this.captain = captain;
        this.colors = colors;
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

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public Integer getVictory() {
        return victory;
    }

    public void setVictory(Integer victory) {
        this.victory = victory;
    }

    public Integer getDefeat() {
        return defeat;
    }

    public void setDefeat(Integer defeat) {
        this.defeat = defeat;
    }

    public Integer getTie() {
        return tie;
    }

    public void setTie(Integer tie) {
        this.tie = tie;
    }

    public Integer getGoalScored() {
        return goalScored;
    }

    public void setGoalScored(Integer goalScored) {
        this.goalScored = goalScored;
    }

    public Integer getGoalConceded() {
        return goalConceded;
    }

    public void setGoalConceded(Integer goalConceded) {
        this.goalConceded = goalConceded;
    }

    public Integer goalDifference() {
        return (goalScored - goalConceded);
    }


    public Integer points() {
        return ((this.victory * 3) + this.tie);
    }


    public Integer matchPlayed() {
        return (this.victory + this.tie + this.defeat);
    }

    public static class EquipeComparator implements Comparator<Equipe> {
        @Override
        public int compare(Equipe o1, Equipe o2) {
            return o2.points().compareTo(o1.points());
        }
    }
}
