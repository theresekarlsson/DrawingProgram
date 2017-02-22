package model;

import java.awt.Color;
import java.awt.Graphics;
import view.DrawingUtilInterface;

/**
 * The Class DrawingShape.You need to use this class as a superclass for all your
 * shapes. The DrawingAPI (which is placed in the View package) relies on DrawingShape 
 * as input to drawing the different shapes on screen.
 */
public class DrawingShape implements DrawingComposite {

	private static final long serialVersionUID = 1L;
	
	DrawingUtilInterface di;	/** The DrawingUtilInterface. */
	
	int width;
	int x1;
	int y1;
	int height;
	int lineWidth;
	Color lineColor;
	Color areaColor;
	DrawingShape ds;
	
	/** Instantiates a new drawing shape.
	 * @param di the di
	 */
	public DrawingShape(DrawingUtilInterface di) {
		this.di = di;
	}
	
	public void setDrawingAPI (DrawingUtilInterface di) {
		this.di=di;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}
	
	public int getX1() {
		return x1;
	}

	public void setWidth(int w) {
		this.width = w;
	}
	
	public int getWidth() {
		return width;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}
	
	public int getY1() {
		return y1;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public void setLineWidth(int w) {
		lineWidth = w;
	}

	public int getLineWidth() {
		return lineWidth;
	}

	public void setLineColor(Color c) {
		lineColor = c;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setAreaColor(Color c) {
		areaColor = c;
	}

	public Color getAreaColor() {
		return areaColor;
	}

	public void add(DrawingComposite d) {
		// TODO Auto-generated method stub
		
	}

	public void remove(DrawingComposite d) {
		// TODO Auto-generated method stub
		
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
	}
	
	public DrawingShape checkCoordinatesForShape(int xCheck, int yCheck) {
		return ds;
	}
}