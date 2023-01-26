package main;

import java.awt.Font;

import javax.swing.JTextField;

public class Door extends Interactable {
	static boolean showTextField = false;
	private String correctSolution;
	String userSolution;
	boolean unlocked = false;
	
	Door(String correctAnswer, int rotation) {
		// Interactable method to set image
		setImageFile("door.png");
		this.correctSolution = correctAnswer;
		
		// Set rotation (to create doors going in different directions)
		this.rotation = Math.toRadians(rotation);
	}
	
	@Override
	void interact() {
		showTextField = true;
		System.out.println(showTextField);
		
		// Mark puzzle as solved if user gets correct answer // TODO move this to check after typing something
		//if (userSolution.toLowerCase().equals(correctSolution)) unlocked = true;
	}
}
