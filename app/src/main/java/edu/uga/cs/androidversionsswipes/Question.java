package edu.uga.cs.androidversionsswipes;


public class Question {
    private long   id;
    private String country;
    private String continent;
    private String choice1;
    private String choice2;



    public Question(String country, String continent) {
        this.id = -1;
        this.country = country;
        this.continent = continent;
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

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getContinent()
    {
        return continent;
    }

    public void setContinent(String continent)
    {
        this.continent = continent;
    }


    public String toString()
    {
        return id + ": " + country + " " + continent;
    }
}

