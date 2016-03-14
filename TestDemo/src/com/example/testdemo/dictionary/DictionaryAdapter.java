package com.example.testdemo.dictionary;

import com.example.testdemo.R;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author YC
 * @time 2016-3-4 ����4:38:14
 */
public class DictionaryAdapter extends CursorAdapter {

	private LayoutInflater layoutInflater;
	/**
	 * @param context
	 * @param c
	 * @param autoRequery
	 */
	public DictionaryAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
		// TODO �Զ����ɵĹ��캯�����
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	
	
	@Override
	public CharSequence convertToString(Cursor cursor) {
		// TODO �Զ����ɵķ������
//		return super.convertToString(cursor);
		return cursor==null ? "" :cursor.getString(cursor.getColumnIndex("_id"));
	}



	private void setView(View view, Cursor cursor)
	{
		TextView tvWordItem = (TextView) view;
		tvWordItem.setText(cursor.getString(cursor.getColumnIndex("_id")));
	}
	
	@Override
	public void bindView(View view, Context arg1, Cursor cursor) {
		setView(view, cursor);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = layoutInflater.inflate(R.layout.word_list_item, null);
		setView(view, cursor);
		return view;
	}

}
