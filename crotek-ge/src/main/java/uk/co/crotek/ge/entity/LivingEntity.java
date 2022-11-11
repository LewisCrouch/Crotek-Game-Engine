package uk.co.crotek.ge.entity;

import uk.co.crotek.ge.display.renderer.RenderQueue;

public abstract class LivingEntity extends Entity
{
	private float maxHealth;
	private float health;

	private Entity damageSource;
	private Entity healSource;

	private boolean renderedWhenDead;

	public LivingEntity(float x, float y, float width, float height, float maxHealth, int imageId)
	{
		super(x, y, width, height, imageId);
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.renderedWhenDead = false;
	}

	@Override
	public void render(float deltaTime, RenderQueue renderQueue)
	{
		if(this.isDead() && !this.renderedWhenDead) return;
		super.render(deltaTime, renderQueue);
	}

	public void damage(float amount, Entity damageSource)
	{
		this.setHealth(this.health - amount);
		this.damageSource = damageSource;
	}

	public void heal(float amount, Entity healSource)
	{
		this.setHealth(this.health + amount);
		this.healSource = healSource;
	}

	public void heal(Entity healSource)
	{
		this.setHealth(this.maxHealth);
		this.healSource = healSource;
	}

	public boolean isDead()
	{
		return this.health <= 0F;
	}

	public float getMaxHealth()
	{
		return this.maxHealth;
	}

	public void setMaxHealth(float maxHealth)
	{
		this.maxHealth = maxHealth;
	}

	public float getHealth()
	{
		return this.health;
	}

	public void setHealth(float health)
	{
		this.health = health;
		if(this.health < 0F) this.health = 0F;
		if(this.health > this.maxHealth) this.health = this.maxHealth;
	}

	public Entity getDamageSource()
	{
		return this.damageSource;
	}

	public Entity getHealSource()
	{
		return this.healSource;
	}

	public boolean isRenderedWhenDead()
	{
		return this.renderedWhenDead;
	}

	public void setRenderedWhenDead(boolean renderedWhenDead)
	{
		this.renderedWhenDead = renderedWhenDead;
	}
}
