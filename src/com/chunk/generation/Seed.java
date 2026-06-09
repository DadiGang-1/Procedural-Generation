package com.chunk.generation;

import java.util.Random;

public class Seed {
	
	private static final long SEED = 123456789;
	private static final long SEEDX = 341873128712L;
	private static final long SEEDY = 132897987541L;
	
	private static long localSeed;
	private static float pointValue;
	
	public Seed() {
	}
	
	public static long getLocalSeed() {
		return localSeed;
	}

	public static void setLocalSeed(long localSeed) {
		Seed.localSeed = localSeed;
	}

	public static float getPointValue() {
		return pointValue;
	}

	public static void setPointValue(float pointValue) {
		Seed.pointValue = pointValue;
	}

	public float getControlPoint(int x, int y) {
		//long lS = SEED + x * SEEDX + y * SEEDY;
		setLocalSeed(SEED + x * SEEDX + y * SEEDY);
		Random random = new Random(getLocalSeed());
		return random.nextFloat();
	}
}
