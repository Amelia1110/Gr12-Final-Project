package main;

public class Flower extends Interactable{
	static boolean gamePassed = false;
	
	Flower() {
		setImageFile("flower.png");
	}
	
	@Override
	void interact() {
		// Interacts only when flower is showing
		if (EscapeRoomieGame.showFlower) {
			gamePassed = true;
			EscapeRoomieGame.gameRunning = false;
		}
	}
}
