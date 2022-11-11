package uk.co.crotek.ge.display.renderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class StringRenderer extends Renderer
{
	public static int DEFAULT_STYLE = Font.PLAIN;
	public static int DEFAULT_SIZE = 11;
	public static String DEFAULT_FONT = "arial";
	public static Color DEFAULT_COLOR = Color.WHITE;
	public static boolean DEFAULT_SHADOW = false;
	public static float DEFAULT_SHADOW_OFFSET_X = 1F;
	public static float DEFAULT_SHADOW_OFFSET_Y = 1F;
	public static Color DEFAULT_SHADOW_COLOR = new Color(0F, 0F, 0F, 0.5F);

	private float x;
	private float y;
	private int style;
	private int size;
	private String font;
	private String text;
	private Color color;
	private boolean shadow;
	private float shadowOffsetX;
	private float shadowOffsetY;
	private Color shadowColor;

	public StringRenderer(float x, float y, String text)
	{
		this.x = x;
		this.y = y;
		this.style = StringRenderer.DEFAULT_STYLE;
		this.size = StringRenderer.DEFAULT_SIZE;
		this.font = StringRenderer.DEFAULT_FONT;
		this.text = text;
		this.color = StringRenderer.DEFAULT_COLOR;
		this.shadow = StringRenderer.DEFAULT_SHADOW;
		this.shadowOffsetX = StringRenderer.DEFAULT_SHADOW_OFFSET_X;
		this.shadowOffsetY = StringRenderer.DEFAULT_SHADOW_OFFSET_Y;
		this.shadowColor = StringRenderer.DEFAULT_SHADOW_COLOR;
	}

	@Override
	public void render(Graphics2D g2d)
	{
		float displacedY = (this.y + this.size) + this.size / 8;

		Font font = g2d.getFont();
		g2d.setFont(new Font(this.font, this.style, this.size));
		if(this.shadow)
		{
			g2d.setColor(this.shadowColor);
			g2d.drawString(this.text, this.x + this.shadowOffsetX, displacedY + this.shadowOffsetY);
		}
		g2d.setColor(this.color);
		g2d.drawString(this.text, this.x, displacedY);
		g2d.setFont(font);
	}

	public StringRenderer copy()
	{
		StringRenderer rs = new StringRenderer(this.x, this.y, this.text);
		rs.size = this.size;
		rs.font = this.font;
		rs.color = this.color;
		rs.shadow = this.shadow;
		rs.shadowOffsetX = this.shadowOffsetX;
		rs.shadowOffsetY = this.shadowOffsetY;
		rs.shadowColor = this.shadowColor;
		rs.style = this.style;
		return rs;
	}

	public float getStringWidth()
	{
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
		Font font = new Font(this.font, this.style, this.size);
		return (float) (font.getStringBounds(this.text, frc).getWidth());
	}

	public float getStringHeight()
	{
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
		Font font = new Font(this.font, this.style, this.size);
		return (float) (font.getStringBounds(this.text, frc).getHeight());
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

	public int getStyle()
	{
		return this.style;
	}

	public void setStyle(int style)
	{
		this.style = style;
	}

	public int getSize()
	{
		return this.size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public String getFont()
	{
		return this.font;
	}

	public void setFont(String font)
	{
		this.font = font;
	}

	public String getText()
	{
		return this.text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Color getColor()
	{
		return this.color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public boolean hasShadow()
	{
		return this.shadow;
	}

	public void setShadow(boolean shadow)
	{
		this.shadow = shadow;
	}

	public float getShadowX()
	{
		return this.shadowOffsetX;
	}

	public void setShadowX(float shadowX)
	{
		this.shadowOffsetX = shadowX;
	}

	public float getShadowY()
	{
		return this.shadowOffsetY;
	}

	public void setShadowY(float shadowY)
	{
		this.shadowOffsetY = shadowY;
	}

	public Color getShadowColor()
	{
		return this.shadowColor;
	}

	public void setShadowColor(Color shadowColor)
	{
		this.shadowColor = shadowColor;
	}
}
