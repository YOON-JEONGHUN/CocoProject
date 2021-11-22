# CocoProject
package com.mega.test05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {
EditText etListAdd;
Button btnListAdd;
ListView listV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        setTitle("리스트뷰 테스트");

        etListAdd = findViewById(R.id.etListAdd);
        btnListAdd = findViewById(R.id.btnListAdd);

        ArrayList<String> list = new ArrayList<>();

        list.add("히어로즈");
        list.add("24시");
        list.add("로스트");
        list.add("빅뱅이론");

        ListView listV = findViewById(R.id.listV);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        listV.setAdapter(adapter);

        btnListAdd.setOnClickListener(v -> {
            String data = etListAdd.getText().toString();
            list.add(data);
            adapter.notifyDataSetChanged();
        });
    }

}




// -------------------------- 조건에 맞는 값 set Text--------------------------------
        stRegion2 = findViewById(R.id.stRegion2);
        dpRegion2 = findViewById(R.id.dpRegion2);
        stList2 = findViewById(R.id.stList2);

        stRegion2.setText(stRegion);
        dpRegion2.setText(dpRegion);
        stList2.setText(stList);

//-------------------------- 버튼 클릭시, 전 화면으로 가기---------------------------------------------------
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            Intent intentBack = new Intent(BookTrain.this, BookMain.class);
            startActivity(intentBack);
        });



  Toast.makeText(getApplicationContext(), "출발역과 도착역이 동일합니다. 다시 선택해주세요.", Toast.LENGTH_SHORT).show();
=====================================================

  Intent intent = new Intent(BookMain.this, BookTrain.class);
                stRegion = findViewById(R.id.stRegion);
                dpRegion = findViewById(R.id.dpRegion);
                String stRegionNext = stRegion.getText().toString();
                String dpRegionNext = dpRegion.getText().toString();
                String sumPersonNext = sumPerson.getText().toString();
                intent.putExtra("stRegionNext", stRegionNext);
                intent.putExtra("dpRegionNext", dpRegionNext);
                intent.putExtra("sumPersonNext", sumPersonNext);
                intent.putExtra("dayNext", selectDay);
                startActivity(intent);
  ==========================================================================================


package com.app.mobile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyGridAdapter extends BaseAdapter {

    Context context;

    MyGridAdapter(Context context1) {
        context = context1;
    }

    int[] posterId = {
            R.drawable.mov01,
            R.drawable.mov02,
            R.drawable.mov03,
            R.drawable.mov04,
            R.drawable.mov05,
            R.drawable.mov06,
            R.drawable.mov07,
            R.drawable.mov08,
            R.drawable.mov09
    };

    @Override
    public int getCount() {
        return posterId.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //gridView에 들어갈 하나하나의 layout을 만들어주는 메서드!
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //gridView에 들어갈 이미지뷰 객체를 만들어주어야 한다.
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
        imageView.setPadding(5, 5, 5, 5);
        // Button button = new Button(context);
        imageView.setImageResource(posterId[i]);
        return imageView;
    }
//기본 어탭터의 기능들을 내가 임의로 재정의해서 사용가능하다.


}

