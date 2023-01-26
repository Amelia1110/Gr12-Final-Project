package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dialog extends Rectangle {
	static boolean showDialog = false;
	static BufferedImage img;
	int currentText = 0;
	// All dialog for the current scene
	final String[] sceneDialog;
	
	// Cannot create a dialog object from other classes
	private Dialog(String[] sceneDialog) {
		// Set texture image
		try {
			img = ImageIO.read(new File("dialog.png"));
			width = img.getWidth();
			height = img.getHeight();
		} catch (IOException e) {
			System.out.println("Warning: dialog.png failed to load");
		}
				
		// Set x and y coordinates
		x = 64;
		y = 64 * 7;
		
		// Fill dialog array
		this.sceneDialog = sceneDialog;
	}
	
	static void setShowDialog(boolean toggle) {
		showDialog = toggle;
	}
	
	
	// INTRO SCENE
	// Create dialog object for each scene
	private static final String[] introScene1Text = {
			"Hello there, you unfortunate little creature.",
			"As you have already noticed - hopefully - you find yourself locked inside this miserable#space with none but one way out - the path we designed for you.",
			"You will solve a series of puzzles, riddles, and games, of which the answers will lead#you to freedom and more.",
			"We have erased most of your memory for you, but I think you deserve to know how#you ended up in this situation:",
			"You are enrolled at a school designed for outcasts, and the reason you are here is#because you have discovered our secret society, exclusive to only the most elite.",
			"You either earn a spot among us and obtain eternal glory -- as well as regaining your#memories back -- by successfully completing the series of tasks...",
			"or you DIE! Simple really, isn't it? Go on then! Start your journey by inputting the word#\"Nightshade\" somewhere!"
			
	};
	static Dialog introScene1 = new Dialog(introScene1Text);
}
