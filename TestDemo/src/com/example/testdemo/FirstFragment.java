package com.example.testdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.testdemo.appshare.Hello;
import com.example.testdemo.cpp.CppActivity;
import com.example.testdemo.first.base.ProgressActivity;
import com.example.testdemo.first.base.SeekBarActivity;
import com.example.testdemo.first.base.SpinnerActivity;
import com.example.testdemo.first.base.WebViewActivity;
import com.example.testdemo.first.gridview.GridViewActivity;
import com.example.testdemo.first.listview.ListViewActivity;
import com.example.testdemo.first.picker.PickerActivity;

public class FirstFragment extends Fragment implements OnClickListener{

	private static final String TAG = "FirstFragment";
	
	private View mView;
	
	private Button fir_button1;
	private Button fir_button2;
	private Button fir_button3;
	private Button fir_button4;
	private Button fir_button5;
	private Button fir_button6;
	private Button fir_button7;
	private Button fir_button8;
	private Button fir_button9;
	private Button fir_button10;
	private Button fir_button11;
	private Button fir_button12;
	private Button fir_button13;
	
	private ScrollView mScrollView;
	
	private Context mContext;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.first_main, null);
		mContext = mView.getContext();
		initView();
		return mView;
	}

	private void initView() {
		fir_button1 = (Button) mView.findViewById(R.id.fir_button1);
		fir_button1.setOnClickListener(this);

		fir_button2 = (Button) mView.findViewById(R.id.fir_button2);
		fir_button2.setOnClickListener(this);
		
		fir_button3 = (Button) mView.findViewById(R.id.fir_button3);
		fir_button3.setOnClickListener(this);

		fir_button4 = (Button) mView.findViewById(R.id.fir_button4);
		fir_button4.setOnClickListener(this);
		
		fir_button5 = (Button) mView.findViewById(R.id.fir_button5);
		fir_button5.setOnClickListener(this);
		
		fir_button6 = (Button) mView.findViewById(R.id.fir_button6);
		fir_button6.setOnClickListener(this);
		
		fir_button7 = (Button) mView.findViewById(R.id.fir_button7);
		fir_button7.setOnClickListener(this);
		
		fir_button8 = (Button) mView.findViewById(R.id.fir_button8);
		fir_button8.setOnClickListener(this);
		
		fir_button9 = (Button) mView.findViewById(R.id.fir_button9);
		fir_button9.setOnClickListener(this);
		
		fir_button10 = (Button) mView.findViewById(R.id.fir_button10);
		fir_button10.setOnClickListener(this);
		
		fir_button11 = (Button) mView.findViewById(R.id.fir_button11);
		fir_button11.setOnClickListener(this);
		
		fir_button12 = (Button) mView.findViewById(R.id.fir_button12);
		fir_button12.setOnClickListener(this);
		
		fir_button13 = (Button) mView.findViewById(R.id.fir_button13);
		fir_button13.setOnClickListener(this);
		
		mScrollView = (ScrollView) mView.findViewById(R.id.scrollView);
		mScrollView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_UP://�ɿ�
					
					break;
				case MotionEvent.ACTION_DOWN://����
					
					break;
				case MotionEvent.ACTION_MOVE://�ƶ�
					/*
					 * 1��getScrollY--->�����������ľ���
					 * 2��getMeasureHeight--->�����ܹ��߶ȣ����ܱ�getHeight��
					 * 3��getHeight--->UI�����ϵĸ߶�
					 */
					//����
					if (mScrollView.getScrollY() <= 0)
					{
						Toast.makeText(mContext, "����������", Toast.LENGTH_LONG).show();
					}
					//�ײ�
					//��һ���Ӳ��ָ߶�  <= һ��Ļ�߶�+�������Ĺ�������
					if (mScrollView.getChildAt(0).getMeasuredHeight() <= 
							mScrollView.getHeight() + mScrollView.getScrollY())
					{
						Toast.makeText(mContext, "�������ײ�", Toast.LENGTH_LONG).show();
	
					}
					LOG("mScrollView.getScrollY()=" + mScrollView.getScrollY()
							+ "mScrollView.getHeight()=" + mScrollView.getHeight()
							+ "mScrollView.getChildAt(0).getMeasuredHeight() = " + mScrollView.getChildAt(0).getMeasuredHeight() );
					break;
				default:
					break;
				}
				return false;
			}
		});
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fir_button1:
			startActivity(new Intent(mContext, ListViewActivity.class));
			break;
		case R.id.fir_button2:
			startActivity(new Intent(mContext, PickerActivity.class));
			break;
		case R.id.fir_button3:
			startActivity(new Intent(mContext, GridViewActivity.class));
			break;
		case R.id.fir_button4:
			startActivity(new Intent(mContext, SpinnerActivity.class));
			break;
		case R.id.fir_button5:
			startActivity(new Intent(mContext, ProgressActivity.class));
			break;
		case R.id.fir_button6:
			startActivity(new Intent(mContext, WebViewActivity.class));
			break;
		case R.id.fir_button7:
//			mScrollView.scrollTo(0, -30);//���һ�����
			mScrollView.scrollBy(0, -30);//���ǰһ��
			break;
		case R.id.fir_button8:
//			mScrollView.scrollTo(0, 30);
			mScrollView.scrollBy(0, 30);
			break;
		case R.id.fir_button9:
			startActivity(new Intent(mContext, SeekBarActivity.class));
			break;
		case R.id.fir_button10:
			startActivity(new Intent(mContext, CppActivity.class));
			break;	
		case R.id.fir_button11:
			//ͨ��Action����
//			startActivity(new Intent("com.example.testdemo.appshare.Main1"));
			//ͨ��data,������ͬ��Action
			startActivity(new Intent("com.example.testdemo.appshare.Main1",Uri.parse("app://hello")));
			break;
		case R.id.fir_button12:
			Hello.sayHello(mContext);
			//�ⲿӦ����Ҫ���Ȩ��
			startActivity(new Intent("com.example.testdemo.appshare.Main1",Uri.parse("app://hello")));
			break;	
		case R.id.fir_button13:
			int count = 6/0;
			break;	
		default:
			break;
		}
	}
	
	private void LOG(String string)
	{
		Log.e(TAG, string);
	}
}
