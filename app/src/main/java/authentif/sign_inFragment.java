package authentif;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.resume.databinding.SignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import DataCommunication.AuthViewmodel;
import Homepage.HomePage;


public class sign_inFragment extends Fragment {

    private FirebaseAuth mAuth; // instance of firebase
    AuthViewmodel Signviemodel;
    SignInBinding signInBinding;
    String Name,Mail,Mdp1,Mdp2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        signInBinding = SignInBinding.inflate(inflater, container, false);
        View view = signInBinding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Signviemodel = new ViewModelProvider(requireActivity()).get(AuthViewmodel.class);
        signInBinding.signinConnexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetUpSignInData();
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }

    /********** Setting Up data **************/

    public void SetUpSignInData(){

        Name = EditToString(signInBinding.signinNomEditText);
        Mail = EditToString(signInBinding.signinMailEditText);
        Mdp1 = EditToString(signInBinding.signinMdpEditText);
        Mdp2 = EditToString(signInBinding.signinMdp2EditText);

        if(((isEmpty(signInBinding.signinMailEditText)) ==true) || ((isEmpty(signInBinding.signinNomEditText)) ==true)
                || ((isEmpty(signInBinding.signinMdpEditText)) ==true) || ((isEmpty(signInBinding.signinMdp2EditText)) ==true)){  // if one of the field is empty
            Toast.makeText(getContext(), "all field need to be fill", Toast.LENGTH_SHORT).show();
            //Log.i("signFragment","sign field are empty");
        } else { // all the field are filled

            if((isEqual(signInBinding.signinMdpEditText,signInBinding.signinMdp2EditText)) == true){  // password are the same
                // sett up all the data if the button is pressed
                Signviemodel.SetSigninName(Name);
                Signviemodel.SetSigninMail(Mail);
                Signviemodel.SetSigninMdp1(Mdp1);
                //Signviemodel.SetSigninMdp2(Mdp2);

                /********* Firebase mail and password Authentification *************/

                mAuth.createUserWithEmailAndPassword(Mail,Mdp1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),"authentification succeed",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(), HomePage.class));
                            //progressBar.setVisibility(View.INVISIBLE);
                        }
                        else{  // la creation de compte failed
                            Toast.makeText(getContext(),"authentification failed"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } else {       //psw not the same
                Toast.makeText(getContext(), "psw are not the same", Toast.LENGTH_SHORT).show();
            }
        }
    }
    /**********  End of Setting Up data **************/

    /************* isEmpty function *************
     * In: Edittext
     * Out: Boolean
     * Check if the editText is empty*/
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
    /************* End of isEmpty function *************/

    /************* isEqual function *************
     * In: Edittext edit1, edit2
     * Out: Boolean
     * Check if the editText edit1 is equal to edit2*/
    private boolean isEqual(EditText edit1, EditText edit2){
        String word1,word2;
        word1=edit1.getText().toString();
        word2=edit2.getText().toString();
        if(word1.equals(word2) == true){
            return true;
        } else
            return false;
    }
    /************* End of isEqual function *************/

    // make a method to transform EditText into String
    public String EditToString(EditText edit){
        String word;
        word = edit.getText().toString().trim();
        return word;
    }
}
