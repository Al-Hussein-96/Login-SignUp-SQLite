package com.example.al_hussein.login_signup_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.deleteDatabase(UserDbHelper.DATABASE_NAME);
        if (findViewById(R.id.fragment_content) != null) {
            if (savedInstanceState != null)
                return;
            Log.i("onCreate","Mohammad");
            LoginFragment loginFragment = new LoginFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_content, loginFragment).commit();
        }
    }
}
