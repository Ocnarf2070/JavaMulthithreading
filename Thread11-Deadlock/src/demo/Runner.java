package demo;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	private Account acc1 = new Account();
	private Account acc2 = new Account(20000);
	private Random rnd = new Random();
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private void acquireLock(Lock firstLock, Lock secondLock) throws InterruptedException {
		while(true) {
			//Acquire lock
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			try {
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			}
			finally {
				if(gotFirstLock&&gotSecondLock) {
					return;
				}
				if(gotFirstLock) {
					firstLock.unlock();
				}
				if(gotSecondLock) {
					secondLock.unlock();
				}
			}
			//Lock not acquired
			Thread.sleep(1);
		}
	}

	
	public void firstThread() throws InterruptedException {
		for (int i = 0; i < 10000; i++) {
			//lock1.lock(); if it is not implement in a certain order in both methods, it can lead in a Deadlock.
			//lock2.lock(); It happens because the same lock try to lock in different times, making to not proceed the execution of the program. 
			acquireLock(lock1, lock2);
			try {
				Account.transfer(acc1, acc2, rnd.nextInt(100));
			}
			finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
	public void secondThread() throws InterruptedException {
		for (int i = 0; i < 10000; i++) {
			//lock1.lock();
			//lock2.lock();
			acquireLock(lock1, lock2);
			try {
				Account.transfer(acc2, acc1, rnd.nextInt(100));
			}
			finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
	public void finished() {
		System.out.printf("Account 1 balance: %d; Account 2 balance: %d%n",acc1.getBalance(),acc2.getBalance());
		System.out.println("Total balance: "+(acc1.getBalance() + acc2.getBalance()));
	}
}
