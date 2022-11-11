package uk.co.crotek.ge.display.renderer;

import java.awt.Graphics2D;

public abstract class Renderer
{
	private float rotationAngleInDegrees;
	private float rotationTranslateX;
	private float rotationTranslateY;

	public Renderer()
	{
		this.rotationAngleInDegrees = 0F;
		this.rotationTranslateX = 0F;
		this.rotationTranslateY = 0F;
	}

	public void preRender(Graphics2D g2d)
	{
		g2d.rotate(Math.toRadians(this.rotationAngleInDegrees), this.rotationTranslateX, this.rotationTranslateY);

		this.render(g2d);

		g2d.rotate(-Math.toRadians(this.rotationAngleInDegrees), this.rotationTranslateX, this.rotationTranslateY);
	}

	public abstract void render(Graphics2D g2d);

	public void rotate(float rotationAngleInDegrees, float rotationTranslateX, float rotationTranslateY)
	{
		this.rotationAngleInDegrees = rotationAngleInDegrees;
		this.rotationTranslateX = rotationTranslateX;
		this.rotationTranslateY = rotationTranslateY;
	}
}
