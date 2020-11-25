package authentif;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.resume.databinding.LogInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import DataCommunication.AuthViewmodel;
import Homepage.HomePage;

public class log_inFragment extends Fragment {

    LogInBinding logInBinding;
    AuthViewmodel logviewmodel;
    String mail,Pswd;
    FirebaseAuth mAuth;


    public interface  loginComBetweenFragAndActivity {
        public void MdpOublie(View view);  // onclick method of
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        logInBinding = LogInBinding.inflate(inflater, container, false);
        View view = logInBinding.getRoot();
        final Editable mail = logInBinding.loginMailEditText.getText();
        // handling floating action button
        logInBinding.loginFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"le mail est "+mail,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    // check if there is already signed-in user
   @Override
    public void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!= null){
            startActivity(new Intent(getContext(),HomePage.class));
        }

        //updateUI(account);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logviewmodel = new ViewModelProvider(requireActivity()).get(AuthViewmodel.class);
        mAuth= FirebaseAuth.getInstance();

        // si on appuie sur le bouton on set up les donnée
        logInBinding.loginFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetUpLogInData();
            }
        });

    }

    /********** Setting Up data **************/

    public void SetUpLogInData(){

        mail = EditToString(logInBinding.loginMailEditText);
        Pswd = EditToString(logInBinding.loginMdpEditText);

        if(((isEmpty(logInBinding.loginMailEditText)) ==true) || ((isEmpty(logInBinding.loginMdpEditText)) ==true)){  // if one of the field is empty
            Toast.makeText(getContext(), "all field need to be fill", Toast.LENGTH_SHORT).show();
            //Log.i("signFragment","sign field are empty");
        } else { // all the field are filled

                logviewmodel.SetLoginMail(mail);
                logviewmodel.SetLoginPsw(Pswd);
                /*******Sign In with mail and psw **********/
                mAuth.signInWithEmailAndPassword(mail, Pswd)
                    .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(getContext(), HomePage.class));

                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
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
        word = edit.getText().toString();
        return word;
    }
/* @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof loginComBetweenFragAndActivity){
            loginViewListener = (loginComBetweenFragAndActivity) context;
        }else{
            throw new RuntimeException(context.toString() + "must implement loginViewListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        loginViewListener = null;
    }*/
}
