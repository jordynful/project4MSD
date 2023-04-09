package edu.uga.cs.androidversionsswipes;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import android.widget.Toast;

import java.time.LocalDate;
import java.util.Calendar;
public class AndroidVersionFragment extends Fragment {
    MainActivity main = (MainActivity)getActivity();
    //appData.open();
    private AppData appData = null;
    private static ArrayList<String> selectedCountry = new ArrayList<String>();
    private static ArrayList<String> correctAnswers = new ArrayList<String>();
    private static final String[] allContinents = {"Asia", "Africa", "North America", "South America", "Antarctica", "Europe", "Australia"};
    private Button option1Button;
    private Button option2Button;
    private Button option3Button;
    private RadioGroup quizOptions;
    private RadioButton option1RadioBtn;
    private RadioButton option2RadioBtn;
    private RadioButton option3RadioBtn;
    private String option1;
    private String option2;
    private String option3;
    private String correctAnswer;
    private static final String[] androidVersions = {
            "13",
            "12",
            "11",
            "10",
            "Pie",
            "Oreo",
            "Nougat",
            "Marshmallow",
            "Lollipop",
            "KitKat",
            "Jelly Bean",
            "Ice Cream Sandwich",
            "Honeycomb",
            "Gingerbread",
            "Froyo",
            "Eclair",
            "Donut",
            "Cupcake",
            "Petit Four",
            "Android (No codename)"
    };

    // Array of Android version highlights/brief descriptions
    private static final String[] androidVersionsInfo = {
            "Q1\nAugust 16, 2022\n\nAndroid 13 is the thirteenth major version of the Android operating system. It was first announced on 10 Feb 2022 by Google and its beta1 version was first released on 26 Apr 2022.\n\nIn Android 13, apps are now required to request permission from the user before they are able to send notifications.  The new system added support for Bluetooth LE Audio and the LC3 audio codec. ART has been updated with a new garbage collector (GC) utilizing the Linux userfaultfd system call.",
            "Q2\nOctober 4, 2021\n\nAndroid 12 is the twelfth major version of the Android operating system. It was first announced by Google on February 18, 2021, and the first developer preview released on the same day.\n\nAndroid 12 started using Material You, an updated design language based on Material Design.  It introduced the scrolling screenshot, one handed mode, mic and camera indicator and toggle, privacy dashboard and other new features.",
            "Q3\nSeptember 8, 2020\n\nAndroid 11 is the eleventh major version of the Android operating system. It was first announced by Google on February 19, 2020, and the first developer preview released on the same day.\n\nThe logo for the release features a dial turned to 11.\n\nAndroid 11 added chat bubbles, screen recorder, notification history, new permissions controls and other new features.",
            "Q4\nSeptember 3, 2019\n\nAndroid 10 is the tenth major version of the Android operating system. It was first announced by Google on March 13, 2019, and the first beta was released on the same day (under the name \"Android Q\" at the time).\nAndroid 10 was going to be known as \"Queen Cake\". Internally, Android 10 is known as Quince Tart.",
            "Q5\n9.0\nAugust 6, 2018\n\nAndroid Pie is the ninth major version of the Android operating system. It was first announced by Google on March 7, 2018, and the first developer preview was released on the same day. Second preview, considered beta quality, was released on May 8, 2018. The final beta of Android P (fifth preview, also considered as a \"Release Candidate\") was released on July 25, 2018. The first official release was released on August 6, 2018.",
            "Q6\n8.0 – 8.1\nAugust 21, 2017\n\nAndroid Oreo is the 8th major release of the Android operating system. It was first released as a developer preview on March 21, 2017, with factory images for current Nexus and Pixel devices. The final developer preview was released on July 24, 2017, with the stable version released in August 2017.",

    };

    // which Android version to display in the fragment
    //private int versionNum;
    private int quizID;
    private Quiz quiz;

    private int quizNum;
    private static List<Country> countryList;
    private static int questionNum = 0;
    class DBWriterQuiz extends AsyncTask<Quiz, Quiz> {

        // This method will run as a background process to write into db.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onClick listener of the Save button.
        @Override
        protected Quiz doInBackground( Quiz... quizzes ) {
            System.out.print(quizzes[0]);
            appData.storeQuiz( quizzes[0]);
            return quizzes[0];
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.  Note that doInBackground returns a JobLead object.
        // That object will be passed as argument to onPostExecute.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute( Quiz quiz ) {
            // Show a quick confirmation message

            // Clear the EditTexts for next use.


            Log.d( "VersonFragments", "Quiz saved: " + quiz );
        }
    }
    public AndroidVersionFragment() {
        // Required empty public constructor
    }

    public void PrepareSelectedCountry() {
        Random rand = new Random();
        Set<Integer> randomNums = new HashSet<Integer>();

        while (randomNums.size() < 6) {
            int num = rand.nextInt(countryList.size()) + 1;
            randomNums.add(num);
        }
    }

