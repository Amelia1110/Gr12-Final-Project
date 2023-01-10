package main;

public class Map {
	final int[][] mapLayout;
	
	Map(int[][] mapLayout) {
		this.mapLayout = mapLayout;
	}
	
	// Update the map at specified coordinates
	void updateMap(int x, int y, int value) {
		mapLayout[y][x] = value;
	}
}
