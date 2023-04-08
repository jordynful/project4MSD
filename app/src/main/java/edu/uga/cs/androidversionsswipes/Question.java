package edu.uga.cs.androidversionsswipes;
import java.util.List;
import java.util.Random;

public class Question {
    private long   id;
    private String country;
    private String continent;
    private String choice1;
    private String choice2;
    public String[] continents = {"Africa", "Antarctica", "Asia", "Oceania", "Europe", "North America", "South America"};


    public Question(String country, String continent) {

        this.id = -1;
        this.country = country;
        this.continent = continent;

        Random random = new Random();

        int two = random.nextInt(8);
        choice1 = continents[two];
        //access db to get countries for these indexes
        while (choice1 == this.country) {
            two = random.nextInt(8);
            choice1 = continents[two];
        }
        int three = random.nextInt(8);
        choice2 = continents[three];
        while (choice2 == this.country || three == two) {
            three = random.nextInt(8);
            choice2 = continents[three];
        }

        //access db to get countries for these indexes


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

    public int[] getChoices() {
        String[] items = {country, choice1, choice2}; // Create an array of items
        Random random = new Random();
        int[] arr = new int[3];

        for (int i = 0; i < arr.length; i++) {
            int index;
            do {
                index = random.nextInt(items.length);
            } while (contains(arr, index));
            arr[i] = index;
        }

        return arr;
    }

    public boolean contains(int[] arr, int index) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == index) {
                return true;
            }
        }
        return false;
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

