package com.example.fa_rishabhsingh_c078019_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.content.Context;
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
    //ListView list;
    RecyclerView list;
    ImageButton addBtn;
    Context context;
    databaseHelperClass dbHelper = new databaseHelperClass(MainActivity.this);
    placesModelClass objPlace;
    static Boolean firstLauch = true;
    List<placesModelClass> recievedAll = new ArrayList<>();
    ImageButton removed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dbHelper.deleteAll();;
       // sampleData();
        //list = findViewById(R.id.lvplaces);
        addBtn = findViewById(R.id.imbtnAdd);
        list = findViewById(R.id.rvList);
        removed = findViewById(R.id.cardDeletebt);

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"click detected", Toast.LENGTH_SHORT).show();

            }
        });






        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailSCreen = new Intent(MainActivity.this,SELECT_PLACE.class);


                startActivity(detailSCreen);


            }
        });


        //  list.setAdapter( new tableViewAdapter(MainActivity.this,dbHelper.allDataReturn()));
        RecycleAdapter adapter = new RecycleAdapter(dbHelper.allDataReturn(),MainActivity.this);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        // Attach the adapter to the recyclerview to populate ite





//       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
//           @Override
//           public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//               Toast.makeText(context, "on Movgcgcgcvhge", Toast.LENGTH_SHORT).show();
//               return false;
//           }
//
//           @Override
//           public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//               Toast.makeText(context, "on Movgcgcgcvhge", Toast.LENGTH_SHORT).show();
//        recievedAll = dbHelper.allDataReturn();
//
//                 dbHelper.delete(recievedAll.get(viewHolder.getAdapterPosition()));
//
//
//             //adapter.notifyDataSetChanged();
//
//
//           }
//       }).attachToRecyclerView(list);


            }







    public void sampleData()
    {

        dbHelper.addData(new placesModelClass(1,"neyydh",true,25.5,45.8,"73299i"));
        dbHelper.addData(new placesModelClass(1,"neyydh",false,25.5,45.8,"73299i"));
        dbHelper.addData(new placesModelClass(1,"neyydh",true,25.5,45.8,"73299i"));
    }



}