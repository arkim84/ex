package test;

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
					System.out.println("많이 보던 거네11");
				}
			}
		});
		
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0 ; i<5; i++){
					System.out.println("많이 보던 거네22");
				}
			}
		});
		thread2.setPriority(Thread.MAX_PRIORITY);
		thread.start();
		thread2.start();
	}

}

