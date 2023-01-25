package main;

public class Solution extends Interactable {
	static boolean showTextField = false;	// Track when textfield should be drawn
	private String correctSolution;
	String userSolution;
	boolean solved = false;
	
	// Answer object that appears after another event (i.e. between dialog)
	Solution(String correctAnswer) {
		this.correctSolution = correctAnswer;
	}
	
	// Answer object that appears after interacting with an item
	Solution(String fileName, String correctAnswer) {
		// Interactable method to set image
		setImageFile(fileName);
		
		// Define correct puzzle solution
		this.correctSolution = correctAnswer;
	}
	
	@Override
	void interact() {
		// Mark puzzle as solved if user gets correct answer // TODO move this to check after typing something
		if (userSolution.toLowerCase().equals(correctSolution)) solved = true;
	}
}
