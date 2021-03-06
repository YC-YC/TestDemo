package com.example.treeview.utils;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

public abstract class TreeViewAdapter <T> extends BaseAdapter {

	
	protected Context mContext;
	protected List<Node> mAllNodes;
	protected List<Node> mVisibleNodes;
	protected LayoutInflater mInflater;
	protected ListView mTree;
	private OnTreeNodeClickListener mClickListener;
	
	public interface OnTreeNodeClickListener
	{
		void onClick(Node node, int position);
	}
	
	public void setOnTreeNodeClickListener(OnTreeNodeClickListener listener)
	{
		mClickListener = listener;
	}
	
	public TreeViewAdapter(Context context, ListView tree, List<T> datas, int defaultExpandLevel) throws IllegalAccessException, IllegalArgumentException {
		super();
		mContext = context;
		mTree = tree;
		mInflater = LayoutInflater.from(mContext);
		mAllNodes = TreeHelper.getSortedNodes(datas, defaultExpandLevel);
		mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
		
		mTree.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				expandOrCollapse(position);
				if (mClickListener != null)
				{
					mClickListener.onClick(mVisibleNodes.get(position), position);
				}
			}

		});
	}
	
	//点击收缩或展开
	private void expandOrCollapse(int position) {
		Node n = mVisibleNodes.get(position);
		if (n != null)
		{
			if (n.isLeaf())
			{
				return;
			}
			n.setExpand(!n.isExpand());
			
			mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
			notifyDataSetChanged();
		}
	}

	@Override
	public int getCount() {
		return mVisibleNodes.size();
	}

	@Override
	public Object getItem(int position) {
		return mVisibleNodes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Node node = mVisibleNodes.get(position);
		convertView = getConvertView(node, position, convertView, parent);
		convertView.setPadding(node.getLevel()*30, 3, 3, 3);
		return convertView;
	}
	
	//抽象方法供外部继承使用
	public abstract View getConvertView(Node node, int position, View convertView, ViewGroup parent);

}
