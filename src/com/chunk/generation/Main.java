package com.chunk.generation;

//import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Seed seed = new Seed();
		Chunk chunk = new Chunk();
		
		/*
		chunk.printChunk(chunk.generateChunk(0,1));
		chunk.printChunk(chunk.generateChunk(0,0));
		chunk.printChunk(chunk.generateChunk(0,-1));
		
		chunk.printChunk(chunk.generateChunk(1,1));
		chunk.printChunk(chunk.generateChunk(1,0));
		chunk.printChunk(chunk.generateChunk(1,-1));
		*/
		LoadedMap loadedMap = new LoadedMap(0, 0, new Chunk());
		
		for(int i = 0; i < 100; i++) {
			
			loadedMap.generateMap();
			loadedMap.printMap(loadedMap.getLoadedMap());
			/*
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("\033[H\033[2J");
			System.out.flush();
			*/
			loadedMap.setCoordX(i);
		}
		/*
		loadedMap.generateMap();
		loadedMap.printMap(loadedMap.getLoadedMap());
		loadedMap.setCoordX(1);
		loadedMap.generateMap();
		loadedMap.printMap(loadedMap.getLoadedMap());
		*/
		
		
		LoadedMap loadedMap2 = new LoadedMap(0, 0, new Chunk());
		loadedMap2.generateMap();
		loadedMap2.printMap(loadedMap2.getLoadedMap());
	}

}
