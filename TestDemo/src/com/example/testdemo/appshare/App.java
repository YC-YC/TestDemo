package com.example.testdemo.appshare;

import android.app.Application;

/**
 *@Author Administrator
 *@Time 2016-2-24 ����7:42:55
 */
public class App extends Application {

	private String mShareString = "default";

	public String getmShareString() {
		return mShareString;
	}

	public void setmShareString(String mShareString) {
		this.mShareString = mShareString;
	}
	
	//��������
	//����Ӧ��ʱ���ã�������Activity��ִ��
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}
	
	//�ڴ�����
	@Override
	public void onTrimMemory(int level) {
		// TODO Auto-generated method stub
		super.onTrimMemory(level);
	}
}
