package authentif;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.resume.R;
import com.example.resume.databinding.LogInBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import DataCommunication.AuthViewmodel;

public class log_inFragment extends Fragment {
   /* loginComBetweenFragAndActivity loginViewListener;
    EditText login_mail_edit;
    EditText login_mdp_edit;
    FloatingActionButton login_fab_button  ; // connection button
    TextView login_mdpOublie_text;*/
    //private FirebaseAuth mAuth; //firebase instance
    LogInBinding logInBinding;
    AuthViewmodel logviewmodel;
    String mail,Pswd;
    public interface  loginComBetweenFragAndActivity {
        void getloginView(CharSequence loginMail,CharSequence loginMdp);
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

       /* login_fab_button = v.findViewById(R.id.login_fab_button);
        login_mail_edit = v.findViewById(R.id.login_mail_editText);
        login_mdp_edit = v.findViewById(R.id.login_mdp_EditText);
        login_mdpOublie_text = v.findViewById(R.id.login_mdpOublier_text);*/
        return view;
        //return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logviewmodel = new ViewModelProvider(requireActivity()).get(AuthViewmodel.class);
        /// si on appuie sur le bouton on set up les donnée
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
