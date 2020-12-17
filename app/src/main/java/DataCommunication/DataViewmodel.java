package DataCommunication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewmodel extends ViewModel {


    MutableLiveData<String> UID ;
    MutableLiveData<String> NAME ;
    MutableLiveData<String> BOOK ;
    MutableLiveData<String> NBBOOK ;
    MutableLiveData<String> NBCHAP ;
    MutableLiveData<String> CHAPTER ;
    MutableLiveData<String> DESCRIPTION ;

    // INITIALISATION
    public void init(){
        UID= new MutableLiveData<String>();
        NAME= new MutableLiveData<String>();
        BOOK = new MutableLiveData<String>();
        NBBOOK= new MutableLiveData<String>();
        NBCHAP= new MutableLiveData<String>();
        CHAPTER=new MutableLiveData<String>();
        DESCRIPTION=new MutableLiveData<String>();
    }


    /***************LIVEDATA GETTER*******************/
    public LiveData<String> getUID() {
        return UID;
    }

    public LiveData<String> getBOOK() {
        return BOOK;
    }

    public LiveData<String> getNAME() {
        return NAME;
    }

    public LiveData<String> getNBBOOK() {
        return NBBOOK;
    }

    public LiveData<String> getNBCHAP() {
        return NBCHAP;
    }

    public LiveData<String> getCHAPTER() {
        return CHAPTER;
    }

    public LiveData<String> getDESCRIPTION() {
        return DESCRIPTION;
    }


    /***************LIVEDATA SETTER*******************/
    public void setUID(String uid) {
        UID.setValue(uid);
    }

    public void setNAME(String name) {
        NAME.setValue(name);
    }
    public void setBOOK(String book) {
        BOOK.setValue(book);
    }

    public void setNBBOOK(String NbBook) {
        NBBOOK.setValue(NbBook);
    }

    public void setNBCHAP(String NbChap) {
        NBCHAP.setValue(NbChap);
    }

    public void setCHAPTER(String Chapter) {
        CHAPTER.setValue(Chapter);
    }

    public void setDESCRIPTION(String Description) {
        DESCRIPTION.setValue(Description);
    }

}
