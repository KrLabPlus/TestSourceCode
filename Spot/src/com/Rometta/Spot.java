package com.Rometta;

import com.Rometta.R.id;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Spot extends Activity {

	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.spot_main);
	 
	 EditText edit_text = (EditText) findViewById(id.editText1);
     // エディットテキストのテキストを設定します
     //editText.setText("テスト");
     // エディットテキストのテキストを全選択します
     edit_text.selectAll();
     // エディットテキストのテキストを取得します
     String text = edit_text.getText().toString();
     Toast.makeText(this, text, Toast.LENGTH_LONG);
	 }

	 Boolean _first = true;

	 private accordionSet _as1;
	 private accordionSet _as2;
	 private accordionSet _as3;
	 private accordionSet _as4;
	 private accordionSet _as5;
	 
	 @Override
	 public void onWindowFocusChanged(boolean hasFocus) {
	 if (_first) {
	 _first = false;
	 _as1 = new accordionSet((LinearLayout)findViewById(id.btn1), (LinearLayout)findViewById(id.content1), (LinearLayout)findViewById(id.content2), (LinearLayout)findViewById(id.content3));
	 _as2 = new accordionSet((LinearLayout)findViewById(id.btn2));
	 _as3 = new accordionSet((LinearLayout)findViewById(id.btn3));
	 _as4 = new accordionSet((LinearLayout)findViewById(id.btn4));
	 _as5 = new accordionSet((LinearLayout)findViewById(id.btn5), (LinearLayout)findViewById(id.content01), (LinearLayout)findViewById(id.content02));
     
	 }
	 super.onWindowFocusChanged(hasFocus);
	 }
	 
	 @Override
	 protected void onDestroy() {
	 if (!_first) {
	 _as1.deleteAccordion();
	 _as2.deleteAccordion();
	 _as3.deleteAccordion();
	 _as4.deleteAccordion();
	 _as5.deleteAccordion();
	 }
	 super.onDestroy();
	 }


public class accordionSet {

 private LinearLayout _btn;
 private LinearLayout _content01;
 private LinearLayout _content02;
 private LinearLayout _content1;
 private LinearLayout _content2;
 private LinearLayout _content3;
 private Handler _handler0;
 private Handler _handler;
 private float _height01;
 private float _height02;
 private float _height;
 private float current01 = 0.0f;
 private float current02 = 0.0f;
 private float current = 0.0f;
 private Thread _thread0;
 private Thread _thread;
 private String _bound0 = "close";
 private String _bound = "close";
 private int _startTime0;
 private int _startTime;
 private DecelerateInterpolator mInterpolator0 = new DecelerateInterpolator();
 private DecelerateInterpolator mInterpolator = new DecelerateInterpolator();
 private int easeTime = 400;
 
