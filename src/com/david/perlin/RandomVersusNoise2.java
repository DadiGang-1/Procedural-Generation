package com.david.perlin;

import java.util.Random;
import java.util.Scanner;

public class RandomVersusNoise2 {
	
	private static final char[] STEP =  {' ', '░', '▒', '▓', '█'};
	
	private static final int WIDTH = 70;
	private static final int FREQUENCY = 10;
	
	private static final int SEGMENT_LENGTH = WIDTH/FREQUENCY;
	private static final int SEGMENT_NUMBER = WIDTH/SEGMENT_LENGTH;
	
	
	
	
	public static void main(String[] args) {

		float[] lineRandom = new float[WIDTH];
		float[] lineNoise = new float[WIDTH];
		float[] randomPoint = new float[SEGMENT_NUMBER+1];
				
		Random random = new Random();
		
		
		for(int x = 0; x < WIDTH; x++) {
			lineRandom[x] = random.nextFloat();
		}
		
		for(int x = 0; x < SEGMENT_NUMBER+1; x++) {
			randomPoint[x] = random.nextFloat();
		}
		
		for(int sn = 0; sn < SEGMENT_NUMBER; sn++) {			
			for(int x = 0; x < SEGMENT_LENGTH; x++) {
				float t = x / ((SEGMENT_LENGTH-1)*1.0f);
				lineNoise[x + (sn*SEGMENT_LENGTH)] = lerp(randomPoint[sn], randomPoint[sn+1], t);
			}
		}
		
		//printHeightMap(lineNoise);
		printHeightLine(lineNoise);
		float lastRandomPoint = randomPoint[SEGMENT_NUMBER];
		int loop = 0;
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String userInput = scanner.nextLine();
			if(userInput.equals("")) {
				float[] lineGeneration = new float[SEGMENT_LENGTH];
				float nextRandomPoint = random.nextFloat();
				for(int x = 0; x < SEGMENT_LENGTH; x++) {
					float t = x / ((SEGMENT_LENGTH-1)*1.0f);
					lineGeneration[x] = lerp(lastRandomPoint, nextRandomPoint, t);
				}
				lastRandomPoint = nextRandomPoint;
				printHeightLine(lineGeneration);
				loop++;
			} else if(userInput.equals("0")) {
				break;
			}
			
		}
		
		//printLine(lineRandom);
		//printLine(randomPoint);
		//printLine(lineNoise);
		//printHeightLine(lineRandom);
		//System.out.printf("\n");
		//printHeightMap(lineNoise);
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
	
	public static void printHeightMap(float[] map) {
		for(float m : map) {
			System.out.print(STEP[Math.round(m*5) >= 4 ? 4 : Math.round(m*5)]);
		}
	}
	
	public static float lerp(float a, float b, float t) {
		return a + (b - a) * t;
	}
}
