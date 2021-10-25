package com.pro3.coco;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class InsertDBActivity extends AppCompatActivity {

    EditText etTrainNo, etStList, etStDate, etDpDate, etPerson, etStRegion, etDpRegion, etGenPrice, etSpcPrice;
    Button btnInit, btnInsert, btnSelect, btnDelete, btnUpdate;
    ListView etTrainNoResult;
    InsertDB insertDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_dbactivity);
        getSupportActionBar().hide();

        etTrainNo = findViewById(R.id.trainNo);
        etStList = findViewById(R.id.stList);
        etStDate = findViewById(R.id.stDate);
        etDpDate = findViewById(R.id.dpDate);
        etPerson = findViewById(R.id.person);
        etStRegion = findViewById(R.id.stRegion);
        etDpRegion = findViewById(R.id.dpRegion);
        etGenPrice = findViewById(R.id.genPrice);
        etSpcPrice = findViewById(R.id.spcPrice);

        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);

        etTrainNoResult = findViewById(R.id.trainNoResult);

        insertDBHelper = new InsertDB(this);

        btnInit.setOnClickListener(v -> {
            SQLiteDatabase sqlDB = insertDBHelper.getWritableDatabase();
            insertDBHelper.onUpgrade(sqlDB, 1, 2);
            Log.d("sqlite3DDL", "DDL초기화@@@@");
            sqlDB.close();
            Log.d("sqlite3DDL", "데이터베이스 closed@@@");
        });// btnInit

        btnInsert.setOnClickListener(v -> {
            SQLiteDatabase sqlDB = insertDBHelper.getWritableDatabase();
            String trainNo = etTrainNo.getText().toString();
            String stList = etStList.getText().toString();
            String stDate = etStDate.getText().toString();
            String dpDate = etDpDate.getText().toString();
            String person = etPerson.getText().toString();
            String stRegion = etStRegion.getText().toString();
            String dpRegion = etDpRegion.getText().toString();
            String genPrice = etGenPrice.getText().toString();
            String spcPrice = etSpcPrice.getText().toString();
            sqlDB.execSQL("insert into trainTBL values ('" + trainNo + "', '" + stList + "', '" + stDate + "', '" + dpDate + "', '" + person + "', '" + stRegion + "', '" + dpRegion + "', '" + genPrice + "', '" + spcPrice + "');");
            Log.d("sqlite3DML", "데이터삽입 성공@@@@");
            sqlDB.close();
        });// btnInsert


       /* btnSelect.setOnClickListener(v -> {
            SQLiteDatabase sqlDB = myDBHelper.getWritableDatabase();
            String TrainNo = etTrainNo.getText().toString();
            String sql = "SELECT * FROM trainTBL WHERE trainNo = '" + trainNo + "'";
            Cursor cursor = sqlDB.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                String result = cursor.getString(0) + " " + cursor.getString(1);
                Log.d("sqlite3DML", result);
                etResult.setText(cursor.getString(0));
                etbbbbbbbResult.setText(cursor.getString(1));
            }
            cursor.close();
            sqlDB.close();
        });// btnSelect*/

        // 리스트 형태 출력


        btnSelect.setOnClickListener(v -> {
            SQLiteDatabase sqlDB = insertDBHelper.getWritableDatabase();
            String sql = "SELECT * FROM trainTBL";
            Cursor cursor = sqlDB.rawQuery(sql, null);

            ArrayList trainList = new ArrayList<>();

            while (cursor.moveToNext()) {

                trainList.add(cursor.getInt(0));
                trainList.add(cursor.getString(2));
                trainList.add(cursor.getString(3));
                trainList.add(cursor.getInt(7));
                trainList.add(cursor.getInt(8));

            }
            ArrayAdapter trainListAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.trainlist, trainList);

            etTrainNoResult.setAdapter(trainListAdapter);


            cursor.close();
            sqlDB.close();

        });// btnSelect



        btnDelete.setOnClickListener(v -> {
            SQLiteDatabase sqlDB = insertDBHelper.getWritableDatabase();
            String trainNo = etTrainNo.getText().toString();
            sqlDB.execSQL("DELETE FROM trainTBL WHERE trainNo = '" + trainNo + "' ");
            Log.d("sqlite3DML", "데이터삭제 성공@@@@");
            sqlDB.close();
        });// btnDelete

        btnUpdate.setOnClickListener(v -> {
            SQLiteDatabase sqlDB = insertDBHelper.getWritableDatabase();
            String trainNo = etTrainNo.getText().toString();
            String stDate = etStDate.getText().toString();
            sqlDB.execSQL("UPDATE trainTBL SET gNumber = '" + stDate + "' WHERE trainNo = '" + trainNo + "' ");
            Log.d("sqlite3DML", "데이터수정 성공@@@@");
            sqlDB.close();
        });// btnUpdate
    }
}