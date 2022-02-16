package com.example.fa_rishabhsingh_c078019_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView list;
    ImageButton addBtn;
    databaseHelperClass dbHelper = new databaseHelperClass(MainActivity.this);
    static Boolean firstLauch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         list = findViewById(R.id.lvplaces);
         addBtn = findViewById(R.id.imbtnAdd);





         list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailSCreen = new Intent(MainActivity.this,SEE_PLACE.class);
                 detailSCreen.putExtra("scr",position);

                 startActivity(detailSCreen);

             }
         });
         addBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent detailSCreen = new Intent(MainActivity.this,SELECT_PLACE.class);


                 startActivity(detailSCreen);


             }
         });


       list.setAdapter( new tableViewAdapter(MainActivity.this,dbHelper.allDataReturn()));

    }





    public void sampleData(){

        dbHelper.addData(new placesModelClass(1,"neyydh",true,25.5,45.8,"73299i"));
        dbHelper.addData(new placesModelClass(1,"neyydh",false,25.5,45.8,"73299i"));
        dbHelper.addData(new placesModelClass(1,"neyydh",true,25.5,45.8,"73299i"));
    }


}