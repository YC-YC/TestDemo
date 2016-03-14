package com.example.testdemo.cpp;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 *@Author Administrator
 *@Time 2016-2-23
 */
public class CppActivity extends Activity {

	private int[] array = new int[]{
//			3,9,7,6,5,1
			1,9,10,7,4,5,2
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cpp_main);
	}
	
	public void doClick(View view)
	{
		switch (view.getId()) {
		case R.id.button1:
			printArray();
			LOG("bullble------------");
			bullble();
			printArray();
//			bullble2();
			LOG("insertSort------------");
			insertSort();
			printArray();
			LOG("quickSort------------");
			quickSort(0, array.length-1);
			printArray();
			break;

		default:
			break;
		}
	}

	private void printArray()
	{
		LOG("start");
		for (int item: array)
		{
			LOG(item + ",");
		}
		LOG("end");
	}
	private void LOG(String string) {
		Log.i("TestCpp", string);
	}

	//ð������,�Ӵ�С
	private void bullble() {
		int temp;
		for (int i = 0; i < array.length - 1; i++)//����
		{
			for (int j = i +1; j < array.length; j++)//û�������
			{
				if (array[i] < array[j])
				{
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
	}
	
	//ð������Ľ�,������
		private void bullble2() {
			int temp;
			int flag = 1;
			for (int i = 0; (i < array.length - 1) && flag > 0; i++)//����
			{
				flag = 0;
				for (int j = i +1; j < array.length; j++)//û�������
				{
					if (array[i] > array[j])
					{
						temp = array[i];
						array[i] = array[j];
						array[j] = temp;
						flag = 1;
					}
				}
			}
		}
		//�������򣬽�û�����Ԫ�طŵ��������б��к���λ�ã���С����
		private void insertSort() {
			int temp;
			for (int i = 1; i < array.length; i++)//û�������
			{
					temp = array[i];
					for (int j = i - 1; j >= 0; j--)//�������б�
					{
						if (array[j] < temp)
						{
							array[j+1] = array[j];
						}
						else
						{
							break;
						}
					}
			}
		}
		//��������ѡ���׼�㣬�������ǰ�棬С��������棬�ݹ�ֱ������ֻ��һ�����Ӵ�С
		private void quickSort(int left, int right) {
			if (left < right)
			{
				int k = parition(left, right);
				quickSort(left, k-1);
				quickSort(k+1, right);
			}
		}

		//��ȡ��׼���±�
		private int parition(int left, int right) {
			int key = array[right];	//���һ��Ϊ��׼��
			int i = left-1;	//ָ�����С�ڻ�׼���λ��
			int temp;
			for (int j = left; j < right; j++)
			{
				if (array[j] < key)
				{
					i++;
					temp = array[j];
					array[j] = array[i];
					array[i] = temp;
				}
			}
			//����׼����ں��ʵ�λ��
			temp = array[i+1];
			array[i+1] = array[right];
			array[right] = temp;
			return i+1;
		}
		private int parition2(int low, int high) {
			int key = array[low];	//��һ��Ϊ��׼��

			while(low < high)
			{
				//�Ӻ����ҵ�һ�����ʵ�ֵ��ǰ��Ľ���
				while (low < high && array[high] >= key)
					high--;
				swap(low,high);
				//��ǰ���ҵ�һ�����ʵ�ֵ�����Ľ���
				while (low < high && array[low] <= key)
					low++;
				swap(high,low);
			}
			return low;
		}

		private void swap(int low, int high) {
			int temp = array[low];
			array[low] = array[high];
			array[high] = temp;
		}
}
