package com.example.zig_zag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class admin_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);
    }

    public void adduser(View view) {
        Intent i = new Intent(getApplicationContext(), new_user.class);
        startActivity(i);
        //Intent i = new Intent(getApplicationContext(), edit_user_screen.class);
        //startActivity(i);
    }

    public void edituser(View view) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        // FragmentManager manager=getFragmentManager();
        search_user_dialog su = new search_user_dialog();
        su.show(manager, null);

    }

    public void checckremaintime(View view) {
        Intent i = new Intent(getApplicationContext(), check_remain.class);
        startActivity(i);
    }

    public void addadmin(View view) {
        Intent i = new Intent(getApplicationContext(), new_admin.class);
        startActivity(i);
    }



    public void admin_search_user(View view) {
        Intent i = new Intent(getApplicationContext(), edit_user.class);
        startActivity(i);
    }

    public void viewalladmins(View view) {
        Intent i = new Intent(getApplicationContext(), alladmins.class);
        startActivity(i);
    }

    public void viewallusers(View view) {
        Intent i = new Intent(getApplicationContext(), all_users.class);
        startActivity(i);
    }
}
