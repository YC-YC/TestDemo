package com.example.testdemo.second.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.testdemo.R;

public class StaticFragmentActivity extends Activity {

	private TextView tv;
	private Button bt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.static_fragment);
		
		/*
		 * ��̬���ؿ���Activity���ҵ�����
		 */
		tv = (TextView) findViewById(R.id.fragment_tv);
		tv.setText("Fragment�е�Button");
		bt = (Button) findViewById(R.id.fragment_bt);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tv.setText("Activity�в���Fragment�е�Button");
			}
		});
	}
}
