package com.example.fa_rishabhsingh_c078019_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class databaseHelperClass  extends SQLiteOpenHelper {
    public static final String PLACES_TABLE = "PLACES_TABLE";
    public static final String PLACE_ID = "PLACE_ID";
    public static final String PLACE_NAME = "PLACE_NAME";
    public static final String PLACE_LATITUDE = "PLACE_LATITUDE";
    public static final String PLACE_LONGITUDE = "PLACE_LONGITUDE";
    public static final String PLACE_VISIT = "PLACE_VISIT";
    public static final String PLACE_DATE = "PLACE_DATE";

    public databaseHelperClass(@Nullable Context context) {
        super(context, "db.places", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDataBaseStatement = " CREATE TABLE " + PLACES_TABLE + "(" + PLACE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PLACE_NAME + " TEXT," + PLACE_LATITUDE + " DOUBLE," + PLACE_LONGITUDE + " DOUBLE," + PLACE_VISIT + " INTEGER , " + PLACE_DATE + " DEFAULT CURRENT_DATE )";
                db.execSQL(createDataBaseStatement);

    }

    public boolean intTOBool(int a){
        if(a == 0)
        return  false;
   return  true; }


    public Boolean addData(placesModelClass place){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLACE_NAME,place.getPlace());
        cv.put(PLACE_LATITUDE,place.getLatitude());
        cv.put(PLACE_LONGITUDE,place.getLongitude());
        cv.put(PLACE_VISIT,place.getVisit());
        long insert = db.insert(PLACES_TABLE, null, cv);
        if(insert == -1)
            return false;

        return true;

    }
    public List allDataReturn() {
        List<placesModelClass> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + PLACES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int pID = cursor.getInt(0);
                String pName = cursor.getString(1);
                Double lati = cursor.getDouble(2);
                Double longi = cursor.getDouble(3);
                Boolean  pVisit = intTOBool(cursor.getInt(4));
                String pDate = cursor.getString(5);


               placesModelClass placeInst = new placesModelClass(pID,pName,pVisit,lati,longi,pDate);

                returnList.add(placeInst);
            }
            while (cursor.moveToNext());

        } else {   // failed to retrive data}


        }
        return returnList;
    }
    public void deleteAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(PLACES_TABLE, null, null);

    }

    public int updateRow(placesModelClass prod){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLACE_NAME,prod.getPlace());
        cv.put(PLACE_VISIT,prod.getVisit());
        cv.put(PLACE_ID,prod.getId());
        cv.put(PLACE_LATITUDE,prod.getLatitude());
        cv.put(PLACE_LONGITUDE,prod.getLongitude());
        cv.put(PLACE_DATE,prod.getDate());
        int update = db.update(PLACES_TABLE, cv, PLACE_ID + "=" + prod.getId(), null);

     return update;
    }






    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
