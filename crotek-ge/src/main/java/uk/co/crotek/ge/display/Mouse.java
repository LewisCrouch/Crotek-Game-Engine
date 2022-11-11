package uk.co.crotek.ge.display;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter
{
	private static Mouse instance = new Mouse();

	private MouseMovementListener mouseMovementListener;

	private boolean[] mouseStates;
	private boolean[] checkMouseStates;
	private boolean mouseDown;
	private int lastButton;

	public Mouse()
	{
		this.mouseMovementListener = new MouseMovementListener();

		this.mouseStates = new boolean[1024];
		this.checkMouseStates = new boolean[1024];
		this.mouseDown = false;
		this.lastButton = -1;
	}

	/* Unused overrides */
	@Override public void mouseClicked(MouseEvent ev) { }
	@Override public void mouseEntered(MouseEvent ev) { }
	@Override public void mouseExited(MouseEvent ev) { }

	@Override
	public void mousePressed(MouseEvent ev)
	{
		this.lastButton = ev.getButton();
		if(!this.checkMouseStates[ev.getButton()]) this.mouseStates[ev.getButton()] = true;
		this.mouseDown = true;
	}

	@Override
	public void mouseReleased(MouseEvent ev)
	{
		this.mouseStates[ev.getButton()] = false;
		this.checkMouseStates[ev.getButton()] = false;
		this.mouseDown = false;
	}

	public MouseMovementListener getMouseMovementListener()
	{
		return this.mouseMovementListener;
	}

	public static boolean isButtonDown()
	{
		return Mouse.instance.mouseDown;
	}

	public static boolean isButtonDown(int btn)
	{
		return Mouse.instance.mouseStates[btn];
	}

	public static boolean isButtonPressed(int btn)
	{
		if(Mouse.instance.mouseStates[btn])
		{
			Mouse.instance.mouseStates[btn] = false;
			return true;
		}
		return false;
	}

	public static boolean checkButton(int btn)
	{
		if(Mouse.instance.mouseStates[btn])
		{
			Mouse.instance.mouseStates[btn] = false;
			return Mouse.instance.checkMouseStates[btn] = true;
		}
		return false;
	}

	public static int getLastButton()
	{
		return Mouse.instance.lastButton;
	}

	public static boolean isLMBDown()
	{
		return Mouse.isButtonDown(MouseEvent.BUTTON1);
	}

	public static boolean isRMBDown()
	{
		return Mouse.isButtonDown(MouseEvent.BUTTON2);
	}

	public static boolean isMMBDown()
	{
		return Mouse.isButtonDown(MouseEvent.BUTTON3);
	}

	public static int getMouseX()
	{
		return Mouse.instance.mouseMovementListener.getMouseX();
	}

	public static int getMouseY()
	{
		return Mouse.instance.mouseMovementListener.getMouseY();
	}

	public static Mouse getInstance()
	{
		return Mouse.instance;
	}
}