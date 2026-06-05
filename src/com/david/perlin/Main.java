package com.david.perlin;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int gridSizeX = 16;
		int gridSizeY = 16;
		int[][] map = new int[gridSizeX][gridSizeY];
		
		for(int i=0; i<gridSizeX;i++) {
			for(int j=0; j<gridSizeY;j++) {
				if((i > 6 && i < 14) && (j > 6 && j < 14)) {
					if(i == 9) {
						map[i][j] = 4;
					} else  {						
						map[i][j] = 0;
					}
				} else {
					if(i == 9) {
						map[i][j] = 3;
					} else  {						
						map[i][j] = 1;
					}
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
