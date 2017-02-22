package view;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class DrawingPanelListener extends MouseInputAdapter {

	private SwingView sv;
	
	public DrawingPanelListener(SwingView swingView) {
		sv = swingView;
	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();     
		int y = e.getY();
		sv.relayMousePressed(x, y);
	}

	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		sv.relayMouseReleased(x, y);
	}
	
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		sv.relayMouseDragged(x, y);
	}
	
	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mouseMoved(MouseEvent arg0) {

	}
}