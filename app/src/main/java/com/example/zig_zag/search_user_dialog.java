package com.example.zig_zag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class search_user_dialog extends DialogFragment implements View.OnClickListener {

    View form;
    Button buttonsearchname;
    EditText searchname;
    TextView te;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        form = inflater.inflate(R.layout.activity_search_user, container, false);
        searchname = (EditText) form.findViewById(R.id.searchuser_name);
        buttonsearchname = (Button) form.findViewById(R.id.edituser_button_search);
        te = (TextView) form.findViewById(R.id.test);

        buttonsearchname.setOnClickListener(this);

        return form;
    }

    @Override
    public void onClick(View view) {


        //هنا هعمل كود الداتابيز للبحث عن يوزر
        if (searchname.getText().toString().equals("nnn")) {


            Intent i = new Intent(getActivity().getBaseContext(), edit_user_screen.class);
            startActivity(i);


        } else {
            te.setText(searchname.getText().toString());


        }
    }


}
