package com.itb.tmbdmobileapp.Modelos;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String username;
    private String email;

    private List<Movie> pelis = new ArrayList<>();
    private List<People> actores = new ArrayList<>();
    private List<TV> series = new ArrayList<>();

    public User(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;

    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Movie> getPelis() {
        return pelis;
    }

    public void setPelis(List<Movie> pelis) {
        this.pelis = pelis;
    }

    public List<People> getActores() {
        return actores;
    }

    public void setActores(List<People> actores) {
        this.actores = actores;
    }

    public List<TV> getSeries() {
        return series;
    }

    public void setSeries(List<TV> series) {
        this.series = series;
    }

    public void insertarMovie (Movie m){
        pelis.add(m);
    }

    public void insertarActor (People p){
        actores.add(p);
    }

    public void insertarSerie (TV s){
        series.add(s);
    }
}
