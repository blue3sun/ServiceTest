package com.lanjing.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class SecondActivity extends Activity {

	private MyService.MyBinder mMyBinder;
	private ServiceConnection mServiceConnection = new ServiceConnection(){
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mMyBinder = (MyService.MyBinder)service;
			int result = mMyBinder.add(1,2);
			Log.e("info", "onServiceConnected: result的值是"+result);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mMyBinder = null;
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
	}
	public void doClick(View v){
		Intent intent = new Intent(this, MyService.class);
		switch(v.getId()){
			case R.id.btn1:
				startService(intent);
				break;
			case R.id.btn2:
				stopService(intent);
				break;
			case R.id.btn3:
				bindService(intent,mServiceConnection,BIND_AUTO_CREATE);
			break;
			case R.id.btn4:
				unbindService(mServiceConnection);
				break;
		}
		
	}
	
}
