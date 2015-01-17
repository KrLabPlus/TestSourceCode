package com.example.test;



import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate((Integer) R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

        
		switch (item.getItemId()) {
		case R.id.menu_credit:
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setTitle("このアプリについて");
	    builder.setMessage("Rometta! ver 1.0.1" + "\n" +"現在このアプリケーションは最新です。" + "\n" +"\n" 
	    		+ "Rometta!はとさでん交通株式会社が運営する高知県の路面電車専用の乗り換えアプリケーションです。"
	    		+ "このアプリケーションは以下のAPIを使用しています。"+ "\n" + "\n" + "Google Maps API"
	    		+ "\n" + "ぐるなびAPI" + "\n" + "Yahoo! Open Local Platform");
	    AlertDialog dialog = builder.create();
	    dialog.show();
	    return true;
	}	
		return false;
	}
}
