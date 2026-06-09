package com.chunk.generation;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Seed seed = new Seed();
		Chunk chunk = new Chunk();
		
		
		chunk.printChunk(chunk.generateChunk(0,1));
		chunk.printChunk(chunk.generateChunk(0,0));
		chunk.printChunk(chunk.generateChunk(0,-1));
		
		chunk.printChunk(chunk.generateChunk(1,1));
		chunk.printChunk(chunk.generateChunk(1,0));
		chunk.printChunk(chunk.generateChunk(1,-1));
		
	}

}
