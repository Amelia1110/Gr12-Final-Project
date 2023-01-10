package main;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class EscapeRoomieGame {
	// Panels for all maps
	DrawingPanel introPanel;
	// Store all textures
	ArrayList<Texture> textures = new ArrayList<Texture>();
	
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
		introPanel = new DrawingPanel();
		setupJFrame();
	}
	
	// Setup window
	void setupJFrame() {
		// Set parameters
		JFrame window = new JFrame("Escape Room");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Render window
		window.add(introPanel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
	// Declare all textures
	void addTextures() { 
		textures.add(Texture.wood);
	}
	
	// Generate all maps
	void createMapObjects() {
		// Initialize first map
		int[][] newMap = {	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
							{0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
							{0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
							{0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
							{0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
							{0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		};
		
		// Create new map object
		Map testMap = new Map(newMap);
	}
	
	
	// DrawingPanel class
	private class DrawingPanel extends JPanel {
		// Game dimensions
		static final int PANW = 768; //Each image is 64 x 64 pixels, lets make these multiples of 64
		static final int PANH = 512;
		
		// Panel constructor
		DrawingPanel() {
			// Setup JPanel
			this.setPreferredSize(new Dimension(PANW, PANH));
			this.setBackground(Color.BLACK);
		}
	}
}