 public accordionSet(LinearLayout btn){
  _btn = btn;
 }

//詳細設定

public accordionSet(LinearLayout btn, LinearLayout content01, LinearLayout content02) {
	 _btn = btn;
	 _content01 = content01;
	 _content02 = content02;
	 _handler0 = new Handler();
	 _height01 = _content01.getHeight();
	 _height02 = _content02.getHeight();
	 mInterpolator0 = new DecelerateInterpolator();
	 _content01.setLayoutParams(new LinearLayout.LayoutParams(
	 LayoutParams.MATCH_PARENT, 0));
	 _content02.setLayoutParams(new LinearLayout.LayoutParams(
			 LayoutParams.MATCH_PARENT, 0));
	 _btn.setOnClickListener(new OnClickListener() {
    @Override
	 public void onClick(View v) {
	 _startTime0 = (int)System.currentTimeMillis();
	 if (_bound0.equals("open")) {
	 _bound0 = "close";
	 } else {
	 _bound0 = "open";
	 }
	 if (_thread0 == null || !_thread0.isAlive()) {
	 _thread0 = null;
	 makeThread0();
	 _thread0.start();
	 }
	 }
	 });

}

 
 //完了したアコーディオンリスト

public accordionSet(LinearLayout btn, LinearLayout content1, LinearLayout content2, LinearLayout content3) {
 _btn = btn;
 _content1 = content1;
 _content2 = content2;
 _content3 = content3;
 _handler = new Handler();
 _height = _content1.getHeight();
 mInterpolator = new DecelerateInterpolator();
 _content1.setLayoutParams(new LinearLayout.LayoutParams(
 LayoutParams.MATCH_PARENT, 0));
 _content2.setLayoutParams(new LinearLayout.LayoutParams(
		 LayoutParams.MATCH_PARENT, 0));
 _content3.setLayoutParams(new LinearLayout.LayoutParams(
		 LayoutParams.MATCH_PARENT, 0));
 _btn.setOnClickListener(new OnClickListener() {
	 @Override
 public void onClick(View v) {
 _startTime = (int)System.currentTimeMillis();
 if (_bound.equals("open")) {
 _bound = "close";
 } else {
 _bound = "open";
 }
 if (_thread == null || !_thread.isAlive()) {
 _thread = null;
 makeThread();
 _thread.start();
 }

 }
 });
 }
 

 

 private void makeThread0() {
 _thread0 = new Thread(new Runnable() {
 public void run() {
 while (easeTime > (int)System.currentTimeMillis() - _startTime0) {
 int diff = (int)System.currentTimeMillis() - _startTime0;
 if (_bound0.equals("open")) {
 current01 = _height01 * mInterpolator0.getInterpolation((float)diff/(float)easeTime);
 current02 = _height02 * mInterpolator0.getInterpolation((float)diff/(float)easeTime);
 } else {
 current01 = _height01-_height01 * mInterpolator0.getInterpolation((float)diff/(float)easeTime);
 current02 = _height02-_height02 * mInterpolator0.getInterpolation((float)diff/(float)easeTime);
 }
 threadFunc0();
 }
 }
 });
 }
 private void makeThread() {
	 _thread = new Thread(new Runnable() {
	 public void run() {
	 while (easeTime > (int)System.currentTimeMillis() - _startTime) {
	 int diff = (int)System.currentTimeMillis() - _startTime;
	 if (_bound.equals("open")) {
	 current = _height * mInterpolator.getInterpolation((float)diff/(float)easeTime);
	 } else {
	 current = _height-_height * mInterpolator.getInterpolation((float)diff/(float)easeTime);
	 }
	 threadFunc();
	 }
	 }
	 });
	 }

 private void threadFunc0() {
 _handler0.post(new Runnable() {

public void run() {
 _content01.setLayoutParams(new LinearLayout.LayoutParams(
 LinearLayout.LayoutParams.MATCH_PARENT, (int) current01));
 _content02.setLayoutParams(new LinearLayout.LayoutParams(
		 LinearLayout.LayoutParams.MATCH_PARENT, (int) current02));
 }
 });
 try {
 Thread.sleep(1);
 } catch (InterruptedException e) {
 }
 }

 private void threadFunc() {
 _handler.post(new Runnable() {

public void run() {
 _content1.setLayoutParams(new LinearLayout.LayoutParams(
 LinearLayout.LayoutParams.MATCH_PARENT, (int) current));
 _content2.setLayoutParams(new LinearLayout.LayoutParams(
		 LinearLayout.LayoutParams.MATCH_PARENT, (int) current));
 _content3.setLayoutParams(new LinearLayout.LayoutParams(
		 LinearLayout.LayoutParams.MATCH_PARENT, (int) current));
 }
 });
 try {
 Thread.sleep(1);
 } catch (InterruptedException e) {
 }
 }
 public void deleteAccordion() {
 _btn.setOnClickListener(null);
 _btn = null;
 _content01 = null;
 _content02 = null;
 _content1 = null;
 _content2 = null;
 _content3 = null;
 }
}


}
