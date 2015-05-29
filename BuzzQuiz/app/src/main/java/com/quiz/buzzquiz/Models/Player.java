package com.quiz.buzzquiz.Models;

import java.util.ArrayList;

/**
 * Created by m on 2015-05-29.
 */
public class Player {
    public int Id;
    public String Name;
    public String Credentials;

    public Player(int id, String name, String credentials) {
        this.Id = id;
        this.Name = name;
        this.Credentials = credentials;
    }

    public static class List extends ArrayList<Player> {
    }
}