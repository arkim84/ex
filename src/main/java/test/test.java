package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class test {
	
	@Test
	public void test1() {
		
		double d = 100.2; // 8byte
		int t = 100; // 4byte
		int result = t + (int)d;
		
		System.out.println(d);
		System.out.println(result);
	}

	@Test
	public void test2() {
		Runnable task = new Task();
		Thread thread = new Thread(task);
		thread.run();
	}
	
	@Test
	public void test3() {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0 ; i<5; i++){
					System.out.println("���� ���� �ų�11");
				}
			}
		});
		
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0 ; i<5; i++){
					System.out.println("���� ���� �ų�22");
				}
			}
		});
		thread2.setPriority(Thread.MAX_PRIORITY);
		thread.start();
		thread2.start();
	}
	
	@Test
	public void arraysAsList() {
		List<String> list1 = Arrays.asList("ȫ�浿","�ſ��","�ڹ�");
		for(String str : list1){
			System.out.println(str);
		}
	}
	
	@Test
	public void linkedList() {
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new LinkedList<String>();
		
		long startTime;
		long endTime;
		
		startTime = System.nanoTime();
		for(int i=0; i<10; i++){
			list1.add(0,"test");
			list1.add(1, String.valueOf(i)); //���Ƿ� �ε����� ����, �ش� �ε����� ���� ������ ���� �ε����� �Ѿ?
			System.out.println("�׽�Ʈ");
			System.out.println(list1);
		}
		endTime = System.nanoTime();
		System.out.println("��̸���Ʈ"+(endTime-startTime));
		System.out.println(list1.size());
		System.out.println("�ε���5"+list1.get(5));
		
		startTime = System.nanoTime();
		for(int i=0; i<10000; i++){
			list2.add(0, String.valueOf(i));
		}
		endTime = System.nanoTime();
		System.out.println("��ũ�帮��Ʈ"+(endTime-startTime));
	}

}

