package com.example.resume;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import authentif.log_inFragment;
import authentif.sign_inFragment;

public class authadapter extends FragmentPagerAdapter  {

    private Context m_context;
    int m_TotalTabs;
    CharSequence m_mail;
    CharSequence m_mdp;
    private String[] tabTitles = new String[]{"Login", "Sign In"};
    public authadapter(@NonNull FragmentManager fm,Context context, int totalTabs) {
        super(fm);
        this.m_context=context;
        this.m_TotalTabs=totalTabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                log_inFragment login= new log_inFragment();
                return login;
            case 1:
                sign_inFragment sign_in= new sign_inFragment();
                return sign_in;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return m_TotalTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
