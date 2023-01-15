package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class Player extends Rectangle{
	//player properties
	static final int MAXHEALTH = 100; 
	int health = 100;	//health of player
	int money = 0; 		//player will use the money to purchase in-game items 
	private int vx = 2;	//xspeed at which player moves
	private int vy = 2;	//yspeed at which player moves
	boolean showHitBox = false; //for testing because player is a rectangle 

	BufferedImage image; //for drawing the image on graphics 

	int stretch = 20;

	Player(int x, int y) {
		width = 100;
		height = 50;
		this.x = x-(width/2);
		this.y = y-(height/2);
		//these are customizable 

		try {
			image = ImageIO.read(new File("player.png"));
			//these for hitbox
			width = image.getWidth();
			height = image.getHeight();
		} catch (IOException e) {
			System.out.println("Warning: player.png failed to load");
		}
	}

	void move (int key) {
		switch (key) { 
		case 'W':
		case 38:
			if (this.intersects(Texture.topWall)){
				x = y = 200;
			}
			y -= vy;
			break;

		case 'A':
		case 37:
			x -= vx;
			break;

		case 'S':
		case 40:
			y += vy;
			break;

		case 'D':
		case 39:
			x += vx;
			break;

		}
	}

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
