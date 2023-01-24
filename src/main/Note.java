package main;

// For any interactable that triggers a scene of dialog
public class Note extends Interactable {
	Dialog scene;
	
	Note() {}
	
	Note(String fileName, Dialog scene) {
		// Interactable method to set image
		setImageFile(fileName);
		
		// Define which dialog object the note triggers
		this.scene = scene;
	}
	
	Note(String fileName, Dialog scene, int rotation) {
		// Interactable method to set image
		setImageFile(fileName);
		super.rotation = rotation;
		
		// Define which dialog object the note triggers
		this.scene = scene;
	}
	
	@Override
	void interact() {
		scene.currentText = 0;
		EscapeRoomieGame.currentScene = scene;
		Dialog.showDialog = true;	
	}
}
