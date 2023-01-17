package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Interactables extends Rectangle{
	BufferedImage img;
	double rotation;
	
	// Cannot create an interactable from other classes
	private Interactables() {}
	
	// Create the interactable with an image
	private Interactables(String fileName) {
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
	private Interactables(String fileName, int rotation) {
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
	static Interactables doorUp = new Interactables("door.png");
	static Interactables doorRight = new Interactables("door.png", 90);
	static Interactables doorDown = new Interactables("door.png", 180);
	static Interactables doorLeft = new Interactables("door.png", 270);
	static Interactables introNote = new Interactables("intronote.png");
}