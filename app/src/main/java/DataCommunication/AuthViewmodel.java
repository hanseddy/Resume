package DataCommunication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AuthViewmodel extends ViewModel {
    /***************data managing signin******************/
    MutableLiveData<String> Name ;
    MutableLiveData<String> Mail ;
    MutableLiveData<String> Mdp1 ;
    MutableLiveData<String> Mdp2 ;
    /***************data managing Login******************/
    MutableLiveData<String> loginMail ;
    MutableLiveData<String> loginPsw ;
    public void init(){
        // Signin
        Name= new MutableLiveData<String>();
        Mail= new MutableLiveData<String>();
        Mdp1= new MutableLiveData<String>();
        Mdp2= new MutableLiveData<String>();
        //Login
        loginMail=new MutableLiveData<String>();
        loginPsw=new MutableLiveData<String>();
    }
     /********** Sigin getter function **********/
    // getter Name
    public LiveData<String> getSigninName(){
        return Name;
    }
    // getter Mail
    public LiveData<String> getSigninMail(){
        return Mail;
    }
    // getter Mdp1
    public LiveData<String> getSigninMdp1(){
        return Mdp1;
    }
    // getter Mdp2
    public LiveData<String> getSigninMdp2(){
        return Mdp2;
    }

    /********** Login getter function **********/
    //getter loginMail
    public LiveData<String> getLoginMail(){
        return loginMail;
    }
    //getter loginPsw
    public LiveData<String> getLoginPsw(){
        return loginPsw;
    }

    /********** Signin setter function **********/
    // setter Name
    public void SetSigninName(String N){
        Name.setValue(N);
    }
    // setter Mail
    public void SetSigninMail(String M){
        Mail.setValue(M);
    }
    // setter Mdp1
    public void SetSigninMdp1(String Mdp){
        Mdp1.setValue(Mdp);
    }
    // setter Mdp2
    public void SetSigninMdp2(String Mdp){
        Mdp2.setValue(Mdp);
    }

    /********** Login setter function **********/
    // setter loginMail
    public void SetLoginMail(String Mdp){
        loginMail.setValue(Mdp);
    }
    // setter loginMail
    public void SetLoginPsw(String Mdp){
        loginPsw.setValue(Mdp);
    }
}
