package edu.uga.cs.androidversionsswipes;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is facilitates storing and restoring job leads stored.
 */
public class AppData {

    public static final String DEBUG_TAG = "AppData";

    // this is a reference to our database; it is used later to run SQL commands
    private SQLiteDatabase   db;
    private SQLiteOpenHelper dbHelper;
    private static final String[] allColumns = {
            DBHelper.JOBLEADS_COLUMN_ID,
            DBHelper.JOBLEADS_COLUMN_COUNTRY,
            DBHelper.JOBLEADS_COLUMN_CONTINENT,
    };

    private static final String[] allQuizColumns = {
            DBHelper.QUIZZES_COLUMN_ID,
            DBHelper.QUIZZES_COLUMN_DATE,
            DBHelper.QUIZZES_COLUMN_SCORE,
    };

    public AppData( Context context ) {
        this.dbHelper = DBHelper.getInstance( context );
    }

    // Open the database
    public void open() {
        db = dbHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "AppData: db open" );
    }

    // Close the database
    public void close() {
        if( dbHelper != null ) {
            dbHelper.close();
            Log.d(DEBUG_TAG, "AppData: db closed");
        }
    }

    public boolean isDBOpen()
    {
        return db.isOpen();
    }

    // Retrieve all job leads and return them as a List.
    // This is how we restore persistent objects stored as rows in the job leads table in the database.
    // For each retrieved row, we create a new JobLead (Java POJO object) instance and add it to the list.
    public List<Country> retrieveAllCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query( DBHelper.TABLE_COUNTRIES, allColumns,
                    null, null, null, null, null );

            // collect all job leads into a List
            if( cursor != null && cursor.getCount() > 0 ) {

                while( cursor.moveToNext() ) {

                    if( cursor.getColumnCount() >= 2) {

                        // get all attribute values of this job lead
                        columnIndex = cursor.getColumnIndex(DBHelper.JOBLEADS_COLUMN_ID );
                        long id = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex( DBHelper.JOBLEADS_COLUMN_COUNTRY );
                        String country = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( DBHelper.JOBLEADS_COLUMN_CONTINENT );
                        String continent = cursor.getString( columnIndex );

                        // create a new JobLead object and set its state to the retrieved values
                        Country country1 = new Country( country, continent);
                        country1.setId(id); // set the id (the primary key) of this object
                        // add it to the list
                        countries.add( country1 );
                        Log.d(DEBUG_TAG, "Retrieved JobLead: " + country1);
                    }
                }
            }
            if( cursor != null )
                Log.d( DEBUG_TAG, "Number of records from DB: " + cursor.getCount() );
            else
                Log.d( DEBUG_TAG, "Number of records from DB: 0" );
        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception caught: " + e );
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        // return a list of retrieved job leads
        Log.d( DEBUG_TAG, "counties : " + countries );
        return countries;
    }


    public List<Quiz> retrieveAllQuizzes() {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query( DBHelper.TABLE_QUIZZES, allQuizColumns,
                    null, null, null, null, null );

            // collect all job leads into a List
            if( cursor != null && cursor.getCount() > 0 ) {

                while( cursor.moveToNext() ) {

                    if( cursor.getColumnCount() >= 2) {

                        // get all attribute values of this job lead
                        columnIndex = cursor.getColumnIndex(DBHelper.QUIZZES_COLUMN_ID );
                        long id = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex( DBHelper.QUIZZES_COLUMN_DATE );
                        String date = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( DBHelper.QUIZZES_COLUMN_SCORE );
                        int score = cursor.getInt( columnIndex );

                        // create a new JobLead object and set its state to the retrieved values
                        Quiz quiz1 = new Quiz( date, score);
                        quiz1.setId(id); // set the id (the primary key) of this object
                        // add it to the list
                        quizzes.add( quiz1 );
                        Log.d(DEBUG_TAG, "Retrieved JobLead: " + quiz1);
                    }
                }
            }
            if( cursor != null )
                Log.d( DEBUG_TAG, "Number of records from DB: " + cursor.getCount() );
            else
                Log.d( DEBUG_TAG, "Number of records from DB: 0" );
        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception caught: " + e );
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        // return a list of retrieved job leads
        Log.d( DEBUG_TAG, "counties : " + quizzes );
        return quizzes;
    }

    // Store a new job lead in the database.
    public Country storeCountry( Country country1 ) {

        // Prepare the values for all of the necessary columns in the table
        // and set their values to the variables of the JobLead argument.
        // This is how we are providing persistence to a JobLead (Java object) instance
        // by storing it as a new row in the database table representing job leads.
        ContentValues values = new ContentValues();
        values.put( DBHelper.JOBLEADS_COLUMN_COUNTRY, country1.getCountry());
        values.put( DBHelper.JOBLEADS_COLUMN_CONTINENT, country1.getContinent() );

        // Insert the new row into the database table;
        // The id (primary key) is automatically generated by the database system
        // and returned as from the insert method call.
        long id = db.insert( DBHelper.TABLE_COUNTRIES, null, values );

        // store the id (the primary key) in the JobLead instance, as it is now persistent
        country1.setId( id );

        Log.d( DEBUG_TAG, "Stored new country with id: " + String.valueOf( country1.getId() ) );

        return country1;
    }

    public Quiz storeQuiz( Quiz quiz1 ) {

        // Prepare the values for all of the necessary columns in the table
        // and set their values to the variables of the JobLead argument.
        // This is how we are providing persistence to a JobLead (Java object) instance
        // by storing it as a new row in the database table representing job leads.
        ContentValues values = new ContentValues();
        values.put( DBHelper.QUIZZES_COLUMN_DATE, quiz1.getDate());
        values.put( DBHelper.QUIZZES_COLUMN_SCORE, quiz1.getScore());

        // Insert the new row into the database table;
        // The id (primary key) is automatically generated by the database system
        // and returned as from the insert method call.
        long id = db.insert( DBHelper.TABLE_QUIZZES, null, values );

        // store the id (the primary key) in the JobLead instance, as it is now persistent
        quiz1.setId( id );

        Log.d( DEBUG_TAG, "Stored new country with id: " + String.valueOf( quiz1.getId() ) );

        return quiz1;
    }


}
