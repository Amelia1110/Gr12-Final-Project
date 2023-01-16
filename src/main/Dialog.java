package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dialog extends Rectangle {
	static boolean showDialog = false;
	static BufferedImage img;
	static String text = null;
	
	// Cannot create a dialog object from other classes
	private Dialog() {
		// Set texture image
				try {
					img = ImageIO.read(new File("dialog.png"));
					width = img.getWidth();
					height = img.getHeight();
				} catch (IOException e) {
					System.out.println("Warning: image failed to load");
				}
	}
	
	static void setShowDialog(boolean toggle) {
		showDialog = toggle;
	}
	
	// We will have one dialog object and just change text/toggle on and off
	Dialog dialog = new Dialog();
}
