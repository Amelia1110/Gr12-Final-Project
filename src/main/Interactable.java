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

	static Interactable wallLight = new Interactable("wallLight.png");

	// These are items up to purchase in shop 
	static Interactable burger = new Item("burger.png", 40, 80); //gives player 40 health points
	static Interactable milk = new Item("milk.png", 20, 40); //gives player 20 health points
	static Interactable flashlight = new Interactable("flashlight.png"); //gives player more map visibilityI
	static Interactable hint = new Interactable("hint.png"); //gives player an extra hint
	
	// Puzzle items in game
	static Interactable closedBook = new Question("closedBook.png", "closedBookPuzzle.png"); //puzzle #1
	static Interactable paper = new Question("paper.png", "paperPuzzle.png"); //puzzle #2
	static Interactable rubikCube = new Interactable("rubikCube.png"); //puzzle #3
	static Interactable musicNote = new Question("musicNote.png", "musicNotePuzzle.png"); //puzzle #4
	static Interactable clock = new Question("clock.png", "clockPuzzle.png"); //puzzle #5
	static Interactable jewelry = new Question("jewelry.png", "jewelryPuzzle.png"); //puzzle #6
	static Interactable mathNote = new Question("mathNote.png", "mathNotePuzzle.png"); //puzzle #7
	static Interactable alphabet = new Question("alphabet.png", "alphabetPuzzle.png"); //puzzle #8
	static Interactable openBook = new Question("openBook.png", "openBookPuzzle.png"); //puzzle #9
	static Interactable flower = new Flower();
	
	// Shop Icon 
	static Interactable shop = new Shop("shopIcon.png"); 
	
	// Introduction item
	static Interactable introNote = new Note("intronote.png", Dialog.introScene1);
	
	// Access unlocking doors
	static Interactable introToRoom1 = new Door("nightshade", 0, "room1Panel", "introPanel", false, 6 * 64, 7 * 64);
	static Interactable room1ToRoom2 = new Door("see", 90, "room2Panel", "room1Panel", false, 1 * 64, 8 * 64); 
	static Interactable room1ToRoom3 = new Door("exams", 0, "room3Panel", "room1Panel", false, 14 * 64, 8 * 64); 
	static Interactable room1ToRoom4 = new Door("127dbftense", 270, "room4Panel", "room1Panel", false, 15 * 64, 2 * 64);
	static Interactable room1ToRoom5 = new Door("jortega", 180, "room5Panel", "room1Panel", false, 2 * 64, 1 * 64); 
	
	// Free Access doors 
	static Interactable shopToRoom1 = new Door("", 270, "room1Panel", "shopPanel", true, 10 * 64, 7 * 64);
	static Interactable room2ToRoom1 = new Door("", 270, "room1Panel", "room2Panel", true, 15 * 64, 6 * 64);
	static Interactable room3ToRoom1 = new Door("", 180, "room1Panel", "room3Panel", true, 5 * 64, 3 * 64);
	static Interactable room4ToRoom1 = new Door("", 90, "room1Panel", "room4Panel", true, 6 * 64, 4 * 64);
	static Interactable room5ToRoom1 = new Door("", 0, "room1Panel", "room5Panel", true, 6 * 64, 6 * 64);
	
}