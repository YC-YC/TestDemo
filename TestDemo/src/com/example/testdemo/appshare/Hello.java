package com.example.testdemo.appshare;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 *@Author Administrator
 *@Time 2016-2-24 ����9:11:59
 */
public class Hello {
	public static final String PERMISSION_SAY_HELLO = "com.example.testdemo.appshare.permission.SAY_HELLO";

	public static void sayHello(Context context)
	{
		int getResult = context.checkCallingOrSelfPermission(PERMISSION_SAY_HELLO);
		if (getResult != PackageManager.PERMISSION_GRANTED)
		{
			throw new SecurityException("ִ����Ҫ��" + PERMISSION_SAY_HELLO+"Ȩ��");
		}
	}
}
