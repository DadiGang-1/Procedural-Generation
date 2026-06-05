package com.david.perlin;

import java.util.Random;

public class ProceduralCave {
	
	private static final char ORE = 'O';
	private static final char STONE = '.';
	private static final int WIDTH = 32;
	private static final int LENGTH = 32;
	private static final int ORE_LIMIT = 20;
	private static final int EXIT_MIN_DISTANCE = 15;
	
	public static void main(String[] args) {

		char[][] map = new char[WIDTH][LENGTH];
		
		Random random = new Random();
		
		int startX = random.nextInt(WIDTH);
		int startY = random.nextInt(LENGTH);
		double rayon = 4;
		
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < LENGTH; y++) {
				map[x][y] = STONE;
			}
		}
		
		for(int u = 0; u < ORE_LIMIT; u++) {
			placeOre(map, WIDTH, LENGTH);
		}
		
		map[startX][startY] = 'X';
		
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < LENGTH; y++) {
				if(isInCircle(startX, startY, x, y, rayon) && map[x][y] == STONE) {
					map[x][y] = ' ';
				}
			}
		}
		
		placeExit(map, WIDTH, LENGTH, startX, startY, EXIT_MIN_DISTANCE);
		
		
		
		printMap(WIDTH, LENGTH, map);
	}
	
	public static boolean isInCircle(double centerX, double centerY, int x, int y, double rayon) {
		double distance = Math.sqrt(Math.pow(x - centerX,2) + Math.pow(y - centerY,2));
		return distance <= rayon;
	}
	
	public static void placeOre(char[][] map, int gridSizeX, int gridSizeY) {
		Random orePlacement = new Random();
		int x = orePlacement.nextInt(gridSizeX);
		int y = orePlacement.nextInt(gridSizeY);
		map[x][y] = ORE;
	}
	
	public static void placeExit(char[][] map, int gridSizeX, int gridSizeY, int startX, int startY, double exitDsitance) {
		Random exitPlacement = new Random();
		int x = exitPlacement.nextInt(gridSizeX);
		int y = exitPlacement.nextInt(gridSizeY);
		//if(!isInCircle(x, y, startX, startY, exitDsitance)) {
		if(isInCircle(startX, startY, x, y, exitDsitance)) {
			placeExit(map, gridSizeX, gridSizeY, startX, startY, exitDsitance);
		} else {			
			map[x][y] = 'E';
		}
	}
	
	public static void printMap(int gridSizeX, int gridSizeY,char[][] map) {
		for(int i=0; i<gridSizeX;i++) {
			for(int j=0; j<gridSizeY;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

}
