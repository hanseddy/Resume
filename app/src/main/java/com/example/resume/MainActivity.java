package com.example.resume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import authentif.log_inFragment;

public class MainActivity extends AppCompatActivity  {
    final String TAG= "AuthentificationActivity";
    // initialization of views
    FloatingActionButton m_Fb_fabButton;
    FloatingActionButton m_Google_fabButton;
    TabLayout m_auth_tablayout;
    ViewPager m_auth_viewpager;
    //login ui
    EditText m_login_mail_Edit ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /******************* setting up tablayout and viewpager**************/
        m_Fb_fabButton=findViewById(R.id.auth_FbFAB_button);
        m_Google_fabButton=findViewById(R.id.auth_googleFAB_button);
        m_auth_tablayout=findViewById(R.id.auth_tabLayout);
        m_auth_viewpager=findViewById(R.id.auth_viewpager);
        m_auth_tablayout.addTab(m_auth_tablayout.newTab().setText("Login"));
        m_auth_tablayout.addTab(m_auth_tablayout.newTab().setText("Sign in"));
        m_auth_tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //set up with viewpager
        m_auth_tablayout.setupWithViewPager(m_auth_viewpager);
        /******************* end of tablayout setting **************/
        final authadapter adapter = new authadapter(getSupportFragmentManager(),getApplicationContext(),m_auth_tablayout.getTabCount());
        m_auth_viewpager.setAdapter(adapter);


        //tablayout onclick listener
        tablistener();
        /******************* firebase authentification**************/
        //log_inFragment log_inFragment =(log_inFragment)getFragmentManager().findFragmentById(R.id.login_fragment);
    }
    /*******************tablayout clickListener***************/
    //function: listen the button press of tablayout child and set the fragment to inflate
    // In: void
    // Out: void
    private void tablistener() {
        m_auth_tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            m_auth_viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    /******* end of tablistener method************/



}