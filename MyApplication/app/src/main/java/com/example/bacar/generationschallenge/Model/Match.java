package com.example.bacar.generationschallenge.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Bacar on 06/06/2017.
 */

public class Match implements Serializable {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Day")
    @Expose
    private String journée;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("EquipeDom")
    @Expose
    private String equipeDomicile;
    @SerializedName("EquipeExt")
    @Expose
    private String equipeExterieur;
    @SerializedName("ScoreDom")
    @Expose
    private String scoreEquipeDomicile;
    @SerializedName("ScoreExt")
    @Expose
    private String scoreEquipeExterieur;
    @SerializedName("Played")
    @Expose
    private Boolean played;


    public Match() {
        this.id = "";
        this.journée = "";
        this.date = "";
        this.equipeDomicile = "";
        this.equipeExterieur = "";
        this.scoreEquipeDomicile = "";
        this.scoreEquipeExterieur = "";
        this.played = Boolean.FALSE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getPlayed() {
        return played;
    }

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    public Match(String journee, String date, String equipeDomicile, String equipeExterieur, String scoreEquipeDomicile, String scoreEquipeExterieur) {
        this.journée = journee;
        this.date = date;
        this.equipeDomicile = equipeDomicile;
        this.equipeExterieur = equipeExterieur;
        this.scoreEquipeDomicile = scoreEquipeDomicile;
        this.scoreEquipeExterieur = scoreEquipeExterieur;
        this.played = Boolean.FALSE;

    }
    public String getJournée() {
        return journée;
    }

    public void setJournée(String journée) {
        this.journée = journée;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getScoreEquipeDomicile() {
        return scoreEquipeDomicile;
    }

    public void setScoreEquipeDomicile(String scoreEquipeDomicile) {
        this.scoreEquipeDomicile = scoreEquipeDomicile;
    }

    public String getScoreEquipeExterieur() {
        return scoreEquipeExterieur;
    }

    public void setScoreEquipeExterieur(String scoreEquipeExterieur) {
        this.scoreEquipeExterieur = scoreEquipeExterieur;
    }

}
