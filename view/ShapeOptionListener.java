package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeOptionListener implements ActionListener {

	private SwingView sv;
	
	public ShapeOptionListener(SwingView swingView) {
		sv = swingView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedShape = e.getActionCommand();
		sv.relayShapeOption(selectedShape);
	}
}