package com.example.list;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.testdemo.R;
/*
 * ��дListView
 * 1����ӹ��캯��
 * 2�����footer
 * 3������OnSrollListener
 * 
 * 1�����ͷ��
 * 2��ͨ������padding����ʾ����ǰ����֪ͨ����������
 * 3����дonTouchEvent��ȡ״̬�ͼ���ƫ��
 * 4���ͷ�ʱ����
 */
public class MyListView extends ListView implements OnScrollListener{

	private static final String TAG = "MyListView";
	
	private View footer;
	private View header;
	
	private int mLastVisibleItem;	//���һ��
	private int mTotalListItem;		//����
	private int mFirstVisibleItem;	//��һ���ɼ���
	private int mScrollState;		//����״̬
	
	private boolean mbLoading;		//������
	private boolean mIsRemark;		//��˰���
	
	private int startY;	//��ʼYֵ
	
	private final int NONE = 0;	//����״̬
	private final int PULL = 1;	//��ʾ����״̬
	private final int RELEASE = 2;	//��ʾ�ɿ�״̬
	private final int REFLASHING = 3;	//����ˢ��
	
	private int state;	//��ǰ״ֵ̬
	
	
	
	private int mHeaderHeight;	//�ϱ߾�
	
	private ILoadListener mListener;
	
	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public MyListView(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		footer = inflater.inflate(R.layout.listview2_footer, null);
		footer.findViewById(R.id.list_footer).setVisibility(GONE);
		this.addFooterView(footer);//��ӵײ�����
		
		header = inflater.inflate(R.layout.listview2_header, null);
		measureView(header);
		mHeaderHeight = header.getMeasuredHeight();
		LOG("mHeaderHeight = " + mHeaderHeight);
		topPadding(-mHeaderHeight);//ʵ������
		this.addHeaderView(header);//���ͷ��
		
		
		setOnScrollListener(this);
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		mScrollState = scrollState;
		if (mLastVisibleItem == mTotalListItem &&	//�������ֹͣ�˻���
				scrollState == SCROLL_STATE_IDLE){
			if (!mbLoading){
				mbLoading = true;
				footer.findViewById(R.id.list_footer).setVisibility(VISIBLE);
				mListener.onLoadMore();
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.mLastVisibleItem = firstVisibleItem + visibleItemCount;
		this.mTotalListItem = totalItemCount;
		mFirstVisibleItem = firstVisibleItem;
//		LOG("onScroll lastvisibleItem=" + mLastVisibleItem + 
//				",total=" + mTotalListItem + ",first=" + mFirstVisibleItem);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (mFirstVisibleItem == 0){//��˰���
				mIsRemark = true;
				startY = (int) ev.getY();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			onMove(ev);
			break;
		case MotionEvent.ACTION_UP:
			if (state == RELEASE){	//��ʾ�ɿ�״̬�ɿ�����ˢ��״̬
				state = REFLASHING;
				reflashViewByState();
				mListener.onRefresh();
			}else if (state == PULL){
				mIsRemark = false;
				state = NONE;
				reflashViewByState();
			}
			break;
		}
		return super.onTouchEvent(ev);
	}
	
	
	private void onMove(MotionEvent ev) {
		if (!mIsRemark){	//���ǵ�һ���
			return;
		}
		int space = (int) (ev.getY() - startY);
		int topPadding = space - mHeaderHeight;
//		LOG("space = " + space + ",padding=" + topPadding);
		switch (state) {
		case NONE:
			if (space > 0){//��������״̬
				state = PULL;
				reflashViewByState();
			}
			break;
		case PULL:
			topPadding(topPadding);
			if (space > mHeaderHeight + 30 && 
					mScrollState == SCROLL_STATE_TOUCH_SCROLL){
				state = RELEASE;
				reflashViewByState();
			}
			break;
		case RELEASE:
			topPadding(topPadding);
			if (space > 0 && space < mHeaderHeight + 30 ){
				state = PULL;
				reflashViewByState();
			}else if(space <= 0){
				state = NONE;
				mIsRemark = false;
				reflashViewByState();
			}
			break;
		}
	}
	
	/*
	 * ����״̬ˢ��ͼ��
	 */
	private void reflashViewByState() {
		TextView tip = (TextView) header.findViewById(R.id.tip);
		ImageView arrow = (ImageView) header.findViewById(R.id.arrow);
		ProgressBar progressBar = (ProgressBar) header.findViewById(R.id.fresh_progress);
		//��ͷ��Ӷ���
		RotateAnimation anim1 = new RotateAnimation(0, 180,//0��180
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim1.setDuration(500);
		anim1.setFillAfter(true);
		RotateAnimation anim2 = new RotateAnimation(180, 0,//180��0
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim2.setDuration(500);
		anim2.setFillAfter(true);
//		LOG("reflashViewByState state=" + state);
		switch (state) {
		case NONE:
			topPadding(-mHeaderHeight);
			arrow.clearAnimation();
			break;
		case PULL:
			arrow.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.GONE);
			tip.setText("��������ˢ��");
			arrow.clearAnimation();
			arrow.setAnimation(anim2);
//			LOG("reflashViewByState PULL");
			break;
		case RELEASE:
			arrow.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.GONE);
			tip.setText("�ɿ�����ˢ��");
			arrow.clearAnimation();
			arrow.setAnimation(anim1);
			break;
		case REFLASHING:
			topPadding(50);
			arrow.clearAnimation();
			topPadding(mHeaderHeight);
			arrow.setVisibility(View.GONE);
			progressBar.setVisibility(View.VISIBLE);
			tip.setText("����ˢ��");
			break;
		}
	}

	/*
	 * ֪ͨ������ռ�ø߿�
	 */
	private void measureView(View view){
		ViewGroup.LayoutParams p = view.getLayoutParams();
		if (p == null){
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int width = ViewGroup.getChildMeasureSpec(0, 0, p.height);
		int height;
		int tmpHeight = p.height;
		if (tmpHeight > 0){
			height = MeasureSpec.makeMeasureSpec(tmpHeight, MeasureSpec.EXACTLY);
		}else{
			height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		view.measure(width, height);
	}

	/*
	 * ͷ��ͨ�������ϱ߾���ʵ���϶�Ч��
	 */
	private void topPadding(int topPadding){
		LOG("topPadding = "+ topPadding);
		header.setPadding(header.getPaddingLeft(), topPadding, header.getPaddingRight(), header.getPaddingBottom());
		header.invalidate();
	}
	
	/*
	 * �������
	 */
	public void loadComplete(){
		mbLoading = false;
		footer.findViewById(R.id.list_footer).setVisibility(GONE);
	}
	
	/*
	 * ����ˢ�����
	 */
	public void refreshComplete(){
		state = NONE;
		reflashViewByState();
		mIsRemark = false;
		TextView tv = (TextView) header.findViewById(R.id.lastupdate_time);
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String time = format.format(date);
		tv.setText(time);
	}
	
	public void setLoadListener(ILoadListener listener){
		mListener = listener;
	}
	/*
	 * �ṩ����������õĽӿ�
	 */
	public interface ILoadListener{
		public void onLoadMore();
		public void onRefresh();
	}
	
	private void LOG(String string){
		Log.e(TAG, string);
	}
}
