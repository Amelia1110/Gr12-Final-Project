package main;

import javax.swing.JOptionPane;

public class Item extends Interactable{
	int hp; // The amount of health points the item gives the player upon purchasing 
	int cost; // Cost of the item
	static boolean duringPurchase = false; // Checks if user is in the process of interacting with a shop item

	Item(String fileName, int hp, int cost) {
		setImageFile(fileName);
		this.hp = hp;
		this.cost = cost;
	}

	@Override
	void interact() {
		duringPurchase = true;
		// Prints out a failure message when player does not have enough money
		if (EscapeRoomieGame.player.money < this.cost) {
			JOptionPane.showMessageDialog(null,"YOU ARE BROKE, SOLVE MORE PUZZLES AND COME BACK");
			duringPurchase = false;
			return;
		}
		
		// Gives player shop related information
		JOptionPane.showMessageDialog(null,"Your money: " + Integer.toString(EscapeRoomieGame.player.money));
		int ans = JOptionPane.showConfirmDialog(null,"Do you want to purchase this item? Price: " + this.cost 
				+ "\nIt gives you " + this.hp + " health points.", "Confirmation", JOptionPane.YES_NO_OPTION);
		
		// If player wishes to purchase item
		if(ans == JOptionPane.YES_OPTION){  
			// Checks if player's health goes beyond max possible health
			if ((EscapeRoomieGame.player.health + this.hp) > Player.MAXHEALTH) {
				JOptionPane.showMessageDialog(null,"Item purchased, max HP reached");
				EscapeRoomieGame.player.health = Player.MAXHEALTH;
				duringPurchase = false;
			}
			else {
				EscapeRoomieGame.player.health += this.hp;
				JOptionPane.showMessageDialog(null,"Item purchased"); // Confirmation message
				duringPurchase = false;
			}
			EscapeRoomieGame.player.money -= this.cost; // Player pays money 
		}  else {
			JOptionPane.showMessageDialog(null, "Okay, come back soon!");
			duringPurchase = false;
		}
	}
}
