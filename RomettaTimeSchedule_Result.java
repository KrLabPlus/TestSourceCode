package com.Rometta.TimeSchedule;

import java.util.ArrayList;
import java.util.List;

import com.Rometta.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class RomettaTimeSchedule_Result extends Activity implements OnClickListener {
	//与えられた値と仮定する
	String home, news, ki = "0";
	String[] weektime, weekdirection, holitime, holidirection, weekfriday, holifriday, weekheartram, holiheartram;
	//普通の路面電車=0,金曜限定=1, ハートラム=2として仮で扱ってま
	Intent it;
	Bitmap bitmap;

	private Button btn1;
	private Button btn2;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rometta_timeschedule_result);
        btn1 = (Button)findViewById(R.id.heijitu);
        btn2 = (Button)findViewById(R.id.yasumi);

        tv = (TextView)findViewById(R.id.train_houkou);

        it = getIntent();

        home = it.getStringExtra("STATION");
        weektime = it.getStringArrayExtra("WEEK_TIME_LIST");
        weekdirection = it.getStringArrayExtra("WEEK_DIRECTION_LIST");
        holitime = it.getStringArrayExtra("HOLI_TIME_LIST");
        holidirection = it.getStringArrayExtra("HOLI_DIRECTION_LIST");
        weekfriday = it.getStringArrayExtra("WEEK_FRIDAY_LIST");
        holifriday = it.getStringArrayExtra("HOLI_FRIDAY_LIST");
        weekheartram = it.getStringArrayExtra("WEEK_HEARTRAM_LIST");
        holiheartram = it.getStringArrayExtra("HOLI_HEARTRAM_LIST");
        news = it.getStringExtra("DIRECTION");

        if(news.equals("n")){
        	tv.setText("南北線 上り");
        } else if(news.equals("s")) {
        	tv.setText("南北線 下り");
        } else if(news.equals("e")) {
        	tv.setText("東西線 上り");
        } else {
        	tv.setText("東西線 下り");
        }


	    btn1.setOnClickListener(this);
	    btn2.setOnClickListener(this);

	    btn1.setBackgroundResource(R.drawable.timeschedule_button_pressed);

	    //駅名（乗り駅)
	    TextView textView = (TextView) findViewById(R.id.train_name);
	    textView.setText(home);

		ListView listView = (ListView) findViewById(R.id.listview1);

		// リスト
		List<RomettaTimeSchedule_ResultItemData> list = weekDay();
		RomettaTimeSchedule_CustomAdapter customAd = new RomettaTimeSchedule_CustomAdapter(this, list);
        listView.setAdapter(customAd);
	}

    public void onClick(View v) {
		ListView listView = (ListView) findViewById(R.id.listview1);
    	switch (v.getId()) {
    	case R.id.heijitu:
    		btn1.setBackgroundResource(R.drawable.timeschedule_button_pressed);
    		btn2.setBackgroundResource(R.drawable.timeschedule_button_normal);
			List<RomettaTimeSchedule_ResultItemData> list1 = weekDay();
			RomettaTimeSchedule_CustomAdapter customAd1 = new RomettaTimeSchedule_CustomAdapter(this, list1);
        	listView.setAdapter(customAd1);
        	break;
    	case R.id.yasumi:
    		btn1.setBackgroundResource(R.drawable.timeschedule_button_normal);
    		btn2.setBackgroundResource(R.drawable.timeschedule_button_pressed);
			List<RomettaTimeSchedule_ResultItemData> list2 = holiDay();
			RomettaTimeSchedule_CustomAdapter customAd2 = new RomettaTimeSchedule_CustomAdapter(this, list2);
        	listView.setAdapter(customAd2);
        	break;
    	}
    }


	//平日のときの処理
	private List<RomettaTimeSchedule_ResultItemData> weekDay(){
		List<RomettaTimeSchedule_ResultItemData> list = new ArrayList<RomettaTimeSchedule_ResultItemData>();
        for (int i = 0; i < weektime.length; i++) {
        	RomettaTimeSchedule_ResultItemData tempData = new RomettaTimeSchedule_ResultItemData();

            tempData.setText1(weektime[i]);
            tempData.setText2(weekdirection[i]+"行き");

            if(weekfriday[i].equals("1")){
            	bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timeschedule_friday);
            	bitmap = Bitmap.createScaledBitmap(bitmap, 40, 40, true);
                tempData.setColor1(bitmap);
            } else if(weekheartram[i].equals("1")){
            	bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timeschedule_heartrum);
            	bitmap = Bitmap.createScaledBitmap(bitmap, 40, 40, true);
                tempData.setColor1(bitmap);
            } else {
            	tempData.setText1(weektime[i] + "  ");
            }

            tempData.setId(i);
            list.add(tempData);
        }
		return list;
	}

	//休日のときの処理
	private List<RomettaTimeSchedule_ResultItemData> holiDay(){
		List<RomettaTimeSchedule_ResultItemData> list = new ArrayList<RomettaTimeSchedule_ResultItemData>();
        for (int i = 0; i < holitime.length; i++) {
        	RomettaTimeSchedule_ResultItemData tempData = new RomettaTimeSchedule_ResultItemData();

            tempData.setText1(holitime[i]);
            tempData.setText2(holidirection[i]+"行き");

            if(holifriday[i].equals("1")){
            	bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timeschedule_friday);
            	bitmap = Bitmap.createScaledBitmap(bitmap, 40, 40, true);
                tempData.setColor1(bitmap);
            } else if(holiheartram[i].equals("1")){
            	bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timeschedule_heartrum);
            	bitmap = Bitmap.createScaledBitmap(bitmap, 40, 40, true);
                tempData.setColor1(bitmap);
            } else {
            	tempData.setText1(holitime[i] +"  ");
            }

            tempData.setId(i);
            list.add(tempData);
        }
		return list;
	}
}