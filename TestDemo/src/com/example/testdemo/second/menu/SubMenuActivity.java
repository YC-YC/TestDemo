package com.example.testdemo.second.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.testdemo.R;

public class SubMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_option);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/*
		 * �ֶ����
		 */
		/*SubMenu file = menu.addSubMenu("�ļ�");
		SubMenu edit = menu.addSubMenu("�༭");
		file.setHeaderTitle("�ļ�����");
		file.setHeaderIcon(R.drawable.ic_launcher);
		file.add(1, 1, 1, "�½�");
		file.add(1, 2, 1, "��");
		file.add(1, 3, 1, "����");

		edit.setHeaderTitle("�༭����");
		edit.setHeaderIcon(R.drawable.ic_launcher);
		edit.add(2, 1, 1, "����");
		edit.add(2, 2, 1, "ճ��");
		edit.add(2, 3, 1, "����");*/
		
		/*
		 * xml���
		 */
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.sub_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		if (item.getGroupId() == 1 || item.getGroupId() == R.id.submenu1) {
			switch (item.getItemId()) {
			case 1:
			case R.id.submenu1_1:
				Toast.makeText(this, "������½�", Toast.LENGTH_SHORT).show();
				break;
			case 2:
			case R.id.submenu1_2:
				Toast.makeText(this, "����˴�", Toast.LENGTH_SHORT).show();
				break;
			case 3:
			case R.id.submenu1_3:
				Toast.makeText(this, "����˱���", Toast.LENGTH_SHORT).show();
				break;
			case R.id.submenu1_4:
				Toast.makeText(this, "��������Ϊ", Toast.LENGTH_SHORT).show();
				break;
			case R.id.submenu2_1:
				Toast.makeText(this, "����˸���", Toast.LENGTH_SHORT).show();
				break;
			case R.id.submenu2_2:
				Toast.makeText(this, "�����ճ��", Toast.LENGTH_SHORT).show();
				break;
			case R.id.submenu2_3:
				Toast.makeText(this, "����˼���", Toast.LENGTH_SHORT).show();
				break;
			case R.id.submenu2_4:
				Toast.makeText(this, "�����ɾ��", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
//		} else if (item.getGroupId() == 2|| item.getGroupId() == R.id.submenu2) {
//			switch (item.getItemId()) {
//			case 1:
//			case R.id.submenu2_1:
//				Toast.makeText(this, "����˸���", Toast.LENGTH_SHORT).show();
//				break;
//			case 2:
//			case R.id.submenu2_2:
//				Toast.makeText(this, "�����ճ��", Toast.LENGTH_SHORT).show();
//				break;
//			case 3:
//			case R.id.submenu2_3:
//				Toast.makeText(this, "����˼���", Toast.LENGTH_SHORT).show();
//				break;
//			case R.id.submenu2_4:
//				Toast.makeText(this, "�����ɾ��", Toast.LENGTH_SHORT).show();
//				break;
//			}
//		}
		return super.onOptionsItemSelected(item);
	}
}
