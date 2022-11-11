package uk.co.crotek.ge.entity;

import uk.co.crotek.ge.Game;
import uk.co.crotek.ge.display.renderer.ImageRenderer;
import uk.co.crotek.ge.display.renderer.RenderQueue;
import uk.co.crotek.util.SID;

public abstract class Entity
{
	private String sid;
	private float x;
	private float y;
	private float width;
	private float height;
	private int imageId;

	public Entity(float x, float y, float width, float height, int imageId)
	{
		this.sid = SID.generateId();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.imageId = imageId;
	}

	public void update(float deltaTime) { }

	public void render(float deltaTime, RenderQueue renderQueue)
	{
		ImageRenderer ir = new ImageRenderer
		(
			this.x * Game.getOneMetreInPixels(),
			this.y * Game.getOneMetreInPixels(),
			Game.getScale(),
			this.imageId
		);
		renderQueue.add(ir);
	}

	public void move(float velocityX, float velocityY, float deltaTime)
	{
		this.x += velocityX * deltaTime;
		this.y += velocityY * deltaTime;
	}

	public boolean isColliding(float x, float y, float width, float height)
	{
		boolean ix = this.x * Game.getOneMetreInPixels() + this.width * Game.getOneMetreInPixels() > x * Game.getOneMetreInPixels() && this.x * Game.getOneMetreInPixels() < x * Game.getOneMetreInPixels() + width * Game.getOneMetreInPixels();
		boolean iy = this.y * Game.getOneMetreInPixels() + this.height * Game.getOneMetreInPixels() > y * Game.getOneMetreInPixels() && this.y * Game.getOneMetreInPixels() < y * Game.getOneMetreInPixels() + height * Game.getOneMetreInPixels();
		return ix && iy;
	}

	public boolean isColliding(Entity entity)
	{
		return this.isColliding(entity.x, entity.y, entity.width, entity.height);
	}

	public String getSid()
	{
		return this.sid;
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

	public int getImageId()
	{
		return this.imageId;
	}

	public boolean equals(Entity entity)
	{
		return this.sid == entity.sid;
	}

	@Override
	public String toString()
	{
		return this.sid;
	}
}
