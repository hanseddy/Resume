package Homepage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.resume.MainActivity;
import com.example.resume.R;
import com.example.resume.databinding.HomePageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import model.UserData;

public class HomePage extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
HomePageBinding binding;
FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
String UID = null;
BottomNavigationView bottomNavigationView;
DatabaseReference RootRef;  // firebase
    Map<String,String> UserData =new HashMap<String, String>();// instantiate Map
    model.UserData  FirebaseData = new UserData(UserData);
    private String TAG= "Homepage";
    UserData postUserData;
    //private Navigation NavigationHostFragment = ;
    NavHostFragment navHostFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.home_page);
        binding = HomePageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if(user != null){
            UID = user.getUid(); // firebase identification number;
        } else {
            Log.i("HomePage","no loggin user ");
        }
        /*******Firebase init *******/
        RootRef = FirebaseDatabase.getInstance().getReference();
        //FirebaseData.PopulateDataBase(UserData,"didi",0,0,"l'odyssé","lorem ipsu kara vunigo bala","chapter1");// populate Mapdata
       // RootRef.child(UID).setValue(FirebaseData);  // write in data

        /*******bottom navigation view *******/
        bottomNavigationView=binding.bottomNavigationView;
        navHostFragment= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navHostFragment.getNavController());
    }
    public void menuClicked(View v){
        //Toast.makeText(this, "menu is clicked", Toast.LENGTH_SHORT).show();
        PopupMenu Menu = new PopupMenu(this,v);
        MenuInflater inflateMenu = Menu.getMenuInflater();
        Menu.setOnMenuItemClickListener(this);
        inflateMenu.inflate(R.menu.menu_layout,Menu.getMenu());
        Menu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "logout is clicked", Toast.LENGTH_SHORT).show();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomePage.this, MainActivity.class));
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*** on check si dans la base de donné l'uid exist
         *                - NON = on cree une base de donné pour la l'utilisateur et on initialise tout à 0
         *                - OUI = on check si le nombre de livre est inferieur ou egale a 0
         *                         -> OUI:
         *                         -> NON: on inflate la page home full.
         */
        DatabaseReference UserUI = RootRef.child(UID);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postUserData = dataSnapshot.getValue(UserData.class);
                if(!dataSnapshot.exists()) {  // si UID n'existe pas
                    FirebaseData.PopulateDataBase(UserData,"0",0,0,"0","0","0");// on cree une nouvelle base de donné avec la nouvelle
                    //inflate home empty
                } else if((postUserData.DATA.get("NbBook"))!="0"){  // if there is already a book
                    Navigation.findNavController(HomePage.this,R.id.fragment).navigate(R.id.homeFullFragment);//inflate home full
                    //navHostFragment.findNavController(this).navigate(R.id.homeFullFragment);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage()); //Don't ignore errors!
            }
        };
        UserUI.addListenerForSingleValueEvent(eventListener);
    }
}