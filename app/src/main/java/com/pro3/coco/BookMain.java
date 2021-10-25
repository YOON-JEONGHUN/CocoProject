package com.pro3.coco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;


public class BookMain extends AppCompatActivity {

    ImageView plus1, plus2, plus3, plus4, minus1, minus2, minus3, minus4;
    TextView result1, result2, result3, result4, sumPerson;
    Button searchStationbtn, trainSearchbtn;
    EditText searchStation;
    CalendarView calendarView2;
    LinearLayout personLayout, searchLayout;
    TextView stRegion, dpRegion;

    // 총인원 수 누적
    int sum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.book_main);
//--------------------------------------- Tab ------------------------------------
        TabHost tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        // Tab 나누기(편도, 왕복)
        TabHost.TabSpec tabSpecLine1 = tabHost.newTabSpec("Line1").setIndicator("편도");
        tabSpecLine1.setContent(R.id.tabLine1);
        tabHost.addTab(tabSpecLine1);
        TabHost.TabSpec tabSpecLine2 = tabHost.newTabSpec("Line2").setIndicator("왕복");
        tabSpecLine2.setContent(R.id.tabLine2);
        tabHost.addTab(tabSpecLine2);

        tabHost.setCurrentTab(0);
//---------------------------------------------------------------------------
        // 예약인원 수 +, -
        plus1 = findViewById(R.id.plus1);
        plus2 = findViewById(R.id.plus2);
        plus3 = findViewById(R.id.plus3);
        plus4 = findViewById(R.id.plus4);
        minus1 = findViewById(R.id.minus1);
        minus2 = findViewById(R.id.minus2);
        minus3 = findViewById(R.id.minus3);
        minus4 = findViewById(R.id.minus4);

        // 인원 수 각 결과값
        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);
        result3 = findViewById(R.id.result3);
        result4 = findViewById(R.id.result4);

        // 총인원 수 결과값
        sumPerson = findViewById(R.id.sumPerson);

        // 역 검색
        searchStation = findViewById(R.id.searchStation);
        searchStationbtn = findViewById(R.id.searchStationbtn);

        // 캘린더 뷰, person레이아웃
        calendarView2 = findViewById(R.id.calendarView2);
        personLayout = findViewById(R.id.personLayout);

        // 출발역, 도착열 TextView
        stRegion = findViewById(R.id.stRegion);
        dpRegion = findViewById(R.id.dpRegion);

        // searchLayout(초기세팅은 달력과 인원수만 보이게 하고 검색창은 숨김)
        searchLayout = findViewById(R.id.searchLayout);
        searchLayout.setVisibility(View.INVISIBLE);

        // 열차조회 btn
        trainSearchbtn = findViewById(R.id.trainSearchbtn);

//----------------------------------- list + adapter ----------------------
        // station 리스트
        ArrayList<String> station = new ArrayList<>();
        station.add("서울역");
        station.add("용산역");
        station.add("수원역");
        station.add("부산역");

        ListView listView = findViewById(R.id.stationList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, station);
        listView.setAdapter(adapter);
        // 초기설정은 안보이도록 설정
        listView.setVisibility(View.GONE);

//--------------------------------------- 출발역 event -----------------------------------------
        stRegion.setOnClickListener(v -> {
            searchLayout.setVisibility(View.VISIBLE);
            personLayout.setVisibility(View.GONE);
            calendarView2.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

            // item click event
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (dpRegion.getText().equals(station.get(position))) {
                        Toast.makeText(getApplicationContext(), "출발역과 도착역이 동일합니다. 다시 선택해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        stRegion.setText(station.get(position));
                        Toast.makeText(getApplicationContext(), "출발역은 " + station.get(position) + "입니다. 도착역을 선택해주세요", Toast.LENGTH_SHORT).show();
                        personLayout.setVisibility(View.VISIBLE);
                        calendarView2.setVisibility(View.VISIBLE);
                        searchLayout.setVisibility(View.INVISIBLE);
                        listView.setVisibility(View.GONE);
                    }
                }
            });
        });

//--------------------------------------- 도착역 event -----------------------------------------

        dpRegion.setOnClickListener(v -> {
            searchLayout.setVisibility(View.VISIBLE);
            personLayout.setVisibility(View.GONE);
            calendarView2.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (stRegion.getText().equals(station.get(position))) {
                        Toast.makeText(getApplicationContext(), "출발역과 도착역이 동일합니다. 다시 선택해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        dpRegion.setText(station.get(position));
                   /* station.remove(position); 지우고 업데이트
                    adapter.notifyDataSetChanged();*/
                        Toast.makeText(getApplicationContext(), "도착역은 " + station.get(position) + "입니다.", Toast.LENGTH_SHORT).show();
                        personLayout.setVisibility(View.VISIBLE);
                        calendarView2.setVisibility(View.VISIBLE);
                        searchLayout.setVisibility(View.INVISIBLE);
                        listView.setVisibility(View.GONE);
                    }
                }
            });
        });
// ------------------------- 검색 버튼 ---------------------------------------------

        searchStationbtn.setOnClickListener(v ->

        {
            String search = searchStation.getText().toString();
            // 정규식에 맞는지 검사(한글완성형 -> true)
            Boolean check = Pattern.matches("^[가-힣]*$", search);

            if (check) {
                // 레이아웃 교체( 달력, 인원 선택 사라지고 검색이 보여짐)
                personLayout.setVisibility(View.GONE);
                calendarView2.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), search + "를 검색합니다.", Toast.LENGTH_SHORT).show();
                searchStation.setText("");
            } else {
                Toast.makeText(getApplicationContext(), "다시 입력해주세요", Toast.LENGTH_SHORT).show();
                searchStation.setText("");
            }
        });

 //---------------------------------- person button (+ , -) ---------------------------

        // 각 버튼 실행 값
        plusMethod(plus1, result1);
        plusMethod(plus2, result2);
        plusMethod(plus3, result3);
        plusMethod(plus4, result4);

        minusMethod(minus1, result1);
        minusMethod(minus2, result2);
        minusMethod(minus3, result3);
        minusMethod(minus4, result4);

