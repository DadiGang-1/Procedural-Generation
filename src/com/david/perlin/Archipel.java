package com.david.perlin;

public class Archipel {

	public static void main(String[] args) {

		int gridSizeX = 16;
		int gridSizeY = 16;
		int[][] map = new int[gridSizeX][gridSizeY];
		
		double centerX = 8;
		double centerY = 7;
		double rayon = 7;
		
		double lackcenterX = 6;
		double lackcenterY = 7;
		double lackRayon = 2;
		
		for(int x=0; x<gridSizeX;x++) {
			for(int y=0; y<gridSizeY;y++) {
				
				if(isInCircle(centerX, centerY, x, y, rayon)) {
					if(isInCircle(lackcenterX, lackcenterY, x, y, lackRayon)) {
						map[x][y] = 0;
					} else {
						map[x][y] = 1;
					}
					
				} else {
					map[x][y] = 0;
				}
			}
		}
		
		printMap(gridSizeX, gridSizeY, map);
	}
	
	public static boolean isInCircle(double centerX, double centerY, int x, int y, double rayon) {
		double distance = Math.sqrt(Math.pow(x - centerX,2) + Math.pow(y - centerY,2));
		return distance <= rayon;
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
