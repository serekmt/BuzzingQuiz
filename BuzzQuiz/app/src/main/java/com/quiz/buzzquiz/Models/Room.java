package com.quiz.buzzquiz.Models;

import java.util.ArrayList;

/**
 * Created by m on 2015-05-28.
 */
//public class Comment {
//    public int id;
//    public String book_id;
//    public String date;
//    public String author;
//    public String comment;
//
//    public Comment(int id, String book_id, String date, String author, String comment) {
//        this.id = id;
//        this.book_id = book_id;
//        this.date = date;
//        this.author = author;
//        this.comment = comment;
//    }
//
//    public static class List extends ArrayList<Comment> {
//    }
//}

public class Room {
    public int Id;
    public String Name;

    public Room(int id, String name) {
        this.Id = id;
        this.Name = name;
    }

    public static class List extends ArrayList<Room> {
    }
}