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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class EscapeRoomieGame implements ActionListener, MouseListener, KeyListener {
	// True when game is running
	static boolean gameRunning = true;
	
	// Show flower when last door is unlocked: meaning once player interacts with flower they win!
	static boolean showFlower = false;
	
	// JFrame
	static JFrame window;
	
	// Panels for all maps
	static DrawingPanel introPanel, room1Panel, room2Panel, room3Panel, room4Panel, room5Panel, shopPanel;
	
	// Keeps track of which panel and what objects are currently being displayed
	static DrawingPanel activePanel; // Current Panel
	static Dialog currentDialog; 
	static Question currentPuzzle;
	static Door currentDoor;

	// Panel size is 18 by 12 squares, window width by window height
	static final int PANW = 18 * 64; //Each image is 64 x 64 pixels, let's make these multiples of 64
	static final int PANH = 12 * 64;
	
	// Player visibility radius, the higher it is the more visibility the player has 
	static int radius = 80; 

	// Store all objects
	static ArrayList<Texture> textures = new ArrayList<Texture>(); // This is for things like floor and walls
	static ArrayList<Interactable> interactables = new ArrayList<Interactable>(); // This is for all the items that the player can interact with throughout the game
	static HashMap<String, DrawingPanel> panels = new HashMap<String, DrawingPanel>(); 

	// Spawn player
	static Player player = new Player(9*64, 5*64);
  
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
	static BetterKeyListener bKeyL = new BetterKeyListener(); // For player control and other actions that are key-controlled

	// Constructor, create game
	EscapeRoomieGame() {
		addTextures();
		addInteractables(); 
    
		// Adding KeyListeners to panels and adding panels to the hashmap for organization
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
		// These numbers are used when initializing maps 
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
		// These numbers are used when initializing maps 
		interactables.add(null);	//0
		interactables.add(Interactable.room1ToRoom2);//1
		interactables.add(Interactable.room1ToRoom3);//2
		interactables.add(Interactable.room1ToRoom4);//3
		interactables.add(Interactable.room1ToRoom5);//4
		
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
		interactables.add(Interactable.room2ToRoom1); //22
		interactables.add(Interactable.room3ToRoom1); //23
		interactables.add(Interactable.room4ToRoom1); //24
		interactables.add(Interactable.room5ToRoom1); //25
    
		interactables.add(Interactable.shop); //26
	}

	// Informing the user of how to navigate through the game 
	// NOTE: because of the differences in characters between mac and windows and we are working via mac, windows might not be 
	// 		 able to read some characters! In case that happens, the missing letters are: W, S, A, D, E, and X
	void instructionMessage() {
		JOptionPane.showMessageDialog(null, "1. Press ‘W’ to move up\n"
				+ "2. Press ‘S’ to move down\n"
				+ "3. Press ‘A’ to move left\n"
				+ "4. Press ‘D’ to move right\n"
				+ "5. Press ‘E’ to interact with an item\n"
				+ "6. Press ‘X’ to view this instruction page again\n"
				+ "7. Press 'Enter' to submit puzzle answer\n"
				+ "8. Left click mouse to move forward/close an interaction\n"
				+ "9. Tip: careful with doors (dark thingies that resemble portals), they are places where you enter your answers.\n"
				+ "10. For every wrong answer you enter, you get health points taken off!\n      If you interact with a door and has no answer to give, you will lose health.\n"
				+ "11. So, there is a shop -- hidden somewhere -- for when you want to buy items to restore health!",
				"Instructions", JOptionPane.INFORMATION_MESSAGE);
	}

	/*** for mainTimer ***/
	@Override
	public void actionPerformed(ActionEvent e) {	
		int[][] groundMap = activePanel.targetMap.mapGround; 
		int[][]	topMap = activePanel.targetMap.mapTopLayer;

		// Move as long as user isn't in the process of an action that shouldn't be interrupted
		if (!Door.typing && !Question.puzzleShowing && gameRunning && !Dialog.showDialog) {
			//move player (assuming that a key has been pressed)
			if (bKeyL.isKeyDown('A') || bKeyL.isKeyDown(37)) player.move('A', groundMap, topMap);
			if (bKeyL.isKeyDown('W') || bKeyL.isKeyDown(38)) player.move('W', groundMap, topMap);
			if (bKeyL.isKeyDown('D') || bKeyL.isKeyDown(39)) player.move('D', groundMap, topMap);
			if (bKeyL.isKeyDown('S') || bKeyL.isKeyDown(40)) player.move('S', groundMap, topMap);
		}

		activePanel.repaint();
	};

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
			// Updates sentences in dialogs when user clicks the mouse
			if (currentDialog != null && Dialog.showDialog && currentDialog.currentText < currentDialog.sceneDialog.length) {
				currentDialog.currentText++;
			}
			if (currentDialog != null && currentDialog.currentText == currentDialog.sceneDialog.length) {
				Dialog.showDialog = false;
			}
      
			// Closes puzzles when user wishes to, by clicking the mouse
			if (Question.puzzleShowing) Question.puzzleShowing = false;
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		// Making sure an action can be performed
		if (!Door.typing && gameRunning) {
			int[][]	topMap = activePanel.targetMap.mapTopLayer;
			
			// Player interacts with an interactable when 'e' is pressed
			if (e.getKeyChar() == 'e') {
				Interactable target = interactables.get(player.canInteractWith(topMap));
				 // Only shows interaction results if player is interacting an interactable objects
				if (target != null) {
					target.interact();
				}
			}
      
			// Allows players to see the instruction message again when they press 'x'
			if (e.getKeyChar() == 'x') {
			  instructionMessage();
			}
		}
		
		// When user is entering solution at a door, it reads their input
		else {
			currentDoor.getUserInput(e.getKeyCode(), e.getKeyChar());
		}
		
		activePanel.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}