package worker;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import view.MainFrame;

public class Worker extends SwingWorker<Boolean, Integer>{
	
	private MainFrame view;
	public Worker(MainFrame view) {
		// TODO Auto-generated constructor stub
		this.view = view;
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		for (int i = 0; i < 30; i++) {
			Thread.sleep(100);
			System.out.println("Hello "+i);
			publish(i);
		}
		return true;
	}
	
	@Override
	protected void done() {
		try {
			Boolean status = get();
			if(status)view.getStatusLabel().setText("Completed!");
			else view.getStatusLabel().setText("Not Completed.");
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void process(List<Integer> chunks) {
		int value = chunks.get(chunks.size()-1);
		view.getCountLabel().setText("Current value: "+value);
	}

}
