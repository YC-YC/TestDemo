package com.example.testdemo.first.base;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testdemo.R;

/*
 * 1���������Դ
 * 2������������
 * 3�����������˵� ��ʽ
 * 4������������������б���
 * 5�����õ����Ӧ�¼�
 */
public class SpinnerActivity extends Activity implements OnItemSelectedListener{
	
	private TextView mTextView;
	private Spinner mSpinner;
	
	private List<String> mData = new ArrayList<String>();
	private ArrayAdapter<String> mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner);
		
		initView();
	}

	private void initView() {
		mTextView = (TextView) findViewById(R.id.spinner_tv);
		mSpinner = (Spinner) findViewById(R.id.spinner1);
		
		mTextView.setText("ѡ��ĳ����ǣ�����");
		mData.add("����");
		mData.add("�Ϻ�");
		mData.add("����");
		mData.add("����");
		
		mAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, 
				mData);
		mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		mSpinner.setAdapter(mAdapter);
		
		mSpinner.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		mTextView.setText("ѡ��ĳ����ǣ�" + mAdapter.getItem(position));
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}


}
