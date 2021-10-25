package com.pro3.coco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import java.util.ArrayList;


public class BookTrain extends AppCompatActivity {

    TextView trainNoList, stDateList, dpDateList;
    Button genPriceList, spcPriceList;
    InsertDB trainListSelect;
    ArrayList<TrainListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_train);
        getSupportActionBar().hide();


//-----------------------------------------------------------------------------
        trainListSelect = new InsertDB(this);

        SQLiteDatabase sqlDB = trainListSelect.getWritableDatabase();

        String sql = "SELECT trainNo, stDate, dpDate, genPrice, spcPrice FROM trainTBL";
        Cursor cursor = sqlDB.rawQuery(sql, null);


        while (cursor.moveToNext()) {

            TrainListItem trainListItem = new TrainListItem();
            trainListItem.setTrainNo(cursor.getInt(0));
            trainListItem.setStDate(cursor.getString(1));
            trainListItem.setDpDate(cursor.getString(2));
            trainListItem.setGenPrice(cursor.getInt(3));
            trainListItem.setSpcPrice(cursor.getInt(4));
            items.add(trainListItem);
        }

        cursor.close();
        sqlDB.close();


//--------------------------------------------------------------------------------
        // layout 지정(Main 안에 그리드를 넣을 layout)
        //  LinearLayout gridlayout = findViewById(R.id.gridLayout);

        // gridview(Main 안의 그리드 객체)
        GridView gridView = findViewById(R.id.gridView2);

        // View inflate(Main 에 넣을 xml layout 을 설정)
        //  View view = View.inflate(BookTrain.this, R.layout.trainlistgrid, null);

        TrainListAdapter trainListAdapter = new TrainListAdapter(this);
        //  gridlayout.addView(view);
        gridView.setAdapter(trainListAdapter);
//----------------------------------------------------


    }

    public class TrainListAdapter extends BaseAdapter {

        Context context;

        TrainListAdapter(Context context1) {
            context = context1;
        }

        @Override
        public int getCount() {

            return items.size();
        }

        @Override
        public TrainListItem getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            View inflateV = View.inflate(context, R.layout.trainlistgrid, null);

            trainNoList = inflateV.findViewById(R.id.trainNoListView);
            stDateList = inflateV.findViewById(R.id.stDateListView);
            dpDateList = inflateV.findViewById(R.id.dpDatelistView);
            genPriceList = inflateV.findViewById(R.id.genPricebtn);
            spcPriceList = inflateV.findViewById(R.id.spcPricebtn);

            //Log.d("데이터확인", "@@@@@@ 안나옴!!"+ items.get(i).getTrainNo());
            Log.d("데이터확인", "@@@@@@ 안나옴!!"+ items.get(i).getGenPrice());

               trainNoList.setText(items.get(i).getTrainNo()+ "");
                stDateList.setText(items.get(i).getStDate());
                dpDateList.setText(items.get(i).getDpDate());
                genPriceList.setText(items.get(i).getGenPrice() + "원");
                spcPriceList.setText(items.get(i).getSpcPrice()+ "원");

            return inflateV;
        }


    }

}