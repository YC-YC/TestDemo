package com.example.testdemo.second.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testdemo.R;

/*
 * 1����Viewע�������Ĳ˵�registerForContextMenu
 * 2��������ݵ������Ĳ˵�
 * 	���ֶ�����xml���
 * 3�����õ���¼�onContextItemSelected()
 */
public class ContextMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_context);
		showListView();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("�ļ�����");
		menu.setHeaderIcon(R.drawable.ic_launcher);
		/*
		 * �ֶ����
		 */
//		menu.add(1, 1, 1, "����");
//		menu.add(1, 2, 1, "ճ��");
//		menu.add(1, 3, 1, "����");
//		menu.add(1, 4, 1, "ɾ��");
		
		/*
		 * xml���
		 */
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
		case R.id.contextmenu1:
			Toast.makeText(this, "����˸���", Toast.LENGTH_SHORT).show();
			break;
		case 2:
		case R.id.contextmenu2:
			Toast.makeText(this, "�����ճ��", Toast.LENGTH_SHORT).show();
			break;
		case 3:
		case R.id.contextmenu3:
			Toast.makeText(this, "����˼���", Toast.LENGTH_SHORT).show();
			break;
		case 4:
		case R.id.contextmenu4:
			Toast.makeText(this, "�����ɾ��", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	private void showListView() {
		ListView listView = (ListView) findViewById(R.id.contextmenu_list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getData());
		listView.setAdapter(adapter);
		this.registerForContextMenu(listView);//��view��������Ĳ˵�
	}

	private ArrayList<String> getData() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			list.add("�ļ�" + i);
		}
		return list;
	}
}
