package com.example.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/*
 * openFileOutput()���ڰ�����������ļ���
 * �������ļ����浽/data/data/<package>/filesĿ¼
 */
public class FileActivity extends Activity {
	
	private static final String FILE_NAME = "text.txt";
	
	private EditText et_filecontent;
	private EditText et_fileShow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.file);
		et_filecontent = (EditText) findViewById(R.id.et_filecontent);
		et_fileShow = (EditText) findViewById(R.id.et_fileshow);
		/*File file = new File(Environment.getExternalStorageDirectory() + FILE_NAME);
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		file.delete();*/
		
//		File file = this.getFilesDir();//��ǰӦ��Ŀ¼
//		File dir = this.getCacheDir();//�����ļ�Ŀ¼
//		File dir = this.getDir("imooc", MODE_PRIVATE);//����һ����Ŀ¼

//		this.getExternalCacheDir();//����SD����cacheĿ¼
		
	}
	
	public void saveFile(View view)
	{
		WriteFiles(et_filecontent.getText().toString());
		et_fileShow.setText(ReadFiles());
	}
	
	private void WriteFiles(String content)
	{
		try {
			FileOutputStream fos = openFileOutput("test.txt", MODE_PRIVATE);
			fos.write(content.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String ReadFiles()
	{
		String content = null;
		try {
			FileInputStream fis = openFileInput("test.txt");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = fis.read(buffer)) != -1)
			{
				baos.write(buffer, 0, len);
			}
			content = baos.toString();
			fis.close();
			baos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
}
