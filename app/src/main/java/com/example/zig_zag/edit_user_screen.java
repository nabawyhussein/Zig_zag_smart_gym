package com.example.zig_zag;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class edit_user_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_screen);


        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int minut = c.get(Calendar.MINUTE);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final EditText e_date = (EditText) findViewById(R.id.date_dialog);
        final EditText e_time = (EditText) findViewById(R.id.time_dialog);
     /*   e_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog t_dialog=new TimePickerDialog(edit_user_screen.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        e_time.setText(hourOfDay+":"+minute);
                    }
                },hour,minut,true);

t_dialog.setTitle("set time");
t_dialog.show();
            }
        });*/
        e_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d_dialoge = new DatePickerDialog(edit_user_screen.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yeard, int monthofyear, int dayOfMonth) {

                        e_date.setText(yeard + " - " + monthofyear + " - " + dayOfMonth);
                    }
                }, year, month, day);
                d_dialoge.setTitle("select end date");
                d_dialoge.show();
            }
        });
        e_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d_dialoge = new DatePickerDialog(edit_user_screen.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yeard, int monthofyear, int dayOfMonth) {

                        e_date.setText(yeard + " - " + monthofyear + " - " + dayOfMonth);
                    }
                }, year, month, day);
                d_dialoge.setTitle("select start date");
                d_dialoge.show();
            }
        });


    }

    public void edit_set_start(View view) {

    }

    public void edit_set_end(View view) {

    }

    public void edit_save(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("confirm edit !.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
