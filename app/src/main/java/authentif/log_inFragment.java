package authentif;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.resume.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class log_inFragment extends Fragment {
    //loginComBetweenFragAndActivity loginViewListener;
    EditText login_mail_edit;
    EditText login_mdp_edit;
    FloatingActionButton login_fab_button  ; // connection button
    TextView login_inscription_text;
    TextView login_mdpOublie_text;

    /*public interface  loginComBetweenFragAndActivity {
        void getloginView(CharSequence loginMail,CharSequence loginMdp);
    }*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.log_in,container,false);
        login_fab_button = v.findViewById(R.id.login_fab_button);
        login_mail_edit = v.findViewById(R.id.login_mail_editText);
        login_mdp_edit = v.findViewById(R.id.login_mdp_EditText);
        login_inscription_text = v.findViewById(R.id.login_inscription_text);
        login_mdpOublie_text =v.findViewById(R.id.login_mdpOublier_text);
        // handling floating action button
        login_fab_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence mail= login_mail_edit.getText();
                CharSequence mdp= login_mdp_edit.getText();
                //loginViewListener.getloginView(mail,mdp);
            }
        });

        return v;
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
