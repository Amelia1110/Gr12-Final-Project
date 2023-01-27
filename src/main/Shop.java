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
		// Switches displaying panel to shopPanel when player wishes to access shop
		shopShowing = true;
		EscapeRoomieGame.window.add(EscapeRoomieGame.shopPanel);
		EscapeRoomieGame.window.remove(EscapeRoomieGame.room1Panel);
		EscapeRoomieGame.activePanel = EscapeRoomieGame.shopPanel;
		EscapeRoomieGame.window.setVisible(true);
	}
}

