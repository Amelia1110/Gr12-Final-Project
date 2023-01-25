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
		EscapeRoomieGame.window.add(EscapeRoomieGame.shopPanel);
		EscapeRoomieGame.window.remove(EscapeRoomieGame.room1Panel);
		EscapeRoomieGame.activePanel = EscapeRoomieGame.shopPanel;
	}
	
	static void exit() {
		EscapeRoomieGame.window.add(EscapeRoomieGame.room1Panel);
		EscapeRoomieGame.window.remove(EscapeRoomieGame.shopPanel);
		EscapeRoomieGame.activePanel = EscapeRoomieGame.room1Panel;
	}
}

