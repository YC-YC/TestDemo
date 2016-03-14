package com.example.testdemo.second.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.testdemo.R;

public class DialogActivity extends Activity implements OnClickListener {

	private Button bt_dialog1;
	private Button bt_dialog2;
	private Button bt_dialog3;
	private Button bt_dialog4;
	private Button bt_dialog5;

	String[] single_list = { "ר����", "������", "�о���", "˶ʿ��" };
	String[] mult_list = { "����", "����", "ƹ����", "��ë��" };
	String[] item_list = { "����", "��ѧ", "Ӣ��"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_activity);
		initView();
	}

	private void initView() {
		bt_dialog1 = (Button) findViewById(R.id.bt_dialog1);
		bt_dialog1.setOnClickListener(this);

		bt_dialog2 = (Button) findViewById(R.id.bt_dialog2);
		bt_dialog2.setOnClickListener(this);

		bt_dialog3 = (Button) findViewById(R.id.bt_dialog3);
		bt_dialog3.setOnClickListener(this);

		bt_dialog4 = (Button) findViewById(R.id.bt_dialog4);
		bt_dialog4.setOnClickListener(this);
		
		bt_dialog5 = (Button) findViewById(R.id.bt_dialog5);
		bt_dialog5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_dialog1:
			showDialog1();
			break;
		case R.id.bt_dialog2:
			showDialog2();
			break;
		case R.id.bt_dialog3:
			showDialog3();
			break;
		case R.id.bt_dialog4:
			showDialog4();
			break;
		case R.id.bt_dialog5:
			showDialog5();
			break;
		default:
			break;
		}
	}

	/*
	 * ȷ�϶Ի���
	 */
	private void showDialog1() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("ȷ�϶Ի���");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage("ȷ�϶Ի�����ʾ����");
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogActivity.this, "�����ȷ��", Toast.LENGTH_LONG)
						.show();
			}
		});
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogActivity.this, "�����ȡ��", Toast.LENGTH_LONG)
						.show();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	/*
	 * ��ѡ�����Ի���
	 */
	private void showDialog2() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("��ѡ�����Ի���");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setSingleChoiceItems(single_list, 0,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(DialogActivity.this,
								"����� " + single_list[which], Toast.LENGTH_LONG)
								.show();
					}
				});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	/*
	 * ��ѡ�����Ի���
	 */
	private void showDialog3() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("��ѡ�����Ի���");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMultiChoiceItems(mult_list, null,
				new DialogInterface.OnMultiChoiceClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which,
							boolean isChecked) {
						String str = mult_list[which];
						if (isChecked) {
							Toast.makeText(DialogActivity.this, "ѡ���� " + str,
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(DialogActivity.this, "ȡ���� " + str,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	/*
	 * �б�Ի���
	 */
	private void showDialog4() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("��ѡ�����Ի���");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setItems(item_list, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String str = item_list[which];
				Toast.makeText(DialogActivity.this, "ѡ���� " + str,
						Toast.LENGTH_SHORT).show();
			}
		});
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	/*
	 * �Զ���Ի���
	 */
	private void showDialog5() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("�Զ���Ի���");
		builder.setIcon(R.drawable.ic_launcher);
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.mydialog, null);
		builder.setView(view);
		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
