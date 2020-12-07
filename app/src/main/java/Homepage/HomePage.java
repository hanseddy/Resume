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

import com.example.resume.MainActivity;
import com.example.resume.R;
import com.example.resume.databinding.HomePageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
HomePageBinding binding;
FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
String UID = null;
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

        binding.HomeAddPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, UID , Toast.LENGTH_SHORT).show();
            }
        });

     /*   binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomePage.this, MainActivity.class));
            }
        });*/
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
}