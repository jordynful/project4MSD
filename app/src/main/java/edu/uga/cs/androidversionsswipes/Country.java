package edu.uga.cs.androidversionsswipes;

public class Country {
    private long   id;
    private String country;
    private String continent;


    public Country( String country, String continent) {
        this.id = -1;  // the primary key id will be set by a setter method
        this.country = country;
        this.continent = continent;
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

    public void setCompanyName(String country)
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


