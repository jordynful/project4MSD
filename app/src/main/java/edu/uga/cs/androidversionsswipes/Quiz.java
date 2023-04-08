package edu.uga.cs.androidversionsswipes;

import java.util.List;
import java.util.Random;
public class Quiz {
    private long   id;
    private String date;
    private int score;

    //will also have variables for questions
    private Question q1;
    private Question q2;
    private Question q3;
    private Question q4;
    private Question q5;
    public List<Country> countries;
    public Quiz() {

        countries = MainActivity.getInstance().getListOfCountries();

        Random random = new Random();
        int one = random.nextInt(196);
        int two = random.nextInt(196);
        while (two == one) {
            two = random.nextInt(196);
        }
        int three = random.nextInt(196);
        while (three == one || three == two) {
            three = random.nextInt(196);
        }
        int four = random.nextInt(196);
        while (four == one || four == two || four == three) {
            four = random.nextInt(196);
        }
        int five = random.nextInt(196);
        while (five == one || five == two || five == three || five == four) {
            five = random.nextInt(196);
        }
        Country country1 = new Country(countries.get(one).getCountry(), countries.get(one).getContinent());
        Country country2 = new Country(countries.get(two).getCountry(), countries.get(two).getContinent());
        Country country3 = new Country(countries.get(three).getCountry(), countries.get(three).getContinent());
        Country country4 = new Country(countries.get(four).getCountry(), countries.get(four).getContinent());
        Country country5 = new Country(countries.get(five).getCountry(), countries.get(five).getContinent());
        //access db to get country and continents

        q1 = new Question(country1.getCountry(), country1.getContinent());
        q2 = new Question(country2.getCountry(), country2.getContinent());
        q3 = new Question(country3.getCountry(), country3.getContinent());
        q4 = new Question(country4.getCountry(), country4.getContinent());
        q5 = new Question(country5.getCountry(), country5.getContinent());
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

    public Question getQuestion(int question) {
        if (question == 1) {
            return q1;
        }
        else if (question == 2) {
            return q2;
        }
        else if (question == 3) {
            return q3;
        }
        else if (question == 4) {
            return q4;
        }
        else  {
            return q5;
        }
    }
    public String toString()
    {
        return id + ": " + date + " " + score;
    }
}


