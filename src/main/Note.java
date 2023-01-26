package main;

// For any interactable that triggers a scene of dialog
public class Note extends Interactable {
	Dialog noteDialog;
	
	Note() {}
	
	Note(String fileName, Dialog scene) {
		// Interactable method to set image
		setImageFile(fileName);
		
		// Define which dialog object the note triggers
		this.noteDialog = scene;
	}
	
	Note(String fileName, Dialog scene, int rotation) {
		// Interactable method to set image
		setImageFile(fileName);
		super.rotation = rotation;
		
		// Define which dialog object the note triggers
		this.noteDialog = scene;
	}
	
	@Override
	void interact() {
		noteDialog.currentText = 0;
		EscapeRoomieGame.currentDialog = noteDialog;
		Dialog.showDialog = true;	
	}
}
