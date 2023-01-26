package main;

public class Flower extends Interactable{
	static boolean gamePassed = false;
	
	Flower() {
		setImageFile("flower.png");
	}
	
	@Override
	void interact() {
		gamePassed = true;
		EscapeRoomieGame.gameRunning = false;
	}
}
