package com.chunk.generation;

public class Chunk {
	private static final int WIDTH = 16;
	private static final int LENGTH = 16;
	private static final int FREQUENCY = 1;
	
	private static final int SEGMENT_LENGTH = WIDTH/FREQUENCY;
	private static final int SEGMENT_NUMBER = WIDTH/SEGMENT_LENGTH;
	
	private static final char[] STEP =  {' ', '░', '▒', '▓', '█'};
	
	float[][] chunk = new float[WIDTH][LENGTH];
	
	public Chunk() {
		
	}
	
	public float[][] getChunk() {
		return chunk;
	}

	public void setChunk(float[][] chunk) {
		this.chunk = chunk;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getLength() {
		return LENGTH;
	}

	public static int getFrequency() {
		return FREQUENCY;
	}

	public int getSegmentLength() {
		return SEGMENT_LENGTH;
	}

	public static int getSegmentNumber() {
		return SEGMENT_NUMBER;
	}

	public float[][] generateChunk(int chunkY, int chunkX) {
		Seed seed = new Seed();		
		
		for(int snY = 0; snY < SEGMENT_NUMBER; snY++) {	
			for(int snX = 0; snX < SEGMENT_NUMBER; snX++) {			
				
				for(int y = SEGMENT_LENGTH-1; y >= 0; y--) {
					for(int x = 0; x < SEGMENT_LENGTH; x++) {
		
						float tx = x / ((SEGMENT_LENGTH-1)*1.0f);
						float ty = y / ((SEGMENT_LENGTH-1)*1.0f);
						
						float a = seed.getControlPoint(chunkX, chunkY-1);
						float b = seed.getControlPoint(chunkX-1, chunkY-1);
						float c = seed.getControlPoint(chunkX, chunkY);
						float d = seed.getControlPoint(chunkX-1, chunkY);
						
						float top = lerp(a, b, tx);
						float bot = lerp(c, d, tx);
						
						float value = lerp(top, bot, ty);
						
						chunk[x + (snX*SEGMENT_LENGTH)][y + (snY*SEGMENT_LENGTH)] = value;
			
					}
				}
				
			}
		}
			
		return chunk;
	}
	
	public static float lerp(float a, float b, float t) {
		return a + (b - a) * t;
	}
	
	public void printChunk(float[][] chunk) {
		for(int i=0; i<WIDTH;i++) {
			for(int j=0; j<LENGTH;j++) {
				if (chunk[i][j] == 0) {
					System.out.print(".");
				} else if(chunk[i][j] <= 0.20f) {					
					System.out.print(STEP[0]);
				} else if(chunk[i][j] <= 0.40f) {
					System.out.print(STEP[1]);
				} else if(chunk[i][j] <= 0.60f) {
					System.out.print(STEP[2]);
				} else if(chunk[i][j] <= 0.80f) {
					System.out.print(STEP[3]);
				} else {
					System.out.print(STEP[4]);
				}
			}
			System.out.println();
		}
	}
}
