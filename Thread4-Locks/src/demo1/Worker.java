package demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
	
	private Random random = new Random();
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();

	public void main() {
		System.out.println("Starting... ");
		long start = System.currentTimeMillis();
		//process();
		Thread t1 = new Thread(new Runnable() { 
			
			@Override
			public void run() {
				process();
			}
		});
		Thread t2 = new Thread(new Runnable() { 
			
			@Override
			public void run() {
				process();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Time take: "+(end-start));
		System.out.println("List1: "+list1.size());
		System.out.println("List2: "+list2.size());
		
		//synchronized in the method: 7000 ms
		//synchronized implemented inside the method: 3500 ms

	}
	
	//synchronized is a good option, but it take more time due to the internal lock.
	// public synchronized void stageOne(){...} 
	public void stageOne() {
		synchronized (lock1) { //With this implementation, a thread can run this, not two threads at the same time. Thus, the other thread can execute another method while this is been executed.
			try {
				Thread.sleep(1);
				list1.add(random.nextInt(100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
				list2.add(random.nextInt(100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	public void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
		
	}
}
