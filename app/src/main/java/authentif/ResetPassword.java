package authentif;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.resume.databinding.ResetPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Homepage.HomePage;

public class ResetPassword extends AppCompatActivity {
    ResetPasswordBinding binding;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ResetPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth= FirebaseAuth.getInstance();
        binding.ResetPaswSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = binding.ResetPswMailEdit.getText().toString().trim();
                user = FirebaseAuth.getInstance().getCurrentUser();
                if(isEmpty(binding.ResetPswMailEdit)== true){
                    Toast.makeText(ResetPassword.this,"please fill all the field", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(ResetPassword.this,"le mail est"+mail, Toast.LENGTH_SHORT).show();
                    //binding.resetPswQuestionText.setText(mail);
                    mAuth.sendPasswordResetEmail(mail)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.i("sendSuccesful", "Email sent.");
                                        Toast.makeText(ResetPassword.this,"Email sent.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                                    }
                                    else{
                                        Toast.makeText(ResetPassword.this,"Email not sent.", Toast.LENGTH_SHORT).show();
                                        Log.i("sendNotSuccessful", "Email not sent.");
                                    }
                                }
                            });
                    }
                 }
            });

    }
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
}