package edu.uga.cs.androidversionsswipes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AndroidVersionFragment extends Fragment {

    // Array of Android version code names
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
    private int versionNum;

    public AndroidVersionFragment() {
        // Required empty public constructor
    }


    public static AndroidVersionFragment newInstance( int versionNum ) {
        AndroidVersionFragment fragment = new AndroidVersionFragment();
        Bundle args = new Bundle();
        args.putInt( "versionNum", versionNum );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        if( getArguments() != null ) {
            versionNum = getArguments().getInt( "versionNum" );
        }
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_android_version, container, false );
    }

    @Override
    public void onViewCreated( @NonNull View view, Bundle savedInstanceState ) {
        //public void onActivityCreated(Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

        TextView titleView = view.findViewById( R.id.titleView );
        TextView highlightsView = view.findViewById( R.id.highlightsView );

        titleView.setText( androidVersions[ versionNum ] );
        highlightsView.setText( androidVersionsInfo[ versionNum ] );
    }

    public static int getNumberOfVersions() {
        return androidVersions.length;
    }
}