package uk.co.crotek.ge;

import uk.co.crotek.ge.display.Display;
import uk.co.crotek.ge.display.renderer.RenderQueue;
import uk.co.crotek.util.SecondsConverter;

public abstract class Game
{
	protected static int scale;
	protected static float oneMetreInPixels;

	private Display display;

	private int targetUps;

	private boolean running;
	private long startTime;
	private int totalUpdates;
	private int currentUps;

	public Game(String title, int innerWidth, int innerHeight, int targetUps)
	{
		Game.scale = 1;
		Game.oneMetreInPixels = 100F;

		this.display = new Display(title, innerWidth, innerHeight);
		this.targetUps = targetUps;

		this.initResources();

		this.startGame();
	}

	private void startGame()
	{
		final long oneSecond = 1000000000L;
		final long udiff = oneSecond / this.targetUps;

		this.startTime = System.nanoTime();

		long prev = this.startTime;
		long curr;

		long time = prev;
		int updatesThisSecond = 0;

		while(this.running = true)
		{
			curr = System.nanoTime();
			long timeBetween = curr - prev;
			if(timeBetween >= udiff)
			{
				float deltaTime = (float) timeBetween / (float) oneSecond;
				//if(++this.totalUpdates % this.targetUps == 0) this.secondsRunning++;
				this.update(deltaTime);
				updatesThisSecond++;
				this.render(deltaTime, this.display.getRenderQueue());
				this.display.repaintContent();
				prev = curr;
			}

			if(System.nanoTime() - oneSecond >= time)
			{
				this.currentUps = updatesThisSecond;
				updatesThisSecond = 0;
				time = curr;
			}
		}
	}

	public abstract void initResources();
	public abstract void update(float deltaTime);
	public abstract void render(float deltaTime, RenderQueue renderQueue);

	public Display getDisplay()
	{
		return this.display;
	}

	public void setDisplay(Display display)
	{
		this.display = display;
	}

	public int getTargetUps()
	{
		return this.targetUps;
	}

	protected void setScale(int scale)
	{
		Game.scale = scale;
		if(Game.scale < 1) Game.scale = 1;
	}

	protected void setOneMetreInPixels(float oneMetreInPixels)
	{
		Game.oneMetreInPixels = oneMetreInPixels;
		if(Game.oneMetreInPixels < 1F) Game.oneMetreInPixels = 1F;
	}

	public boolean isRunning()
	{
		return this.running;
	}

	public long getTotalUpdates()
	{
		return this.totalUpdates;
	}

	public String getTimeElapsed()
	{
		return SecondsConverter.format((int) ((System.nanoTime() - this.startTime) / 1000000000L));
	}

	public int getCurrentUps()
	{
		return this.currentUps;
	}

	public static int getScale()
	{
		return Game.scale;
	}

	public static float getOneMetreInPixels()
	{
		return Game.oneMetreInPixels;
	}
}
