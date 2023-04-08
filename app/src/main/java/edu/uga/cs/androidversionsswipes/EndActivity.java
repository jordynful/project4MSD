package edu.uga.cs.androidversionsswipes;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


public class EndActivity  extends AppCompatActivity {
    int quizResult;
    Button startButton;
    Button resultButton;
    private AppData appData = null;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        //this is where we will read in the data from database or csv file... start with csv file
        System.out.println("we're in EndActivity onCreate hehe");
        setContentView( R.layout.activity_end);
        Button startButton2 = (Button)findViewById(R.id.startButton2);
        Button resultButton = (Button)findViewById(R.id.resultButton);
        startButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startButton2.setVisibility(startButton2.GONE);
                resultButton.setVisibility(resultButton.GONE);
                ViewPager2 pager = findViewById(R.id.viewpager);
                AndroidVersionsPagerAdapter avpAdapter = new AndroidVersionsPagerAdapter(getSupportFragmentManager(), getLifecycle());
                pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                pager.setAdapter(avpAdapter);
                System.out.println("APP DATA:" + appData);

                appData = new AppData(EndActivity.this);
                // Open that database for reading of the full list of job leads.
                // Note that onResume() hasn't been called yet, so the db open in it
                // was not called yet!
                appData.open();
                //new DBReader().execute();
            }
        });
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("===============resultbttn");
                startButton2.setVisibility(startButton2.GONE);
                resultButton.setVisibility(resultButton.GONE);
            }});
    }

}
