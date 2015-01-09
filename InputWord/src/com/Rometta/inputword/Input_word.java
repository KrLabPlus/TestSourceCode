package com.Rometta.inputword;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Input_word extends Activity {

	//アイコン画像
	Button TopButton1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_word);
		// 各リソース取得
        TopButton1 = (Button)findViewById(R.id.button_A);
         //ボタンクリック時の動作を定義
      //  this.TopButton1.setOnClickListener(buttonOnClickListener);
        TopButton1.setEnabled(true);
        TopButton1.setPressed(true);
        TopButton1.setPressed(false); 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input_word, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	// アイコンクリック時のイベント
   // private Button.OnClickListener buttonOnClickListener = new Button.OnClickListener() {
    //	 public void onClick(View v) {
    	//      TopButton1.requestFocus(); // EditTextにフォーカスを移動
    	  //    TopButton1.setLayoutDirection(4); // 4文字目の後にカーソルを移動（最初の文字の前が0となる)
    	    //  String str = TopButton1.getText().toString();
    	   //   TopButton1.setLayoutDirection(str.length()); // 文字列の最後の位置にカーソルを移動
    //	 }
   // };
}
