package authentif;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.resume.R;
import com.example.resume.databinding.LogInBinding;
import com.example.resume.databinding.SignInBinding;


public class sign_inFragment extends Fragment {
    SignInBinding signInBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.sign_in, container, false);
        signInBinding = SignInBinding.inflate(inflater, container, false);
        View view = signInBinding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signInBinding.signinConnexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"ca marche",Toast.LENGTH_LONG).show();
            }
        });
    }
}
