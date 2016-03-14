package com.example.testdemo.first.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.testdemo.R;

/*
 * 1���½�������
 * 2����������
 * 3����ͼ���������
 */
public class ListViewActivity extends Activity {
	
	private ListView mListView;
	private ArrayAdapter<String> arr_adAdapter;
	private SimpleAdapter simp_aAdapter;
	
	private List<Map<String, Object>> datalist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		
		initView();
	}

	private void initView() {
		
		mListView = (ListView) findViewById(R.id.listView1);
		/*
		 * BaseAdapter
		 */
//		String[] arr_data = {"����", "Ӣ��", "��ѧ", "����"};
//		arr_adAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_data);
//		mListView.setAdapter(arr_adAdapter);
		
		/*
		 * simpleAdapter
		 * 
		 * data:����Դ�� List<? extends Map<String, ?>> data����һ��map��ɵ�List����
		 * 		ÿ��map��ӦlistView�б��е�һ��
		 * 		ÿ��map����ֵ�ԣ��еļ�Ϊfrom��ָ���ļ�
		 * from:map�ļ�ֵ��
		 * to:��ͼID����from��Ӧ
		 * 
		 * from��Ϊ��ֵ���飬��to��Ӧ��ͼ��id,map�е�Keyֵ��from��Ӧ��value��ʵ�ʵ�����
		 */
		datalist = new ArrayList<Map<String, Object>>();
		datalist = getData();
		simp_aAdapter = new SimpleAdapter(this, datalist, R.layout.list_item, 
				new String[]{"pic", "text"}, 
				new int[]{R.id.list_item_img, R.id.list_item_textview});
		mListView.setAdapter(simp_aAdapter);
	}

	private List<Map<String, Object>> getData() {
		for(int i = 0; i < 20; i++)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pic", R.drawable.ic_launcher);
			map.put("text", "Ļ��" + i);
			datalist.add(map);
		}
		return datalist;
	}
}
