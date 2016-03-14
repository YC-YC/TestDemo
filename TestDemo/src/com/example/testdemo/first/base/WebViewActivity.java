package com.example.testdemo.first.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.testdemo.R;

public class WebViewActivity extends Activity {

	private static final String URL = "http://2014.qq.com"; 
	private WebView webView;
	private ProgressDialog mProgressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		
		initView();
	}

	private void initView() {

		webView = (WebView) findViewById(R.id.webview);
		webView.loadUrl(URL);
		//����WebViewĬ��ͨ������������ϵͳ���������ҳ����Ϊ��ʹ����ҳ������WebView�д�
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//����trueʱ�������ҳ��WebView�д� 
				view.loadUrl(url);
				return true;
			}
		});
		
		/*
		 * ����֧��javascript
		 */
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		
		/*
		 * ����ҳ������ʹ�û������
		 */
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		
		
		/*
		 * ��ȡ���ؽ���
		 */
		webView.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				if (newProgress == 100)
				{
					//�������
					closeDialog();
				}
				else
				{
					openDialog(newProgress);
				}
			}

			private void openDialog(int newProgress) {
				if (mProgressDialog == null)
				{
					mProgressDialog = new ProgressDialog(WebViewActivity.this);
					mProgressDialog.setIcon(R.drawable.ic_launcher);
					mProgressDialog.setTitle("���ؽ���");
					mProgressDialog.show();
				}
				mProgressDialog.setProgress(newProgress);
				mProgressDialog.setMessage("��ǰ���ؽ���Ϊ��" + newProgress + "%");
			}

			private void closeDialog() {
				if (mProgressDialog != null && mProgressDialog.isShowing())
				{
					mProgressDialog.dismiss();
					mProgressDialog = null;
				}
			}
		});
	}
	
	/*
	 * �޸ķ��ؼ��Ĵ���(non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			Toast.makeText(this, "" + webView.getUrl(), Toast.LENGTH_LONG).show();
			if (webView.canGoBack())
			{
				webView.goBack();
				return true;
			}
			else
			{
				System.exit(0);//�˳��̶�
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
