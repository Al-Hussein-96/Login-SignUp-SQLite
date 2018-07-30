package com.example.al_hussein.roomdatabase;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment {
    private EditText editText1,editText2,editText3;
    private Button button;

    public AddUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_user, container, false);

        editText1 = view.findViewById(R.id.editText);
        editText2 = view.findViewById(R.id.editText2);

        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String id = editText3.getText().toString();
                String user = editText1.getText().toString();
                String email = editText2.getText().toString();

                User user1 = new User(user,email);
                MainActivity.myAppDatabase.myDao().addUser(user1);
                Toast.makeText(getActivity(),"User Added Successfully",Toast.LENGTH_SHORT).show();

                editText1.setText("");
                editText2.setText("");
            }
        });

        return view;
    }

}
