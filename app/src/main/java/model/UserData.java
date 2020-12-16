package model;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

public class UserData {
    public Map<String, String> DATA = new HashMap<String, String>();
    public UserData() {
    }

    public UserData(Map<String, String> DATA) {
        this.DATA = DATA;
    }
    public void PopulateDataBase(Map<String, String> DATA, String Name, Integer NbBook, Integer NbChap, String Title, String Description, String Chapter) {

        DATA.put("Name", Name);
        DATA.put("NbBook", NbBook.toString());  // update number of book
        DATA.put("NbChap", NbChap.toString());  // update number of chapter
        DATA.put("Book_1", Title);  // service generate book key
        DATA.put("Description", Description);
        DATA.put("Chap1-1", Chapter);  // service generate chapter key
    }
    /***********set up Data service *************/
    // update Name
    public void setDataName(DatabaseReference DatabaseRef, String Name){
        //DatabaseRef.child(UID).child("DATA").child("Name").setValue(Name);
        DatabaseRef.child("DATA").child("Name").setValue(Name);
    }
    // update Number of book
    public void setDataNbBook(DatabaseReference DatabaseRef, Integer Nb_book){
        DatabaseRef.child("DATA").child("NbBook").setValue(Nb_book.toString());
    }
    // update Number of chapter
    public void setDataNbChapter(DatabaseReference DatabaseRef, Integer Nb_Chapter){
        DatabaseRef.child("DATA").child("NbChap").setValue(Nb_Chapter.toString());
    }
    // update Title
    public void setDataTitle(DatabaseReference DatabaseRef,  String Title){
        DatabaseRef.child("DATA").child("Title").setValue(Title);
    }
    // update Description
    public void setDataDescription(DatabaseReference DatabaseRef, String Description){
        DatabaseRef.child("DATA").child("Name").setValue(Description);
    }
    // update Chapter
    public void setDataChapter(DatabaseReference DatabaseRef, String Chapter){
        DatabaseRef.child("DATA").child("Name").setValue(Chapter);
    }
}
