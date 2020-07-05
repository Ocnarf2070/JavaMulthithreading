package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingWorker;

import worker.Worker;

public class Controller implements ActionListener{
	
	private SwingWorker<?, ?> worker;
	public Controller(SwingWorker<?, ?> worker) {
		this.worker=worker;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("START"))start();
		
	}
	private void start() {
		worker.execute();
	}

}
