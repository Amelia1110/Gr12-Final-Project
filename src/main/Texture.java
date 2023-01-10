package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture extends Rectangle{
	BufferedImage img;
	
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
	
	// Create all textures (All textures should be 64 x 64)
	static Texture wood = new Texture("wood.png");
}
