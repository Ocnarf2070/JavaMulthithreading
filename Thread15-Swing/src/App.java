import javax.swing.SwingUtilities;

import controller.Controller;
import view.MainFrame;
import worker.Worker;

public class App {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				createGUI();
			}
		});
	}

	protected static void createGUI() {
		MainFrame frame = new MainFrame("Swingworker Demo");
		Worker worker = new Worker(frame);
		Controller ctr = new Controller(worker);
		frame.addController(ctr);
	}
}
