package demo;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
	
	private static void producer() throws InterruptedException {
		Random rnd = new Random();
		
		while (true) {
			queue.put(rnd.nextInt(100));
		}
	}
	
	private static void consumer() throws InterruptedException {
		Random rnd = new Random();
		while(true) {
			Thread.sleep(100);
			if(rnd.nextInt(10) == 0) {
				int value = queue.take();
				System.out.printf("Taken value: %d; Queue size is: %d%n", value, queue.size());
			}
		}
	}
}
