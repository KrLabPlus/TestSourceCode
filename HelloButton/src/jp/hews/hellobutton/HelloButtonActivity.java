package jp.hews.hellobutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class HelloButtonActivity extends Activity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hello_button);
		
		/* ボタンを取り出して、リスナーを登録する */
        Button b = (Button)findViewById(R.id.button_id);
             
        /* リスナークラスを作って登録する　*/
        ClickListener listener = new ClickListener();
        b.setOnClickListener(listener);
	}

			
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hello_button, menu);
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
}

class ClickListener implements OnClickListener {
	public void onClick(View v) {
		Button b = (Button)v;
		b.setText("押したね");
	}
};