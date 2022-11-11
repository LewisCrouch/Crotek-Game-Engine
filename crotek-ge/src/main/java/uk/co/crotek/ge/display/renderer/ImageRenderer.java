package uk.co.crotek.ge.display.renderer;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import uk.co.crotek.util.ImageRegister;

public class ImageRenderer extends Renderer
{
	private float x;
	private float y;
	private float scale;
	private int imageId;

	public ImageRenderer(float x, float y, int imageId)
	{
		this.x = x;
		this.y = y;
		this.scale = 1;
		this.imageId = imageId;
	}

	public ImageRenderer(float x, float y, float scale, int imageId)
	{
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.imageId = imageId;
	}

	@Override
	public void render(Graphics2D g2d)
	{
		Image image = ImageRegister.getImage(this.imageId);
		AffineTransform prevT = g2d.getTransform();
		AffineTransform t = new AffineTransform();
		t.translate(this.x, this.y);
		g2d.scale(this.scale, this.scale);
		g2d.drawImage(image, t, null);
		g2d.setTransform(prevT);
	}

	public float getX()
	{
		return this.x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return this.y;
	}

	public void setY(float y)
	{
		this. y = y;
	}

	public float getScale()
	{
		return this.scale;
	}

	public void setScale(float scale)
	{
		this.scale = scale;
	}

	public int getImageId()
	{
		return this.imageId;
	}

	public void setImageId(int imageId)
	{
		this.imageId = imageId;
	}
}
