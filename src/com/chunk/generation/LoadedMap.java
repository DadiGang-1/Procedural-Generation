package com.chunk.generation;

public class LoadedMap {
	private static final int RENDER_DISTANCE = 8;
	private static final char[] STEP =  {' ', '░', '▒', '▓', '█'};
	
	private int width;
	private int length;
	private int fullWidth;
	private int fullLength;
	private int coordX;
	private int coordY;
	private float[][] loadedMap;
	
	public float[][] getLoadedMap() {
		return loadedMap;
	}

	public void setLoadedMap(float[][] loadedMap) {
		this.loadedMap = loadedMap;
	}

	public LoadedMap(int coordX, int coordY, Chunk chunk) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.width = chunk.getWidth();
		this.length = chunk.getLength();
		this.fullWidth = width * (RENDER_DISTANCE*2 + 1);
		this.fullLength = length * (RENDER_DISTANCE*2 + 1);
		this.loadedMap = new float[fullWidth][fullLength];
	}
	
	public float[][] generateMap() {	
		int minX = coordX - RENDER_DISTANCE;
		int maxX = coordX + RENDER_DISTANCE;
		int minY = coordY - RENDER_DISTANCE;
		int maxY = coordY + RENDER_DISTANCE;
		for(int x = minX; x <= maxX; x++) {
			for(int y = maxY; y >= minY; y--) {
				if(isChunkOnRenderDistance(x, y)) {
					Chunk chunk = new Chunk();
					chunk.generateChunk(x, y);
					//loadedMap = insertChunkOnMap(chunk, x + (minX)*(-1), (y + maxY*(-1))*(-1));
					loadedMap = insertChunkOnMap(chunk, (y + maxY*(-1))*(-1), x + (minX)*(-1));
				}
			}
		}
		return loadedMap;
	}
	
	public boolean isChunkOnRenderDistance(int x, int y) {
		double distance = Math.sqrt(Math.pow(x - coordX,2) + Math.pow(y - coordY,2));
		return distance <= RENDER_DISTANCE;
	}
	
	public float[][] insertChunkOnMap(Chunk chunk, int chunkX, int chunkY) {
		chunk.getChunk();
		int segmentLenght = chunk.getSegmentLength();
		for(int x = 0 + (chunkX*segmentLenght); x < segmentLenght + (chunkX*segmentLenght); x++) {
			for(int y = 0 + (chunkY*segmentLenght); y < segmentLenght + (chunkY*segmentLenght); y++) {
				loadedMap[x][y] = chunk.getChunk()[x - (chunkX*segmentLenght)][y - (chunkY*segmentLenght)];
			}
		}
		return loadedMap;
	}
	
	public void printMap(float[][] map) {
		for(int i=0; i<fullWidth;i++) {
			for(int j=0; j<fullLength;j++) {
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

}
