package com.example.testdemo.first.base;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SeekBarActivity extends Activity implements OnSeekBarChangeListener{

	/*
	 * SeekBar�̳�ProgressBar
	 * �ܶ෽����ProgressBarһ������Ҫʵ��OnSeekBarChangeListener
	 */
	
	private SeekBar mSeekBar = null;
	private TextView mTextView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seekbar);
		
		initView();
	}

	private void initView() {
		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		mSeekBar.setOnSeekBarChangeListener(this);
		
		mTextView = (TextView) findViewById(R.id.seekbar_value);
		
	}

	//��ֵ�ı�
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		mTextView.setText("��ǰֵΪ��" + progress);
	}

	//��ʼ�϶�
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	//ֹͣ�϶�
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	
}
