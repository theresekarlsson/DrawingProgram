package model;
import java.awt.Color;

import view.DrawingUtil;

public class DrawingManager {
  
	private DrawingContainer drawContainer;
	private FileHandler fh;
	private String shapeDefault = "CIRCLE";
	private String stateDefault = "NEW";
	private String currentState = stateDefault;
	private String currentShape = shapeDefault;
	private Color currentColorOutline = null;
	private Color currentColorFill = null;
	private DrawingUtil drawUtil;
	private int lineThickness = 0;
	private int startPosX, startPosY, endPosX, endPosY, width, height, editPosY, editPosX, xDiff, yDiff;
	
	public DrawingManager(DrawingUtil drawingUtil) {
		drawUtil = drawingUtil;
		fh = new FileHandler();
		drawContainer = new DrawingContainer();	
		openContainer();
	}
	public void openContainer() {
		drawContainer = fh.openFromFile(drawContainer);
	}
	public DrawingContainer getContainer() {
		return drawContainer;
	}
	public void saveContainer() {
		fh.saveToFile(drawContainer);
	}
	public void drawCircle() {
		Circle circle = new Circle(drawUtil, startPosX, startPosY, width, height, lineThickness, currentColorOutline, currentColorFill);
		drawContainer.add(circle);
		fh.saveToFile(drawContainer);
	}

	public void drawSquare() {
		Rect rectangle = new Rect(drawUtil, startPosX, startPosY, width, height, lineThickness, currentColorOutline, currentColorFill);
		drawContainer.add(rectangle);
		fh.saveToFile(drawContainer);
	}
	
	public void drawLine() {
		Line line = new Line(drawUtil, startPosX, startPosY, endPosX, endPosY, lineThickness, currentColorFill);
		drawContainer.add(line);
		fh.saveToFile(drawContainer);
	}
	
	public void setStateOption(String selectedState) {
		currentState = selectedState;
	}

	public String getStateOption() {
		return currentState;
	}

	public void setShapeOption(String selectedShape) {
		currentShape = selectedShape;
	}
	public String getShapeOption() {
		return currentShape;
	}
	
	public void setStartCoordinates(int x, int y) {
		startPosX = x;
		startPosY = y;
	}
	public int getStartCoordinateX() {
		return startPosX;
	}
	public int getStartCoordinateY() {
		return startPosY;
	}
	
	public void setEditCoordinates(int x, int y) {
		editPosX = x;
		editPosY = y;
	}
	public int getEditCoordinateX() {
		return editPosX;
	}
	public int getEditCoordinateY() {
		return editPosY;
	}
	
	public void setDiffCoordinates(int x, int y) {
		xDiff = x;
		yDiff = y;
	}
	public int getDiffCoordinateX() {
		return xDiff;
	}
	public int getDiffCoordinateY() {
		return yDiff;
	}
	
	public void setWidthAndHeight(int w, int h) {
		width = w;
		height = h;
	}
	
	public void setEndCoordinates(int xEnd, int yEnd) {
		endPosX = xEnd;
		endPosY = yEnd;
	}
	
	public void setLineThickness(int value) {
		lineThickness = value;
	}
	public int getLineThickness() {
		return lineThickness;
	}
	
	public void setColorFill(Color chosenColorFill) {
		currentColorFill = chosenColorFill;
	}
	
	public Color getColorFill() {
		return currentColorFill;
	}

	public void setColorOutline(Color chosenColorOutline) {
		currentColorOutline = chosenColorOutline;
	}
	
	public Color getColorOutline() {
		return currentColorOutline;
	}
	
	public DrawingShape checkTheCoordinates() {
		DrawingShape ds = drawContainer.checkCoordinatesForShape(editPosX, editPosY);
		return ds;
	}
}