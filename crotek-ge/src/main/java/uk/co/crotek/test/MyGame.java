package uk.co.crotek.test;

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import uk.co.crotek.ge.Game;
import uk.co.crotek.ge.display.Keyboard;
import uk.co.crotek.ge.display.renderer.RectangleRenderer;
import uk.co.crotek.ge.display.renderer.RenderQueue;
import uk.co.crotek.ge.display.renderer.StringRenderer;
import uk.co.crotek.util.ImageRegister;
import uk.co.crotek.util.Logger;

public class MyGame extends Game
{
	public static final String NAME = "MyGame";
	public static final String VERSION = "1.0";

	private boolean debug;

	private PlayerEntity player;
	private PlayerEntity enemy;

	private boolean collidingWithEnemy;

	public MyGame()
	{
		super(MyGame.NAME, 800, 400, 60);
	}

	@Override
	public void initResources()
	{
		this.setOneMetreInPixels(16F);
		this.setScale(4);

		this.debug = true;

		StringRenderer.DEFAULT_FONT = "verdana";

		int playerImageId = 0;
		try
		{
			playerImageId = ImageRegister.registerImage("player.png");
		}
		catch(IOException ex)
		{
			Logger.err("Failed to load player image :(");
		}

		int enemyImageId = 0;
		try
		{
			enemyImageId = ImageRegister.registerImage("enemy.png");
		}
		catch(IOException ex)
		{
			Logger.err("Failed to load enemy image :(");
		}

		this.player = new PlayerEntity(1F, 1F, 0.8125F, 1F, playerImageId);
		this.enemy = new PlayerEntity(3F, 1F, 1F, 1F, enemyImageId);

		this.collidingWithEnemy = false;
	}

	@Override
	public void update(float deltaTime)
	{
		if(Keyboard.checkKey(KeyEvent.VK_F3)) this.debug = !this.debug;

		this.player.setSprinting(Keyboard.isKeyDown(KeyEvent.VK_SHIFT));

		this.collidingWithEnemy = this.player.isColliding(this.enemy);

		if(Keyboard.isKeyDown(KeyEvent.VK_W)) this.player.moveUp(deltaTime);
		if(Keyboard.isKeyDown(KeyEvent.VK_S)) this.player.moveDown(deltaTime);
		if(Keyboard.isKeyDown(KeyEvent.VK_A)) this.player.moveLeft(deltaTime);
		if(Keyboard.isKeyDown(KeyEvent.VK_D)) this.player.moveRight(deltaTime);

		if(this.debug)
		{
			if(Keyboard.isKeyPressed(KeyEvent.VK_Z)) this.setScale(Game.getScale() - 1);
			if(Keyboard.isKeyPressed(KeyEvent.VK_X)) this.setScale(Game.getScale() + 1);
		}

		this.player.update(deltaTime);
		this.enemy.update(deltaTime);
	}

	@Override
	public void render(float deltaTime, RenderQueue renderQueue)
	{
		RectangleRenderer rr = new RectangleRenderer(0, 0, this.getDisplay().getInnerWidth(), this.getDisplay().getInnerHeight(), Color.GRAY);
		renderQueue.add(rr);

		this.enemy.render(deltaTime, renderQueue);
		this.player.render(deltaTime, renderQueue);

		StringRenderer sr = new StringRenderer(4F, 4F, String.format("%s %s", MyGame.NAME, MyGame.VERSION));
		sr.setY(this.getDisplay().getInnerHeight() - sr.getStringHeight() - 4);
		sr.setStyle(Font.BOLD);
		sr.setShadow(true);
		renderQueue.add(sr);

		if(this.debug)
		{
			sr = new StringRenderer(4F, 4F, this.getDebugStr() + " / DT: " + deltaTime);
			sr.setStyle(Font.BOLD);
			sr.setShadow(true);
			renderQueue.add(sr);
		}
	}

	public String getDebugStr()
	{
		String debugStr = "";
		debugStr += String.format("UPS: %d", this.getCurrentUps());
		debugStr += String.format(" / Time: %s", this.getTimeElapsed());
		debugStr += String.format(" / Scale: %d", Game.getScale());
		debugStr += String.format(" / 1MIP: %.2f", Game.getOneMetreInPixels());
		debugStr += String.format(" / X: %.2f", this.player.getX());
		debugStr += String.format(" / Y: %.2f", this.player.getY());
		debugStr += String.format(" / Colliding: %s", this.collidingWithEnemy);
		return debugStr;
	}
}
