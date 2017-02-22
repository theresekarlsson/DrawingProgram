package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Vector;

import view.DrawingUtilInterface;

/**
 * The Class DrawingContainer. This class can be used as the Container for your Drawings. 
 * However this is a convenience class provided for your leisure. You can build you're own
 * much better version. Just remember to implement DrawingComposite. 
 */
public class DrawingContainer implements DrawingComposite, Cloneable {
	
	private static final long serialVersionUID = 1L;
	private Vector<DrawingComposite> v;
	
	/**
	 * Instantiates a new drawing container. It also instantiates the Vector used for the members the container holds.
	 */
	public DrawingContainer() {
		v = new Vector<DrawingComposite>();
	}
	
	/**
	 * Iterates over all members calling their respective draw methods. 
	 * This structure can easily be duplicated for creating other chains, 
	 * like identifying a component at at specific location.
	 *
	 * @param g the Graphics object holding screen estate.
	 */
			
	public void draw(Graphics g) {
		DrawingComposite t;
		Enumeration<DrawingComposite> e = v.elements();
		
		while(e.hasMoreElements()) {
			t = e.nextElement();
			t.draw(g);
		}
	}
	
	public void add(DrawingComposite s) {
		v.add(s);
	}
	
	public void remove(DrawingComposite s) {
		System.out.println(s);
		System.out.println(v.size());
		v.remove(s);
		System.out.println(v.size());
	}
	
	/**
	 * Gets the container. Will return a reference to a Container, will only return Containers on other members null
	 * @return the container
	 */
	public DrawingComposite getContainer() {
		return this;
	}
	
	/**
	 * Should not be implemented in any specific way for a container.
	 * @return the x1
	 */
	public int getX1() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Should not be implemented in any specific way for a container.
	 * @return the width
	 */
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Should not be implemented in any specific way for a container.
	 * @return the y1
	 */
	public int getY1() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Should not be implemented in any specific way for a container.
	 * @return the height
	 */
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Should not be implemented in any specific way for a container.
	 * @return the line width
	 */
	public int getLineWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Should not be implemented in any specific way for a container.
	 * @return the line color
	 */
	public Color getLineColor() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Should not be implemented in any specific way for a container.
	 * @return the area color
	 */
	public Color getAreaColor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setDrawingAPI(DrawingUtilInterface di) {
		DrawingComposite t;
		Enumeration<DrawingComposite> e=v.elements();
		while(e.hasMoreElements()) {
			t= e.nextElement();
			t.setDrawingAPI(di);
		}
		// TODO Auto-generated method stub
		
	}
	
	public DrawingShape checkCoordinatesForShape(int xCheck, int yCheck) {
		DrawingComposite t;
		System.out.println(v.size());
		Enumeration<DrawingComposite> e = v.elements();
		
		while (e.hasMoreElements()) {
			t = e.nextElement();
			DrawingShape ds = t.checkCoordinatesForShape(xCheck, yCheck);
			if (ds != null) {
				return ds;
			}
		}
		return null;
	}
}