package com.example.slidingMenu;

import com.example.testdemo.R;
import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/*
 * 1��onMeasure:�����ڲ�View(��View)�Ŀ�͸��Լ��Լ��Ŀ�͸�
 * 2��onLayout:������View��λ��
 * 3����дonTouchEventʵ�ֻ����ɿ�����
 * 4���Զ���padding
 * 5����Ӱ����л�
 * 6������ʽ�໮��ʽһ���·�˵������ݵ���
 * 	����������ƫ����mMenuWidth~0
 * 	���Զ�����TraslationX
 * 	�ݶ�getScroolX:mMenuWidth~0
 * 	���ö�����ʱ������ACTION_MOVE�в��ã���onScrollChanged�д���
 * 7������ʽ�໮��ʽ����
 * a���Ҳ�����ƫ������1.0~0.7
 * scale:1.0~0.0
 * ֵ: 0.7+��scale*0.3��
 * b���˵���ʾʱ�����š�͸���ȱ仯0.7~1.0  0.6~1.0
 * ֵ��1-0.3*scale		1.0 - 0.4*scale
 * 
 * 
 *
 * 
 * 
 */
public class SlidingMenu extends HorizontalScrollView {

	private static final String TAG = "SlidingMenu";
	
	private LinearLayout mWapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mScreenWidth;	//��Ļ���
	private int mMenuRightPadding = 50;	//�˵����ұߵľ���dp
	
	private boolean once = false;
	private boolean inOpen = false;	//�Ƿ�򿪲˵�
	
	private int mMenuWidth;	//�˵��Ŀ��
	
	/*
	 * ������ֱ��New����ô˷���
	 */
	public SlidingMenu(Context context) {
		this(context,null);
	}

	
	/*
	 * δ��������ʱ����
	 */
	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	/*
	 * �Զ�������ʱ����
	 */
	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		/*
		 * ��ȡ�Զ�������
		 */
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, 
				R.styleable.SliderMenu, 
				defStyle, 
				0);
		
		int n = a.getIndexCount();//��ȡ�Զ�������
		for (int i = 0; i < n; i++)
		{
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.SliderMenu_rightPadding:
				//��dpת��px
				mMenuRightPadding = a.getDimensionPixelOffset(attr,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 50, context
										.getResources().getDisplayMetrics()));
				break;
			}
		}
		
		a.recycle();
		
		//��ȡ��Ļ���
		WindowManager wm =(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		mScreenWidth = metrics.widthPixels;
		
//		//��dpת��px
//		mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, 
//				context.getResources().getDisplayMetrics());
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		if (!once)
		{
			once = true;
			mWapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			mContent = (ViewGroup) mWapper.getChildAt(1);
			
			//�˵����
			mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;	
			mMenuWidth = mScreenWidth - mMenuRightPadding;
			mContent.getLayoutParams().width = mScreenWidth;
			LOG("mMenuWidth = " + mMenuWidth + ", mScreenWidth = " + mScreenWidth);
			
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed)
		{
			this.scrollTo(mMenuWidth, 0);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			int scrollX = getScrollX();
			LOG("scrollX=" + scrollX);
			if (scrollX >= mMenuWidth /2)
			{
				this.smoothScrollTo(mMenuWidth, 0);
				inOpen = false;
			}
			else
			{
				this.smoothScrollTo(0, 0);
				inOpen = true;
			}
			return true;
		case MotionEvent.ACTION_DOWN:
			
			break;
		case MotionEvent.ACTION_MOVE:
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
	
	/*
	 * l��ScrollX
	 * 
	 * @see android.view.View#onScrollChanged(int, int, int, int)
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {

		super.onScrollChanged(l, t, oldl, oldt);
		//�������Զ���������translationX
		float scale = l*1.0f/mMenuWidth;	//1~0
		
		/* a���Ҳ�����ƫ������1.0~0.7
		 * scale:1.0~0.0
		 * ֵ: 0.7+��scale*0.3��
		 * b���˵���ʾʱ�����š�͸���ȱ仯0.7~1.0  0.6~1.0
		 * ֵ��1-0.3*scale		1.0 - 0.4*scale
		 */
		float rightScale = 0.7f + (0.3f*scale);
		float leftScale = 1.0f - (0.3f*scale);
		float leftAlpha = 1.0f - (0.4f*scale);
		
		
//		ViewHelper.setTranslationX(mMenu, mMenuWidth*scale);
		//setTranslationX��ƫ����ԭ����mMenuWidth~0
		//��Ч�����ÿɣ��Ӹ�ƫ����
		ViewHelper.setTranslationX(mMenu, mMenuWidth*scale*0.8f);
		
		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight()/2);//�������ŵ�Ϊ�������ĵ�
		
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
		
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, leftAlpha);
		
	}
	
	private void openMenu()
	{
		if (inOpen) return;
		this.smoothScrollTo(0, 0);
		inOpen = true;
	}
	
	private void closeMenu()
	{
		if (!inOpen) return;
		this.smoothScrollTo(mMenuWidth, 0);
		inOpen = false;
	}
	
	/*
	 * �����л�
	 */
	public void toggleMenu()
	{
		if (inOpen)
		{
			closeMenu();
		}
		else
		{
			openMenu();
		}
	}
	
	private void LOG(String string)
	{
		Log.i(TAG, string);
	}

}
