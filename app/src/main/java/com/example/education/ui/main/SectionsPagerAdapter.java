package com.example.education.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.education.FragmentMaterial;
import com.example.education.FragmentNotification;
import com.example.education.FragmentSittings;
import com.example.education.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    String[] aa;
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1,R.string.tab_text_2,R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm,String ss) {
        super(fm);
        mContext = context;
        aa=new String[8];
        aa=ss.split("/t");

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                Bundle bundle=new Bundle();
                bundle.putString("name",aa[0]);
                bundle.putString("phone",aa[1]);
                bundle.putString("gender",aa[2]);
                bundle.putString("city",aa[3]);
                bundle.putString("test"," ");
                bundle.putString("date",aa[5]);
                bundle.putString("password",aa[6]);
                bundle.putString("image",aa[7]);
                FragmentMaterial fragmentMaterial=new FragmentMaterial();
                fragmentMaterial.setArguments(bundle);
                fragment=fragmentMaterial;
                break;
            case 1:
                Bundle bundle2=new Bundle();
                bundle2.putString("name",aa[0]);
                bundle2.putString("phone",aa[1]);
                bundle2.putString("gender",aa[2]);
                bundle2.putString("city",aa[3]);
                bundle2.putString("test"," ");
                bundle2.putString("date",aa[5]);
                bundle2.putString("password",aa[6]);
                bundle2.putString("image",aa[7]);
                FragmentNotification fragmentNotification=new FragmentNotification();
                fragmentNotification.setArguments(bundle2);
                fragment=fragmentNotification;
                break;
            case 2:
                Bundle bundle1=new Bundle();
                bundle1.putString("name",aa[0]);
                bundle1.putString("phone",aa[1]);
                bundle1.putString("gender",aa[2]);
                bundle1.putString("city",aa[3]);
                bundle1.putString("test"," ");
                bundle1.putString("date",aa[5]);
                bundle1.putString("password",aa[6]);
                bundle1.putString("image",aa[7]);
                FragmentSittings fragmentSittings=new FragmentSittings();
                fragmentSittings.setArguments(bundle1);
                fragment=fragmentSittings;
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}