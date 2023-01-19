package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class EscapeRoomieGame implements ActionListener, MouseListener {
	// Panels for all maps
	static DrawingPanel introPanel, room1Panel;
	
	// Keeps track of which panel is currently being displayed
	static DrawingPanel activePanel;
	static Dialog currentScene = Dialog.introScene1;

	// Store all textures
	static ArrayList<Texture> textures = new ArrayList<Texture>();
	static ArrayList<Interactable> interactables = new ArrayList<Interactable>();

	// Create player object
	static Player player = new Player(6*64, 5*64);

	// Run program
	public static void main(String[] args) {
		// Start program with swing graphics
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EscapeRoomieGame();
			}
		});
	}
	
	Timer mainTimer = new Timer(10, this);
	BetterKeyListener bKeyL = new BetterKeyListener();

	// Constructor, create game
	EscapeRoomieGame() {
		addTextures();
		addInteractables();
		introPanel = new DrawingPanel(Map.introRoom);
		room1Panel = new DrawingPanel(Map.room1);
		setupJFrame();
		mainTimer.start();
	}

	// Setup window
	void setupJFrame() {
		// Set parameters
		JFrame window = new JFrame("Escape Room");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Render window
		window.add(introPanel);
		activePanel = introPanel;
		window.pack();
		window.setLocationRelativeTo(null);
		window.addMouseListener(this);
		window.setVisible(true);
	}
	
	// Declare all textures
	void addTextures() { 
		textures.add(null);	//0
		textures.add(Texture.floor);		//1
		textures.add(Texture.leftWall);		//2
		textures.add(Texture.rightWall);	//3
		textures.add(Texture.topWall);		//4
		textures.add(Texture.bottomWall);	//5
		textures.add(Texture.topLeftOutCornerWall); //6
		textures.add(Texture.topRightOutCornerWall);//7
		textures.add(Texture.botLeftOutCornerWall); //8
		textures.add(Texture.botRightOutCornerWall);//9
		textures.add(Texture.topLeftInCornerWall);  //10
		textures.add(Texture.topRightInCornerWall); //11
		textures.add(Texture.botLeftInCornerWall);  //12
		textures.add(Texture.botRightInCornerWall); //13
		
	}
	
	// Declare all interactables
	void addInteractables() {
		interactables.add(null);	//0
		interactables.add(Interactable.doorUp);		//1
		interactables.add(Interactable.doorRight); 	//2
		interactables.add(Interactable.doorDown);		//3
		interactables.add(Interactable.doorLeft);		//4
		interactables.add(Interactable.wallLight); 	//5
		
	}

	// DrawingPanel class
	private class DrawingPanel extends JPanel {
		// Game dimensions
		static final int PANW = 18 * 64; //Each image is 64 x 64 pixels, lets make these multiples of 64
		static final int PANH = 12 * 64;
		static Font pixeloidSans;
		static Font gameFont;

		Graphics2D g2;

		// Target map
		final Map targetMap;

		//inaccessible
		private DrawingPanel() {
			// cannot create drawing panel without a map
			targetMap = null;
		}

		// Panel constructor
		DrawingPanel(Map map) {
			targetMap = map;
			// Setup JPanel
			this.setPreferredSize(new Dimension(PANW, PANH));
			this.setBackground(Color.BLACK);
			this.addKeyListener(bKeyL);
			this.setFocusable(true);
			
			// Create Font for dialogs
			try {
				pixeloidSans = Font.createFont(0, new File("gameFont.ttf"));
				gameFont = pixeloidSans.deriveFont(20f);
				
			} catch (FontFormatException e) {
				System.out.println("Warning: font failed to load");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Warning: font failed to load");
				e.printStackTrace();
			}
		}

		// Draw components
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); //draw background
			//Setup graphics component
			g2 = (Graphics2D) g;
			//antialiasing:
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			// Set font
			g2.setFont(gameFont);

			//draw map
			loadMap();
			//draw interactables
			loadInteractables();
			
			//draw player
			loadPlayer();
			
			// draw dialog if there dialog is set to on
			if (Dialog.showDialog) {
				g2.drawImage(currentScene.img, currentScene.x, currentScene.y, null);
				g2.setColor(Color.WHITE);
				drawDialog();
			}
		}


		// Load the map
		void loadMap() {
			int xPos;
			int yPos;

			Texture texture;
			
			// Iterate through and draw each element in the ground layer of the map
			for (int y = 0; y < targetMap.mapGround.length; y++) {
				for (int x = 0; x < targetMap.mapGround[0].length; x++) {
					// Declare the x and y coordinate of where the image will be drawn
					xPos = PANW/targetMap.mapGround[0].length * x;
					yPos = PANH/targetMap.mapGround.length * y;			
					
					if (targetMap.mapGround[y][x] != 0) {
						// Determine which texture is being referenced
						texture = textures.get(targetMap.mapGround[y][x]);
						
						// Draw a rotated image
						if(texture.rotation != 0.0) {
							g2.rotate(texture.rotation, xPos + texture.height/2, yPos + texture.height/2);
							g2.drawImage(texture.img, xPos, yPos, null);
							g2.rotate(-texture.rotation, xPos + texture.width/2, yPos + texture.height/2);
							
						}
						// Draw a normal image
						else g2.drawImage(texture.img, xPos, yPos, null);
					}
				}
			}
		}
			
		void loadInteractables() {
			int xPos;
			int yPos;
			
			Interactable interactable;
			
			// Iterate through and draw each element in the second layer of the map
			for (int y = 0; y < targetMap.mapTopLayer.length; y++) {
				for (int x = 0; x < targetMap.mapTopLayer[0].length; x++) {
					// Declare the x and y coordinate of where the image will be drawn
					xPos = PANW / targetMap.mapTopLayer[0].length * x;
					yPos = PANH / targetMap.mapTopLayer.length * y;

					if (targetMap.mapTopLayer[y][x] != 0) {
						// Determine which interactable is being referenced
						interactable = interactables.get(targetMap.mapTopLayer[y][x]);

						// Draw a rotated image
						if (interactable.rotation != 0.0) {
							g2.rotate(interactable.rotation, xPos + interactable.width / 2, yPos + interactable.height / 2);
							g2.drawImage(interactable.img, xPos, yPos, null);
							g2.rotate(-interactable.rotation, xPos + interactable.width / 2, yPos + interactable.height / 2);

						}

						// Draw a normal image
						else g2.drawImage(interactable.img, xPos, yPos, null);
					}
				}
			}
		}

		// Draw player
		void loadPlayer() {
			g2.setColor(Color.RED);	//color of hitbox
			g2.drawImage(player.image, player.x, player.y, null);
			if (player.health > 50) g2.setColor(Color.GREEN);
			g2.fillRect(player.x, player.y - 5, player.health*player.width/100, 3);
			if (player.showHitBox) {
				g2.drawRect(player.x, player.y, player.width, player.height);
			}
		}
		
		// Draw dialog
		void drawDialog() {
			String current = currentScene.sceneDialog[currentScene.currentText];
			String[] dialog = current.split("#", 0);
			
			for (int i = 0; i < dialog.length; i++) {
				g2.drawString(dialog[i], currentScene.x + 45, currentScene.y + 60 + (i * 30));
			}
		}
	}

	
	/*** for mainTimer ***/
	@Override
	public void actionPerformed(ActionEvent e) {	
		int[][] map = activePanel.targetMap.mapGround;
		
		//move player (assuming that a key has been pressed)
		if (bKeyL.isKeyDown('A') || bKeyL.isKeyDown(37)) player.move('A', map);
		if (bKeyL.isKeyDown('W') || bKeyL.isKeyDown(38)) player.move('W', map);
		if (bKeyL.isKeyDown('D') || bKeyL.isKeyDown(39)) player.move('D', map);
		if (bKeyL.isKeyDown('S') || bKeyL.isKeyDown(40)) player.move('S', map);
		
		activePanel.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int xCor, yCor;
		xCor = e.getX();
		yCor = e.getY();
		System.out.println(xCor + ", " + yCor + "\n");
		
		if (Dialog.showDialog) {
			currentScene.currentText++;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}