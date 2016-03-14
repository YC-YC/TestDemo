package com.example.testdemo.second.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testdemo.R;
import com.example.testdemo.second.base.MyFragment.FragmentCallBack;

/*
 * ʵ��Fragment�ṩ�Ľӿ�ʵ��ͨѶ
 */
public class DynamicFragmentActivity extends Activity implements FragmentCallBack{
	private static final String TAG = "DynamicFragmentActivity";
	
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamic_fragment);
		tv = (TextView) findViewById(R.id.dynamic_tv);
	}
	
	public void addFragment(View view)
	{
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
	
		Fragment fragment = new MyFragment();
		/*
		 * ���fragment
		 */
		beginTransaction.add(R.id.dynamic_layout, fragment);
		//���ص���һ��Fragment
		beginTransaction.addToBackStack(null);
		beginTransaction.commit();
		
		//��ȡ�󶨵�Fragment
//		Log.i(TAG, fragmentManager.findFragmentById(id).);
	}
	
	public void setValueToFragment(View view)
	{
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
	
		Bundle bundle = new Bundle();
		bundle.putString("name", "Activity��Fragment��������");
		Fragment fragment = new MyFragment();
		//�������
		fragment.setArguments(bundle);
		beginTransaction.add(R.id.dynamic_layout, fragment,"MyFragment");
		//���ص���һ��Fragment
		beginTransaction.addToBackStack(null);
		beginTransaction.commit();
	}

	@Override
	public void callback(String code) {
		tv.setText("Activity���յ�Fragment������Ϊ��" + code);
	}
}
