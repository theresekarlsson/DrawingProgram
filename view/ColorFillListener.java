package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorFillListener implements ActionListener {

	private SwingView sv;
	
	public ColorFillListener(SwingView swingView) {
		sv = swingView;
	}

	public void actionPerformed(ActionEvent e) {
		sv.relayColorFill();
	}
}