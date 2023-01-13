package main;

public class Map {
	final int[][] mapGround;
	final int[][] mapLayer2;
	
	Map(int[][] mapLayout, int[][] mapLayer2) {
		this.mapGround = mapLayout;
		this.mapLayer2 = mapLayer2;
	}
	
	// Update the map at specified coordinates
	void updateMapGround(int x, int y, int value) {
		mapGround[y][x] = value;
	}
	
	// Update the map at specified coordinates
	void updateMapLayer2(int x, int y, int value) {
		mapLayer2[y][x] = value;
	}
}
