package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/*
 * �̳�ContentProvider
 * mxl���ע�Ტ��
 * ��д����
 * uri:content://com.exampel.contentprovider/music/#
 * content://���̶���ʽ
 * com.exampel.contentprovider:mxl��ע���provider�е�android:authorites���Զ�Ӧ��ֵ
 * music���������ĸ���Ŀ
 * #:ָ����Ŀ�µ�������¼��#��ͨ�����
 * 
 * ͨ��UriMatcher���ͷ�װUri
 * ͨ��ContentResolver����ContentProvider�е�����
 * 
 */
public class MyProvider extends ContentProvider {

	//Provider��������ʱ����� 
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}
	//����Uri��selection��ѯ����
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	//���ص�ǰUri��MIME���ͣ������Uri��Ӧ�������ж�����¼����ôMIME�����ַ���������vnd.anrdoid.dir��ͷ
	//���ֻ��һ����MIMEy������vnd.android.cursor.item��ͷ
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	//����Uri��������
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	//����Uri��selectionɾ������
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	//����Uri�޸�selectionָ����ȫ����¼
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
