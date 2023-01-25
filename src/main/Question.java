package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// For any interactable that triggers a scene of dialog
public class Question extends Interactable {
	static boolean puzzleShowing = false;
	BufferedImage puzzleImage;
	
	Question() {}
	
	Question(String iconImage, String puzzleImage) {
		// Interactable method to set icon image
		setImageFile(iconImage);

		// Set puzzle image
		try {
			this.puzzleImage = ImageIO.read(new File(puzzleImage));
			width = this.puzzleImage.getWidth();
			height = this.puzzleImage.getHeight();
		} catch (IOException e) {
			System.out.println("Warning: puzzle image failed to load");
		}
		
		x = EscapeRoomieGame.PANW/2 - width/2;
		y = EscapeRoomieGame.PANH/2 - height/2;
	}

	@Override
	void interact() {
		puzzleShowing = true;	
		EscapeRoomieGame.currentPuzzle = this;
	}
}
