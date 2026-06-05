package com.david.perlin;

import java.util.Random;
import java.util.Scanner;

public class MapNoiseGeneration {
	
	private static final char[] STEP =  {' ', '░', '▒', '▓', '█'};
	
	private static final int WIDTH = 20;
	private static final int LENGTH = 20;
	private static final int FREQUENCY = 2;
	
	private static final int SEGMENT_LENGTH = WIDTH/FREQUENCY;
	private static final int SEGMENT_NUMBER = WIDTH/SEGMENT_LENGTH;
	
	
	
	
	public static void main(String[] args) {

		float[][] randomPoint = new float[SEGMENT_NUMBER+1][SEGMENT_NUMBER+1];		
		float[][] mapNoise = new float[WIDTH][LENGTH];
				
		Random random = new Random();
		
		for(int x = 0; x < SEGMENT_NUMBER+1; x++) {
			for(int y = 0; y < SEGMENT_NUMBER+1; y++) {
				randomPoint[x][y] = random.nextFloat();
			}
		}
		
		
		for(int snX = 0; snX < SEGMENT_NUMBER; snX++) {			
			for(int snY = 0; snY < SEGMENT_NUMBER; snY++) {	
				
				for(int x = 0; x < SEGMENT_LENGTH; x++) {
					for(int y = 0; y < SEGMENT_LENGTH; y++) {
						
						float tx = x / ((SEGMENT_LENGTH-1)*1.0f);
						float ty = y / ((SEGMENT_LENGTH-1)*1.0f);
						
						float a = randomPoint[snX][snY];
						float b = randomPoint[snX+1][snY];
						float c = randomPoint[snX][snY+1];
						float d = randomPoint[snX+1][snY+1];
						
						float top = lerp(a, b, tx);
						float bot = lerp(c, d, tx);
						
						float value = lerp(top, bot, ty);
						
						mapNoise[x + (snX*SEGMENT_LENGTH)][y + (snY*SEGMENT_LENGTH)] = value;
					}
				}
			}
		}
		
		printMap(SEGMENT_NUMBER+1, SEGMENT_NUMBER+1, randomPoint);
		System.out.println("\n");
		printHeightMap(WIDTH, LENGTH, mapNoise);
		
		
		float[] lastRandomPoint = new float[SEGMENT_NUMBER+1];
		for(int i = 0; i < SEGMENT_NUMBER; i++) {
			lastRandomPoint[i] = randomPoint[SEGMENT_NUMBER][i];
		}
		
		int loop = 0;
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			String userInput = scanner.nextLine();
			if(userInput.equals("")) {
				
				float nextRandomPoint[] = new float[SEGMENT_LENGTH];
				for(int i = 0; i < SEGMENT_NUMBER; i++) {
					nextRandomPoint[i] =  random.nextFloat();
				}
				
				float[][] mapGeneration = new float[SEGMENT_LENGTH][SEGMENT_LENGTH];
				
				for(int snX = 0; snX < SEGMENT_LENGTH; snX++) {			
					for(int snY = 0; snY < SEGMENT_LENGTH; snY++) {	
						
						for(int x = 0; x < SEGMENT_LENGTH; x++) {
							for(int y = 0; y < SEGMENT_LENGTH; y++) {
								
								float tx = x / ((SEGMENT_LENGTH-1)*1.0f);
								float ty = y / ((SEGMENT_LENGTH-1)*1.0f);
								
								float a = lastRandomPoint[snX];
								float b = lastRandomPoint[snX+1];
								float c = nextRandomPoint[snX];
								float d = nextRandomPoint[snX+1];
								
								float top = lerp(a, b, tx);
								float bot = lerp(c, d, tx);
								
								float value = lerp(top, bot, ty);
								
								mapGeneration[x + (snX*SEGMENT_LENGTH)][y + (snY*SEGMENT_LENGTH)] = value;
							}
						}
					}
				}
				
				//for(int x = 0; x < SEGMENT_LENGTH; x++) {
				//	float t = x / ((SEGMENT_LENGTH-1)*1.0f);
				//	lineGeneration[x] = lerp(lastRandomPoint, nextRandomPoint, t);
				//}
				
				lastRandomPoint = nextRandomPoint.clone();
				
				printHeightMap(SEGMENT_LENGTH, SEGMENT_LENGTH, mapGeneration);
				loop++;
			} else if(userInput.equals("0")) {
				break;
			}
			
		}
		
		
		
		
		
		
	}
	public static void printMap(int gridSizeX, int gridSizeY, float[][] map) {
		for(int i=0; i<gridSizeX;i++) {
			for(int j=0; j<gridSizeY;j++) {
				System.out.printf("%.02f ", map[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void printHeightMap(int gridSizeX, int gridSizeY, float[][] map) {
		for(int i=0; i<gridSizeX;i++) {
			for(int j=0; j<gridSizeY;j++) {
				if(map[i][j] <= 0.20f) {					
					System.out.print(STEP[0]);
				} else if(map[i][j] <= 0.40f) {
					System.out.print(STEP[1]);
				} else if(map[i][j] <= 0.60f) {
					System.out.print(STEP[2]);
				} else if(map[i][j] <= 0.80f) {
					System.out.print(STEP[3]);
				} else {
					System.out.print(STEP[4]);
				}
			}
			System.out.println();
		}
	}
	
	
	
	public static void printLine(float[] map) {
		for(float m : map) {
			System.out.printf("%.2f ",m);
		}
		System.out.println("\n");
	}
	
	public static void printHeightLine(float[] map) {
		for(float m : map) {
			System.out.printf("%.2f :",m);
			for(int i = 1; i < m*10; i++) {				
				System.out.printf("#");
			}
			System.out.printf("\n");
		}
	}
	
	//public static void printHeightMap(float[] map) {
	//	for(float m : map) {
	//		System.out.print(STEP[Math.round(m*5) >= 4 ? 4 : Math.round(m*5)]);
	//	}
	//}
	
	public static float lerp(float a, float b, float t) {
		return a + (b - a) * t;
	}

}
