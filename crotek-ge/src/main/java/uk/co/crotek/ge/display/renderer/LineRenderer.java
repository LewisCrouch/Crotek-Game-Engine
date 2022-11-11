package uk.co.crotek.ge.display.renderer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;

public class LineRenderer extends Renderer
{
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	private float width;
	private Color color;

	public LineRenderer(float x1, float y1, float x2, float y2, float width, Color color)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.width = width;
		this.color = color;
	}

	@Override
	public void render(Graphics2D g2d)
	{
		g2d.setColor(this.color);
		Stroke prevStroke = g2d.getStroke();
		g2d.setStroke(new BasicStroke(this.width));
		g2d.fill(new Line2D.Float(this.x1, this.y1, this.x2, this.y2));
		g2d.setStroke(prevStroke);
	}

	public float getX1()
	{
		return this.x1;
	}

	public void setX1(float x1)
	{
		this.x1 = x1;
	}

	public float getY1()
	{
		return this.y1;
	}

	public void setY1(float y1)
	{
		this.y1 = y1;
	}

	public float getX2()
	{
		return this.x2;
	}

	public void setX2(float x2)
	{
		this.x2 = x2;
	}

	public float getY2()
	{
		return this.y2;
	}

	public void setY2(float y2)
	{
		this.y2 = y2;
	}

	public float getWidth()
	{
		return this.width;
	}

	public void setWidth(float width)
	{
		this.width = width;
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
