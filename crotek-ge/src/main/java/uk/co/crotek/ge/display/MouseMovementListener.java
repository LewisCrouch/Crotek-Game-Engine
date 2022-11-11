package uk.co.crotek.ge.display;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMovementListener extends MouseMotionAdapter
{
	private int mouseX = -1;
	private int mouseY = -1;

	@Override
	public void mouseDragged(MouseEvent ev)
	{
		this.mouseX = ev.getX();
		this.mouseY = ev.getY();
	}

	@Override
	public void mouseMoved(MouseEvent ev)
	{
		this.mouseX = ev.getX();
		this.mouseY = ev.getY();
	}

	public int getMouseX()
	{
		return this.mouseX;
	}

	public int getMouseY()
	{
		return this.mouseY;
	}
}