    public static AndroidVersionFragment newInstance( int quizID, List<Country> countryList, Quiz quiz1) {

        //int questionNum = 0;


        AndroidVersionFragment fragment = new AndroidVersionFragment();
        fragment.quizID = quizID;
        fragment.countryList = countryList;
        fragment.quiz = quiz1;
        Random rand = new Random();

        Set<Integer> randomNums = new HashSet<Integer>();

        while (randomNums.size() < 6) {
            int num = rand.nextInt(countryList.size());
            randomNums.add(num);
            if (selectedCountry.contains(countryList.get(num).getCountry())) {
                continue;
            } else {
                selectedCountry.add(countryList.get(num).getCountry());
                correctAnswers.add(countryList.get(num).getContinent());
            }
        }
        Bundle args = new Bundle();
        args.putInt( "quizID", quizID);
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        if( getArguments() != null ) {
            //versionNum = getArguments().getInt( "versionNum" );
            quizID = getArguments().getInt( "quizID" );

            //int questionNum = getArguments().getInt("questionNum");
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        main = (MainActivity) getActivity();
        appData = new AppData(getContext());
        appData.open();

        View rootView = inflater.inflate(R.layout.fragment_android_version, container, false );
//        questionView = rootView.findViewById(R.id.question_view);
        option1Button = rootView.findViewById(R.id.option1_button);

        quizOptions = rootView.findViewById(R.id.optionsRadioGroup);
        option1RadioBtn = rootView.findViewById(R.id.radioButton);
        option2RadioBtn = rootView.findViewById(R.id.radioButton2);
        option3RadioBtn = rootView.findViewById(R.id.radioButton3);

        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;
        if (randomNumber==1) {
            correctAnswer = correctAnswers.get(questionNum);
            //System.out.println("CORRECTANSWER"+correctAnswer);
            option1 = correctAnswers.get(questionNum);
            Random random1 = new Random();
            int index1 = -1, index2 = -1;
            while (index1 == -1 || allContinents[index1].equals(correctAnswer)) {
                index1 = random1.nextInt(allContinents.length);
            }
            while (index2 == -1 || index2 == index1 || allContinents[index2].equals(correctAnswer)) {
                index2 = random1.nextInt(allContinents.length);
            }
            //option2RadioBtn = allContinents[index1];
            //option3RadioBtn = allContinents[index2];

            option2 = allContinents[index1];
            option3 = allContinents[index2];
        }else if (randomNumber==2) {
            correctAnswer = correctAnswers.get(questionNum);
            option2 = correctAnswers.get(questionNum);
            Random random1 = new Random();
            int index1 = -1, index2 = -1;
            while (index1 == -1 || allContinents[index1].equals(correctAnswer)) {
                index1 = random1.nextInt(allContinents.length);
            }
            while (index2 == -1 || index2 == index1 || allContinents[index2].equals(correctAnswer)) {
                index2 = random1.nextInt(allContinents.length);
            }
            option1 = allContinents[index1];
            option3 = allContinents[index2];
        }else if (randomNumber==3) {
            correctAnswer = correctAnswers.get(questionNum);
            option3 = correctAnswers.get(questionNum);
            Random random1 = new Random();
            int index1 = -1, index2 = -1;
            while (index1 == -1 || allContinents[index1].equals(correctAnswer)) {
                index1 = random1.nextInt(allContinents.length);
            }
            while (index2 == -1 || index2 == index1 || allContinents[index2].equals(correctAnswer)) {
                index2 = random1.nextInt(allContinents.length);
            }
            option1 = allContinents[index1];
            option2 = allContinents[index2];
        }

//        questionView.setText(question);
        option1RadioBtn.setText(option1);
        option2RadioBtn.setText(option2);
        option3RadioBtn.setText(option3);

        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedOptionId = quizOptions.getCheckedRadioButtonId();
                if (selectedOptionId == option1RadioBtn.getId()) {
                    showResult(option1.equals(correctAnswer));
                    option1RadioBtn.setEnabled(false);
                    option2RadioBtn.setEnabled(false);
                    option3RadioBtn.setEnabled(false);
                    option1Button.setClickable(false);
                    // Option 1 is selected
                } else if (selectedOptionId == option2RadioBtn.getId()) {
                    showResult(option2.equals(correctAnswer));
                    option1RadioBtn.setEnabled(false);
                    option2RadioBtn.setEnabled(false);
                    option3RadioBtn.setEnabled(false);
                    option1Button.setClickable(false);
                    // Option 2 is selected
                } else if (selectedOptionId == option3RadioBtn.getId()) {
                    // Option 3 is selected
                    showResult(option3.equals(correctAnswer));
                    option1RadioBtn.setEnabled(false);
                    option2RadioBtn.setEnabled(false);
                    option3RadioBtn.setEnabled(false);
                    option1Button.setClickable(false);
                } else {
                    Toast.makeText(getContext(), "Please select an answer.", Toast.LENGTH_SHORT).show();
                    // No option is selected
                }
//                option1RadioBtn.setEnabled(false);
//                option2RadioBtn.setEnabled(false);
//                option3RadioBtn.setEnabled(false);
//                option1Button.setClickable(false);

            }
        });




        return rootView;

    }

    private void showResult(boolean isCorrect) {
        if (isCorrect) {
            quiz.setScore(quiz.getScore() + 1);
            System.out.print("score " + quiz.getScore());
            Toast.makeText(getContext(), "Correct！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onViewCreated( @NonNull View view, Bundle savedInstanceState ) {
        //public void onActivityCreated(Bundle savedInstanceState){\
        System.out.println("CORRECTANSWER"+correctAnswer);

        questionNum+=1;
        System.out.println("Number: "+questionNum);
        if(questionNum == 7) {
            //store results here
            System.out.print("quiz " + quiz);
            new DBWriterQuiz().execute( quiz );
            Intent intent = new Intent(getActivity(),EndActivity.class);
            intent.putExtra("score",quiz.getScore() );
            startActivity(intent);

        } else {
            super.onViewCreated(view, savedInstanceState);
            TextView titleView = view.findViewById(R.id.titleView);
            TextView highlightsView = view.findViewById(R.id.highlightsView);

            titleView.setText(androidVersions[quizID]);
            highlightsView.setText("Q" + questionNum + "\nAugust 16, 2022\n\nName the continent on which " + selectedCountry.get(questionNum) + " is located ?");
//            highlightsView.setText(androidVersionsInfo[quizID]);
        }
    }

    public static int getNumberOfVersions() {
        return 7;
//        return androidVersions.length;
    }


}