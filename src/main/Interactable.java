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
	Interactable() {}
	
	// Create the interactable with an image
	Interactable(String fileName) {
		setImageFile(fileName);
	}
	
	// Import images to display on map 
	void setImageFile(String fileName) {
		// Set interactable image
		try {
			img = ImageIO.read(new File(fileName));
			width = img.getWidth();
			height = img.getHeight();
		} catch (IOException e) {
			System.out.println("Warning: interactable failed to load");
		}
	}
	
	void interact() {
		
	}
	
	// TODO Change these all to unique door objects, remember to add rotations
	// Create all textures (All textures should be 64 x 64)
	static Interactable doorUp = new Interactable("door.png");
	static Interactable doorRight = new Interactable("door.png");
	static Interactable doorDown = new Interactable("door.png");
	static Interactable doorLeft = new Interactable("door.png");
	static Interactable wallLight = new Interactable("wallLight.png");

	//these are items up to purchase in shop 
	static Interactable burger = new Interactable("burger.png"); //gives player 40 health points
	static Interactable milk = new Interactable("milk.png"); //gives player 20 health points
	static Interactable flashlight = new Interactable("flashlight.png"); //gives player more map visibilityI
	static Interactable hint = new Interactable("hint.png"); //gives player an extra hint
	
	//puzzle items in game
	static Interactable closedBook = new Interactable("closedBook.png"); //puzzle #1
	static Interactable paper = new Interactable("paper.png"); //puzzle #2
	static Interactable rubikCube = new Interactable("rubikCube.png"); //puzzle #3
	static Interactable musicNote = new Interactable("musicNote.png"); //puzzle #4
	static Interactable clock = new Interactable("clock.png"); //puzzle #5
	static Interactable jewelry = new Interactable("jewelry.png"); //puzzle #6
	static Interactable mathNote = new Interactable("mathNote.png"); //puzzle #7
	static Interactable alphabet = new Interactable("alphabet.png"); //puzzle #8
	static Interactable openBook = new Interactable("openBook.png"); //puzzle #9
	static Interactable flower = new Interactable("flower.png"); //puzzle #10

	//introduction item
	static Interactable introNote = new Note("intronote.png", Dialog.introScene1);
}