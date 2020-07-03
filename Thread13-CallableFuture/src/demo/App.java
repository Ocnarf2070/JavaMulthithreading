package demo;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class App {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Integer> future = executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
					Random rnd = new Random();
					int duration = rnd.nextInt(4000);
					if(duration > 2000) throw new IOException("Sleeping for to long.");
					System.out.println("Starting ...");
					try {
						Thread.sleep(duration);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Finished.");
					return duration;
			}
		});
		
//		Future <?> future2 = executor.submit(new Callable<Void>() {
//
//			@Override
//			public Void call() throws Exception {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		});
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);

		try {
			System.out.println("The result is: "+future.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getCause().getMessage());
		}
	}
}
