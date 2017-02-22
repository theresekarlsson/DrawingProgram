package view;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener {

	private SwingView sv;
	private JSlider source;
	
	public SliderListener(SwingView swingView, JSlider slide) {
		sv = swingView;
		source = slide;
	}

	public void stateChanged(ChangeEvent e) {
		source = (JSlider) e.getSource();
		
		if (!source.getValueIsAdjusting()) {
	        int value = (int)source.getValue();
	        sv.relaySlider(value);
	    }
	}
}