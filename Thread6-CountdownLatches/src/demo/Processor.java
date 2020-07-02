package demo;

import java.util.concurrent.CountDownLatch;

public class Processor implements Runnable{
	private CountDownLatch latch;
	
	
	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}


	@Override
	public void run() {
		System.out.println("Started.");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();
	}
}
