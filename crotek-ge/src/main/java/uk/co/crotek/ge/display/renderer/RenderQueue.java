package uk.co.crotek.ge.display.renderer;

import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RenderQueue
{
	private List<Renderer> queue = Collections.synchronizedList(new ArrayList<Renderer>());
	private List<Renderer> backupQueue = Collections.synchronizedList(new ArrayList<Renderer>());

	private boolean locked;
	private Graphics2D currentG2d;

	public void add(Renderer renderable)
	{
		if(this.locked) this.backupQueue.add(renderable);
		else this.queue.add(renderable);
	}

	public void remove(Renderer r)
	{
		this.queue.remove(r);
	}

	public Iterator<Renderer> iterator()
	{
		return this.queue.iterator();
	}

	public void lock()
	{
		this.locked = true;
	}

	public void release()
	{
		this.locked = false;
		this.queue.addAll(this.backupQueue);
		this.backupQueue.clear();
	}

	public void setCurrentG2d(Graphics2D currentG2d)
	{
		this.currentG2d = currentG2d;
	}

	public Graphics2D getCurrentG2d()
	{
		return this.currentG2d;
	}
}
