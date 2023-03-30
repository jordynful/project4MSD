package edu.uga.cs.androidversionsswipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AppData appData = null;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        //this is where we will read in the data from database or csv file... start with csv file

        setContentView( R.layout.activity_main );

        ViewPager2 pager = findViewById( R.id.viewpager );
        AndroidVersionsPagerAdapter avpAdapter = new
                AndroidVersionsPagerAdapter(
                getSupportFragmentManager(), getLifecycle() );
        pager.setOrientation(
                ViewPager2.ORIENTATION_HORIZONTAL );
        pager.setAdapter( avpAdapter );


        class JobLeadDBWriter extends AsyncTask<Country, Country> {

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


                Log.d( TAG, "Job lead saved: " + country );
            }
        }

    }
}