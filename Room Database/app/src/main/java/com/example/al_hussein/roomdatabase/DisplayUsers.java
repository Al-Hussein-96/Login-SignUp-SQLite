package com.example.al_hussein.roomdatabase;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayUsers extends Fragment {
    private TextView textView;

    public DisplayUsers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_users, container, false);


        textView = view.findViewById(R.id.tv);

        Display();

        return view;
    }

    private void Display() {

        List<User> users = MainActivity.myAppDatabase.myDao().getUsers();

        String info = "";

        for(User user:users)
        {
            int id = user.getId();
            String name = user.getName();
            String email = user.getEmail();

            info = info + "\n\n" + "Id: " + id + "\nName: " + name + "\nEmail: " + email;

        }
        textView.setText(info);
    }

}
