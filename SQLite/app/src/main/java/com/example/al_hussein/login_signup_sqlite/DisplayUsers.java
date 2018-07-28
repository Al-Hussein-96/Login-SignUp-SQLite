package com.example.al_hussein.login_signup_sqlite;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayUsers extends Fragment {
    private TextView textView;
    public DisplayUsers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_display_users, container, false);
        textView = view.findViewById(R.id.textview);
        displayUsers();
        return view;
    }

    private void displayUsers() {
        UserDbHelper userDbHelper = new UserDbHelper(getActivity());
        Cursor cursor = userDbHelper.ReadUsers();
        String info = "";
        while (cursor.moveToNext()) {
            String id = Integer.toString(cursor.getInt(cursor.getColumnIndex(UserContract.ContactEntry.CONTACT_ID)));
            String name = cursor.getString(cursor.getColumnIndex(UserContract.ContactEntry.NAME));
            String email = cursor.getString(cursor.getColumnIndex(UserContract.ContactEntry.EMAIL));
            String Password = cursor.getString(cursor.getColumnIndex(UserContract.ContactEntry.PASSWORD));
            info = info + "\n\n" + "Id: " + id + "\nName: " + name + "\nEmail: " + email + "\nPassword: " + Password;
        }
        textView.setText(info);
        userDbHelper.close();
    }

}
