package com.example.al_hussein.login_signup_sqlite;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private Button btnLogin, btnDisplay;
    private TextView tvSignUp;
    private EditText etUsername, etPassword;

    UserDbHelper userDbHelper;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        userDbHelper = new UserDbHelper(getActivity());
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btnLogin = view.findViewById(R.id.btnLogin);
        btnDisplay = view.findViewById(R.id.btnDisplay);
        etUsername = view.findViewById(R.id.Username);
        etPassword = view.findViewById(R.id.Password);


        tvSignUp = (TextView) view.findViewById(R.id.SignUp);
        SpannableString content = new SpannableString("SignUp");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvSignUp.setText(content);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    String UserName = etUsername.getText().toString();
                    String PassWord = etPassword.getText().toString();

                    User CurrUser = userDbHelper.checkUser(new User(null, UserName, null, PassWord));

                    if (CurrUser != null) {
                        Toast.makeText(getActivity(), "Done Login", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Username Or Password not Correctly", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Username Or Password not Correctly", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new DisplayUsers()).addToBackStack(null).commit();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Clicked: ", "SignUp");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new SignUpFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }

    private boolean validate() {
        boolean valid = true;

        String UserName = etUsername.getText().toString();
        String PassWord = etPassword.getText().toString();

        if (UserName.length() <= 5) {
            valid = false;
        }

        if (PassWord.isEmpty() || PassWord.length() <= 5) {
            valid = false;
        }

        return valid;
    }


}
