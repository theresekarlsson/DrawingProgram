package model;

import java.awt.Color;
import java.awt.Graphics;
import view.DrawingUtilInterface;

public class Rect extends DrawingShape {

	private static final long serialVersionUID = 1L;

	public void draw(Graphics g) {
		di.drawRect(this, g);
	}
	
	public Rect(DrawingUtilInterface di, int x1, int y1, int w, int h, int width, Color lineColor, Color area) {
		super(di);
		setX1(x1);
		setWidth(w);
		setY1(y1);
		setHeight(h);
		setLineWidth(width);
		setLineColor(lineColor);
		setAreaColor(area);
	}
	
	public DrawingShape checkCoordinatesForShape(int xCheck, int yCheck) {
		int x2 = x1 + width;
		int y2 = y1 + height;
		if ((xCheck > x1 && xCheck < x2) && (yCheck > y1 && yCheck < y2)) {
			System.out.println("Här är en fyrkant.");
			return this;
		}
		else {
			return null;
		}
	}
}