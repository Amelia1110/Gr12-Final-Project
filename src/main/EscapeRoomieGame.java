package main;

import javax.swing.SwingUtilities;

public class EscapeRoomieGame {

	public static void main(String[] args) {
		// Start program with swing graphics
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
				new EscapeRoomieGame();
			}
		});
	}
	
	// Constructor, create game
	EscapeRoomieGame() {
		
	}
	
	// Setup window
	void setupJFrame() {
		
	}
	
	// Generate all maps
	void createMapObjects() {
		// Initialize first map
		int[][] newMap = {	{0, 0, 0, 0},
							{0, 0, 0, 0},
							{0, 0, 0, 0}
		};
		
		// Create new map object
		Map testMap = new Map(newMap);
	}
}
