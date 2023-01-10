package main;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

class Player extends Rectangle{
	//player properties
	static final int MAXHEALTH = 100; 
	int health = 100;
	int money = 0; //player will use the money to purchase in-game items 
	private int speed = 5;
	
	//boolean flipped = false //this is needed if the player has an actual image 
	//boolean showHitBox = false; //for testing because player is a rectangle 
	
	BufferedImage image; //for drawing the image on graphics 
	
	int stretch = 20;

	//movement 
	Player(int x, int y) {
		this.x = x;
		this.y = y;
		//these are customizable 
		width = 100;
		height = 50;
	}

	//THIS FOR DRAWING IMAGE! FIXME
	/*
	void draw(GraphicsConsole gc) {
		gc.setColor(Color.RED);
		if (!flipped) {
			gc.drawImage(image, x-stretch/2, y-stretch/2, width+stretch, height+stretch);
		} else {
			gc.drawImage(image, x+width+stretch/2, y-stretch/2, -width-stretch, height+stretch);
		}
		if (showHitBox)
			gc.drawRect(x, y, width, height);
		if(health > 50)gc.setColor(Color.GREEN);
			gc.fillRect(x, y, health*width/100, 10);
	}*/
	
	//THIS FOR MOVEMENT: WILL BE IN MAIN CLASS THOUGH! FIXME
	/*
	**you implement keyListener first, then do this in the keyPressed section
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode(); //gets the keyCode of the key pressed
		//verifies if the key is the up arrow key, if so, the colour theme changes 
		if (keyCode == 'W') {
			player.y++;
		}	
		etc.
		if (keyCode == 'A' && player.x > 0) {
			player.flipped = false;
			player.x--;
		}
		if (keyCode == 'D' && player.x < WINW-player.width) {
			player.flipped = true;
			player.x++;
		}
	}
	*/
	
	
	//THIS FOR ITEMS
	//basically, this class is for all power-ups 
	//there will be a method called PurchaseItems in main game 
	//public class Items {
	//int moneyRequired = 500;
	//	
	//}

	/*public class FlashLight{
		int moneyRequired = 500;
	}*/

	/*public class Snacks{
		int moneyRequired = 2000;
		int healthPoint = 25;
	}*/

	/*public class illegalFood{
		int moneyRequired = 4200;
		int healthPoint = 50;
	}*/
}
