package com.example.testdemo.floatview;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.testdemo.R;

/**
 *@Author Administrator
 *@Time 2016-2-29 ����12:07:09
 */
public class FloatService extends Service {

	LinearLayout mLayout;	//�������ڲ���
	
	WindowManager.LayoutParams mParams;
	WindowManager mWManager;
	
	private Button mBtnFloat;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		LOG("onCreate");
		creatFloatView();
	}

	private void creatFloatView() {
		mParams = new WindowManager.LayoutParams();
		mWManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);
	
//		mParams.type = LayoutParams.TYPE_PHONE;
		mParams.type = LayoutParams.TYPE_TOAST;	//���������Ͳ���Ҫ��ϵͳ����Ȩ��
		mParams.format = PixelFormat.RGBA_8888;
		//���ø������ڲ��ɾ۽���ʵ�ֲ���������������������ɼ����ڵĲ�����  
		mParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
		//������������ʾ��ͣ��λ��Ϊ����ö�  
		mParams.gravity = Gravity.LEFT | Gravity.TOP; 
//		mParams.gravity = Gravity.CENTER; 
		
		// ����Ļ���Ͻ�Ϊԭ�㣬����x��y��ʼֵ�������gravity  
		mParams.x = 0;  
		mParams.y = 0;  
  
        //�����������ڳ�������    
		mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;  
		mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		
        
		 LayoutInflater inflater = LayoutInflater.from(getApplication());  
	        //��ȡ����������ͼ���ڲ���  
	        mLayout = (LinearLayout) inflater.inflate(R.layout.float_win, null);  
	        	        
	        LOG("mLayout.getWidth()--->" + mLayout.getWidth());  
	        LOG("mLayout.getHeight()--->" + mLayout.getHeight());  
	       
	        
	        //���mFloatLayout  
	        mWManager.addView(mLayout, mParams);  
	        
	        mLayout.measure(
	        		View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), 
	        		View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
	        
	        
	        //�������ڰ�ť  
	        mBtnFloat = (Button)mLayout.findViewById(R.id.btn_float); 
	        
	        LOG("Width/2--->" + mBtnFloat.getMeasuredWidth()/2);  
	        LOG("Height/2--->" + mBtnFloat.getMeasuredHeight()/2);  
	       
	        mBtnFloat.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 Toast.makeText(FloatService.this, "onClick", Toast.LENGTH_SHORT).show();					
				}
			});
	        mBtnFloat.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_MOVE:
						LOG("event[X,Y] = "+event.getRawX()+"," + event.getRawY());
						LOG("event[X,Y] = "+mBtnFloat.getMeasuredWidth()+"," + mBtnFloat.getMeasuredWidth());
						//getRawX�Ǵ���λ���������Ļ�����꣬getX������ڰ�ť������  
						mParams.x = (int) event.getRawX() - mBtnFloat.getMeasuredWidth()/2;  
						//��25Ϊ״̬���ĸ߶�  
						mParams.y = (int) event.getRawY() - mBtnFloat.getMeasuredHeight()/2 - 25;  
						//ˢ��  
						mWManager.updateViewLayout(mLayout, mParams);  
						return true;  //�˴����뷵��false������OnClickListener��ȡ��������
					}
					return false;
				}
			});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		LOG("onDestroy");
		if (mLayout != null)
		{
			mWManager.removeView(mLayout);
		}
	}

	private void LOG(String string)
	{
		Log.i("FloatServie", string);
	}
}
