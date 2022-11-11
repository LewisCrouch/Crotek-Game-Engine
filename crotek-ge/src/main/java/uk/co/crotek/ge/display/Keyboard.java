package uk.co.crotek.ge.display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{
	private static Keyboard instance = new Keyboard();

	private boolean[] keyStates;
	private boolean[] checkKeyStates;
	private boolean keyDown;
	private int lastKey;
	private char lastChar;

	public Keyboard()
	{
		this.keyStates = new boolean[1024];
		this.checkKeyStates = new boolean[1024];
		this.keyDown = false;
		this.lastKey = -1;
	}

	@Override
	public void keyPressed(KeyEvent ev)
	{
		this.lastKey = ev.getKeyCode();
		this.lastChar = ev.getKeyChar();
		if(!this.checkKeyStates[ev.getKeyCode()]) this.keyStates[ev.getKeyCode()] = true;
		this.keyDown = true;
	}

	@Override
	public void keyReleased(KeyEvent ev)
	{
		this.keyStates[ev.getKeyCode()] = false;
		this.checkKeyStates[ev.getKeyCode()] = false;
		this.keyDown = false;
	}

	@Override
	public void keyTyped(KeyEvent ev)
	{
		this.lastKey = ev.getKeyCode();
		this.lastChar = ev.getKeyChar();
		if(!this.checkKeyStates[ev.getKeyCode()]) this.keyStates[ev.getKeyCode()] = true;
		this.keyDown = true;
	}

	public static boolean isKeyDown()
	{
		return Keyboard.instance.keyDown;
	}

	public static boolean isKeyDown(int key)
	{
		return Keyboard.instance.keyStates[key];
	}

	public static boolean isKeyPressed(int key)
	{
		if(Keyboard.instance.keyStates[key])
		{
			Keyboard.instance.keyStates[key] = false;
			return true;
		}
		return false;
	}

	public static boolean checkKey(int key)
	{
		if(Keyboard.instance.keyStates[key])
		{
			Keyboard.instance.keyStates[key] = false;
			return Keyboard.instance.checkKeyStates[key] = true;
		}
		return false;
	}

	public static int getLastKey()
	{
		return Keyboard.instance.lastKey;
	}

	public static char getLastChar()
	{
		return Keyboard.instance.lastChar;
	}

	public static Keyboard getInstance()
	{
		return Keyboard.instance;
	}
}