package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dialog extends Rectangle {
	static boolean showDialog = true;
	BufferedImage img;
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
			"To help you focus on this task, we have erased all memories for you except the#following pieces:"
	};
	static Dialog introScene1 = new Dialog(introScene1Text);
	
	private static final String[] introScene2Text = {
			"Here is a taste of what to expect for the duration of the whole task of freeing yourself from the cage:",
			"You will solve a series of puzzles, riddles, and games, of which the answers will lead you to freedom and more. Since we are not heartless creatures or your enemies, here is a hint: each of the numbers correlates to a letter based on their order in the alphabet."
	};
	static Dialog introScene2 = new Dialog(introScene2Text);
	
	private static final String[] introScene3Text = {
			"We are glad your intelligence still remains, unlike most of your memories. You’re welcome, by the way, if the hint helped you at all.",
			"As we mentioned, we are not your enemies, so whenever you are stuck, press ‘H’ for hints – three in total, unless you purchase more in the future… – again, you’re welcome.",
			"We are also glad you have successfully solved the first puzzle, so as promised, here is what you need to know:",
			"You are enrolled at a school designed for outcasts, and the reason you are here is because you have discovered our secret society, exclusive to only the most elite. You either regain your freedom, memories, and eternal glory by successfully completing this task we have carefully designed for you, or you die.",
			"Simple really, isn’t it?",
			"Now, crack the following message and type your answer below, same process as last one: (better not mess this up, it is the name of our o’mighty society)"
	};
	static Dialog introScene3 = new Dialog(introScene3Text);
}
