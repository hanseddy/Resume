package com.example.resume;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import DataCommunication.AuthViewmodel;
import authentif.log_inFragment;

public class MainActivity extends AppCompatActivity  {
    // initialization of views
    FloatingActionButton m_Fb_fabButton;
    FloatingActionButton m_Google_fabButton;
    TabLayout m_auth_tablayout;
    ViewPager m_auth_viewpager;
    Context m_fragmentContext;
    //login ui
    EditText m_login_mail_Edit ;
    FragmentManager fragmentmanager;
    log_inFragment m_loginFragment;
    AuthViewmodel viewmodel;
    String m_Name,m_mail,m_Mdp1,m_Mdp2; // var signIn
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

        tablistener(); //tablayout onclick listener

        /***************communication using viewmodel and livedata******************/
        viewmodel = new ViewModelProvider(this).get(AuthViewmodel.class);
        viewmodel.init(); //initialisation of data
        // if fb FAB button is clicked set data by calling settings service

        UpdateSignInData();  // update data from signIn fragment


        /*************** End of communication using viewmodel and livedata******************/

        /******************* firebase authentification**************/
        //log_inFragment log_inFragment =(log_inFragment)getFragmentManager().findFragmentById(R.id.login_fragment);
    }

    /*******************tablayout clickListener**************
     * In: void
     * Out: void
     * Description: listen the button press of tablayout child and set the fragment to inflate*/

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


    /******* Update data from signIn Fragment ***********
     * In: void
     * Out: void
     * Description: updates data needed for Firebase SignIn */
    public void UpdateSignInData(){
        //Get Name data from fragment SignIn through viewmodel and livedata
        viewmodel.getSigninName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                m_Name = s;
            }
        });
        //Get mail data from fragment SignIn through viewmodel and livedata
        viewmodel.getSigninMail().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                m_mail = s;
            }
        });
        //Get Mdp1 data from fragment SignIn through viewmodel and livedata
        viewmodel.getSigninMdp1().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                m_Mdp1= s;
            }
        });
        //Get Mdp2 data from fragment SignIn through viewmodel and livedata
        viewmodel.getSigninMdp2().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                m_Mdp2= s;
            }
        });
    }
   /******* End of data update from signIn Fragment ***********/


}