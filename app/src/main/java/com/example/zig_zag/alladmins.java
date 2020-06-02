package com.example.zig_zag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class alladmins extends AppCompatActivity {
    Realm real;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alladmins);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Realm.init(this);
        RealmConfiguration rcon = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(rcon);
        real = Realm.getDefaultInstance();

        //  real.beginTransaction(); عملت عليه كومنت لان ده بستخدمه بس لما بكون عايز اكتب حاجه انما هنا انا بقرا بس
        List<admin_entity> alladminslist = real.where(admin_entity.class).findAll();
        ListView adlist = (ListView) findViewById(R.id.adminslist);
        String[] names = new String[alladminslist.size()];

        for (int i = 0; i < names.length; i++) {
            names[i] = alladminslist.get(i).getAdminname();
        }
        ArrayAdapter<String> namesadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        adlist.setAdapter(namesadapter);
//لو عايز انه يحصل حاجه لما يدوس على عنصر فالليست فيو دى
        adlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(alladmins.this, editadmin.class);
                //هنا ببعتله العنصر اللى دست عليه وبعرف انا دست على انهى عنصر من خلال البوزيشن اللى متاخد براميتار فى الفنكشن بتاعه اون ايتم كليك
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
        real.close();

    }
}
