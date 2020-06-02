package com.example.zig_zag;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class admin_edit_user extends AppCompatActivity {
    Realm real;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_user);
        final EditText e_start = (EditText) findViewById(R.id.edit_start);
        final EditText e_end = (EditText) findViewById(R.id.edit_end);
        final TextView e_id = (TextView) findViewById(R.id.userid);
        int ind = getIntent().getIntExtra("id", 0);
        final EditText e_name = (EditText) findViewById(R.id.edit_name);
        final EditText e_phone = (EditText) findViewById(R.id.edit_phone);
        final EditText e_payed= (EditText) findViewById(R.id.edit_pay);
        final EditText e_remain= (EditText) findViewById(R.id.edit_remain);
        final Button saveedits= (Button) findViewById(R.id.edituser_button_save);
        Realm.init(this);
        RealmConfiguration rcon = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(rcon);
        real = Realm.getDefaultInstance();
        //فتح للكتابه
        //  real.beginTransaction();
        List<user_entity> alluserslist = real.where(user_entity.class).findAll();
        final user_entity edituser = alluserslist.get(ind);
        e_name.setText(edituser.getName());
        e_phone.setText(edituser.getPhone());
        String iddd=Integer.toString(edituser.getUserid());
        e_id.setText(iddd);
        String paay=Integer.toString(edituser.getPay());
        e_payed.setText(paay);
        String remaain=Integer.toString(edituser.getRemain());
        e_remain.setText(remaain);
       System.out.println(edituser.getStart());

        Date d_start=edituser.getStart();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String startDate = dateFormat.format(d_start);
        e_start.setText(startDate);
        Date d_end=edituser.getEnd();
        DateFormat dateendFormat = new SimpleDateFormat("yyyy/MM/dd");
        String endtDate = dateendFormat.format(d_end);
        e_end.setText(endtDate);
        /////////////////////////////
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int minut = c.get(Calendar.MINUTE);
        final int hour = c.get(Calendar.HOUR_OF_DAY);

        e_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d_dialoge = new DatePickerDialog(admin_edit_user.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yeard, int monthofyear, int dayOfMonth) {

                        e_start.setText(yeard + "/" + (monthofyear +1)+ "/" + dayOfMonth);
                    }
                }, year, month, day);
                d_dialoge.setTitle("from");
                d_dialoge.show();
            }
        });
        e_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d_dialoge = new DatePickerDialog(admin_edit_user.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yeard, int monthofyear, int dayOfMonth) {

                        e_end.setText(yeard + "/" +( monthofyear +1)+ "/" + dayOfMonth);
                    }
                }, year, month, day);
                d_dialoge.setTitle("to");
                d_dialoge.show();
            }
        });


///////////////////////////////////////////////////////////////////

        Button update = (Button) findViewById(R.id.edituser_button_save);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(admin_edit_user.this)
                        .setTitle("Edit warning")
                        .setMessage("Are you sure you want to edit user :" + edituser.getName())

                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                real.beginTransaction();
                                // لان مينفعش اغير البرايمرى كى بعد ما اتكريت اول مره خلاص     editedadmin.setAdminid(e1.getText().toString());


                                edituser.setName(e_name.getText().toString());
                                edituser.setPhone(e_phone.getText().toString());
                                edituser.setPay(Integer.parseInt(e_payed.getText().toString()));
                                edituser.setRemain(Integer.parseInt(e_remain.getText().toString()));


                                DateFormat format =new SimpleDateFormat("yyyy/MM/dd");
                                Date   S_date=new Date();

                                final String start=e_start.getText().toString();
                                final String end=e_end.getText().toString();
                                try {

                                     S_date = format.parse(start);
                                    edituser.setStart(S_date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                DateFormat eformat = new SimpleDateFormat("yyyy/MM/dd");
                                Date  e_date=new Date();
                                try {
                                     e_date = format.parse(end);

                                    edituser.setEnd(e_date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                real.copyToRealmOrUpdate(edituser);
                                real.commitTransaction();
                                real.close();
                                Toast.makeText(admin_edit_user.this, "edit done!",
                                        Toast.LENGTH_LONG).show();

                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();


            }
        });

////////////////////////////////////////////////////////
        Button delete = (Button) findViewById(R.id.rwmove_user);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(admin_edit_user.this)
                        .setTitle("Delete warning")
                        .setMessage("Are you sure you want to delete user :" + edituser.getName())

                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //فتح ترنزاكشن للكتابه او التغيير
                                real.beginTransaction();
                                edituser.deleteFromRealm();
                                //تسجيل التعديلات
                                real.commitTransaction();
                                //قفل الاتصال وده لازم بعد كل نهايه ترنزاكشن عشان ميحصلش اى ايرور
                                real.close();
                                Toast.makeText(admin_edit_user.this, "delete done!",
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
