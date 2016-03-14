package com.example.testdemo.second.notify;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testdemo.R;

@SuppressLint("NewApi")
public class NotifyActivity extends Activity implements OnClickListener {

	private final int NOTIFICATION_ID = 1000;
	
	private Button bt_notify1;
	private Button bt_notify2;
	
	private NotificationManager manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		setContentView(R.layout.notify_activity);
		initView();
	}
	
	private void initView() {
		bt_notify1 = (Button) findViewById(R.id.bt_notify1);
		bt_notify1.setOnClickListener(this);

		bt_notify2 = (Button) findViewById(R.id.bt_notify2);
		bt_notify2.setOnClickListener(this);

	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_notify1:
			showNofity();
			break;
		case R.id.bt_notify2:
			cancelNotify();
			break;

		default:
			break;
		}
	}

	/*
	 * ȡ��֪ͨ
	 */
	private void cancelNotify() {
		manager.cancel(NOTIFICATION_ID);//ͨ��IDȡ��
	}

	/*
	 * ����֪ͨ��
	 */
	private void showNofity() {
		Builder builder = new Notification.Builder(this);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setTicker("hello");	//�ֻ�״̬������ʾ
		builder.setWhen(System.currentTimeMillis());//����ʱ��
		builder.setContentTitle("֪ͨ��֪ͨ");//���ñ���
		builder.setContentText("��������");
		
		Intent intent = new Intent(this, NotifyActivity.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
		builder.setContentIntent(pi);//��������ͼ
		
		/*
		 * ����Ч������Ҫ���Ȩ��
		 */
		builder.setDefaults(Notification.DEFAULT_SOUND);//������ʾ��
		builder.setDefaults(Notification.DEFAULT_LIGHTS);//����ָʾ��
		builder.setDefaults(Notification.DEFAULT_VIBRATE);//������
		
		Notification notification = builder.build();
		manager.notify(NOTIFICATION_ID, notification);
	}

}
