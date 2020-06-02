package com.example.zig_zag;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class check_remain extends AppCompatActivity {
    Realm real;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_remain);
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int minut = c.get(Calendar.MINUTE);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final EditText start_date = (EditText) findViewById(R.id.check_start);
        final EditText e_time = (EditText) findViewById(R.id.check_end);
      String start=start_date.getText().toString();
        String end=e_time.getText().toString();
        ///////////////////////////////
        /////////////////////////////

      /*  e_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog t_dialog=new TimePickerDialog(check_remain.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        e_time.setText(hourOfDay+":"+minute);
                    }
                },hour,minut,true);

                t_dialog.setTitle("set time");
                t_dialog.show();
            }
        });*/
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d_dialoge = new DatePickerDialog(check_remain.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yeard, int monthofyear, int dayOfMonth) {

                        start_date.setText(yeard + "/" + (monthofyear +1)+ "/" + dayOfMonth);
                    }
                }, year, month, day);
                d_dialoge.setTitle("from");
                d_dialoge.show();
            }
        });
        e_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d_dialoge = new DatePickerDialog(check_remain.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yeard, int monthofyear, int dayOfMonth) {

                        e_time.setText(yeard + "/" +( monthofyear +1)+ "/" + dayOfMonth);
                    }
                }, year, month, day);
                d_dialoge.setTitle("to");
                d_dialoge.show();
            }
        });


    }

    public void get_range_user(View view) {
        Intent mIntent = new Intent(getApplicationContext(), check_user_in_range.class);
        Bundle mBundle = new Bundle();

        final EditText start_range = (EditText) findViewById(R.id.check_start);
        final EditText end_range = (EditText) findViewById(R.id.check_end);

        String start=start_range.getText().toString();
        String end=end_range.getText().toString();

        mBundle.putString("startkey", start);
        mBundle.putString("endkey", end);
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
    }
       /*كود بتاع يقارن داتا فى فتره محدده
        realm.where(ReservationObject.class)
     .greaterThanOrEqualTo("DateTimeStamp", jan1)
     .lessThan("DateTimeStamp", jan2)
     .findAll()
         */

}
