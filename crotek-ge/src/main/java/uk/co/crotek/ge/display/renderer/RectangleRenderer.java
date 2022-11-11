package uk.co.crotek.ge.display.renderer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class RectangleRenderer extends Renderer
{
	private float x;
	private float y;
	private float width;
	private float height;
	private Color color;

	public RectangleRenderer(float x, float y, float width, float height, Color color)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	@Override
	public void render(Graphics2D g2d)
	{
		g2d.setColor(this.color);
		g2d.fill(new Rectangle2D.Float(this.x, this.y, this.width, this.height));
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
		this.y = y;
	}

	public float getWidth()
	{
		return this.width;
	}

	public void setWidth(float width)
	{
		this.width = width;
	}

	public float getHeight()
	{
		return this.height;
	}

	public void setHeight(float height)
	{
		this.height = height;
	}

	public Color getColor()
	{
		return this.color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}
}
