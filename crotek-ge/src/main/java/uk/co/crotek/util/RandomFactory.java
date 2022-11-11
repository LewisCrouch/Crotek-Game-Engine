package uk.co.crotek.util;

import java.util.Random;

/**
 * Generates an instance of java.util.Random with seed Sytem.nanoTime().
 */
public class RandomFactory
{
	private static Random instance;

	public static Random getInstance()
	{
		if(RandomFactory.instance == null) RandomFactory.instance = new Random(System.nanoTime());
		return RandomFactory.instance;
	}
}
