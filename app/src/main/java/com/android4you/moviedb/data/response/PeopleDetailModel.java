package com.android4you.moviedb.data.response;

import java.util.List;

public class PeopleDetailModel {


    /**
     * birthday : 1981-03-02
     * deathday : null
     * id : 18997
     * name : Bryce Dallas Howard
     * also_known_as : []
     * gender : 1
     * biography : Actress and daughter of Ron Howard who landed her first major film role in the 2004 M. Night Shyamalan feature The Village. She received a Golden Globe Award nomination for her performance in the TV movie As You Like It. She also stars as Claire Dearing in the 2015 blockbuster Jurassic World.
     * popularity : 22.114
     * place_of_birth : Los Angeles, California, USA
     * profile_path : /7HIHalGGoJd101Wt3Y78Znfiohj.jpg
     * adult : false
     * imdb_id : nm0397171
     * homepage : null
     */

    private String birthday;
    private Object deathday;
    private int id;
    private String name;
    private int gender;
    private String biography;
    private double popularity;
    private String place_of_birth;
    private String profile_path;
    private boolean adult;
    private String imdb_id;
    private Object homepage;
    private List<String> also_known_as;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Object getDeathday() {
        return deathday;
    }

    public void setDeathday(Object deathday) {
        this.deathday = deathday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public Object getHomepage() {
        return homepage;
    }

    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }

    public List<String> getAlso_known_as() {
        return also_known_as;
    }

    public void setAlso_known_as(List<String> also_known_as) {
        this.also_known_as = also_known_as;
    }
}