// ------------------------------ calendar ---------------------------------

        long date = calendarView2.getDate();
        // 최소 날짜
        calendarView2.setMinDate(date);
        // 한달 뒤
        calendarView2.setMaxDate(date + 2629745999l);

        // 달력 클릭 event , 날짜 변경
        calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), "선택한 날짜는 " + (month+1) + "월 " + dayOfMonth + "일 입니다.", Toast.LENGTH_SHORT).show();
                String selectDay = (month + 1) + dayOfMonth + "";
            }
        });
        /*
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time1 = format1.format(date);
        Log.d("날짜입력받은 값", "선택된 날짜는 " + time1);
        */


// ----------------------------- trainSearchbtn(열차조회) -----------------------
        trainSearchbtn.setOnClickListener(v -> {
        if(sum == 0 ){
            Toast.makeText(getApplicationContext(), "예약인원을 선택하세요", Toast.LENGTH_SHORT).show();
        } else {

        }

        }); // trainSearchbtn end

//-----------------------------------------------------------------
    } // onCreate end


// ----------------------------- plus 기능 공통, 조건 +
    public void plusMethod(ImageView button, TextView textView) {
        button.setOnClickListener(v -> {
            // 최대 9개까지 선택 가능
            int result = Integer.parseInt(textView.getText().toString());
            if (result < 9 && sum < 9) {
                result = result + 1;
                textView.setText(result + "");
                sum = sum + 1;
                sumPerson.setText("총 " + sum + " 명");
                button.setImageResource(R.drawable.plus);
            } else if (result == 9) {
                textView.setText("9");
                button.setImageResource(R.drawable.plusgray);
            }
        }); // click
    } // plusMethod

//------------------------------- minus 기능 공통, 조건 -
    public void minusMethod(ImageView button, TextView textView) {
        button.setOnClickListener(v -> {
            int result = Integer.parseInt(textView.getText().toString());
            if (result >= 1 && sum < 10) {
                result = result - 1;
                textView.setText(result + "");
                sum = sum - 1;
                sumPerson.setText("총 " + sum + " 명");
                button.setImageResource(R.drawable.minus);
            } else if (result == 0) {
                button.setImageResource(R.drawable.minusgray);
                textView.setText("0");
            }
        }); // click
    } // minusMethod

}


/*  plus1.setOnClickListener(v -> {
            int res1 = Integer.parseInt(result1.getText().toString());
            if (res1 < 9) {
                res1 = res1 + 1;
                result1.setText(res1 + "");
                sum = sum + 1;
                sumPerson.setText("총 " + sum + " 명");
            } else if (res1 == 9) {
                result1.setText("9");
            }
        });

        minus1.setOnClickListener(v -> {
            int res1 = Integer.parseInt(result1.getText().toString());
            if (res1 >= 1) {
                res1 = res1 - 1;
                result1.setText(res1 + "");
                sum = sum - 1;
                sumPerson.setText("총 " + sum + " 명");
            } else if (res1 == 0) {
                result1.setText("0");
            }
        });

        plus2.setOnClickListener(v -> {
            int res1 = Integer.parseInt(result2.getText().toString());
            if (res1 < 9) {
                res1 = res1 + 1;
                result2.setText(res1 + "");
                sum = sum + 1;
                sumPerson.setText("총 " + sum + " 명");
            } else if (res1 == 9) {
                result2.setText("9");
            }
        });

        minus2.setOnClickListener(v -> {
            int res1 = Integer.parseInt(result2.getText().toString());
            if (res1 >= 1) {
                res1 = res1 - 1;
                result2.setText(res1 + "");
                sum = sum - 1;
                sumPerson.setText("총 " + sum + " 명");
            } else if (res1 == 0) {
                result2.setText("0");
            }
        });

        plus3.setOnClickListener(v -> {
            int res1 = Integer.parseInt(result3.getText().toString());
            if (res1 < 9) {
                res1 = res1 + 1;
                result3.setText(res1 + "");
                sum = sum + 1;
                sumPerson.setText("총 " + sum + " 명");
            } else if (res1 == 9) {
                result3.setText("9");
            }
        });

        minus3.setOnClickListener(v -> {
            int res1 = Integer.parseInt(result3.getText().toString());
            if (res1 >= 1) {
                res1 = res1 - 1;
                result3.setText(res1 + "");
                sum = sum - 1;
                sumPerson.setText("총 " + sum + " 명");
            } else if (res1 == 0) {
                result3.setText("0");
            }
        });

        plus2.setOnClickListener(v -> {
            int res1 = Integer.parseInt(result2.getText().toString());
            if (res1 < 9) {
                res1 = res1 + 1;
                result2.setText(res1 + "");
                sum = sum + 1;
                sumPerson.setText("총 " + sum + " 명");
            } else if (res1 == 9) {
                result2.setText("9");
            }
        });

        minus4.setOnClickListener(v -> {
            int res1 = Integer.parseInt(result4.getText().toString());
            if (res1 >= 1) {
                res1 = res1 - 1;
                result4.setText(res1 + "");
                sum = sum - 1;
                sumPerson.setText("총 " + sum + " 명");
            } else if (res1 == 0) {
                result4.setText("0");
            }
        });*/