package com.example.contentprovider;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

public class ProviderActivity extends Activity {

	private static final String TAG = "ProviderActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	/*
	 * ��ȡϵͳ�Դ���ͨѶ¼
	 * ��Ҫ��Ӷ��绰����д�绰��Ȩ��
	 */
	private void getSystemContracts()
	{
		ContentResolver resolver = getContentResolver();
		Cursor cursor = resolver.query(Contacts.CONTENT_URI, 
				new String[]{Contacts._ID, Contacts.DISPLAY_NAME}, 
				null, null, null);
		if (null != cursor)
		{
			while(cursor.moveToNext())
			{
				int id = cursor.getInt(cursor.getColumnIndex(Contacts._ID));
				String name = cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME));
				LOG("id=" + id +" name=" + name);
				/*
				 * ��ѯ��ϵ�˵绰����
				 */
				Cursor cursor2 = resolver.query(Phone.CONTENT_URI, 
						new String[]{Phone.NUMBER, Phone.TYPE}, //�����
						Phone.CONTACT_ID + "=" + id, //�����ǲ鵽��id
						null, null);
				if (null != cursor2)
				{
					while(cursor2.moveToNext())
					{
						int type = cursor2.getInt(cursor2.getColumnIndex(Phone.TYPE));
						if (type == Phone.TYPE_HOME)//��ͥ����
						{
							int number = cursor2.getInt(cursor2.getColumnIndex(Phone.NUMBER));
							LOG("��ͥ���룺" + number);
						}
						else if (type == Phone.TYPE_MOBILE)//�ֻ�����
						{
							int number = cursor2.getInt(cursor2.getColumnIndex(Phone.NUMBER));
							LOG("�ֻ����룺" + number);
						}
					}
					cursor2.close();
				}
				
				/*
				 * ����ID��ѯ�����ַ
				 */
				Cursor cursor3 = resolver.query(Email.CONTENT_URI,
						new String[]{Email.DATA, Email.TYPE},
						Email.CONTACT_ID + "=" + id, 
						null, null);
				if (null != cursor3)
				{
					while(cursor3.moveToNext())
					{
						int type = cursor3.getInt(cursor3.getColumnIndex(Email.TYPE));
						if (type == Email.TYPE_WORK)//��������
						{
							String email = cursor3.getString(cursor3.getColumnIndex(Email.DATA));
							LOG("�����ǣ�" + email);
						}
					}
					cursor3.close();
				}
			}
			cursor.close();
		}
	}
	
	private void insertItem()
	{
		ContentResolver resolver = getContentResolver();
		ContentValues values = new ContentValues();
		Uri uri = resolver.insert(RawContacts.CONTENT_URI, values);
		long id = ContentUris.parseId(uri);
		//��������
		values.clear();
		values.put(StructuredName.RAW_CONTACT_ID, id);
		values.put(StructuredName.DISPLAY_NAME, "����");
		values.put(StructuredName.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
		resolver.insert(Data.CONTENT_URI, values);
		
		//����绰��Ϣ
		values.clear();
		values.put(Phone.RAW_CONTACT_ID, id);
		values.put(Phone.NUMBER, "234567");
		values.put(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
		resolver.insert(Data.CONTENT_URI, values);
		
	}
	
	private void LOG(String string)
	{
		Log.e(TAG, string);
	}
}
