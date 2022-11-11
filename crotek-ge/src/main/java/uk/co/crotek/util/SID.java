package uk.co.crotek.util;

import java.util.ArrayList;
import java.util.List;

/**
 * For generating a 16 chracter ID that is unique to this session.
 *
 */
public class SID
{
	public static char[] ID_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

	private static List<String> ids;

	/**
	 * Returns a randomly generated 16 character id that is unique to this session.
	 * @return
	 */
	public static String generateId()
	{
		String key = "";
		while(key.length() < 16) key += SID.ID_CHARS[RandomFactory.getInstance().nextInt(SID.ID_CHARS.length)];

		for(String k : SID.getIds())
		{
			if(key.equals(k)) return SID.generateId();
		}

		SID.getIds().add(key);
		return key;
	}

	public static List<String> getIds()
	{
		if(SID.ids == null) SID.ids = new ArrayList<String>();
		return SID.ids;
	}
}
