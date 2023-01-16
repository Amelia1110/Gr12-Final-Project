package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Interactable extends Rectangle{
	BufferedImage img;
	double rotation;
	
	// Cannot create an interactable from other classes
	private Interactable() {}
	
	// Create the interactable with an image
	private Interactable(String fileName) {
		// Set int image
		try {
			img = ImageIO.read(new File(fileName));
			width = img.getWidth();
			height = img.getHeight();
		} catch (IOException e) {
			System.out.println("Warning: image failed to load");
		}		
	}
	
	// Create a rotated interactable with an image
	private Interactable(String fileName, int rotation) {
		// Set texture image
		try {
			img = ImageIO.read(new File(fileName));
			width = img.getWidth();
			height = img.getHeight();
		} catch (IOException e) {
			System.out.println("Warning: image failed to load");
		}

		// Set rotation
		this.rotation = Math.toRadians(rotation);
	}
	
	// Create all textures (All textures should be 64 x 64)
	static Interactable introNote = new Interactable("intronote.png");
	static Interactable doorUp = new Interactable("door.png");
	static Interactable doorRight = new Interactable("door.png", 90);
	static Interactable doorDown = new Interactable("door.png", 180);
	static Interactable doorLeft = new Interactable("door.png", 270);
	
}