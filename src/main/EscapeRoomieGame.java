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
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class EscapeRoomieGame implements ActionListener, MouseListener {
	// Panels for all maps
	DrawingPanel introPanel;
	// Declare all maps
	Map testIntroMap;

	// Store all textures
	ArrayList<Texture> textures = new ArrayList<Texture>();

	Interactables introNote = new Interactables("player.png");

	Player player = new Player(280, 250);

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
		createMapObjects();
		addTextures();
		introPanel = new DrawingPanel(testIntroMap);
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
		window.pack();
		window.setLocationRelativeTo(null);
		window.addMouseListener(this);
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
		int[][] newMap = {			 //8
				{0, 0, 4, 4, 4, 4, 0, 0},
				{0, 2, 1, 1, 1, 1, 3, 0},
				{0, 2, 1, 1, 1, 1, 3, 0},
				{0, 2, 1, 1, 1, 1, 3, 0},
				{0, 2, 1, 1, 1, 1, 3, 0},
				{0, 2, 1, 1, 1, 1, 3, 0},
				{0, 2, 1, 1, 1, 1, 3, 0},
				{0, 0, 5, 5, 5, 5, 0, 0},//8
		};

		testIntroMap = new Map(newMap);
	}

	// DrawingPanel class
	private class DrawingPanel extends JPanel {

		// Game dimensions
		static final int PANW = 512; //Each image is 64 x 64 pixels, lets make these multiples of 64
		static final int PANH = 512;

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
		}

		// Draw components
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); //draw background
			//Setup graphics component
			g2 = (Graphics2D) g;
			//antialiasing:
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			//draw map
			loadMap();
			//draw player
			loadPlayer();
			//draw interactables
			loadInteractables();
			//check collisions
			checkCollision();
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

					if (targetMap.mapLayout[y][x] != 0) {
						g2.drawImage(textures.get(targetMap.mapLayout[y][x]).img, xPos, yPos, null);
					}
				}
			}
		}

		void loadInteractables() {
			g2.drawImage(introNote.img, 230, 420, null);
		}

		void loadPlayer() {
			g2.setColor(Color.RED);	//color of hitbox
			g2.drawImage(player.image, player.x, player.y, null);

			if (player.showHitBox) {
				g2.drawRect(player.x, player.y, player.width, player.height);
				if (player.health > 50) g2.setColor(Color.GREEN);
				g2.fillRect(player.x, player.y, player.health*player.width/100, 10);
			}
		}

		void checkCollision() {

		}
	}

	/*** for mainTimer ***/
	@Override
	public void actionPerformed(ActionEvent e) {
		//move player (assuming that a key has been pressed)
		if (bKeyL.isKeyDown('A') || bKeyL.isKeyDown(37)) player.move('A');
		if (bKeyL.isKeyDown('W') || bKeyL.isKeyDown(38)) player.move('W');
		if (bKeyL.isKeyDown('D') || bKeyL.isKeyDown(39)) player.move('D');
		if (bKeyL.isKeyDown('S') || bKeyL.isKeyDown(40)) player.move('S');

		introPanel.repaint();
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
