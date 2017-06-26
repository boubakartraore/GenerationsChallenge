package com.example.bacar.generationschallenge.Model;

import org.json.JSONObject;

import java.util.Comparator;

import static java.sql.Types.NULL;

/**
 * Created by Bacar on 30/05/2017.
 */

public class Joueurs {

    private Integer id;
    private String firstname;
    private String lastname;
    private String mail;
    private String password;
    private String poste;
    private Integer team_id;
    private String telephone;
    private Integer goal;

    public Joueurs() {
        this.id = NULL;
        this.firstname = "";
        this.lastname = "";
        this.mail = "";
        this.password = "";
        this.poste = "";
        this.team_id = NULL;
        this.telephone = "";
        this.goal = 0;
    }

    /*public Joueurs(JSONObject jObject){
        this.id = jObject.optInt("id");
        this.firstname = jObject.optString("firstname");
        this.lastname = jObject.optString("lastname");
        this.mail = jObject.optString("mail");
        this.password = jObject.optString("password");
        this.poste = jObject.optString("poste");
        this.team_id = jObject.optInt("team_id");
        this.telephone = jObject.optInt("telephone");
        this.goal = jObject.optInt("goal");
    }*/

    public Joueurs(Integer id, String firstname, String lastname, String mail, String password, String poste, Integer team_id, String telephone, Integer goal) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.password = password;
        this.poste = poste;
        this.team_id = team_id;
        this.telephone = telephone;
        this.goal = goal;

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public static class JoueursComparator implements Comparator<Joueurs> {
        @Override
        public int compare(Joueurs o1, Joueurs o2) {
            return o2.getGoal().compareTo(o1.getGoal());
        }
    }
}
