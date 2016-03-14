package com.example.customerview;

import com.example.testdemo.R;
import com.example.testdemo.Topbar;
import com.example.testdemo.Topbar.topbarClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/*
 * 1����������atts.xml
 * 2���Զ�����TopBar.java
 * 3�������Զ���View
 * 
 */
public class CustomActivity extends Activity implements topbarClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_main);
		Topbar topbar = (Topbar) findViewById(R.id.topbar);
		topbar.setOnTopbarClickListener(this);
	}

	@Override
	public void leftClick() {
		Toast.makeText(CustomActivity.this, "������󰴼�", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void rightClick() {
		Toast.makeText(CustomActivity.this, "������Ұ���", Toast.LENGTH_SHORT).show();
		
	}
}
