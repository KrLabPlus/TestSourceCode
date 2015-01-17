package com.example.test;

import android.app.Activity;
import android.app.AlertDialog;
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
	    builder.setTitle("クレジット");
	    builder.setMessage("ダイアログのメッセージ");
	    AlertDialog dialog = builder.create();
	    dialog.show();
	    return true;
	}	
		return false;
	}
}
