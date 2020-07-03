package demo;

import java.util.LinkedList;
import java.util.Random;

public class Processor {
	private LinkedList<Integer> list = new LinkedList<>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	
	public void produce() throws InterruptedException{
		int value=0;
		while(true) {
			synchronized (lock) {
				while(list.size()==LIMIT) lock.wait();
				list.add(value++);
			}
		}
	}
	
	public void consume() throws InterruptedException{
		Random rnd = new Random();
		while (true) {
			synchronized (lock) {
				while(list.size()==0) lock.wait();
				System.out.print("List size is: "+list.size());
				int value = list.removeFirst();
				System.out.println("; First value is: "+value);
				lock.notify();
			}
			Thread.sleep(rnd.nextInt(1000));
		}
	}


}
