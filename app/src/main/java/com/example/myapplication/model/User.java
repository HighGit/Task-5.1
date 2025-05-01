package com.example.myapplication.model;

import java.util.List;

public class User {

    private int user_id;
    private String username;
    private String password;

    private List<String> playlist;

    public User(String username, int user_id, String password) {
        this.username = username;
        this.user_id = user_id;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.playlist = null;
    }

    public User() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getPlaylist() {
        return playlist;
    }

    public void addToPlayList(String link) {
        playlist.add(link);
    }

    public void removeFromPlayList(String link) {
        playlist.remove(link);
    }

    public void setPlaylist(List<String> links) {
        this.playlist = links;
    }


}
