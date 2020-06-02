package com.example.zig_zag;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class new_admin extends AppCompatActivity {
    Realm real;
    EditText e1, e2, e3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_admin);
        Realm.init(this);
        RealmConfiguration rcon = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(rcon);
        real = Realm.getDefaultInstance();
        e1 = (EditText) findViewById(R.id.addadmin_id);
        e2 = (EditText) findViewById(R.id.addadmin_name);
        e3 = (EditText) findViewById(R.id.addadmin_password);
        Button b1 = (Button) findViewById(R.id.addadmin_button_save);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<admin_entity> alladminslist = real.where(admin_entity.class).findAll();

//هنا بملى كل الداتا فى جدول الادمن بملاها فى query
                RealmQuery<admin_entity> query=real.where(admin_entity.class);
                //هنا بحط اسم الاتربيوت بتاع الجدول اللى عايز اعمل فحص عليه وبحط بعده القيمه اللى عايز اتاكد منها
                query.equalTo("adminid",e1.getText().toString());
                //هنا بحط كل اللى فالكويرى بحطهم فى اوبحكت عشان اعرف اتعامل معاه
                RealmResults<admin_entity>result=query.findAll();
                System.out.println(result.size());
                if (result.size()>0){
                    Toast.makeText(new_admin.this, "Id is aredy exist please try again !",
                            Toast.LENGTH_LONG).show();

                }
               else {

                    admin_entity no = new admin_entity();
                    no.setAdminid(e1.getText().toString());
                    no.setAdminname(e2.getText().toString());
                    no.setAdminpassword(e3.getText().toString());

                    real.beginTransaction();
                    real.copyToRealm(no);
                    real.commitTransaction();

                    real.close();
                    Toast.makeText(new_admin.this, "Done!",
                            Toast.LENGTH_LONG).show();
                    //عشان اقفل الصفحه دى بعمل فنش
                    finish();
                }
            }
        });


    }

    public void save_newadmin(View view) {
    }
}
