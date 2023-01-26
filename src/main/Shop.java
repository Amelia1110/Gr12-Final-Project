package main;

public class Shop extends Interactable{
	static boolean shopShowing = false;
	
	Shop() {}
	
	Shop(String shopIcon) {
		// Interactable method to set icon image
		setImageFile(shopIcon);
	}
	
	@Override
	void interact() {
		shopShowing = true;
		EscapeRoomieGame.window.add(EscapeRoomieGame.shopPanel);
		EscapeRoomieGame.window.remove(EscapeRoomieGame.room1Panel);
		EscapeRoomieGame.activePanel = EscapeRoomieGame.shopPanel;
		EscapeRoomieGame.radius = 1000;
		EscapeRoomieGame.window.setVisible(true);
	}
	
	static void exit() {
		shopShowing = false;
		EscapeRoomieGame.window.add(EscapeRoomieGame.room1Panel);
		EscapeRoomieGame.window.remove(EscapeRoomieGame.shopPanel);
		EscapeRoomieGame.activePanel = EscapeRoomieGame.room1Panel;
		EscapeRoomieGame.radius = 80;
		EscapeRoomieGame.window.setVisible(true);
	}
}

