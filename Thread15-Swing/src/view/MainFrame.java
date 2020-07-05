package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame{
	private JLabel countLabel = new JLabel("Current value: 0");
	private JLabel statusLabel = new JLabel("Task not completed");
	private JButton startButton = new JButton("Start");
	
	public MainFrame(String title) {
		super(title);
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		add(countLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		add(statusLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		add(startButton, gc);
		
		setSize(200, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void addController(ActionListener ctr) {
		startButton.addActionListener(ctr);
		startButton.setActionCommand("START");
	}
	
	public JLabel getStatusLabel() {
		return statusLabel;
	}
	
	public JLabel getCountLabel() {
		return countLabel;
	}
}
