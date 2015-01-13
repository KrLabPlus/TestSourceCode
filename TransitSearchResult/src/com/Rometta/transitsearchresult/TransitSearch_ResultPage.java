package com.Rometta.transitsearchresult;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class TransitSearch_ResultPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transit_search_result_page);
		

        // リソースに準備した画像ファイルからBitmapを作成しておく
        Bitmap image1;
        Bitmap image2;
        Bitmap image3;
        Bitmap image4;
        Bitmap image5;
        image1 = BitmapFactory.decodeResource(getResources(), R.drawable.one);
        image2 = BitmapFactory.decodeResource(getResources(), R.drawable.two);
        image3 = BitmapFactory.decodeResource(getResources(), R.drawable.three);
        image4 = BitmapFactory.decodeResource(getResources(), R.drawable.four);
        image5 = BitmapFactory.decodeResource(getResources(), R.drawable.five);
        
 
		
        
     // データの作成
        List<CustomData> objects = new ArrayList<CustomData>();
        CustomData item1 = new CustomData();
        item1.setImagaData(image1);
        item1.setTextData("結果1");
        item1.setTextData2("詳細1");
 
        CustomData item2 = new CustomData();
        item2.setImagaData(image2);
        item2.setTextData("結果2");
        item2.setTextData2("詳細2");
 
        CustomData item3 = new CustomData();
        item3.setImagaData(image3);
        item3.setTextData("結果3");
        item3.setTextData2("詳細3");
        
        CustomData item4= new CustomData();
        item4.setImagaData(image4);
        item4.setTextData("結果4");
        item4.setTextData2("詳細4");
        
        CustomData item5 = new CustomData();
        item5.setImagaData(image5);
        item5.setTextData("結果5");
        item5.setTextData2("詳細5");
        
        
        
        objects.add(item1);
        objects.add(item2);
        objects.add(item3);
        objects.add(item4);
        objects.add(item5);

        CustomAdapter customAdapter = new CustomAdapter(this, 0, objects);
        
        // リストビューにデータを設定
        ListView listView1 = (ListView)findViewById(R.id.result_list);
        listView1.setAdapter(customAdapter);
	}
}