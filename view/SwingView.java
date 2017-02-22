package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import controller.Controller;
import model.DrawingContainer;

public class SwingView {
	
	private static final int MIN = 0, MAX = 10, INIT = 0;
	private Controller con;
	private ExitListener exitListener;
	private DrawingPanelListener drawPanelListener;
	private DrawingUtil drawUtil;
	private JColorChooser colorChooser;
	private JLabel lblColorFill, lblColorOutline, lblThickness;
	private ButtonGroup rBtnStateGroup, rBtnShapeGroup;
	private JFrame frame;
	private DrawingPanel drawPanel;
	private JRadioButton rBtnLine, rBtnCircle, rBtnSquare;
	private JButton btnColorFill, btnColorOutline, btnDelete;
	private JSlider slider;
	private DrawingContainer drawCon;
	
	public SwingView (Controller c) {
		con = c;
		drawUtil = new DrawingUtil();
		exitListener = new ExitListener(this);
		drawPanelListener = new DrawingPanelListener(this);
	}

	public void repaintGUI() {
		drawPanel.setDc(drawCon);
		drawPanel.repaint();
		drawPanel.revalidate();
	}
	
	public DrawingUtil getDrawUtil() {
		return drawUtil;
	}

	public void relayColorFill() {
		Color chosenColorFill = JColorChooser.showDialog(colorChooser, "Välj fyllnadsfärg", null); 
		lblColorFill.setBackground(chosenColorFill);
		con.handleColorFill(chosenColorFill);
	}

	public void relayColorOutline() {
		Color chosenColorOutline = JColorChooser.showDialog(colorChooser, "Välj kantfärg", null); 
		lblColorOutline.setBackground(chosenColorOutline);
		con.handleColorOutline(chosenColorOutline);
	}

	public void relayStateOption(String selectedState) {
		con.handleStateOption(selectedState);
	}

	public void relayShapeOption(String selectedShape) {
		con.handleShapeOption(selectedShape);
	}

	public void relaySlider(int value) {
		con.handleSlider(value);
	}

	public void relayExit() {
		con.handleExit();
	}

	public void relayMousePressed(int x, int y) {
		con.handleMousePressed(x, y);
	}

	public void relayMouseReleased(int xEnd, int yEnd) {
		con.handleMouseReleased(xEnd, yEnd);
	}

	public void relayMouseDragged(int xDown, int yDown) {
		con.handleMouseDragged(xDown, yDown);
	}
	
	public void relayDelete() {
		con.handleDelete();
	}

	public void disableAttributeOptions() {
		rBtnCircle.setEnabled(false);
		rBtnSquare.setEnabled(false);
		rBtnLine.setEnabled(false);
		btnColorFill.setEnabled(false);
		btnColorOutline.setEnabled(false);
		lblThickness.setEnabled(false);
		slider.setEnabled(false);
		btnDelete.setEnabled(true);
	}

	public void enableAttributeOptions() {
		rBtnCircle.setEnabled(true);
		rBtnSquare.setEnabled(true);
		rBtnLine.setEnabled(true);
		btnColorFill.setEnabled(true);
		btnColorOutline.setEnabled(true);
		lblThickness.setEnabled(true);
		slider.setEnabled(true);
		btnDelete.setEnabled(false);
	}

	public void enableColorOutline() {
		btnColorOutline.setEnabled(true);
	}

	public void disableColorOutline() {
		btnColorOutline.setEnabled(false);
	}
	
