package com.example.testdemo.third.base;

import java.io.File;

import com.example.testdemo.R;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;


/*
 * SQLite֧��NULL/InTEGER/REAL/TEXT/BLOB
 * ������̿�����ͬһʱ���ͬһ���ݿ��ȡ���ݣ���ֻ��һ������д������
 * 1��SQLiteDatabase
 * 		�ṩһЩ����SQLite���ݿ����
 * 		�ṩ������ɾ����ִ��SQL���ִ�������������ݿ��������ķ�����ÿ��Ӧ��ֻ��һ�����ݿ�
 * 		execSQL/insert/delete/update/query/rawQuery
 * 2��SQLiteOpenHelper
 * 
 */
public class SQLiteActivity extends Activity {

	private TextView tv;
	
	private static final String TABLE_NAME = "usertb";
	
	private SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite);
		tv = (TextView) findViewById(R.id.sqlite);
		//����һ�����ݿⲢ�Ҵ�
		db = openOrCreateDatabase("mysqlite1.db", MODE_PRIVATE, null);
		
		
		tv.setText("�������ݿ�\r\n");
		db.execSQL("create table if not exists "+TABLE_NAME + "(_id integer primary key autoincrement, name text not null, age integer not null, sex text not null)");
		tv.append("ִ��execSQL:create table if not exits "+TABLE_NAME + "(_id integer primary key autoincrement, name text not null, age integer not null, sex text not null)\r\n");
		
//		db.execSQL("delete from "+TABLE_NAME);
//		tv.append("\r\n��ձ�������");
//		db.execSQL("create table "+TABLE_NAME + "(_id integer primary key autoincrement, name text not null, age integer not null, sex text not null)");
		
		tv.append("\r\nִ��execSQL������1������");
		db.execSQL("insert into  "+TABLE_NAME + "(name,sex,age) values('����','��','18')");
		/*
		 * ContentValues����hashmap
		 */
		ContentValues values = new ContentValues();
		values.put("name", "����");
		values.put("sex", "Ů");
		values.put("age", "19");
		db.insert(TABLE_NAME, null, values);
		values.clear();
		values.put("name", "����");
		values.put("sex", "Ů");
		values.put("age", "20");
		db.insert(TABLE_NAME, null, values);
		values.clear();
		values.put("name", "����");
		values.put("sex", "Ů");
		values.put("age", "21");
		db.insert(TABLE_NAME, null, values);
		values.clear();
		values.put("name", "����");
		values.put("sex", "Ů");
		values.put("age", "21");
		db.insert(TABLE_NAME, null, values);
		tv.append("\r\nִ��ContentValues��������4������");
		getDBContent();
		values.clear();
		values.put("sex", "��");
		//ID����3�ĸĳɡ��С�
		tv.append("\r\n����ID����3�ĸĳɡ��С�");
		db.update(TABLE_NAME, values, "_id>?", new String[]{"3"});
		tv.append("\r\nɾ�����ִ��С��š��ļ�¼");
		db.delete(TABLE_NAME, "name like ?", new String[]{"%��%"});
		getDBContent();
		db.execSQL("drop table "+TABLE_NAME);
		tv.append("\r\n��ձ�������");
		db.close();
		
	}
	private void getDBContent() {
		//��ѯ
		Cursor cursor = db.rawQuery("select * from  "+TABLE_NAME + "", null);
		if (cursor != null)
		{
			tv.append("\r\n��ѯ������");
			while(cursor.moveToNext())
			{
				tv.append("\r\n��ȡֵΪ id=" + cursor.getInt(cursor.getColumnIndex("_id"))
						+ "name=" +cursor.getString(cursor.getColumnIndex("name"))
						+ "sex=" +cursor.getString(cursor.getColumnIndex("sex"))
						+ "age=" +cursor.getInt(cursor.getColumnIndex("age")));
			}
			cursor.close();
		}
	}
}
