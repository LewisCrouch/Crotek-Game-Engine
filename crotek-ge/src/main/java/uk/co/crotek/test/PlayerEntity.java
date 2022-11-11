package uk.co.crotek.test;

import uk.co.crotek.ge.entity.LivingEntity;

public class PlayerEntity extends LivingEntity
{
	private float moveSpeed;
	private boolean sprinting;
	private float sprintModifier;

	public PlayerEntity(float x, float y, float width, float height, int imageId)
	{
		super(x, y, width, height, 20F, imageId);
		this.moveSpeed = 1.5F;
		this.sprinting = false;
		this.sprintModifier = 2.0F;
	}

	public void moveUp(float deltaTime)
	{
		this.move(0F, -this.getCurrentMoveSpeed(), deltaTime);
	}

	public void moveDown(float deltaTime)
	{
		this.move(0F, +this.getCurrentMoveSpeed(), deltaTime);
	}

	public void moveLeft(float deltaTime)
	{
		this.move(-this.getCurrentMoveSpeed(), 0F, deltaTime);
	}

	public void moveRight(float deltaTime)
	{
		this.move(this.getCurrentMoveSpeed(), 0F, deltaTime);
	}

	public float getMoveSpeed()
	{
		return this.moveSpeed;
	}

	public void setMoveSpeed(float moveSpeed)
	{
		this.moveSpeed = moveSpeed;
	}

	public float getCurrentMoveSpeed()
	{
		if(this.sprinting) return this.moveSpeed * this.sprintModifier;
		return this.moveSpeed;
	}

	public boolean isSprinting()
	{
		return this.sprinting;
	}

	public void setSprinting(boolean sprinting)
	{
		this.sprinting = sprinting;
	}

	public float getSprintModifier()
	{
		return this.sprintModifier;
	}

	public void setSprintModifier(float sprintModifier)
	{
		this.sprintModifier = sprintModifier;
	}
}
