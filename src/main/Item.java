package main;

import javax.swing.JOptionPane;

public class Item extends Interactable{

	int hp;
	int cost;
	
	Item(String fileName, int hp, int cost) {
		setImageFile(fileName);
		this.hp = hp;
		this.cost = cost;
	}
	
	@Override
	void interact() {
		if (EscapeRoomieGame.player.money < cost) {
			
			JOptionPane.showMessageDialog(null,"YOU ARE BROKE");
			return;
		}
		
		EscapeRoomieGame.player.health += hp;
		if (EscapeRoomieGame.player.health > Player.MAXHEALTH) {
			JOptionPane.showMessageDialog(null,"Max HP reached");
			EscapeRoomieGame.player.health = Player.MAXHEALTH;
		}
		JOptionPane.showMessageDialog(null,"Item purchased");
		EscapeRoomieGame.player.money -= cost;
	}
}
