package com.lanjing.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("info", "MyService onCreate....");
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e("info","MyService onStartCommand....");
		return super.onStartCommand(intent, flags, startId);
	}
    @Override
    public IBinder onBind(Intent arg0) {
        Log.e("info", "MyService onBind....");
        MyBinder myBinder = new MyBinder();
        return myBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e("info", "MyService onRebind....");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("info", "MyService onUnbind....");
        //调用onRebind()方法的前提是先前的onUnbind()方法执行成功，且onUnbind()返回true：
        // 因此不能直接调用 super.onUnbind(intent)，因为该方法默认返回false，这里需要我们手动使其返回true，
        // 这样再次绑定时onRebind()就会执行。否则，如果退出时不显示的指定onUnbind()为成功的话(为false)，
        // 那么重新启动绑定服务时，
        // Service的onBind()方法和onReBind都不会执行，但是ServiceConnection方法一定会回调
        //return super.onUnbind(intent);
        return true;
    }

    @Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("info", "MyService onDestroy....");
	}

    //创建Binder对象
    public class MyBinder extends Binder{
        public int add(int a,int b){
            return a+b;
        }
    }
}
