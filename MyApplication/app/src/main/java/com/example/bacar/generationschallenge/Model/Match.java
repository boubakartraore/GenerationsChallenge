package com.example.bacar.generationschallenge.Model;

import java.util.Date;

/**
 * Created by Bacar on 06/06/2017.
 */

public class Match {

    private Integer journée;
    private Date date;
    private String equipeDomicile;
    private String equipeExterieur;
    private Integer scoreEquipeDomicile;
    private Integer scoreEquipeExterieur;
    private Boolean played;

    public Match() {
        this.journée = 0;
        this.date = new Date();
        this.equipeDomicile = "";
        this.equipeExterieur = "";
        this.scoreEquipeDomicile = 0;
        this.scoreEquipeExterieur = 0;
        this.played = Boolean.FALSE;
    }

    public Boolean getPlayed() {
        return played;
    }

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    public Match(Integer journee, Date date, String equipeDomicile, String equipeExterieur, Integer scoreEquipeDomicile, Integer scoreEquipeExterieur) {
        this.journée = journee;
        this.date = date;
        this.equipeDomicile = equipeDomicile;
        this.equipeExterieur = equipeExterieur;
        this.scoreEquipeDomicile = scoreEquipeDomicile;
        this.scoreEquipeExterieur = scoreEquipeExterieur;
        this.played = Boolean.FALSE;

    }

    public Integer getJournée() {
        return journée;
    }

    public void setJournée(Integer journée) {
        this.journée = journée;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEquipeDomicile() {
        return equipeDomicile;
    }

    public void setEquipeDomicile(String equipeDomicile) {
        this.equipeDomicile = equipeDomicile;
    }

    public String getEquipeExterieur() {
        return equipeExterieur;
    }

    public void setEquipeExterieur(String equipeExterieur) {
        this.equipeExterieur = equipeExterieur;
    }

    public Integer getScoreEquipeDomicile() {
        return scoreEquipeDomicile;
    }

    public void setScoreEquipeDomicile(Integer scoreEquipeDomicile) {
        this.scoreEquipeDomicile = scoreEquipeDomicile;
    }

    public Integer getScoreEquipeExterieur() {
        return scoreEquipeExterieur;
    }

    public void setScoreEquipeExterieur(Integer scoreEquipeExterieur) {
        this.scoreEquipeExterieur = scoreEquipeExterieur;
    }
}
