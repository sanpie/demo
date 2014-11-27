package com.example.app42sample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.shephertz.app42.paas.sdk.android.App42API;

public class MainActivity extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((TextView) findViewById(R.id.page_header)).setText("Main Activty");
		 App42API.initialize(
	                this,
	                "<843d02f836f34de72e195cd550223c41cc3e3b823d3813012103109765814caf>",
	                "<a1e0c480942473bc56aa95e778b997949f641df747d36cf5ac32ef155f07a5ef>");
	        App42API.setLoggedInUser("adarsh.agrawal862@gmail.com") ;
	        Util.registerWithApp42("638278271530 ");
		
	}

	public void onClick(View view) {
		Intent intent = new Intent(this, MessageActivity.class);
		startActivity(intent);

	}


	public void onStart() {
		super.onStart();

	}

	/*
	 * * This method is called when a Activty is stop disable all the events if
	 * occuring (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStop()
	 */
	public void onStop() {
		super.onStop();

	}

	/*
	 * This method is called when a Activty is finished or user press the back
	 * button (non-Javadoc)
	 * 
	 * @override method of superclass
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	public void onDestroy() {
		super.onDestroy();

	}

	/*
	 * called when this activity is restart again
	 * 
	 * @override method of superclass
	 */
	public void onReStart() {
		super.onRestart();

	}

	/*
	 * called when activity is paused
	 * 
	 * @override method of superclass (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	public void onPause() {
		super.onPause();
		unregisterReceiver(mBroadcastReceiver);

	}

	/*
	 * called when activity is resume
	 * 
	 * @override method of superclass (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	public void onResume() {
		super.onResume();
		String message = getIntent().getStringExtra(App42GCMService.EXTRA_MESSAGE); 
	        Log.d("MainActivity-onResume", "Message Recieved :"+message);
	        IntentFilter filter = new IntentFilter(App42GCMService.DISPLAY_MESSAGE_ACTION);
	        filter.setPriority(2);
	        registerReceiver(mBroadcastReceiver, filter);
	}
	
	 final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		  
	        @Override
	        public void onReceive(Context context, Intent intent) {
	        	
	        	String message = intent.getStringExtra(App42GCMService.EXTRA_MESSAGE);
	        	Log.i("MainActivity-BroadcastReceiver", "Message Recieved " +" : " +message);
	        	((TextView) findViewById(R.id.text)).setText(message);
	        	
	        }
	    };
}
