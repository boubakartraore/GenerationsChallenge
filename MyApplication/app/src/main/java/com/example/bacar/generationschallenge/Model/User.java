package com.example.bacar.generationschallenge.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Comparator;

import static java.sql.Types.NULL;

/**
 * Created by Bacar on 30/05/2017.
 */

public class User implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("salt")
    @Expose
    private String salt;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("poste")
    @Expose
    private String poste;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("goal")
    @Expose
    private String goal;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User() {
        this.id = "";
        this.firstname = "";
        this.lastname = "";
        this.mail = "";
        this.password = "";
        this.poste = "";
        this.teamId = "";
        this.phone = "";
        this.goal = "";
        this.role = "";
    }

    /*public User(JSONObject jObject){
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

    public User(String id, String firstname, String lastname, String mail, String password, String poste, String team_id, String telephone, String goal, String role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.password = password;
        this.poste = poste;
        this.teamId = team_id;
        this.phone = telephone;
        this.goal = goal;
        this.role = role;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getTeam_id() {
        return teamId;
    }

    public void setTeam_id(String team_id) {
        this.teamId = team_id;
    }

    public String getTelephone() {
        return phone;
    }

    public void setTelephone(String telephone) {
        this.phone = telephone;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal.toString();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static class JoueursComparator implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            return Integer.valueOf(o2.getGoal()).compareTo(Integer.valueOf(o1.getGoal()));
        }
    }
}
