package main;

// For any interactable that triggers a scene of dialog
public class Note extends Interactable {
	Dialog sceneDialog;
	
	Note() {}
	
	Note(String fileName, Dialog sceneDialog) {
		// Interactable method to set image
		setImageFile(fileName);
	}
	
	Note(String fileName, Dialog sceneDialog, int rotation) {
		// Interactable method to set image
		setImageFile(fileName);
		super.rotation = rotation;
	}
	
	@Override
	void interact() {
		System.out.println("Interacted");
	}
}
