package com.example.testdemo.flashing;

import com.example.testdemo.Utils;

import android.os.Bundle;


/**
 * ������ģʽ
 *@Author Administrator
 *@Time 2016-3-4 ����1:08:21
 */
public class FlashLightMainActivity extends FlashActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Utils.LogFlash("FlashLightMainActivity onCreate ------");
		super.onCreate(savedInstanceState);
		Utils.LogFlash("FlashLightMainActivity onCreate ++++++");
	}
	
}
