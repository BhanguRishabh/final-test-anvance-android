package com.example.fa_rishabhsingh_c078019_android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    List<placesModelClass> data=new ArrayList<>();
    Context context;
    databaseHelperClass dbHelper;



    public RecycleAdapter(List<placesModelClass>data , Context context  ){
        this.data = data;
        this.context = context;
        dbHelper = new databaseHelperClass(context);





    }
    public class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView textView;
        private CheckBox  checkBox;
        private ImageButton deletebtn,editbtn;

        public ViewHolder(final View view) {

            super(view);
            textView = view.findViewById(R.id.cardText);
            checkBox = view.findViewById(R.id.cardCheck);
            deletebtn = view.findViewById(R.id.cardDeletebt);
            editbtn = view.findViewById(R.id.cardEditbt);

        }
    }

    @NonNull

     public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.card_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(String.valueOf(data.get(position).getPlace()));
        holder.checkBox.setChecked(data.get(position).getVisit());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  // Toast.makeText(context,holder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
                int id = holder.getAdapterPosition();

                Boolean selection;

                if(isChecked){
                    selection = true;


                        Toast.makeText(context,"PLACE MARKED AS VISTED " + data.get(id).getPlace(),Toast.LENGTH_SHORT).show();
                    //int i = dbHelper.updateRow(new placesModelClass(data.get(id).getId(), data.get(id).getPlace(), true, data.get(id).getLatitude(), data.get(id).getLongitude(), data.get(id).getDate()));





                }
                else {                   //   int i = dbHelper.updateRow(new placesModelClass(data.get(id).getId(), data.get(id).getPlace(), false, data.get(id).getLatitude(), data.get(id).getLongitude(), data.get(id).getDate()));


                    selection = false;}
                dbHelper.updateRow(new placesModelClass(data.get(id).getId(), data.get(id).getPlace(), selection, data.get(id).getLatitude(), data.get(id).getLongitude(), data.get(id).getDate()));
                //Toast.makeText(context,uniqueid,Toast.LENGTH_SHORT).show();


//                try{
//                   //(int id,String place, Boolean visit, Double latitude, Double longitude, String date)
//                   int i = dbHelper.updateRow(new placesModelClass(data.get(uniqueid).getId(), data.get(uniqueid).getPlace(), selection, data.get(uniqueid).getLatitude(), data.get(uniqueid).getLongitude(), data.get(uniqueid).getDate()));
//                   Toast.makeText(context,i,Toast.LENGTH_SHORT).show();
//               }
//               catch (Exception e) {}


            }

        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent indent = new Intent(context,SEE_PLACE.class);
                 indent.putExtra("pos",holder.getAdapterPosition());
                  context.startActivity(indent);
            }
        });
        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indent = new Intent(context,SELECT_PLACE.class);
                indent.putExtra("sendingId",holder.getAdapterPosition());

                 context.startActivity(indent);
            }
        });
        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<placesModelClass> recievedAll = new ArrayList<>();
                recievedAll = dbHelper.allDataReturn();


                dbHelper.deleteRow(recievedAll.get(holder.getAdapterPosition()).getId());
                data.remove(holder.getAdapterPosition());
                 notifyDataSetChanged();






            }
        });





    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
