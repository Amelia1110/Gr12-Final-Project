package main;

public class Map {
	final private int[][] map;
	
	Map(int[][] mapLayout) {
		map = mapLayout;
	}
	
	// Update the map at specified coordinates
	void updateMap(int x, int y, int value) {
		map[y][x] = value;
	}
}
