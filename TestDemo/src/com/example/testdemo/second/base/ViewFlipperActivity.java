package com.example.testdemo.second.base;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/*
 * 1���ɾ�̬���أ�xml���أ�
 * 2���ɶ�̬���أ�������أ�
 */
public class ViewFlipperActivity extends Activity {

	private int[] icons = {R.drawable.pic1,
							R.drawable.pic2,
							R.drawable.pic3,
							R.drawable.pic4};
	
	private float mStartX;
	private ViewFlipper mViewFlipper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewflipper);
		
		initView();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			
			break;
		case MotionEvent.ACTION_DOWN:
			mStartX = event.getX();
			break;
		case MotionEvent.ACTION_UP:
			if (event.getX() - mStartX > 100)//����
			{
				//���ý��붯��
				mViewFlipper.setInAnimation(this, R.anim.left_in);
				//�����˳�����
				mViewFlipper.setOutAnimation(this, R.anim.left_out);
				//��ʾǰһҳ
				mViewFlipper.showPrevious();
			}
			
			if (event.getX() - mStartX < -100)//����
			{
				//���ý��붯��
				mViewFlipper.setInAnimation(this, R.anim.right_in);
				//�����˳�����
				mViewFlipper.setOutAnimation(this, R.anim.right_out);
				//��ʾǰһҳ
				mViewFlipper.showNext();
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	
	private void initView() {
		mViewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
		//��̬����
		for (int iconid : icons) {
			mViewFlipper.addView(getImageView(iconid));
		}
		
		//���ý��붯��
		mViewFlipper.setInAnimation(this, R.anim.left_in);
		//�����˳�����
		mViewFlipper.setOutAnimation(this, R.anim.left_out);
		//���ò���ʱ��
		mViewFlipper.setFlipInterval(3000);
		//��ʼ����
		mViewFlipper.startFlipping();
	}
	private View getImageView(int iconid) {
		ImageView imageView = new ImageView(this);
//		imageView.setImageResource(iconid);
		imageView.setBackgroundResource(iconid);
		return imageView;
	}
}
