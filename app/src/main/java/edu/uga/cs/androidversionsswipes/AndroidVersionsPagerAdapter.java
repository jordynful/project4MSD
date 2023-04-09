package edu.uga.cs.androidversionsswipes;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;
import java.util.List;

public class AndroidVersionsPagerAdapter extends FragmentStateAdapter {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    String date =  year + "-" + month + "-" + day;
    Quiz quiz = new Quiz(date, 0);
    int totalCountryNum = 0;
    List<Country> countryList;
    public AndroidVersionsPagerAdapter(
            FragmentManager fragmentManager,
            Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public Fragment createFragment(int position){
        return AndroidVersionFragment.newInstance(position, countryList, quiz);
    }

    @Override
    public int getItemCount() {
        return AndroidVersionFragment.getNumberOfVersions();
    }
}
