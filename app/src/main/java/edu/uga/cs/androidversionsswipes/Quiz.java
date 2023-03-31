package edu.uga.cs.androidversionsswipes;


public class Quiz {
    private long   id;
    private String date;
    private int score;

    //will also have variables for questions


    public Quiz() {
        this.id = -1;  // the primary key id will be set by a setter method
    }

    public Quiz(String date, int score) {
        this.id = -1;
        this.date = date;
        this.score = score;
        // the primary key id will be set by a setter method
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }


    public String toString()
    {
        return id + ": " + date + " " + score;
    }
}


