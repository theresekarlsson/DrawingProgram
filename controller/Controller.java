package controller;
import java.awt.Color;

import model.DrawingManager;
import model.DrawingShape;
import view.SwingView;

public class Controller {
	
	private SwingView sv;
	private DrawingManager dm;
	private final static String STATE_OPT_NEW = "NEW", STATE_OPT_EDIT = "EDIT";
	private final static String SHAPE_OPT_CIRCLE = "CIRCLE", SHAPE_OPT_SQUARE = "SQUARE", SHAPE_OPT_LINE = "LINE";	
	private final static String OBJ_MOVE = "MOVE", OBJ_UP_LEFT = "UPPER_LEFT", 
			OBJ_LOW_LEFT = "LOWER_LEFT", OBJ_UP_RIGHT ="UPPER_RIGHT", OBJ_LOW_RIGHT ="LOWER_RIGHT";
	private int width, height;
	private DrawingShape currentShape;
	private String corner = OBJ_MOVE;
	
	public Controller() {
		sv = new SwingView(this);
		dm = new DrawingManager(sv.getDrawUtil());
		sv.drawGUI(dm.getContainer());
	}

	public void handleColorFill(Color chosenColorFill) {
		dm.setColorFill(chosenColorFill);
	}

	public void handleColorOutline(Color chosenColorOutline) {
		dm.setColorOutline(chosenColorOutline);
	}

	public void handleExit() {
		dm.saveContainer();
		System.exit(0);
	}

	public void handleSlider(int value) {
		dm.setLineThickness(value);
	}

	public void handleStateOption(String selState) {
		String selectedState = null;
		switch (selState) {
		case "Måla ny":
			selectedState = STATE_OPT_NEW;
			sv.enableAttributeOptions();
			break;
		case "Förändra befintlig":
			selectedState = STATE_OPT_EDIT;
			sv.disableAttributeOptions();
			break;
		default:
			break;
		}
		dm.setStateOption(selectedState);
	}

	public void handleShapeOption(String selShape) {
		String selectedShape = null;
		switch (selShape) {
		case "Cirkel":
			selectedShape = SHAPE_OPT_CIRCLE ;
			sv.enableColorOutline();
			break;
		case "Fyrkant":
			selectedShape = SHAPE_OPT_SQUARE;
			sv.enableColorOutline();
			break;
		case "Linje":
			selectedShape = SHAPE_OPT_LINE;
			sv.disableColorOutline();
			break;
		default:
			break;
		}
		dm.setShapeOption(selectedShape);
	}

	public void handleMousePressed(int x, int y) {
		switch (dm.getStateOption()) {
		case STATE_OPT_NEW:
			dm.setStartCoordinates(x, y);
			break;
		case STATE_OPT_EDIT:
			dm.setEditCoordinates(x, y);
			currentShape = dm.checkTheCoordinates();
			
			if (currentShape != null) {
				int startX, startY, editX, editY, diffX = 0, diffY = 0, height, width, boundary = 10;
				startX = currentShape.getX1();
				startY = currentShape.getY1();
				editX = dm.getEditCoordinateX();
				editY = dm.getEditCoordinateY();
				height = currentShape.getHeight();
				width = currentShape.getWidth();
				
				if ((editX >= startX && editX < startX + boundary) &&
						(editY >= startY && editY < startY + boundary)) {
					diffX = editX - startX;
					diffY = editY - startY;
					corner = OBJ_UP_LEFT;
				}
				else if ((editX >= startX && editX < startX + boundary) && 
						(editY <= (startY + height) && editY > (startY + height) - boundary)) {
					diffX = editX - startX;
					diffY = (startY + height) - editY;
					corner = OBJ_LOW_LEFT;
				}
				else if ((editX <= (startX + width) && editX > ((startX + width) - boundary)) &&
						(editY >= startY && editY < (startY + boundary))) {
					diffX = (startX + width) - editX;
					diffY =  editY - startY;
					corner = OBJ_UP_RIGHT;
				}
				else if ((editX <= startX + width) && editX > ((startX + width) - boundary) && (
						editY <= (startY + height) && editY > ((startY + height) - boundary))) {
					diffX = (startX + width) - editX;
					diffY = (startY + height) - editY;
					corner = OBJ_LOW_RIGHT;
				}
				else { // flytta
					diffX = x - startX;
					diffY = y - startY;
					corner = OBJ_MOVE;
				}
				dm.setDiffCoordinates(diffX, diffY);
			}
			break;
		default:
			break;
		}
	}

