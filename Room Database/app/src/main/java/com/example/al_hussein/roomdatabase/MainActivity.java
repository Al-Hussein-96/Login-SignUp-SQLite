package com.example.al_hussein.roomdatabase;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static MyAppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager = getSupportFragmentManager();
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "userdb").allowMainThreadQueries().build();

        deleteDatabase("userdb");
        if (findViewById(R.id.framelayout) != null) {
            if (savedInstanceState != null)
                return;


            fragmentManager.beginTransaction().add(R.id.framelayout, new HomeFragment()).commit();
        }
    }
}















