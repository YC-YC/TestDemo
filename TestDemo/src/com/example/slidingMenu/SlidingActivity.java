package com.example.slidingMenu;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/*
 * һ��һ��Ĳ໮
 * 1��ViewGroup Menu+content
 * 2��onTouchEvent
 * MOVE:ViewGroup��leftMargin
 * UP:������ʾ�˵��Ŀ�ȣ��������ػ���ʾ����Scroller��LeftMargin+Thread��
 * 
 * �����̳�HorizontalScrollView
 * 1������
 * 
 */
public class SlidingActivity extends Activity {
	
	private SlidingMenu slidingMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.slidermenu_main);
		slidingMenu = (SlidingMenu) findViewById(R.id.slidermenu);
	}
	
	public void toggleMenu(View view)
	{
		slidingMenu.toggleMenu();
	}
}
