package edu.uga.cs.androidversionsswipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;


public class MainActivity extends AppCompatActivity {
    private AppData appData = null;
    public List<Country> listOfCountries;
    int totalCountryNum;
    private static MainActivity instance = null;
    private static final String TAG = "MainActivity";
   class DBReader extends AsyncTask<Void, List<Country>> {
        // This method will run as a background process to read from db.
        // It returns a list of retrieved JobLead objects.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onCreate callback (the job leads review activity is started).
        @Override
        protected List<Country> doInBackground( Void... params ) {
            appData.open();
            Log.d( TAG, "DBReader: trying to retrieve: ");
            List<Country> countryList = appData.retrieveAllCountries();
            System.out.println(countryList);
            totalCountryNum = countryList.size();
            Log.d( TAG, "DBReader: Job leads retrieved: " + countryList.size() );
//            appData = new AppData( this);

            // Open that database for reading of the full list of job leads.
            // Note that onResume() hasn't been called yet, so the db open in it
            // was not called yet!
            if (countryList.size() == 0) {

                try {
                    // Open the CSV data file in the assets folder
                    InputStream in_s = getAssets().open("country_continent.csv");


                    // set up margins for each TextView in the table layout
                    android.widget.TableRow.LayoutParams layoutParams =
                            new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(20, 0, 20, 0);

                    // read the CSV data
                    CSVReader reader = new CSVReader(new InputStreamReader(in_s));
                    String[] nextRow;
                    while ((nextRow = reader.readNext()) != null) {
                        for (int i = 0; i < nextRow.length; i++) {



                        }
                        String country = nextRow[0].toString();
                        String continent = nextRow[1].toString();

                Country country1 = new Country( country, continent);

                // Store this new job lead in the database asynchronously,
                // without blocking the UI thread.
                new DBWriter().execute( country1 );

                    }

                    // add the next row to the table layout

                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }

            }//if countrylist.size() == 0
            listOfCountries = countryList;
            return countryList;
        }

       @Override
       protected void onPostExecute( List<Country> jobsList ) {

            System.out.println("DBReader did something");
       }


    }

    class DBWriter extends AsyncTask<Country, Country> {

        // This method will run as a background process to write into db.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onClick listener of the Save button.
        @Override
        protected Country doInBackground( Country... countries ) {
            appData.storeCountry( countries[0] );
            return countries[0];
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.  Note that doInBackground returns a JobLead object.
        // That object will be passed as argument to onPostExecute.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute( Country country ) {
            // Show a quick confirmation message

            // Clear the EditTexts for next use.


            Log.d( TAG, "Country saved: " + country );
        }
    }
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        appData = new AppData( MainActivity.this);
        // Open that database for reading of the full list of job leads.
        // Note that onResume() hasn't been called yet, so the db open in it
        // was not called yet!
        appData.open();
        new DBReader().execute();
        System.out.println("Total: "+totalCountryNum);

        //this is where we will read in the data from database or csv file... start with csv file
        System.out.println("we're in onCreate hehe");
        setContentView( R.layout.activity_main );
        Button startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startButton.setVisibility(startButton.GONE);
                ViewPager2 pager = findViewById( R.id.viewpager );
                AndroidVersionsPagerAdapter avpAdapter = new AndroidVersionsPagerAdapter(getSupportFragmentManager(), getLifecycle());
                avpAdapter.totalCountryNum = totalCountryNum;
                avpAdapter.countryList = listOfCountries;
                pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL );
                pager.setAdapter( avpAdapter );
                System.out.println("APP DATA:"+appData);

//                appData = new AppData( MainActivity.this);
//            // Open that database for reading of the full list of job leads.
//            // Note that onResume() hasn't been called yet, so the db open in it
//            // was not called yet!
//                appData.open();
//                new DBReader().execute();
            }
        });


        instance = this;

    }

    public List<Country> getListOfCountries() {
       return listOfCountries;
    }

    public static MainActivity getInstance() {
        return instance;
    }
}