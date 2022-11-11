package uk.co.crotek.util;

public class SecondsConverter
{
	/**
	 * Formats seconds into hours, minutes and seconds.
	 * E.g. 1000 seconds would return 00:16:40
	 * @param seconds
	 * @return
	 */
	public static String format(int seconds)
	{
		int h = seconds / 3600; // hours
		int m = (seconds % 3600) / 60; // minutes
		int s = seconds % 60; // seconds

		return String.format("%02d:%02d:%02d", h, m, s);
	}

	public static int secondsToHours(int seconds)
	{
		return seconds / 3600;
	}

	public static int secondsToMinutes(int seconds)
	{
		return (seconds % 3600) / 60;
	}
}
