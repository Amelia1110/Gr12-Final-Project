package main;

//by Amelia Song, Alex You, and Alex Cai
//January 24, 2023
//final project, ICS4U

//this is a 2.5-dimension escape room, demonstrating what the three of us have learned throughout the entire course as well as concepts
//we have learned ourselves from researches in our free time. The purpose of the game is simple, the player has to solve a series of
//puzzles, riddles, etc to gather enough clues to gain freedom ultimately. The player first begins in an introductory room, where
//a note lies on the floor. As soon as the user interacts with the note, a storyline pops up for them. Once the user finishes the 
//introduction, they find themselves in the real game. Some puzzles lead to other puzzles and combining the answers they unlock access
//to the rest of the rooms, while others -- like the ones in the beginning stages of the game -- are easier and they directly grant
//user access to more rooms once solved. The game gets progressively harder -- gaining your freedom is not an easy task!  

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class EscapeRoomieGame implements ActionListener, MouseListener, KeyListener {
	// JFrame
	static JFrame window;
	
	// Panels for all maps
	static DrawingPanel introPanel, room1Panel, room2Panel, room3Panel, room4Panel, room5Panel, shopPanel;
	
	// Keeps track of which panel and what objects are currently being displayed
	static DrawingPanel activePanel, lastPanel;
	static Dialog currentScene;
	static Question currentPuzzle;
	static Door currentDoor;

	// Panel size is 18 by 12 squares
	static final int PANW = 18 * 64; //Each image is 64 x 64 pixels, lets make these multiples of 64
	static final int PANH = 12 * 64;
	
	// Player visibility radius 
	static int radius = 1000; // TODO change this back :)

	// Store all textures
	static ArrayList<Texture> textures = new ArrayList<Texture>();
	static ArrayList<Interactable> interactables = new ArrayList<Interactable>();
	static HashMap<String, DrawingPanel> panels = new HashMap<String, DrawingPanel>();

	// Create player object on tile (8, 5)
	static Player player = new Player(9*64, 5*64);
	
	//checking if shop is showing
	static boolean shopShowing = false;
  
	// Game font
	static Font pixeloidSans;

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
	static BetterKeyListener bKeyL = new BetterKeyListener();

	// Constructor, create game
	EscapeRoomieGame() {
		addTextures();
		addInteractables();
    
		introPanel = new DrawingPanel(Map.introRoom);
		introPanel.addKeyListener(this);
		panels.put("introPanel", introPanel);
		room1Panel = new DrawingPanel(Map.room1);
		room1Panel.addKeyListener(this);
		panels.put("room1Panel", room1Panel);
		room2Panel = new DrawingPanel(Map.room2);
		room2Panel.addKeyListener(this);
		panels.put("room2Panel", room2Panel);
		room3Panel = new DrawingPanel(Map.room3);
		room3Panel.addKeyListener(this);
		panels.put("room3Panel", room3Panel);
		room4Panel = new DrawingPanel(Map.room4);
		room4Panel.addKeyListener(this);
		panels.put("room4Panel", room4Panel);
		room5Panel = new DrawingPanel(Map.room5);
		room5Panel.addKeyListener(this);
		panels.put("room5Panel", room5Panel);
		shopPanel = new DrawingPanel(Map.shopRoom);
		shopPanel.addKeyListener(this);
		panels.put("shopPanel", shopPanel);
    
		setupJFrame();
		mainTimer.start();
	}

	// Setup window
	void setupJFrame() {
		// Set parameters
		window = new JFrame("Escape Room");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instructionMessage();
		
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
		interactables.add(Interactable.doorDown);	//3
		interactables.add(Interactable.doorLeft);	//4
		interactables.add(Interactable.wallLight); 	//5
		
		interactables.add(Interactable.burger); 	//6
		interactables.add(Interactable.milk); 		//7
		interactables.add(Interactable.flashlight); //8
		interactables.add(Interactable.hint); 		//9
		
		interactables.add(Interactable.closedBook);//10
		interactables.add(Interactable.paper);	   //11
		interactables.add(Interactable.rubikCube); //12
		interactables.add(Interactable.musicNote); //13
		interactables.add(Interactable.clock);	   //14
		interactables.add(Interactable.jewelry);   //15
		interactables.add(Interactable.mathNote);  //16
		interactables.add(Interactable.alphabet);  //17	
		interactables.add(Interactable.openBook);//18
		interactables.add(Interactable.flower);    //19
		
		interactables.add(Interactable.introNote); //20
		
		interactables.add(Interactable.introToRoom1); //21
    
		interactables.add(Interactable.shop); //22
	}

	void instructionMessage() {
		JOptionPane.showMessageDialog(null, "1. Press ‘W’ to move up\n"
				+ "2. Press ‘S’ to move down\n"
				+ "3. Press ‘A’ to move left\n"
				+ "4. Press ‘D’ to move right\n"
				+ "5. Press ‘E’ to interact with an item\n"
				+ "6. Press ‘X’ to view this instruction page again\n"
				+ "7. Left click mouse to move forward/close an interaction",
				"Instructions", JOptionPane.INFORMATION_MESSAGE);
	}

	/*

	// DrawingPanel class
	class DrawingPanel extends JPanel {
		// Fonts
		Font dialogFont, promptFont, lockFont;

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
				dialogFont = pixeloidSans.deriveFont(20f);
				promptFont = pixeloidSans.deriveFont(10f);
				lockFont = pixeloidSans.deriveFont(100f);
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

			//draw map
			loadMap();
			
			//draw shop icon in shop: (large shop image on floor)
			if (activePanel.equals(shopPanel)) {
				g2.drawImage(Texture.shopImage.img, 64*7, 64*4, null);			
			}
			
			//draw interactables
			loadInteractables();

			//draw player
			loadPlayer();
			
			g2.setColor(Color.WHITE);
			
			// if an interaction is possible
			if (player.canInteractWith(targetMap.mapTopLayer) != 0) {
				g2.setFont(promptFont);
				g2.drawString("E to Interact", player.x, player.y - 10);
			}
			
			//draw vision restrictions
			Area outer = new Area(new Rectangle(0, 0, getWidth(), getHeight()));
			int x = player.x+player.width/2 - radius;
			int y = player.y+player.height/2 - radius;
			// Rectangle inner = new Rectangle(x, y, 200, 200);
			Ellipse2D.Double inner = new Ellipse2D.Double(x,y,2*radius,2*radius);	
			outer.subtract(new Area(inner));

			g2.setColor(Color.BLACK);
			g2.fill(outer);
			
			// draw dialog if there dialog is set to on
			if (Dialog.showDialog) {
				g2.setColor(Color.WHITE);
				g2.setFont(dialogFont);
				g2.drawImage(Dialog.img, currentScene.x, currentScene.y, null);
				drawDialog();
			}
			
			// draw puzzle image if the boolean puzzleShowing is set to true
			if (Question.puzzleShowing) g2.drawImage(currentPuzzle.puzzleImage, currentPuzzle.x, currentPuzzle.y, null);

			if (Door.typing) {
				g2.setColor(Color.WHITE);
				g2.setFont(lockFont);
				g2.drawString(currentDoor.userInput, 100, PANH/2);
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
		int[][] groundMap = activePanel.targetMap.mapGround;
		int[][]	topMap = activePanel.targetMap.mapTopLayer;

		// Move as long as user isn't typing
		if (!Door.typing && !Question.puzzleShowing) {
			//move player (assuming that a key has been pressed)
			if (bKeyL.isKeyDown('A') || bKeyL.isKeyDown(37)) player.move('A', groundMap, topMap);
			if (bKeyL.isKeyDown('W') || bKeyL.isKeyDown(38)) player.move('W', groundMap, topMap);
			if (bKeyL.isKeyDown('D') || bKeyL.isKeyDown(39)) player.move('D', groundMap, topMap);
			if (bKeyL.isKeyDown('S') || bKeyL.isKeyDown(40)) player.move('S', groundMap, topMap);
		}

		activePanel.repaint();
	};

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int xCor, yCor;
		
			// Get coordinates of click
			xCor = e.getX();
			yCor = e.getY();
			System.out.println(xCor + ", " + yCor + "\n"); //TODO Remove
			
			if (currentScene != null && Dialog.showDialog && currentScene.currentText < currentScene.sceneDialog.length) {
				currentScene.currentText++;
			}
			
			if (currentScene != null && currentScene.currentText == currentScene.sceneDialog.length) {
				Dialog.showDialog = false;
			}
      
			if (Question.puzzleShowing) Question.puzzleShowing = false;
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!Door.typing) {
			int[][]	topMap = activePanel.targetMap.mapTopLayer;
			
			if (e.getKeyChar() == 'e') {
				Interactable target = interactables.get(player.canInteractWith(topMap));
				System.out.println(target);
				
				if (target != null) {
					System.out.println("interact");
					target.interact(); // only shows interaction results if player is interacting an interactable object
				}
			}
      
			if (e.getKeyChar() == 'x') {
			  instructionMessage();
			}
		
		  /*if (e.getKeyChar() == 'q' && Shop.shopShowing) {
			  System.out.println("Hi");
			  Shop.exit();
		  }*/
		}
		
		else {
			currentDoor.getUserInput(e.getKeyCode(), e.getKeyChar());
		}
		
		activePanel.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}