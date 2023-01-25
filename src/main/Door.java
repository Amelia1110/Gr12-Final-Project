package main;

public class Door extends Interactable {
	static boolean showTextField = false;	// Track when textfield should be drawn
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
		// Mark puzzle as solved if user gets correct answer // TODO move this to check after typing something
		if (userSolution.toLowerCase().equals(correctSolution)) unlocked = true;
	}
}
