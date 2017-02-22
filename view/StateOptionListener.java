package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StateOptionListener implements ActionListener {

	private SwingView sv;
	
	public StateOptionListener(SwingView swingView) {
		sv = swingView;
	}

	public void actionPerformed(ActionEvent e) {
		String selectedState = e.getActionCommand();
		sv.relayStateOption(selectedState);
	}
}