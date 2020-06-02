package com.example.zig_zag;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class editadmin extends AppCompatActivity {
    Realm real;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editadmin);
//id لو جالك فاضى فحطلى قيمته بصفر
        int ind = getIntent().getIntExtra("id", 0);
        final TextView e1 = (TextView) findViewById(R.id.editadmin_id);
        final EditText e2 = (EditText) findViewById(R.id.editadmin_name);
        final EditText e3 = (EditText) findViewById(R.id.editadmin_pass);
        Realm.init(this);
        RealmConfiguration rcon = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(rcon);
        real = Realm.getDefaultInstance();
        //فتح للكتابه
        //  real.beginTransaction();
        List<admin_entity> alladminslist = real.where(admin_entity.class).findAll();
        final admin_entity editedadmin = alladminslist.get(ind);
        e1.setText(editedadmin.getAdminid());
        e2.setText(editedadmin.getAdminname());
        e3.setText(editedadmin.getAdminpassword());
        Button delete = (Button) findViewById(R.id.deleteadmin);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(editadmin.this)
                        .setTitle("Delete warning")
                        .setMessage("Are you sure you want to delete admin :" + editedadmin.getAdminname())

                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //فتح ترنزاكشن للكتابه او التغيير
                                real.beginTransaction();
                                editedadmin.deleteFromRealm();
                                //تسجيل التعديلات
                                real.commitTransaction();
                                //قفل الاتصال وده لازم بعد كل نهايه ترنزاكشن عشان ميحصلش اى ايرور
                                real.close();
                                Toast.makeText(editadmin.this, "delete done!",
                                        Toast.LENGTH_LONG).show();
                                finish();
                                // Continue with delete operation
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();


            }
        });


        Button update = (Button) findViewById(R.id.saveedits);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(editadmin.this)
                        .setTitle("Edit warning")
                        .setMessage("Are you sure you want to edit admin :" + editedadmin.getAdminname())

                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                real.beginTransaction();
                           // لان مينفعش اغير البرايمرى كى بعد ما اتكريت اول مره خلاص     editedadmin.setAdminid(e1.getText().toString());
                                editedadmin.setAdminname(e2.getText().toString());
                                editedadmin.setAdminpassword(e3.getText().toString());
                                real.copyToRealmOrUpdate(editedadmin);
                                real.commitTransaction();
                                real.close();
                                Toast.makeText(editadmin.this, "edit done!",
                                        Toast.LENGTH_LONG).show();
                                finish();
                                // Continue with delete operation
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();


            }
        });


    }
}
