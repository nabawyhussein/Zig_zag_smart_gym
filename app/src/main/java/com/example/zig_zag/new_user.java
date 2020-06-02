package com.example.zig_zag;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class new_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int minut = c.get(Calendar.MINUTE);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
       // هبدا اعرف الحاجات بتاعه الداتا بتاعه اليوزر
        final EditText e_start = (EditText) findViewById(R.id.newuser_time_dialog);
        final EditText e_end = (EditText) findViewById(R.id.newuser_date_dialog);
        final EditText e_id = (EditText) findViewById(R.id.userid);
        final EditText e_name = (EditText) findViewById(R.id.adduser_name);
        final EditText e_phone = (EditText) findViewById(R.id.adduser_phone);
        final EditText e_payed= (EditText) findViewById(R.id.adduser_pay);
        final EditText e_remain= (EditText) findViewById(R.id.adduser_remain);
        final Button save_new= (Button) findViewById(R.id.adduser_button_save);

        final Realm real;
        Realm.init(this);
        RealmConfiguration rcon = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(rcon);
        real = Realm.getDefaultInstance();



        /*
        ده كود لو عايز اليوزر يدخل الوقت بالساعات مش بالتاريخ

   e_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog t_dialog = new TimePickerDialog(new_user.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        e_end.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minut, true);

                t_dialog.setTitle("set time");
                t_dialog.show();
            }
        });

         */
        e_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d_dialoge = new DatePickerDialog(new_user.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yeard, int monthofyear, int dayOfMonth) {

                        e_start.setText(yeard + "/" + (monthofyear+1) + "/" + dayOfMonth);
                    }
                }, year, month, day);
                d_dialoge.setTitle("select datee");
                d_dialoge.show();
            }
        });
        e_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d_dialoge = new DatePickerDialog(new_user.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yeard, int monthofyear, int dayOfMonth) {

                        e_end.setText(yeard + "/" + (monthofyear+1) + "/" + dayOfMonth);
                    }
                }, year, month, day);
                d_dialoge.setTitle("select datee");
                d_dialoge.show();
            }
        });


        save_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RealmQuery<user_entity> query=real.where(user_entity.class);
                //هنا بحط اسم الاتربيوت بتاع الجدول اللى عايز اعمل فحص عليه وبحط بعده القيمه اللى عايز اتاكد منها
                //هنا حلوته لانتجر عشان هو متسجل انتيجر فالداتا بيز انما فى ادمن متجل استرنج عادى
                query.equalTo("userid",Integer.parseInt(e_id.getText().toString()));
                //هنا بحط كل اللى فالكويرى بحطهم فى اوبحكت عشان اعرف اتعامل معاه
                RealmResults<user_entity> result=query.findAll();
                System.out.println(result.size());
                System.out.println(result.size());
                System.out.println(result.size());
                if (result.size()>0){
                    Toast.makeText(new_user.this, "Id is aredy exist please try again !",
                            Toast.LENGTH_LONG).show();

                }
                else {
                    user_entity newuser= new user_entity();
                    newuser.setUserid(Integer.parseInt(e_id.getText().toString()));
                    newuser.setName(e_name.getText().toString());
                    newuser.setPhone(e_phone.getText().toString());
                    newuser.setPay(Integer.parseInt(e_payed.getText().toString()));
                    newuser.setRemain(Integer.parseInt(e_remain.getText().toString()));
                    String start=e_start.getText().toString();
                    String end=e_end.getText().toString();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    //  Date S_date = new Date();
                    try {
                        Date  S_date = format.parse(start);

                        newuser.setStart(S_date);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    SimpleDateFormat  eformat = new SimpleDateFormat("yyyy/MM/dd");
                    //   Date e_date = new Date();
                    try {

                        Date    e_date = eformat.parse(end);
                        newuser.setEnd(e_date);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    real.beginTransaction();
                    real.copyToRealm(newuser);
                    real.commitTransaction();

                    real.close();
                    Toast.makeText(new_user.this, "Done!",
                            Toast.LENGTH_LONG).show();
                    //عشان اقفل الصفحه دى بعمل فنش
                    finish();
                }





            }
        });

    }


   }
