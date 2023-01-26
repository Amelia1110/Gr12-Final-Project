package main;

import java.awt.Font;

import javax.swing.JTextField;

public class Door extends Interactable {
	static boolean typing = false;
	private String correctSolution;
	String userInput = "";
	boolean unlocked = false;
	
	Door(String correctSolution, int rotation) {
		// Interactable method to set image
		setImageFile("door.png");
		this.correctSolution = correctSolution;
		
		// Set rotation (to create doors going in different directions)
		this.rotation = Math.toRadians(rotation);
	}
	
	@Override
	void interact() {
		// Keep track of which door is being interacted with
		EscapeRoomieGame.currentDoor = this;
		
		// Player is typing input
		typing = true;
		System.out.println("Hello");
		System.out.println(typing);
		
		// Mark puzzle as solved if user gets correct answer // TODO move this to check after typing something
		//if (userSolution.toLowerCase().equals(correctSolution)) unlocked = true;
	}
	
	// TODO restrict so string can't be past certain size
	void getUserInput(int keyCode, char keyChar) {
		// Adds to userInput string the user types a number, letter, or space
		if ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || keyCode == 32) {
			userInput += keyChar;
		}

		// Deletes the last letter typed
		if (keyCode == 8) userInput = userInput.substring(0, userInput.length() - 1);
		
		if (keyCode == 10) {
			typing = false;
			checkSolution();
		}
	}
	
	void checkSolution() {
		if (userInput.equals(correctSolution)) {
			unlocked = true;
		}
	}
}
