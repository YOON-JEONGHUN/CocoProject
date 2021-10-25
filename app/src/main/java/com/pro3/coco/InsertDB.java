package com.pro3.coco;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class InsertDB extends SQLiteOpenHelper {

    public InsertDB(@Nullable Context context) {
        super(context, "CocoDB", null, 1);
        Log.d("sqlite3DDL", "데이터베이스 생성@@@@");
    }

       @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE trainTBL (trainNo INTEGER, " +
                "stList TEXT, stDate TEXT, dpDate TEXT, person INTEGER, stRegion TEXT, " +
                "dpRegion TEXT, genPrice INTEGER, spcPrice INTEGER);");
        Log.d("sqlite3DML", "Create table@@@@");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS trainTBL");
        Log.d("sqlite3DDL", "Drop table@@@@");
        onCreate(db);
    }

    public TrainListItem getTrainList() {
       SQLiteDatabase sqlDB = getReadableDatabase();

        String sql = "SELECT trainNo, stDate, dpDate, genPrice, spcPrice FROM trainTBL";
        Cursor cursor = sqlDB.rawQuery(sql, null);

        TrainListItem trainList = new TrainListItem();

        while (cursor.moveToNext()) {

            trainList.setTrainNo(cursor.getInt(0));
            trainList.setStDate(cursor.getString(2));
            trainList.setDpDate(cursor.getString(3));
            trainList.setGenPrice(cursor.getInt(7));
            trainList.setSpcPrice(cursor.getInt(8));

        }
        cursor.close();
        sqlDB.close();
        return trainList;
    }

}