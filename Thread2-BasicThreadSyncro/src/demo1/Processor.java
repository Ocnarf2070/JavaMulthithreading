package demo1;

public class Processor extends Thread{
	//Keyword volatile for helping in the access of a variable from two threads 
	//Putting this keyword make the program to function in any system and java version 
	private volatile boolean running = true;
	@Override
	public void run() {
		while(running) {
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void shutdown() {
		running = false;
	}
}
