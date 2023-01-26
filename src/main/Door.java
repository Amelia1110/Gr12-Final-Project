package main;

import java.awt.Font;

import javax.swing.JTextField;

public class Door extends Interactable {
	static boolean typing = false;
	private String correctSolution;
	String userInput = "";
	boolean unlocked = false;
	// Set where you want player to spawn after going through door
	String panelEntered, panelExited;
	int targetXCor;
	int targetYCor;
	
	Door(String correctSolution, int rotation, String panelEntered, String panelExited, int xCor, int yCor) {
		// Interactable method to set image
		setImageFile("door.png");
		this.correctSolution = correctSolution;
		
		// Set rotation (to create doors going in different directions)
		this.rotation = Math.toRadians(rotation);
		
		this.panelEntered = panelEntered;
		this.panelExited = panelExited;
		targetXCor = xCor;
		targetYCor = yCor;
	}
	
	@Override
	void interact() {
		// Keep track of which door is being interacted with
		EscapeRoomieGame.currentDoor = this;
		
		if (!unlocked) {
			// Player is typing input
			typing = true;
			userInput = "";
			System.out.println("Hello");
			System.out.println(typing);
		}
		else {
			EscapeRoomieGame.window.add(EscapeRoomieGame.panels.get(panelEntered));
			EscapeRoomieGame.window.remove(EscapeRoomieGame.panels.get(panelExited));
			EscapeRoomieGame.activePanel = EscapeRoomieGame.panels.get(panelEntered);
			EscapeRoomieGame.player.x = targetXCor;
			EscapeRoomieGame.player.y = targetYCor;
			EscapeRoomieGame.window.setVisible(true);
		}
		
		// Mark puzzle as solved if user gets correct answer // TODO move this to check after typing something
		//if (userSolution.toLowerCase().equals(correctSolution)) unlocked = true;
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
			typing = false;
			checkSolution();
		}
	}
	
	void checkSolution() {
		if (userInput.toLowerCase().equals(correctSolution)) {
			unlocked = true;
		}
	}
}
