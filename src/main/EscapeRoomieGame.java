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
		textures.add(Texture.topLeftOutCornerWall); //6
		textures.add(Texture.topRightOutCornerWall);//7
		textures.add(Texture.botLeftOutCornerWall); //8
		textures.add(Texture.botRightOutCornerWall);//9
		textures.add(Texture.topLeftInCornerWall);  //10
		textures.add(Texture.topRightInCornerWall); //11
		textures.add(Texture.botLeftInCornerWall);  //12
		textures.add(Texture.botRightInCornerWall); //13
		textures.add(Texture.doorUp);		//14
		textures.add(Texture.doorRight); 	//15
		textures.add(Texture.doorDown);		//16
		textures.add(Texture.doorLeft);		//17
	}

	// Generate all maps
	void createMapObjects() {
		// Initialize first map
<<<<<<< HEAD
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
=======
												//6				//12
		int[][] newMap = {		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 6, 4, 4, 4, 4, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 6, 4, 4, 4, 4, 7, 0},
								//5
								{0, 0, 0, 2, 1, 1, 1, 1, 12,4, 4, 13,1, 1, 1, 1, 3, 0},
								{0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0},
								{0, 0, 0, 2, 1, 1, 1, 1, 10,5, 5, 11,1, 1, 1, 1, 3, 0},
								{0, 0, 0, 8, 5, 5, 5, 5, 9, 0, 0, 2, 1, 1, 1, 1, 3, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 5, 5, 5, 5, 9, 0},
								//10
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
>>>>>>> 37db855f9a533ed1f94590e168c572201fe83726
		};
												//6				//12
		int[][] newLayer2Map = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 14,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								//5
								{0, 0, 0, 0, 17,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15,0, 0},
								{0, 0, 0, 0, 0, 16,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								//10
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		};
		testIntroMap = new Map(newMap, newLayer2Map);
	}

	// DrawingPanel class
	private class DrawingPanel extends JPanel {

		// Game dimensions
		static final int PANW = 18 * 64; //Each image is 64 x 64 pixels, lets make these multiples of 64
		static final int PANH = 12 * 64;

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
							g2.rotate(texture.rotation, xPos + 32, yPos + texture.height/2);
							g2.drawImage(texture.img, xPos, yPos, null);
							g2.rotate(-texture.rotation, xPos + texture.width/2, yPos + texture.height/2);
							
						}
						// Draw a normal image
						else g2.drawImage(textures.get(targetMap.mapGround[y][x]).img, xPos, yPos, null);
					}
				}
			}
			
			// Iterate through and draw each element in the second layer of the map
			for (int y = 0; y < targetMap.mapLayer2.length; y++) {
				for (int x = 0; x < targetMap.mapLayer2[0].length; x++) {
					// Declare the x and y coordinate of where the image will be drawn
					xPos = PANW/targetMap.mapLayer2[0].length * x;
					yPos = PANH/targetMap.mapLayer2.length * y;
					
					if (targetMap.mapLayer2[y][x] != 0) {
						// Determine which texture is being referenced
						texture = textures.get(targetMap.mapLayer2[y][x]);
						
						// Draw a rotated image
						if(texture.rotation != 0.0) {
							g2.rotate(texture.rotation, xPos + texture.width/2, yPos + texture.height/2);
							g2.drawImage(texture.img, xPos, yPos, null);
							g2.rotate(-texture.rotation, xPos + texture.width/2, yPos + texture.height/2);
							
						}
						
						// Draw a normal image
						else g2.drawImage(textures.get(targetMap.mapLayer2[y][x]).img, xPos, yPos, null);
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
			if (player.health > 50) g2.setColor(Color.GREEN);
			g2.fillRect(player.x, player.y, player.health*player.width/100, 5);
			if (player.showHitBox) {
				g2.drawRect(player.x, player.y, player.width, player.height);
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