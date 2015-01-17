package com.Rometta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.Rometta.TimeSchedule.RomettaTimeSchedule_Result;
import com.Rometta.Server.HttpPostHandler;
import com.Rometta.Server.HttpPostTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class RomettaSearchStation extends Activity {

	Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, dmp;
	ListView lv;
	TextView tv;
	String bn, parentName = "_parent", childName = "_child", direction = null, st = null;
	String[] parent, child, strAry, strAry2, strAry3;
	int count = 0, j = 0;
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	SimpleAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rometta_search_station);

        bt1 = (Button)findViewById(R.id.button_A);
        bt2 = (Button)findViewById(R.id.button_ka);
        bt3 = (Button)findViewById(R.id.button_sa);
        bt4 = (Button)findViewById(R.id.button_ta);
        bt5 = (Button)findViewById(R.id.button_na);
        bt6 = (Button)findViewById(R.id.button_ha);
        bt7 = (Button)findViewById(R.id.button_ma);
        bt8 = (Button)findViewById(R.id.button_ya);
        bt9 = (Button)findViewById(R.id.button_ra);
        bt0 = (Button)findViewById(R.id.button_wa);

        if(getIntent().getStringExtra("keyword") != null){
        	direction = getIntent().getStringExtra("keyword");
        }

        adapter = new SimpleAdapter(
       		this,
       		list,
       		android.R.layout.simple_list_item_2,
       		new String[] {"Parent", "Child"},
       		new int[] {android.R.id.text1, android.R.id.text2});

        lv = (ListView) findViewById(R.id.listView1);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View vi, int position, long id) {
            	if(direction == null){
            		Intent it = new Intent();
            		if(bn != "WA"){
            			it.putExtra("keyword", parent[position]);

            			setResult(RESULT_OK, it);
            			finish();
            		}
            	}else {
            		if(bn != "WA"){
            			// 乗換の結果画面レイアウトが出来次第デバグ
            			// サーバが現在利用不可の為、値を予め与える
            			st = parent[position];
            			exec_post(st, direction);

            		}
            	}
            }
        });

        bt1.setOnClickListener(new AkaClickListener());
        bt2.setOnClickListener(new AkaClickListener());
        bt3.setOnClickListener(new AkaClickListener());
        bt4.setOnClickListener(new AkaClickListener());
        bt5.setOnClickListener(new AkaClickListener());
        bt6.setOnClickListener(new AkaClickListener());
        bt7.setOnClickListener(new AkaClickListener());
        bt8.setOnClickListener(new AkaClickListener());
        bt9.setOnClickListener(new AkaClickListener());
        bt0.setOnClickListener(new AkaClickListener());

	}

    class AkaClickListener implements OnClickListener{
    	public void onClick(View v){
    		if(dmp != null && dmp != (Button)v){
    			dmp.setBackgroundResource(R.drawable.search_station_button_normal);
    		}
    		if(v == bt1){
    			bt1.setBackgroundResource(R.drawable.search_station_button_pressed);
    			bn = "A";
    		} else if(v == bt2){
    			bt2.setBackgroundResource(R.drawable.search_station_button_pressed);
    			bn = "KA";
    		} else if(v == bt3){
    			bt3.setBackgroundResource(R.drawable.search_station_button_pressed);
    			bn = "SA";
    		} else if(v == bt4){
    			bt4.setBackgroundResource(R.drawable.search_station_button_pressed);
    			bn = "TA";
    		} else if(v == bt5){
    			bt5.setBackgroundResource(R.drawable.search_station_button_pressed);
    			bn = "NA";
    		} else if(v == bt6){
    			bt6.setBackgroundResource(R.drawable.search_station_button_pressed);
    			bn = "HA";
    		} else if(v == bt7){
    			bt7.setBackgroundResource(R.drawable.search_station_button_pressed);
    			bn = "MA";
    		} else if(v == bt8){
    			bt8.setBackgroundResource(R.drawable.search_station_button_pressed);
    			bn = "YA";
    		} else if(v == bt9){
    			bt9.setBackgroundResource(R.drawable.search_station_button_pressed);
    			bn = "RA";
    		} else {
    			bt0.setBackgroundResource(R.drawable.search_station_button_pressed);
    			bn = "WA";
    		}
    		dmp = (Button) v;

    		int parentId = getResources().getIdentifier(bn + parentName, "array", getPackageName());
        	int childId = getResources().getIdentifier(bn + childName, "array", getPackageName());

        	parent = getResources().getStringArray(parentId);
        	child = getResources().getStringArray(childId);

    		if(count != 0){
        		for(int i = count - 1; i >= 0; i--){
        			list.remove(i);
        		}
    		}

        	count = parent.length;

        	for (int i = 0; i < parent.length; i++) {
        		Map<String, String> conMap = new HashMap<String, String>();
        		conMap.put("Parent", parent[i]);
        		conMap.put("Child", child[i]);
        		list.add(conMap);
        	}
    		adapter.notifyDataSetChanged();
    	}
	}
    public void exec_post(String station, String dic) {

	    // 非同期タスクを定義
	    HttpPostTask task = new HttpPostTask(
	      this,
	      "http://krlab.info.kochi-tech.ac.jp/Krlabp/test_time.php?Name=" + station + "&Direction=" + dic,
	      //アクセスする箇所を

	      // タスク完了時に呼ばれるUIのハンドラ
	      new HttpPostHandler(){
	        @Override
	        public void onPostCompleted(String response) {
	        	strAry = response.split(",");

	        	String[] weekday = new String[(strAry.length-1)/6];
	        	int k = 0, l = 0, week = 0, holi = 0;

	        	for(int i = 1; i < strAry.length; i+=6){

	        		strAry2 = strAry[i+5].split("=");
	        		weekday[j] = strAry2[1];
	        		j++;
	        	}

	        	for(int i = 0; i < (strAry.length-1)/6; i++){
	        		if(weekday[i].equals("1")){
	        			week++;
	        		} else {
	        			holi++;
	        		}
	        	}

	        	j = 0;

	        	String[] week_timelist = new String[week];
	        	String[] holi_timelist = new String[holi];
	        	String[] week_directionlist = new String[week];
	        	String[] holi_directionlist = new String[holi];
	        	String[] week_friday = new String[week];
	        	String[] holi_friday = new String[holi];
	        	String[] week_heartram = new String[week];
	        	String[] holi_heartram = new String[holi];

	        	for(int i = 1; i < strAry.length; i+=6){
	        		strAry2 = strAry[i].split("=");
	        		strAry3 = strAry2[1].split(":");

	        		if(weekday[j].equals("1")){
	        			week_timelist[k] = strAry3[0] + ":" + strAry3[1];

	        			strAry2 = strAry[i+1].split("=");
		        		week_directionlist[k] = strAry2[1];

		        		strAry2 = strAry[i+2].split("=");
		        		week_friday[k] = strAry2[1];

		        		strAry2 = strAry[i+4].split("=");
		        		week_heartram[k] = strAry2[1];

		        		k++;
	        		} else {
	        			holi_timelist[l] = strAry3[0] + ":" + strAry3[1];

	        			strAry2 = strAry[i+1].split("=");
		        		holi_directionlist[l] = strAry2[1];

		        		strAry2 = strAry[i+2].split("=");
		        		holi_friday[l] = strAry2[1];

		        		strAry2 = strAry[i+4].split("=");
		        		holi_heartram[l] = strAry2[1];

		        		l++;
	        		}

	        		j++;
	        	}

	        	j = 0;

	        	Intent it = new Intent(getApplicationContext(), RomettaTimeSchedule_Result.class);

	        	it.putExtra("STATION", st);
	        	it.putExtra("DIRECTION", direction);
				it.putExtra("WEEK_TIME_LIST", week_timelist);
				it.putExtra("WEEK_DIRECTION_LIST", week_directionlist);
				it.putExtra("HOLI_TIME_LIST", holi_timelist);
				it.putExtra("HOLI_DIRECTION_LIST", holi_directionlist);
				it.putExtra("WEEK_FRIDAY_LIST", week_friday);
				it.putExtra("HOLI_FRIDAY_LIST", holi_friday);
				it.putExtra("WEEK_HEARTRAM_LIST", week_heartram);
				it.putExtra("HOLI_HEARTRAM_LIST", holi_heartram);

				startActivity(it);
	        }
	        @Override
	        public void onPostFailed(String response) {
	          Toast.makeText(
	            getApplicationContext(),
	            "エラーが発生しました。",
	            Toast.LENGTH_LONG
	          ).show();
	        }
	      }
	    );
	    task.addPostParam( "post_1", "ユーザID" );
	    task.addPostParam( "post_2", "パスワード" );

	    // タスクを開始
	    task.execute();
	  }
}