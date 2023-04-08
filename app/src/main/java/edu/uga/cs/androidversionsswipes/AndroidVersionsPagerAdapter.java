package edu.uga.cs.androidversionsswipes;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class AndroidVersionsPagerAdapter extends FragmentStateAdapter {

    int totalCountryNum = 0;
    List<Country> countryList;
    public AndroidVersionsPagerAdapter(
            FragmentManager fragmentManager,
            Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public Fragment createFragment(int position){
        return AndroidVersionFragment.newInstance(position, countryList);
    }

    @Override
    public int getItemCount() {
        return AndroidVersionFragment.getNumberOfVersions();
    }
}
