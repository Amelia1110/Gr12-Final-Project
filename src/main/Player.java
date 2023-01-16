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
	// Player's position on the map grid (not pixels), index on map
	int xPos;
	int yPos;

	BufferedImage image; //for drawing the image on graphics 

	int stretch = 20;

	Player(int x, int y) {
		width = 100;
		height = 50;
		xPos = (x + width/2)/Map.TILE_DIMENSION;
		yPos = (y + height)/Map.TILE_DIMENSION;
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

	// Player moves in a specified direction
	void move (int key, int[][] map) {
		switch (key) { 
		case 'W':
			y -= vy;
			// Player can't hit wall
			if (hitWall(map)) {
				y += vy;
			}
			break;
		case 'A':
			x -= vx;
			// Player can't hit wall
			if (hitWall(map)) {
				x += vx;
			}
			break;
		case 'S':
			y += vy;
			// Player can't hit wall
			if (hitWall(map)) {
				y -= vy;
			}
			break;
		case 'D':
			x += vx;
			// Player can't hit wall
			if (hitWall(map)) {
				x -= vx;
			}
			break;
		}
	}
	
	// Restrict player movement so player can't go past walls
	boolean hitWall(int[][] map) {
		// calculate the tile on which player is currently standing (using bottom center of player as reference)
		xPos = (x + width/2)/Map.TILE_DIMENSION;
		yPos = (y + height)/Map.TILE_DIMENSION;
		
		// If the player is not standing on a floor tile, return true
		if (map[yPos][xPos] != 1) {
			System.out.println(map[yPos][xPos]);
			return true;
		}
		return false;
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
