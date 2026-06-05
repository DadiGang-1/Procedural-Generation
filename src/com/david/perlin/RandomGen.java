package com.david.perlin;

import java.util.Random;

public class RandomGen {

	public static void main(String[] args) {

		int gridSizeX = 16;
		int gridSizeY = 16;
		int[][] map = new int[gridSizeX][gridSizeY];
		
		for(int i=0; i<gridSizeX;i++) {
			for(int j=0; j<gridSizeY;j++) {
				Random random = new Random();
				if(random.nextFloat() < 0.9f) {					
					map[i][j] = 1;
				} else {
					map[i][j] = 0;
				}
			}
		}
		
		printMap(gridSizeX, gridSizeY, map);
	}
	
	public static void printMap(int gridSizeX, int gridSizeY,int[][] map) {
		for(int i=0; i<gridSizeX;i++) {
			for(int j=0; j<gridSizeY;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

}
