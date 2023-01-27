package main;

public class Flower extends Interactable{
	// This class creates and manages the flower, which is the final object in the game 
	// Flower is only visible once player unlocks all doors. Once player interacts with it, the game is finished and player wins.
	static boolean gamePassed = false;
	
	Flower() {
		setImageFile("flower.png");
	}
	
	@Override
	void interact() {
		// Interactable only when flower is showing
		if (EscapeRoomieGame.showFlower) {
			gamePassed = true;
			EscapeRoomieGame.gameRunning = false;
		}
	}
}
