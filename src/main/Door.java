package main;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextField;

public class Door extends Interactable {
	// Tracks if user is currently typing
	static boolean typing = false;

	// For user information
	Dialog doorInstructions;
	
	// Variables for checking solution
	private String correctSolution;
	String userInput = "";
	boolean unlocked = false;
	
	// Set where you want player to spawn after going through door
	String panelEntered, panelExited;
	int targetXCor;
	int targetYCor;
	
	// Create door object
	Door(String correctSolution, int rotation, String panelEntered, String panelExited, boolean unlocked, int xCor, int yCor) {
		// Interactable method to set image
		if (unlocked) setImageFile("unlockedDoor.png");
		else setImageFile("door.png");
		this.correctSolution = correctSolution;
		
		// Set rotation (to create doors going in different directions)
		this.rotation = Math.toRadians(rotation);
		
		this.panelEntered = panelEntered;
		this.panelExited = panelExited;
		this.unlocked = unlocked;
		targetXCor = xCor;
		targetYCor = yCor;
	}
	
	// Create door object with instructions as dialog
	Door(String correctSolution, int rotation, String panelEntered, String panelExited, boolean unlocked, int xCor, int yCor, Dialog doorInstructions) {
		// Interactable method to set image
		if (unlocked) setImageFile("unlockedDoor.png");
		else setImageFile("door.png");
		this.correctSolution = correctSolution;
		
		// Set rotation (to create doors going in different directions)
		this.rotation = Math.toRadians(rotation);
		
		// Set locations for user teleporting
		this.panelEntered = panelEntered;
		this.panelExited = panelExited;
		this.unlocked = unlocked;
		targetXCor = xCor;
		targetYCor = yCor;
		
		// Set dialog to be displayed
		this.doorInstructions = doorInstructions;
	}
	
	@Override
	void interact() {
		// Keep track of which door is being interacted with
		EscapeRoomieGame.currentDoor = this;
		
		if (!unlocked) {
			// Player is typing input
			typing = true;
			userInput = "Delete&Type:";
			
			// Display dialog instructions if there are any
			if (doorInstructions != null) {
				doorInstructions.currentText = 0;
				EscapeRoomieGame.currentDialog = doorInstructions;
				Dialog.showDialog = true;
			}
		}
		else {
			EscapeRoomieGame.window.add(EscapeRoomieGame.panels.get(panelEntered));
			EscapeRoomieGame.window.remove(EscapeRoomieGame.panels.get(panelExited));
			EscapeRoomieGame.activePanel = EscapeRoomieGame.panels.get(panelEntered);
			EscapeRoomieGame.player.x = targetXCor;
			EscapeRoomieGame.player.y = targetYCor;
			EscapeRoomieGame.window.setVisible(true);
		}
	}
	
	// TODO restrict so string can't be past certain size
	void getUserInput(int keyCode, char keyChar) {
		// Adds to userInput string the user types a number or letter
		if (userInput.length() <= 11) {
			if (((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90))) {
				userInput += keyChar;
			}
		}

		// Deletes the last letter typed
		if (keyCode == 8 && userInput.length() > 0) userInput = userInput.substring(0, userInput.length()-1);
		
		if (keyCode == 10) {
			Dialog.showDialog = false;
			typing = false;
			checkSolution();
		}
	}
	
	void checkSolution() {
		if (userInput.toLowerCase().equals(correctSolution)) {
			setImageFile("unlockedDoor.png");
			unlocked = true;
			EscapeRoomieGame.player.money +=40;
     
			if (this == Interactable.room1ToRoom5) {
				EscapeRoomieGame.showFlower = true;
			}

		}
		else {
			EscapeRoomieGame.player.health -= 10;
			if (EscapeRoomieGame.player.health <= 0) {
				EscapeRoomieGame.gameRunning = false;
			}
		}
	}
}
