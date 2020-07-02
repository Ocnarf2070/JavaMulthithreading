package demo1;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Processor proc1 = new Processor();
		proc1.start();
		
		System.out.println("Press return to stop ...");
		try (Scanner scanner = new Scanner(System.in)) {
			scanner.nextLine();
		}
		proc1.shutdown();
	}
}
