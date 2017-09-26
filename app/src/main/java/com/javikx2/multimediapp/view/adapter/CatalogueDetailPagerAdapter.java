package com.javikx2.multimediapp.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.view.fragment.CatalogueDetailFragment;

public class CatalogueDetailPagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_TABS = 2;
    private Context context;

    public CatalogueDetailPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return CatalogueDetailFragment.newInstance(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String tabTitle = "";
        switch (position) {
            case 0:
                tabTitle = context.getString(R.string.catalogue_sinopsis);
                break;
        }
        return tabTitle;
    }
}

