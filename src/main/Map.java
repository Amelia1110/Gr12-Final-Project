package main;

public class Map {
	final int[][] mapGround;
	final int[][] mapTopLayer;
	final static int TILE_DIMENSION = 64; // Each tile on the map should be 64 pixels, add imported images are 64 pixels
	
	// Inaccesible
	private Map() {
		this.mapGround = null;
		this.mapTopLayer = null;
	}
	
	private Map(int[][] mapLayout, int[][] mapLayer2) {
		this.mapGround = mapLayout;
		this.mapTopLayer = mapLayer2;
		
	}
	
	// Update the map at specified coordinates
	void updateMapGround(int x, int y, int value) {
		mapGround[y][x] = value;
	}
	
	// Update the map at specified coordinates
	void updateMapLayer2(int x, int y, int value) {
		mapTopLayer[y][x] = value;
	}
	
	
	
	
	//****** Generate all maps ******//
	// INTRO MAP, where the player gets the hang of the game
	static int[][] introMap = { {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 6, 4, 4, 4, 4, 7, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0},
								//5
								{0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0},
								//10
								{0, 0, 0, 0, 0, 0, 8, 5, 5, 5, 5, 9, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};

	// for decorations/interactables that go on top of the map										
	static int[][] introTopLayer = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 21,0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									//5
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 20,0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
									//10
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};

	
	// ROOM1 MAP, the room the player spawns in the actual game
	static int[][] room1Map = { {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
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
	};
	
	// for decorations/interactables that go on top of the map					
	static int[][] room1TopLayer = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									//5
									{0, 0, 0, 0, 4 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 10, 0, 0, 21, 0, 0, 0, 0, 0, 2, 0, 0},
									{0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									//10
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};
	
	// ROOM2 MAP, entered through room1 right door
	static int[][] room2Map = { {0, 0, 0, 0, 0, 0, 6, 4, 4, 7, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 6, 4, 4, 13, 1, 1, 3, 0, 6, 4, 4, 4, 4, 7, 0},
								{0, 0, 0, 2, 1, 1, 1, 1, 1, 3, 0, 2, 1, 1, 1, 1, 3, 0},
								{0, 0, 0, 2, 1, 1, 10, 5, 5, 9, 0, 2, 1, 1, 1, 1, 3, 0},
								{0, 0, 0, 2, 1, 1, 3, 0, 0, 0, 6, 13, 1, 1, 1, 1, 3, 0},
								//5
								{6, 4, 4, 13, 1, 1, 4, 4, 4, 4, 13, 1, 1, 1, 10, 5, 9, 0},
								{2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 10, 9, 0, 0, 0},
								{2, 1, 1, 10, 5, 5, 11, 1, 1, 1, 10, 5, 5, 9, 0, 0, 0, 0},
								{2, 1, 1, 3, 0, 0, 2, 1, 1, 1, 12, 4, 4, 4, 4, 7, 0, 0},
								{2, 1, 1, 3, 0, 0, 8, 11, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0},
								//10
								{8, 5, 5, 9, 0, 0, 0, 8, 5, 5, 5, 5, 11, 1, 1, 3, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 5, 5, 9, 0, 0},
	};

	// for decorations/interactables that go on top of the map														
	static int[][] room2TopLayer = {{0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0, 0, 5, 0, 0, 5, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									//5
									{0, 0, 5, 0, 0 ,0, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5, 0, 0, 0},
									{0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									//10
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};

	// ROOM3 MAP, entered through room1 top door
	static int [][] room3Map = { {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								 {6, 4, 4, 7, 0, 0, 6, 4, 4, 4, 4, 4, 4, 4, 4, 7, 0, 0},
								 {2, 1, 1, 12, 4, 4, 13, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0},
								 {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0},
								 {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 10, 11, 1, 1, 3, 0, 0},
								 //5
								 {2, 1, 1, 10, 5 ,5, 5, 5, 11, 1, 1, 3, 2, 1, 1, 3, 0, 0},
								 {2, 1, 1, 3, 6, 4, 4, 4, 13, 1, 1, 3, 2, 1, 1, 3, 0, 0},
								 {2, 1, 1, 12, 13, 1, 1, 1, 1, 1, 1, 3, 2, 1, 1, 3, 0, 0},
								 {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 12, 13, 1, 1, 3, 0, 0},
								 {8, 5, 5, 11, 1, 1, 10, 5, 11, 1, 1, 1, 1, 1, 1, 3, 0, 0},
								 //10
								 {0, 0, 0, 8, 5, 5, 9, 0, 8, 5, 5, 5, 5, 5, 5, 9, 0, 0},
								 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};

	// for decorations/interactables that go on top of the map			
	static int [][] room3TopLayer = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 //5
									 {0, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
									 //10
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};
	
	// ROOM4 MAP, entered through room1 left door 
	static int [][] room4Map = { {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								 {0, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 7, 0, 6, 4, 4, 7, 0},
								 {0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 2, 1, 1, 3, 0},
								 {6, 13, 1, 1, 10, 5, 11, 1, 1, 10, 5, 9, 6, 13, 1, 1, 3, 0},
								 {2, 1, 1, 1, 3, 0, 2, 1, 1, 3, 6, 4, 4, 1, 1, 1, 3, 0},
								 //5
								 {2, 1, 1, 1, 3 ,0, 8, 5, 5, 9, 2, 1, 1, 1, 1, 1, 3, 0},
								 {2, 1, 1, 1, 12, 4, 4, 4, 4, 4, 13, 1, 1, 10, 5, 5, 9, 0},
								 {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 7, 0, 0, 0},
								 {2, 1, 1, 10, 11, 1, 1, 10, 5, 11, 1, 1, 1, 1, 4, 4, 7, 0},
								 {2, 1, 1, 3, 2, 1, 1, 3, 0, 8, 5, 11, 1, 1, 1, 1, 3, 0},
								 //10
								 {8, 5, 5, 9, 8, 5, 5, 9, 0, 0, 0, 8, 5, 5, 5, 5, 9, 0},
								 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};

	// for decorations/interactables that go on top of the map			
	static int [][] room4TopLayer = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 17, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 //5
									 {0, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0},
									 //10
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};

	// ROOM5 MAP, entered through room1 bottom door
	static int [][] room5Map = { {6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 7},
								 {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
								 {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
								 {2, 1, 1, 10, 5, 5, 11, 1, 1, 10, 11, 1, 1, 10, 11, 1, 1, 3},
								 {2, 1, 1, 3, 0, 0, 2,  1,  1, 3, 2, 1, 1, 3, 2, 1, 1, 3},
								 //5
								 {2, 1, 1, 3, 0, 0, 2, 1, 1, 3, 2, 1, 1, 3, 2, 1, 1, 3},
								 {2, 1, 1, 3, 0, 0, 2, 1, 1, 3, 2, 1, 1, 3, 2, 1, 1, 3},
								 {2, 1, 1, 3, 0, 0, 2, 1, 1, 3, 2, 1, 1, 3, 2, 1, 1, 3},
								 {2, 1, 1, 3, 0, 0, 8, 5, 5, 9, 8, 5, 5, 9, 2, 1, 1, 3},
								 {2, 1, 1, 12, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 13, 1, 1, 3},
								 //10
								 {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
								 {8, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9},
	};

	// for decorations/interactables that go on top of the map			
	static int [][] room5TopLayer = {{0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0},
									 {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 //5
									 {0, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0},
									 //10
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};

	// SHOP MAP, where the player can buy power-ups and in-game items 
	static int[][] shopMap = { {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							   {0, 0, 0, 0, 6, 4, 4, 4, 4, 4, 4, 4, 4, 7, 0, 0, 0, 0},
							   {0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0},
							   {0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0},
							   {0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0},
							   //5
							   {0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0},
							   {0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0},
							   {0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0},
							   {0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0},
							   {0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0},
							   //10
							   {0, 0, 0, 0, 8, 5, 5, 5, 5, 5, 5, 5, 5, 9, 0, 0, 0, 0},
							   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};
	
	// for decorations/interactables that go on top of the map			
	static int[][] shopTopLayer = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								   {0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0},
								   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								   {0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0},
								   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								   //5
								   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								   {0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0},
								   //10
								   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};
	
	// Create all map objects
	static Map introRoom = new Map(introMap, introTopLayer);
	static Map room1 = new Map(room1Map, room1TopLayer);
	static Map room2 = new Map(room2Map, room2TopLayer);
	static Map room3 = new Map(room3Map, room3TopLayer);
	static Map room4 = new Map(room4Map, room4TopLayer);
	static Map room5 = new Map(room5Map, room5TopLayer);
	static Map shopRoom = new Map(shopMap, shopTopLayer);
}
