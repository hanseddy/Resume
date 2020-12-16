package DataCommunication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewmodel extends ViewModel {


    MutableLiveData<String> UID ;
    MutableLiveData<String> NAME ;
    MutableLiveData<String> NBBOOK ;
    MutableLiveData<String> NBCHAP ;
    MutableLiveData<String> CHAPTER ;
    MutableLiveData<String> DESCRIPTION ;

    // INITIALISATION
    public void init(){
        UID= new MutableLiveData<String>();
        NAME= new MutableLiveData<String>();
        NBBOOK= new MutableLiveData<String>();
        NBCHAP= new MutableLiveData<String>();
        CHAPTER=new MutableLiveData<String>();
        DESCRIPTION=new MutableLiveData<String>();
    }
    /***************LIVEDATA SETTER*******************/
    public void setUID(MutableLiveData<String> UID) {
        this.UID = UID;
    }

    public void setNAME(MutableLiveData<String> NAME) {
        this.NAME = NAME;
    }

    public void setNBBOOK(MutableLiveData<String> NBBOOK) {
        this.NBBOOK = NBBOOK;
    }

    public void setNBCHAP(MutableLiveData<String> NBCHAP) {
        this.NBCHAP = NBCHAP;
    }

    public void setCHAPTER(MutableLiveData<String> CHAPTER) {
        this.CHAPTER = CHAPTER;
    }

    public void setDESCRIPTION(MutableLiveData<String> DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    /***************LIVEDATA GETTER*******************/
    public MutableLiveData<String> getUID() {
        return UID;
    }

    public MutableLiveData<String> getNAME() {
        return NAME;
    }

    public MutableLiveData<String> getNBBOOK() {
        return NBBOOK;
    }

    public MutableLiveData<String> getNBCHAP() {
        return NBCHAP;
    }

    public MutableLiveData<String> getCHAPTER() {
        return CHAPTER;
    }

    public MutableLiveData<String> getDESCRIPTION() {
        return DESCRIPTION;
    }

}
