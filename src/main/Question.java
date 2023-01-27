package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// For any interactable that shows a puzzle when interacted
public class Question extends Interactable {
	static boolean puzzleShowing = false; // Keeps track of whether the puzzle is showing or not
	BufferedImage puzzleImage;
	
	Question() {}
	
	Question(String iconImage, String puzzleImage) {
		setImageFile(iconImage);

		// Set puzzle image
		try {
			this.puzzleImage = ImageIO.read(new File(puzzleImage));
			width = this.puzzleImage.getWidth();
			height = this.puzzleImage.getHeight();
		} catch (IOException e) {
			System.out.println("Warning: puzzle image failed to load");
		}
		
		// Set location of where the image will be drawn
		x = EscapeRoomieGame.PANW/2 - width/2;
		y = EscapeRoomieGame.PANH/2 - height/2;
	}

	@Override
	void interact() {
		// Method for when player interacts with a puzzle (question) interactable
		puzzleShowing = true;	
		EscapeRoomieGame.currentPuzzle = this;
	}
}
