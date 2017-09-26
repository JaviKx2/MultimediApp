package com.javikx2.multimediapp.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.view.fragment.CatalogueFragment;

import java.util.ArrayList;

public class CataloguePagerAdapter extends FragmentStatePagerAdapter {

    FragmentManager oFragmentManager;
    ArrayList<Fragment> oPooledFragments;
    private Context context;

    public CataloguePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        oFragmentManager = fm;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return CatalogueFragment.newInstance(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String pageTitle = "";
        switch (position) {
            case 0:
                pageTitle = context.getString(R.string.tab_series);
                break;
            case 1:
                pageTitle = context.getString(R.string.tab_movies);
                break;
            case 2:
                pageTitle = context.getString(R.string.tab_documentaries);
                break;
            case 3:
                pageTitle = context.getString(R.string.tab_tv_shows);
                break;
        }
        return pageTitle;
    }
}