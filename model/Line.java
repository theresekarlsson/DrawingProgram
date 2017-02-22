package model;

import java.awt.Color;
import java.awt.Graphics;
import view.DrawingUtilInterface;

public class Line extends DrawingShape {

	private static final long serialVersionUID = 1L;

	public void draw(Graphics g) {
		di.drawLine(this, g);
	}
	
	public Line(DrawingUtilInterface di, int start_x, int start_y, int end_x, int end_y, int line_thickness, Color color) {
		super(di);
		setX1(start_x);
		setY1(start_y);
		setWidth(end_x);
		setHeight(end_y);
		setLineWidth(line_thickness);
		setLineColor(color);
		setAreaColor(Color.BLACK);
	}
	
	// TODO Inte bra nu, rektangelform.
	public DrawingShape checkCoordinatesForShape(int xCheck, int yCheck) {
		
		if (((xCheck > x1 && xCheck < width) && (yCheck > y1 && yCheck < height)) || 
				((xCheck < x1 && xCheck > width) && (yCheck > y1 && yCheck < height)) || 
				((xCheck < x1 && xCheck > width) && (yCheck < y1 && yCheck > height)) ||
				((xCheck > x1 && xCheck < width) && (yCheck < y1 && yCheck > height))) {
			
			System.out.println("Här är en linje");
			return this;
		}
		else {
			return null;
		}
	}
}