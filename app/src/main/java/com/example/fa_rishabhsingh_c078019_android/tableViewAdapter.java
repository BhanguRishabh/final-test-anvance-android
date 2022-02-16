package com.example.fa_rishabhsingh_c078019_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class tableViewAdapter extends BaseAdapter {
    List<placesModelClass> data=new ArrayList<>();
    LayoutInflater inflater;//we will need this to link with the list_row xml file
    ViewHolder holder;


    //constructor
    public tableViewAdapter(Context context, List<placesModelClass>data)
    {
        this.data=data;
        inflater=LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null)
        {
            view=inflater.inflate(R.layout.layout,null);
            holder=new ViewHolder();
            holder.pName=view.findViewById(R.id.tvPlaceName);
            holder.pVisit=view.findViewById(R.id.ckbxDone);


            view.setTag(holder);

        }else
            holder = (ViewHolder) view.getTag();
        holder.pName.setText(String.valueOf(data.get(i).getPlace()));
        holder.pVisit.setChecked(data.get(i).getVisit());





        return view;




    }

    static class ViewHolder{
        private TextView pName;
        private CheckBox pVisit;

    }

}
