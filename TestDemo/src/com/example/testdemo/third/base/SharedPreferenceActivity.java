package com.example.testdemo.third.base;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.testdemo.R;

public class SharedPreferenceActivity extends Activity implements
		OnClickListener {

	/*
	 * �����ǻ���XML�ļ��洢key-value
	 * 
	 * SharedPreferenceֻ�ܻ�ȡ���ݶ���֧�ִ洢���޸� �洢�޸���ͨ��Editor����ʵ��
	 * 
	 * 1����ȡSharedPreference���� 2����ȡSharedPreference.Editor����
	 * 3��ͨ��Editor��Putxxx�������� 4��Editor.commit�ύ
	 * 
	 * �����Ļ�����ط�ʵ���������뷨����
	 */

	private RelativeLayout layout;
	private EditText mEditUser;
	private EditText mEditPsw;
	private CheckBox mCheckBox;
	private Button login;

	private SharedPreferences pref = null;
	Editor mEdit = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpraference);
		
		initView();
		initEvent();
	}

	private void initEvent() {
		
		SetOnTouch(layout);
		
		login.setOnClickListener(this);

		// pref = PreferenceManager.getDefaultSharedPreferences(this);
		// �����ļ����ƺ�Ȩ��
		pref = getSharedPreferences("mypref", MODE_PRIVATE);
		mEdit = pref.edit();
		String name = pref.getString("name", "");
		String psw = pref.getString("psw", "");
		if (name != "") {
			mEditUser.setText(name);
			if (psw != "") {
				mEditPsw.setText(psw);
				mCheckBox.setChecked(true);
			}
		}else{
			mEditUser.setText("");
			mEditPsw.setText("");
			mCheckBox.setChecked(false);
		}
	}

	private void initView() {

		layout = (RelativeLayout) findViewById(R.id.sharedpreference);
		mEditUser = (EditText) findViewById(R.id.et_user);
		mEditPsw = (EditText) findViewById(R.id.et_psw);
		mCheckBox = (CheckBox) findViewById(R.id.checkBox1);
		login = (Button) findViewById(R.id.login);

		// mEdit.putString("name", "����");
		// mEdit.putInt("age", 18);
		// mEdit.putLong("time", System.currentTimeMillis());
		// mEdit.putBoolean("man", true);
		// mEdit.commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:
			String name = mEditUser.getText().toString().trim();
			String psw = mEditPsw.getText().toString().trim();
			if (name != null && psw != null && mCheckBox.isChecked()) {
				mEdit.putString("name", name);
				mEdit.putString("psw", psw);
				mEdit.commit();
				MyToast("����ɹ�");
			} else {
				mEdit.remove("name");
				mEdit.commit();
				MyToast("û�б���");
			}
			break;

		default:
			break;
		}

	}
	

	//����OnTouch�����¼�
	private void SetOnTouch(View view)
	{
		if (!(view instanceof EditText))
		{
			view.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					hideSoftKeyboard(SharedPreferenceActivity.this);
					return false;
				}
			});
		}
		
		//�ݹ����OnTouch�¼�
		if (!(view instanceof ViewGroup))
		{
			for (int i = 0; i < ((ViewGroup)view).getChildCount(); i++) {
				SetOnTouch(((ViewGroup)view).getChildAt(i));
			}
		}
	}
	//�������뷨
	private void hideSoftKeyboard(Activity activity)
	{
		InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
	
	private void MyToast(String string) {
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}
}
