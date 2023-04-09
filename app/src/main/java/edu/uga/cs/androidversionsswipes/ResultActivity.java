package edu.uga.cs.androidversionsswipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    private AppData appData = null;
    int totalQuizNumber;
    private static final String TAG = "ResultActivity";

    List<Quiz> quizzes;
    class DBReaderQuiz extends AsyncTask<Void, List<Quiz>> {
        // This method will run as a background process to read from db.
        // It returns a list of retrieved JobLead objects.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onCreate callback (the job leads review activity is started).
        @Override
        protected List<Quiz> doInBackground( Void... params ) {
            appData.open();
            Log.d( TAG, "DBReader: trying to retrieve: ");
            List<Quiz> quizList = appData.retrieveAllQuizzes();
            System.out.println(quizList);
            totalQuizNumber = quizList.size();
            Log.d( TAG, "DBReader: Job leads retrieved: " + quizList.size() );
//            appData = new AppData( this);

            // Open that database for reading of the full list of job leads.
            // Note that onResume() hasn't been called yet, so the db open in it
            // was not called yet!
            if (quizList.size() == 0) {

            }//if countrylist.size() == 0
            quizzes = quizList;
            return quizList;
        }

        @Override
        protected void onPostExecute( List<Quiz> jobsList ) {
            ListView listView = findViewById(R.id.my_list_view);

            ArrayAdapter<Quiz> adapter = new ArrayAdapter<Quiz> (
                    ResultActivity.this, android.R.layout.simple_list_item_1, quizzes) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    Quiz quiz = quizzes.get(position);
                    // Customize the view to display the quiz information
                    // For example, you can set the text of a TextView to the quiz name
                    TextView textView = view.findViewById(android.R.id.text1);

                    textView.setText("Score: " + quiz.getScore() + "     Date: " + quiz.getDate());
                    return view;
                }
            };

            listView.setAdapter(adapter);
            System.out.println("DBReader did something");
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get a reference to the ListView in our layout file


        // Create an ArrayAdapter to provide the data to the ListView

        // Set the adapter on the ListView


        // Load the quizzes from the database
        appData = new AppData(this);
        appData.open();
        new DBReaderQuiz().execute();
    }


}