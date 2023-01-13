package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Interactables extends Rectangle{
	BufferedImage img;
	
	Interactables(String fileName) {
		// Set int image
		try {
			img = ImageIO.read(new File(fileName));
			width = img.getWidth();
			height = img.getHeight();
		} catch (IOException e) {
			System.out.println("Warning: image failed to load");
		}		
	}
	
	// Create all textures (All textures should be 64 x 64)
	//static Interactables introNote = new Interactables("intronote.png");
}
