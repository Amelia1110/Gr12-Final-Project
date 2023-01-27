package main;

import javax.swing.JOptionPane;

public class Item extends Interactable{
	int hp; // The amount of health points the item gives the player upon purchasing 
	int cost; // Cost of the item
	
	Item(String fileName, int hp, int cost) {
		setImageFile(fileName);
		this.hp = hp;
		this.cost = cost;
	}
	
	@Override
	void interact() {
		// Prints out a message when player 
		JOptionPane.showMessageDialog(null,"Your money: " + Integer.toString(EscapeRoomieGame.player.money));
		int ans = JOptionPane.showConfirmDialog(null,"Do you want to purchase this item?");
		if(ans == JOptionPane.YES_OPTION){  
			if (EscapeRoomieGame.player.money < cost) {
				JOptionPane.showMessageDialog(null,"YOU ARE BROKE");
				return;
			}
			
			// If player reaches max health upon consuming the item, a message appears 
			EscapeRoomieGame.player.health += hp;
			if (EscapeRoomieGame.player.health > Player.MAXHEALTH) {
				JOptionPane.showMessageDialog(null,"Max HP reached");
				EscapeRoomieGame.player.health = Player.MAXHEALTH;
			}
			JOptionPane.showMessageDialog(null,"Item purchased"); // Confirmation message
			EscapeRoomieGame.player.money -= cost; // Player pays money 
		}  else {
			JOptionPane.showMessageDialog(null, "too broke ig");
		}
		
	}
}