	/* Skapar fönster */
	public void drawGUI(DrawingContainer dc) {
		
		drawCon = dc;
		
		// Edit area
		JPanel editPanel = new JPanel();
		editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
		
		// Choose state (paint or edit)
		rBtnStateGroup = new ButtonGroup();
		JRadioButton rBtnPaint = new JRadioButton("Måla ny", true);
		JRadioButton rBtnEdit = new JRadioButton("Förändra befintlig");
		rBtnPaint.addActionListener(new StateOptionListener(this));
		rBtnEdit.addActionListener(new StateOptionListener(this));
		rBtnStateGroup.add(rBtnPaint);
		rBtnStateGroup.add(rBtnEdit);
		
		editPanel.add(Box.createVerticalStrut(5));
		editPanel.add(rBtnPaint);
		editPanel.add(rBtnEdit);
		
		// Choose shape
		rBtnCircle = new JRadioButton("Cirkel", true);
		rBtnSquare = new JRadioButton("Fyrkant");
		rBtnLine = new JRadioButton("Linje");
		rBtnLine.addActionListener(new ShapeOptionListener(this));
		rBtnCircle.addActionListener(new ShapeOptionListener(this));
		rBtnSquare.addActionListener(new ShapeOptionListener(this));
		
		rBtnShapeGroup = new ButtonGroup();
		rBtnShapeGroup.add(rBtnCircle);
		rBtnShapeGroup.add(rBtnSquare);
		rBtnShapeGroup.add(rBtnLine);
		
		editPanel.add(Box.createVerticalStrut(25));
		editPanel.add(rBtnCircle);
		editPanel.add(rBtnSquare);
		editPanel.add(rBtnLine);
		
		// Choose fill color
		JPanel fillColorPanel = new JPanel();
		fillColorPanel.setLayout(new BoxLayout(fillColorPanel, BoxLayout.X_AXIS));
		btnColorFill = new JButton("Fyllnadsfärg");
		btnColorFill.addActionListener(new ColorFillListener(this));
		lblColorFill = new JLabel("        ");
		lblColorFill.setOpaque(true);
		lblColorFill.setBorder(BorderFactory.createLineBorder(Color.black));
		fillColorPanel.add(btnColorFill);
		fillColorPanel.add(Box.createHorizontalStrut(15));
		fillColorPanel.add(lblColorFill);
		
		editPanel.add(Box.createVerticalStrut(5));
		editPanel.add(fillColorPanel);
		
		// Choose line color
		JPanel lineColorPanel = new JPanel();
		lineColorPanel.setLayout(new BoxLayout(lineColorPanel, BoxLayout.X_AXIS));
		btnColorOutline = new JButton("Kantlinjefärg");
		btnColorOutline.addActionListener(new ColorOutlineListener(this));
		lblColorOutline = new JLabel("        ");
		lblColorOutline.setOpaque(true);
		lblColorOutline.setBorder(BorderFactory.createLineBorder(Color.black));
		lineColorPanel.add(btnColorOutline);
		lineColorPanel.add(Box.createHorizontalStrut(15));
		lineColorPanel.add(lblColorOutline);
		
		editPanel.add(Box.createVerticalStrut(20));
		editPanel.add(lineColorPanel);
		
		// Choose thickness of line with slider
		lblThickness = new JLabel("Linjetjocklek");
		slider = new JSlider(JSlider.HORIZONTAL,MIN, MAX, INIT);
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(new SliderListener(this, slider));
		
		editPanel.add(Box.createVerticalStrut(25));
		editPanel.add(lblThickness);
		editPanel.add(slider);

		// Delete button
		btnDelete = new JButton("Ta bort");
		btnDelete.addActionListener(new DeleteListener(this));
		
		// Close button
		JButton btnClose = new JButton("Avsluta");
		btnClose.addActionListener(exitListener);
		
		editPanel.add(Box.createVerticalStrut(25));
		editPanel.add(btnDelete);
		editPanel.add(Box.createVerticalStrut(10));
		editPanel.add(btnClose);
		editPanel.add(Box.createVerticalStrut(10));
		
		
		// Drawing area
		drawPanel = new DrawingPanel(dc);
		drawPanel.setBackground(Color.WHITE);
		drawPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		drawPanel.addMouseListener(drawPanelListener);
		drawPanel.addMouseMotionListener(drawPanelListener);
		
		// Window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize(screenSize.width*(0.50), screenSize.height*(0.60));
        int newHeight = screenSize.height;
        int newWidth = screenSize.width;
		frame = new JFrame("Draw");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(exitListener);
		frame.setSize(newWidth, newHeight);
		frame.setVisible(true);
		
		// Add components to window
		frame.add(editPanel, BorderLayout.WEST);
		frame.add(drawPanel);
	}
}