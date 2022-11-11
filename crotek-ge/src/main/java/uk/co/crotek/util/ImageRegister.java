package uk.co.crotek.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageRegister
{
	private static final ImageRegister instance = new ImageRegister();

	private static HashMap<Integer, BufferedImage> images;
	private static int currentIndex;

	private ImageRegister()
	{
		ImageRegister.images = new HashMap<Integer, BufferedImage>();
		ImageRegister.currentIndex = 0;
	}

	public static int registerImage(String filename) throws IOException
	{
		BufferedImage img = ImageIO.read(ResourceHelper.getResourceFile(filename));
		ImageRegister.images.put(ImageRegister.currentIndex, img);
		return ImageRegister.currentIndex++;
	}

	public static int registerImage(BufferedImage img)
	{
		ImageRegister.images.put(ImageRegister.currentIndex, img);
		return ImageRegister.currentIndex++;
	}

	public static BufferedImage getImage(int imageId)
	{
		if(ImageRegister.images.containsKey(imageId)) return ImageRegister.images.get(imageId);
		return null;
	}

	public static boolean removeImage(int imageId)
	{
		if(ImageRegister.images.containsKey(imageId))
		{
			ImageRegister.images.remove(imageId);
			return true;
		}
		else return false;
	}

	public static ImageRegister getInstance()
	{
		return ImageRegister.instance;
	}
}