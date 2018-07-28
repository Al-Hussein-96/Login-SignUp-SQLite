package com.example.al_hussein.login_signup_sqlite;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    private EditText etUsername, etEmail, etPassword, etConfirmPassWord;
    private Button btnRegister;

    UserDbHelper userDbHelper;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        userDbHelper = new UserDbHelper(getActivity());

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        etUsername = view.findViewById(R.id.tvUsername);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        etConfirmPassWord = view.findViewById(R.id.etConfirmPass);

        btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    String Username = etUsername.getText().toString();
                    String Email = etEmail.getText().toString();
                    String Password = etPassword.getText().toString();

                    if (!userDbHelper.isUserExists(Username)) {
                        userDbHelper.addUser(new User(null, Username, Email, Password));
                        Toast.makeText(getActivity(), "Created Account", Toast.LENGTH_LONG).show();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new LoginFragment()).addToBackStack(null).commit();
                    } else {
                        Toast.makeText(getActivity(), "Not Created Account", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

        return view;
    }

    private boolean validate() {
        boolean valid = true;
        String Username = etUsername.getText().toString();
        String Email = etEmail.getText().toString();
        String Password = etPassword.getText().toString();
        String ConfPass = etConfirmPassWord.getText().toString();

        if (Username.length() <= 5) {
            valid = false;

            Toast.makeText(getActivity(), "Not valid Username must length > 5", Toast.LENGTH_LONG).show();

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            Toast.makeText(getActivity(), "Not Valid Email", Toast.LENGTH_LONG).show();

        }

        if (Password.isEmpty() || ConfPass.isEmpty() || Password.length() <= 5
                || !Password.equals(ConfPass)) {
            valid = false;
            Toast.makeText(getActivity(), "Not Valid Password", Toast.LENGTH_LONG).show();
        }

        return valid;
    }

}
