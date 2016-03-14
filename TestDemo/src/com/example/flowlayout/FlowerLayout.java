package com.example.flowlayout;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/*
 * �Զ���ViewGroup
 * 	1��onMeasure:������View�Ŀ�͸ߣ������Լ��Ŀ�͸ߡ�
 * 		������View�Ĳ����ļ���Ϊ��View���ò���ģʽ�Ͳ���ֵ
 * 		���� = ����ģʽ+����ֵ
 * 		����ģʽ��3�֣�
 * 		EXACTLY����׼��match_parent
 * 		AT_MOST��Wrap_content
 * 		UNSPECIFIED����View��Ҫ���ھͶ��
 * 	2��onLayout��������View��λ��
 * 
 * ViewGroup --LayoutParams
 */

public class FlowerLayout extends ViewGroup {

	public FlowerLayout(Context context) {
		this(context, null);
	}
	public FlowerLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public FlowerLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}


	

	/*
	 * ���븸View�Ŀ��
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		
		//wrap_content
		int width = 0;
		int height = 0;
//		
		
		//��¼ÿһ�еĸ߶ȺͿ��
		int lineWidth = 0;
		int lineHeight = 0;
		
		int count = getChildCount();
		for (int i = 0; i < count; i++)
		{
			View child = getChildAt(i);
			//������View�Ŀ�͸�
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			//�õ�LayoutParams
			MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
			//��Viewռ�ݵĿ�Ⱥ͸߶�
			int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
			int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
			
			//���д���
			if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight())
			{
				width = Math.max(width, lineWidth);
				lineWidth = childWidth;
				height += lineHeight;
				lineHeight = childHeight;
			}else
			{
				lineWidth += childWidth;
				lineHeight = Math.max(childHeight, lineHeight);
			}
			
			if (i == count-1)
			{
				width = Math.max(lineWidth, width);
				height += lineHeight;
			}
		}
		setMeasuredDimension(
				modeWidth == MeasureSpec.EXACTLY?sizeWidth:(width + getPaddingLeft() + getPaddingRight()),
						modeHeight == MeasureSpec.EXACTLY? sizeHeight:(height + getPaddingTop() + getPaddingBottom()));
		
	}
	
	//�洢���е�View,һ���еش�
	private List<List<View>> mAllViews = new ArrayList<List<View>>();
	//ÿһ�еĸ߶�
	private List<Integer> mLineHeight = new ArrayList<Integer>();
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		mAllViews.clear();
		mLineHeight.clear();
		
		int width = getWidth();
		int lineWidth = 0;
		int lineHeight = 0;
		
		List<View> lineViews = new ArrayList<View>();
		int count = getChildCount();
		for (int i = 0; i < count; i++)
		{
			View child = getChildAt(i);
			MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
			int childWidth = child.getMeasuredWidth();
			int childHeight = child.getMeasuredHeight();
			
			if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width- getPaddingLeft() - getPaddingRight())
			{
				mLineHeight.add(lineHeight);
				mAllViews.add(lineViews);
				//����
				lineWidth = 0;
				lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
				
				lineViews = new ArrayList<View>();
			}
			lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
			lineHeight = Math.max(lineHeight, childHeight+ lp.topMargin + lp.bottomMargin);
			lineViews.add(child);
		}
		mLineHeight.add(lineHeight);
		mAllViews.add(lineViews);
		
		//������View��λ��
		int left = getPaddingLeft();
		int top = getPaddingTop();
		
		int lineNum = mAllViews.size();
		
		for (int i = 0; i < lineNum ; i++ )
		{
			lineViews = mAllViews.get(i);
			lineHeight = mLineHeight.get(i);
			
			for (int j = 0; j < lineViews.size(); j ++)
			{
				View child = lineViews.get(j);
				if (child.getVisibility() == View.GONE)
				{
					continue;
				}
				
				MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
				
				int lc = left + lp.leftMargin;
				int tc = top + lp.topMargin;
				int rc = lc + child.getMeasuredWidth();
				int bc = tc + child.getMeasuredHeight();
				
				child.layout(lc, tc, rc, bc);
				
				left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
			}
			
			left = getPaddingLeft();
			top += lineHeight;
		}
	}
	
	/*
	 * �뵱ǰViewGroup��Ӧ��LayoutParams
	 */
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}
}
