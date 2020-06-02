package com.example.zig_zag;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class check_user_in_range extends AppCompatActivity {
    Realm real;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_user_in_range);
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        Realm.init(this);
        RealmConfiguration rcon = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(rcon);
        real = Realm.getDefaultInstance();
        /////////////
        Bundle getBundle = null;
        getBundle = this.getIntent().getExtras();
        String start = getBundle.getString("startkey");
        String end = getBundle.getString("endkey");
       // String start=start_date.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date S_date=new Date();
        try {
            S_date = format.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
      //  String end=e_time.getText().toString();
        SimpleDateFormat endformat = new SimpleDateFormat("yyyy/MM/dd");
        Date end_date=new Date();
        try {
            end_date = endformat.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
     //   System.out.println(S_date);
       // System.out.println(end_date);
/////////////////////////////
     //   List<user_entity> allusers = real.where(user_entity.class).findAll();
       List<user_entity> allusers = real.where(user_entity.class)

               .greaterThanOrEqualTo("end", S_date)
                .lessThanOrEqualTo("end", end_date)
                .findAll();
        ListView userslist = (ListView) findViewById(R.id.range_result);
        String[] names = new String[allusers.size()];

        for (int i = 0; i < names.length; i++) {
            names[i] = allusers.get(i).getName();
        }
        ArrayAdapter<String> namesadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        userslist.setAdapter(namesadapter);

    }
}
