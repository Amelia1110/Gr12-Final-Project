package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class Player extends Rectangle{
	//player properties
	static final int MAXHEALTH = 100; 
	int health = 100;	// Health of player
	int money = 0; 		// Player will use the money to purchase in-game items 
	private int vx = 2;	// Xspeed at which player moves, horizontally
	private int vy = 2;	// Yspeed at which player moves, vertically
	boolean showHitBox = false; //for testing because player is a rectangle 
	// Player's position on the map grid (not pixels), index on map
	int xPos, xPosLeft, xPosRight;
	int yPos;

	BufferedImage image; // For drawing the image on graphics 

	Player(int x, int y) {
		// These are customizable 
		width = 100;
		height = 50;
		xPos = (x + width/2)/Map.TILE_DIMENSION;
		yPos = (y + height)/Map.TILE_DIMENSION;
		this.x = x-(width/2);
		this.y = y-(height/2);
		

		try {
			image = ImageIO.read(new File("player.png"));
			width = image.getWidth();
			height = image.getHeight();
		} catch (IOException e) {
			System.out.println("Warning: player.png failed to load");
		}
	}

	// Player moves in a specified direction
	void move (int key, int[][] groundMap, int[][] layer2Map) {
		switch (key) { 
		case 'W':
			y -= vy;
			// Player can't hit wall
			if (hitWall(groundMap)) {
				y += vy;
			}
			// Track what interactables player is close to
			canInteractWith(layer2Map);
			break;
		case 'A':
			x -= vx;
			// Player can't hit wall
			if (hitWall(groundMap)) {
				x += vx;
			}
			// Track what interactables player is close to
			canInteractWith(layer2Map);
			break;
		case 'S':
			y += vy;
			// Player can't hit wall
			if (hitWall(groundMap)) {
				y -= vy;
			}
			// Track what interactables player is close to
			canInteractWith(layer2Map);
			break;
		case 'D':
			x += vx;
			// Player can't hit wall
			if (hitWall(groundMap)) {
				x -= vx;
			}
			// Track what interactables player is close to
			canInteractWith(layer2Map);
			break;
		}
	}
	
	// Restrict player movement so player can't go past walls
	boolean hitWall(int[][] map) {
		// calculate the tile on which player is currently standing (using bottom center of player as reference)
		xPosLeft = x/Map.TILE_DIMENSION;
		xPosRight = (x + width)/Map.TILE_DIMENSION;
		yPos = (y + height)/Map.TILE_DIMENSION;
		
		// If the player is not standing on a floor tile, return hitWall as true
		if (map[yPos][xPosLeft] != 1) return true;
		if (map[yPos][xPosRight] != 1) return true;
		return false;
	}
	
	// Keeps track of what items player is close to for interact
	int canInteractWith(int[][] map) {
		xPos = (x + width/2)/Map.TILE_DIMENSION;
		yPos = (y + height)/Map.TILE_DIMENSION;
		
		return map[yPos][xPos];
	}
}
