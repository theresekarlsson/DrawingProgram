package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ExitListener implements ActionListener, WindowListener {
	
	private SwingView sv;

	public ExitListener(SwingView swingView) {
		sv = swingView;
	}
	
	public void actionPerformed(ActionEvent e) {
		sv.relayExit();
	}

	public void windowClosing(WindowEvent e) {
		sv.relayExit();
	}
	
	public void windowActivated(WindowEvent arg0) {}

	public void windowClosed(WindowEvent arg0) {}

	public void windowDeactivated(WindowEvent arg0) {}

	public void windowDeiconified(WindowEvent arg0) {}

	public void windowIconified(WindowEvent arg0) {}

	public void windowOpened(WindowEvent arg0) {}

}