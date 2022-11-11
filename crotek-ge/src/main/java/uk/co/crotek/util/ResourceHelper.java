package uk.co.crotek.util;

import java.io.File;

public class ResourceHelper
{
	public static final String RESOURCE_DIR = "resources/";

	public static File getResourceFile(String filename)
	{
		return new File(ResourceHelper.getResourcePath(filename));
	}

	public static String getResourcePath(String filename)
	{
		File dir = new File(ResourceHelper.RESOURCE_DIR);
		if(!dir.exists()) dir.mkdir();
		return ResourceHelper.RESOURCE_DIR + filename;
	}
}
