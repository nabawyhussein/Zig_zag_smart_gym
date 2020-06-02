package com.example.zig_zag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Realm real;
    EditText e1, e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void login(View view) {
        Realm.init(this);
        RealmConfiguration rcon = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(rcon);
        real = Realm.getDefaultInstance();
        e1 = (EditText) findViewById(R.id.login_id);
        e2 = (EditText) findViewById(R.id.loginpass);
        RealmQuery<admin_entity> query=real.where(admin_entity.class);
        //هنا بحط اسم الاتربيوت بتاع الجدول اللى عايز اعمل فحص عليه وبحط بعده القيمه اللى عايز اتاكد منها
        query.equalTo("adminid",e1.getText().toString());
       query.equalTo("adminpassword",e2.getText().toString());
        //هنا بحط كل اللى فالكويرى بحطهم فى اوبحكت عشان اعرف اتعامل معاه
        RealmResults<admin_entity> result=query.findAll();
        //String defid=e1.toString();

        if (result.size()>0){
            startActivity(new Intent(MainActivity.this, admin_screen.class));


        }

        else {
            Toast.makeText(MainActivity.this, "Id or password is invalid please try again !",
                    Toast.LENGTH_LONG).show();


        }
    }

    public void register(View view) {
        Intent i = new Intent(getApplicationContext(), new_admin.class);
        startActivity(i);
    }
}
