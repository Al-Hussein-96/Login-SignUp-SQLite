package com.example.al_hussein.roomdatabase;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private Button AddUser, View;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        AddUser = view.findViewById(R.id.addUser);
        AddUser.setOnClickListener(this);

        View = view.findViewById(R.id.view);
        View.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addUser:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.framelayout, new AddUserFragment()).addToBackStack(null).commit();
                break;
            case R.id.view:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.framelayout, new DisplayUsers()).addToBackStack(null).commit();

                break;
        }
    }
}
