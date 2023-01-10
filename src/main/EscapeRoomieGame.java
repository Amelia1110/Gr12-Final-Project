package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class EscapeRoomieGame {
	// Panels for all maps
	DrawingPanel introPanel;
	// Declare all maps
	Map testIntroMap;
	
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
		createMapObjects();
		addTextures();
		introPanel = new DrawingPanel(testIntroMap);
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
		textures.add(null);
		textures.add(Texture.floor);		//1
		textures.add(Texture.leftWall);		//2
		textures.add(Texture.rightWall);	//3
		textures.add(Texture.topWall);		//4
		textures.add(Texture.bottomWall);	//5

	}
	
	// Generate all maps
	void createMapObjects() {
		// Initialize first map
											//6				//12
		int[][] newMap = {	{0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							//5
							{0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							//10
							{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		};
		
		testIntroMap = new Map(newMap);
	}

	// DrawingPanel class
	private class DrawingPanel extends JPanel {
		// Game dimensions
		static final int PANW = 1152; //Each image is 64 x 64 pixels, lets make these multiples of 64
		static final int PANH = 768;
		static final int MAP_WIDTH = PANW/64; // TODO change this so not magic number
		static final int MAP_HEIGHT = PANH/64;
		
		Graphics2D g2;
		
		// Target map
		final Map targetMap;
		
		private DrawingPanel() {
			// cannot create drawing panel with no parameters
			targetMap = null;
		}
		
		// Panel constructor
		DrawingPanel(Map map) {
			targetMap = map;
			// Setup JPanel
			this.setPreferredSize(new Dimension(PANW, PANH));
			this.setBackground(Color.BLACK);
		}
		
		// Draw components
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // draw background
			
			// Setup graphics component
			g2 = (Graphics2D) g;

			// antialiasing:
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			// draw map
			loadMap();
		}
		
		// Load the map
		void loadMap() {
			int xPos;
			int yPos;
			
			// Iterate through and draw each element in the map
			for (int y = 0; y < targetMap.mapLayout.length; y++) {
				for (int x = 0; x < targetMap.mapLayout[0].length; x++) {
					xPos = PANW/targetMap.mapLayout[0].length * x;
					yPos = PANH/targetMap.mapLayout.length * y;
					
					System.out.println(xPos + " " + yPos);
					System.out.println(targetMap.mapLayout[y][x]);
					
					if (targetMap.mapLayout[y][x] != 0) {
						g2.drawImage(textures.get(targetMap.mapLayout[y][x]).img, xPos, yPos, null);
					}
				}
			}
		}
	}
}
