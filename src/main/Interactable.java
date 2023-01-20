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
			System.out.println("Warning: interactable failed to load");
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
			System.out.println("Warning: interactable failed to load");
		}

		// Set rotation
		this.rotation = Math.toRadians(rotation);
	}
	
	// Create all textures (All textures should be 64 x 64)
	static Interactable doorUp = new Interactable("door.png");
	static Interactable doorRight = new Interactable("door.png", 90);
	static Interactable doorDown = new Interactable("door.png", 180);
	static Interactable doorLeft = new Interactable("door.png", 270);
	static Interactable wallLight = new Interactable("wallLight.png");
	//these are items up to purchase in shop 
	static Interactable burger = new Interactable("burger.png"); //gives player 40 health points
	static Interactable milk = new Interactable("milk.png"); //gives player 20 health points
	static Interactable flashlight = new Interactable("flashlight.png"); //gives player more map visibility
	static Interactable hint = new Interactable("hint.png"); //gives player an extra hint
	//puzzle items in game
	static Interactable closedBook = new Interactable("closedBook.png"); //gives player 20 health points
	static Interactable openBook = new Interactable("openBook.png"); //gives player 20 health points
}