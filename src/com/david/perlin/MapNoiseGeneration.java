package com.david.perlin;

import java.util.Random;
import java.util.Scanner;

public class MapNoiseGeneration {
	
	private static final char[] STEP =  {' ', '░', '▒', '▓', '█'};
	
	private static final int WIDTH = 16;
	private static final int LENGTH = 16;
	private static final int FREQUENCY = 1;
	
	private static final int SEGMENT_LENGTH = WIDTH/FREQUENCY;
	private static final int SEGMENT_NUMBER = WIDTH/SEGMENT_LENGTH;
	
	private static final int RENDER_VISION = 1; // Cercle autour du point 
	private static final long SEED = 123456789;
	
	
	public static void main(String[] args) {

		float[][] randomPoint = new float[SEGMENT_NUMBER+1][SEGMENT_NUMBER+1];		
		float[][] mapNoise = new float[WIDTH][LENGTH];
						
		float[][] chunckLoaded = new float[WIDTH][LENGTH];
		float[][] chunckVoid = new float[WIDTH][LENGTH];
		
		float[][] finalMap = new float[WIDTH*(RENDER_VISION*2+1)][LENGTH*(RENDER_VISION*2+1)];
		
		generateVoid(chunckVoid);
		
		Random random = new Random();
		
		int spawnX = random.nextInt(10) - 5;
		int spawnY = random.nextInt(10) - 5;
		
		 
		// CHUNCK LOADER
		
		
		for(int x = spawnX - RENDER_VISION; x <= spawnX + RENDER_VISION; x++) {
			for(int y = spawnY + RENDER_VISION; y >= spawnY - RENDER_VISION; y--) {
				
				if(isInCircle(spawnX, spawnY, x, y, RENDER_VISION)) {
					
					for(int finalX = 0 + (Math.abs(x*SEGMENT_LENGTH)); finalX < SEGMENT_LENGTH + (Math.abs(x*SEGMENT_LENGTH)); finalX++) {
						for(int finalY = 0 + (Math.abs(y*SEGMENT_LENGTH)); finalY < SEGMENT_LENGTH + (Math.abs(y*SEGMENT_LENGTH)); finalY++) {
							/*
							float tx = x / ((SEGMENT_LENGTH-1)*1.0f);
							float ty = y / ((SEGMENT_LENGTH-1)*1.0f);
							
							float a = getControlPoint(SEED, x, y+1);
							float b = getControlPoint(SEED, x+1, y+1);
							float c = getControlPoint(SEED, x, y);
							float d = getControlPoint(SEED, x+1, y);
							
							float top = lerp(a, b, tx);
							float bot = lerp(c, d, tx);
							
							float value = lerp(top, bot, ty);
							
							finalMap[finalX][finalY] = value;
									//mapNoise[x + (snX*SEGMENT_LENGTH)][y + (snY*SEGMENT_LENGTH)] = value;
							*/
							finalMap[finalX][finalY] = 1;
						}
					}
					
					//System.out.print("O");		
					//generateMap(chunckLoaded, x, y);
					//printHeightMap(WIDTH, LENGTH, chunckLoaded);
					
					
					
				} else {
					// print chunck vide
					for(int finalX = 0 + (Math.abs(x*SEGMENT_LENGTH)); finalX < SEGMENT_LENGTH + (Math.abs(x*SEGMENT_LENGTH)); finalX++) {
						for(int finalY = 0 + (Math.abs(y*SEGMENT_LENGTH)); finalY < SEGMENT_LENGTH + (Math.abs(y*SEGMENT_LENGTH)); finalY++) {
							finalMap[finalX][finalY] = 0;
						}
					}
					
					
					//printHeightMap(WIDTH, LENGTH, chunckVoid);
					//System.out.print("X");	
					/*
					for(float[] m: mapNoise) {
						for(float value : m) {
							value = 0;
						}
					}
					printHeightMap(WIDTH, LENGTH, mapNoise);
					*/
					//generateVoid(mapNoise, x, y);
					
					
					
				}

			}
			System.out.println();
		}
		System.out.println();
		
		printHeightMap(WIDTH*(RENDER_VISION*2+1), LENGTH*(RENDER_VISION*2+1), finalMap);
		
		
		
		
		
		for(int snX = spawnX; snX < SEGMENT_NUMBER; snX++) {			
			for(int snY = spawnY; snY < SEGMENT_NUMBER; snY++) {	
				
				for(int x = 0; x < SEGMENT_LENGTH; x++) {
					for(int y = 0; y < SEGMENT_LENGTH; y++) {
						
						float tx = x / ((SEGMENT_LENGTH-1)*1.0f);
						float ty = y / ((SEGMENT_LENGTH-1)*1.0f);
						
						float a = getControlPoint(SEED, spawnX, spawnY+1);
						float b = getControlPoint(SEED, spawnX+1, spawnY+1);
						float c = getControlPoint(SEED, spawnX, spawnY);
						float d = getControlPoint(SEED, spawnX+1, spawnY);
						
						float top = lerp(a, b, tx);
						float bot = lerp(c, d, tx);
						
						float value = lerp(top, bot, ty);
						
						mapNoise[x + (snX*SEGMENT_LENGTH)][y + (snY*SEGMENT_LENGTH)] = value;
					}
				}
			}
		}
		
		printHeightMap(WIDTH, LENGTH, mapNoise);
		
		
		
		
		
		//printHeightMap(WIDTH, LENGTH, mapNoise);
		
		
		
		
		
		
		
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
		/*
		
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
		
		*/
		
		
		
		
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
				if (map[i][j] == 0) {
					System.out.print(".");
				} else if(map[i][j] <= 0.20f) {					
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
	
	public static float getControlPoint(long worldSeed, int pointX, int pointY) {
		long num1 = 341873128712L;
		long num2 = 132897987541L;
		long localSeed = worldSeed + pointX * num1 + pointY * num2;
		
		Random random = new Random(localSeed);
		return random.nextFloat();
	}
	
	public static boolean isInCircle(double centerX, double centerY, int x, int y, double rayon) {
		double distance = Math.sqrt(Math.pow(x - centerX,2) + Math.pow(y - centerY,2));
		return distance <= rayon;
	}

	public static void generateMap(float[][] mapNoise, int spawnX, int spawnY) {
		//for(int snX = spawnX; snX < SEGMENT_NUMBER; snX++) {			
		//	for(int snY = spawnY; snY < SEGMENT_NUMBER; snY++) {	
				
				for(int x = 0; x < SEGMENT_LENGTH; x++) {
					for(int y = 0; y < SEGMENT_LENGTH; y++) {
						
						float tx = x / ((SEGMENT_LENGTH-1)*1.0f);
						float ty = y / ((SEGMENT_LENGTH-1)*1.0f);
						
						float a = getControlPoint(SEED, spawnX, spawnY+1);
						float b = getControlPoint(SEED, spawnX+1, spawnY+1);
						float c = getControlPoint(SEED, spawnX, spawnY);
						float d = getControlPoint(SEED, spawnX+1, spawnY);
						
						float top = lerp(a, b, tx);
						float bot = lerp(c, d, tx);
						
						float value = lerp(top, bot, ty);
						
						mapNoise[x][y] = value;
						//mapNoise[x + (snX*SEGMENT_LENGTH)][y + (snY*SEGMENT_LENGTH)] = value;
					}
				}
		//	}
		//}
	}
	
	public static void generateVoid(float[][] voidChunck) {
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < LENGTH; y++) {
				voidChunck[x][y] = 0;
			}
		}
	}

}
