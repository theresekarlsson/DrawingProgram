package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileHandler {

	public void saveToFile(DrawingContainer drawContainer) {
		File file = new File("container.txt");
		try {
			FileOutputStream fileOutput = new FileOutputStream(file);
			BufferedOutputStream bufferOutStream = new BufferedOutputStream(fileOutput);
			ObjectOutputStream objectOutStream = new ObjectOutputStream(bufferOutStream);
			objectOutStream.writeObject(drawContainer);
			objectOutStream.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public DrawingContainer openFromFile(DrawingContainer drawCont) {
		File file = new File("container.txt");
		try {
			FileInputStream fileInput = new FileInputStream(file);
			BufferedInputStream bufferInStream = new BufferedInputStream(fileInput);
			ObjectInputStream objectInStream = new ObjectInputStream(bufferInStream);
			drawCont = (DrawingContainer) objectInStream.readObject();
			objectInStream.close();
		} catch (FileNotFoundException e) {
			// returns the empty container.
			return drawCont;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return drawCont;	
	}
}