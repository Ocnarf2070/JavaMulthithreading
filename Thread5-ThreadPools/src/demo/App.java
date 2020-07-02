package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; i++) {
			executor.submit(new Processor(i));
		}
		executor.shutdown();
		System.out.println("All task submited.");
		executor.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("All task completed. ");
	}

}