	public void handleMouseDragged(int xDown, int yDown) {
		if (dm.getStateOption().equals(STATE_OPT_EDIT) && currentShape != null) {
			
			int startX, startY, diffX, diffY, height, width;
			startX = currentShape.getX1();
			startY = currentShape.getY1();
			height = currentShape.getHeight();
			width = currentShape.getWidth();
			diffX = dm.getDiffCoordinateX();
			diffY = dm.getDiffCoordinateY();
		
			switch(corner) {
			case OBJ_UP_LEFT: 
				if (xDown < startX && yDown < startY) {
					currentShape.setX1(startX - 1);
					currentShape.setY1(startY - 1);
					currentShape.setHeight(height + 1);
					currentShape.setWidth(width + 1);
				}
				else if (xDown > startX && yDown > startY) {
					currentShape.setX1(startX + 1);
					currentShape.setY1(startY + 1);
					currentShape.setHeight(height - 1);
					currentShape.setWidth(width - 1);
				}
				break;
			case OBJ_LOW_LEFT:
				if (xDown < startX && yDown > (startY + height)) {
					currentShape.setX1(startX - 1);
					currentShape.setHeight(height + 1);
					currentShape.setWidth(width + 1);	
				}
				else if (xDown > startX && yDown < (startY + height)) {
					currentShape.setX1(startX + 1);
					currentShape.setHeight(height - 1);
					currentShape.setWidth(width - 1);
				}
				break;
			case OBJ_UP_RIGHT:
				if (xDown > (startX + width) && yDown < startY) {
					currentShape.setY1(startY - 1);
					currentShape.setHeight(height + 1);
					currentShape.setWidth(width + 1);
				}
				else if (xDown < (startX + width) && yDown > startY) {
					currentShape.setY1(startY + 1);
					currentShape.setHeight(height - 1);
					currentShape.setWidth(height - 1);
				}
				break;
			case OBJ_LOW_RIGHT:
				if (xDown > (startX + width) && yDown > (startY + height)) {
					currentShape.setHeight(height + 1);
					currentShape.setWidth(width + 1);	
				}	
				else if (xDown < (startX + width) && yDown < (startY + height)) {
					currentShape.setHeight(height - 1);
					currentShape.setWidth(width - 1);
				}
				break;
			case OBJ_MOVE:
				currentShape.setX1(xDown - diffX);
				currentShape.setY1(yDown - diffY);
				
				if (currentShape.toString().contains("Line")) {
					currentShape.setWidth(xDown - (diffX - currentShape.getX1()));
					currentShape.setHeight(yDown - (diffY - currentShape.getY1()));
				}
				break;
			}
			sv.repaintGUI();
		}
	}
	public void handleMouseReleased(int xEnd, int yEnd) {
		if (dm.getStateOption().equals(STATE_OPT_NEW)) {
			if ((dm.getStartCoordinateX() != xEnd && dm.getStartCoordinateY() != yEnd) && 
					(dm.getColorFill() != null || dm.getColorOutline() != null)) {
				drawNewShape(xEnd, yEnd);
			}
		}
	}
	
	public void handleDelete() {
		if (currentShape != null) {
			System.out.println("handleDelete");
			dm.getContainer().remove(currentShape);
			sv.repaintGUI();
		}
	}

	private void drawNewShape(int xEnd, int yEnd) {
		int xStartCurrent, yStartCurrent;
		
		switch (dm.getShapeOption()) {
		case SHAPE_OPT_CIRCLE:
			System.out.println("Controller: Rita cirkel");
			
			xStartCurrent = dm.getStartCoordinateX();
			yStartCurrent = dm.getStartCoordinateY();
			
			if (xEnd > xStartCurrent) {
				width = xEnd - xStartCurrent;
			} 
			if (xEnd < xStartCurrent) {
				width = xStartCurrent - xEnd;
				xStartCurrent = xEnd;
			}
			if (yEnd > yStartCurrent) {
				height = yEnd - yStartCurrent;
				
			}
			if (yEnd < yStartCurrent) {
				height = yStartCurrent - yEnd;
				yStartCurrent = yEnd;
			}
			dm.setStartCoordinates(xStartCurrent, yStartCurrent);
			dm.setEndCoordinates(xEnd, yEnd);
			dm.setWidthAndHeight(width, height);
			dm.drawCircle();
			break;
			
		case SHAPE_OPT_SQUARE:
			System.out.println("Controller: Rita fyrkant");
			
			xStartCurrent = dm.getStartCoordinateX();
			yStartCurrent = dm.getStartCoordinateY();
			
			if (xEnd > xStartCurrent) {
				width = xEnd - xStartCurrent;
			}
			if (xEnd < xStartCurrent) {
				width = xStartCurrent - xEnd;
				xStartCurrent = xEnd;
			}
			if (yEnd > yStartCurrent) {
				height = yEnd - yStartCurrent;
				
			}
			if (yEnd < yStartCurrent) {
				height = yStartCurrent - yEnd;
				yStartCurrent = yEnd;
			}
			dm.setStartCoordinates(xStartCurrent, yStartCurrent);
			dm.setEndCoordinates(xEnd, yEnd);
			dm.setWidthAndHeight(width, height);
			dm.drawSquare();
			break;
			
		case SHAPE_OPT_LINE:
			if (dm.getLineThickness() >= 1) {
				System.out.println("Controller: Rita linje");
				dm.setEndCoordinates(xEnd, yEnd);
				dm.drawLine();
			}
			break;
			
		default:
			break;
		} 
		sv.repaintGUI();
	}
	
	public static void main(String[] args) {
		new Controller();
	}
}