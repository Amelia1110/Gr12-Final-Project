package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture extends Rectangle{
	BufferedImage img;
	double rotation;
	
	// Cannot create a texture from other classes
	private Texture() {}
	
	// Create a texture
	private Texture(String fileName) {
		// Set texture image
		try {
			img = ImageIO.read(new File(fileName));
			width = img.getWidth();
			height = img.getHeight();
		} catch (IOException e) {
			System.out.println("Warning: image failed to load");
		}		
	}
	
	// Create a rotated texture
	private Texture(String fileName, int rotation) {
		// Set texture image
		try {
			img = ImageIO.read(new File(fileName));
			width = img.getWidth();
			height = img.getHeight();
		} catch (IOException e) {
			System.out.println("Warning: image failed to load");
		}		
		
		// Set rotation
		this.rotation = Math.toRadians(rotation);
	}
	
	// Create all textures (All textures should be 64 x 64)
	static Texture floor = new Texture("floor.png");
	static Texture leftWall = new Texture("left_wall.png");
	static Texture rightWall = new Texture("right_wall.png");
	static Texture topWall = new Texture("top_wall.png");
	static Texture bottomWall = new Texture("bottom_wall.png");
	static Texture topLeftOutCornerWall = new Texture("topleft_outercorner_wall.png");
	static Texture topRightOutCornerWall = new Texture("topright_outercorner_wall.png");
	static Texture botLeftOutCornerWall = new Texture("botleft_outercorner_wall.png");
	static Texture botRightOutCornerWall = new Texture("botright_outercorner_wall.png");
	static Texture topLeftInCornerWall = new Texture("topleft_innercorner_wall.png");
	static Texture topRightInCornerWall = new Texture("topright_innercorner_wall.png");
	static Texture botLeftInCornerWall = new Texture("botleft_innercorner_wall.png");
	static Texture botRightInCornerWall = new Texture("botright_innercorner_wall.png");
}
