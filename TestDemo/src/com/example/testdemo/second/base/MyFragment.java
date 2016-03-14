package com.example.testdemo.second.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.testdemo.R;

public class MyFragment extends Fragment {
	private static final String TAG = "MyFragment";
	
	private TextView tv;
	private Button bt;
	private Button bt2;
	
	/*
	 * Fragment��Activity�������ݵķ�ʽ
	 */
	public interface FragmentCallBack{
		public void callback(String code);
	}
	
	private FragmentCallBack callBack;

	//��Fragment����ӵ�Activityʱ����
		@Override
		public void onAttach(Activity activity) {
			// TODO Auto-generated method stub
			super.onAttach(activity);
			callBack = (FragmentCallBack) activity;
		}
	
	//ÿ�δ����������
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/*
		 * ��ȡ���ڵ�Activity
		 */
		Log.i(TAG, getActivity().toString());
		View view = inflater.inflate(R.layout.fragment, container, false);
		tv = (TextView) view.findViewById(R.id.fragment_tv);
		bt = (Button) view.findViewById(R.id.fragment_bt);
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getArguments() != null)
				{
					tv.setText("Fragment�л�ȡ��������Ϊ��" + getArguments().get("name"));
				}
				else
				{
					tv.setText("Activity��û����Fragment��������");
				}
			}
		});
		bt2 = (Button) view.findViewById(R.id.fragment_bt2);
		bt2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				callBack.callback("Fragment��Activity��������");
			}
		});
		return view;
	}

	

	//����Fragmentʱ�ᱻ����
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	//Fragment�������ʱ����
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
	
	//����Fragment
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	//�ָ�Fragmentʱ�����ã�ÿ�ε���onStartʱ�����
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	//ֹͣfragment
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	//����Fragment��������View���ʱ����
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}
	
	//����Fragmentʱ�ᱻ����
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	//Fragment��Activityʱ���� 
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}
	
}
