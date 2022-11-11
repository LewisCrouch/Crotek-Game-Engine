package uk.co.crotek.ge.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import uk.co.crotek.ge.display.renderer.RenderQueue;
import uk.co.crotek.ge.display.renderer.Renderer;
import uk.co.crotek.util.Logger;

public class Display extends JFrame
{
	private RenderQueue renderQueue;
	private JPanel content;

	private Collection<ResizeListener> resizeListeners;

	public Display(String title, int innerWidth, int innerHeight)
	{
		super(title);

		this.renderQueue = new RenderQueue();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		double x = (ss.getWidth() / 2) - (innerWidth / 2);
		double y = (ss.getHeight() / 2) - (innerHeight / 2);
		this.setLocation((int) x, (int) y);

		this.resizeListeners = new HashSet<ResizeListener>();
		this.addComponentListener(new ComponentAdapter() 
		{
			public void componentResized(ComponentEvent ev)
			{
				Iterator<ResizeListener> it = resizeListeners.iterator();
				while(it.hasNext())
				{
					it.next().onResize(getPreferredSize().width, getPreferredSize().height);
				}
			}
		});

		this.content = new JPanel()
		{
			private static final long serialVersionUID = -1770799654006048449L;

			@Override
			public void paint(Graphics g)
			{
				Graphics2D g2d = (Graphics2D) g;

				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

				g2d.setColor(Color.BLACK);
				g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

				renderQueue.setCurrentG2d(g2d);
				renderQueue.lock();
				Iterator<Renderer> it = renderQueue.iterator();
				while(it.hasNext())
				{
					Renderer renderable = it.next();
					if(renderable != null) renderable.preRender(g2d);
					else Logger.err("Failed to render renderable, it's null.");

					try
					{
						it.remove();
					}
					catch(Exception ex)
					{
						Logger.err("Error removing renderable! " + renderable);
						ex.printStackTrace();
					}
				}
				renderQueue.release();
			}
		};

		this.content.setPreferredSize(new Dimension(innerWidth, innerHeight));
		this.content.addMouseMotionListener(Mouse.getInstance().getMouseMovementListener());
		this.content.addMouseListener(Mouse.getInstance());
		this.addKeyListener(Keyboard.getInstance());
		this.add(this.content);

		this.content.requestFocusInWindow();

		this.pack();

		this.setVisible(true);
	}

	public void repaintContent()
	{
		this.content.repaint();
	}

	public int getInnerWidth()
	{
		return this.content.getWidth();
	}

	public void setInnerWidth(int innerWidth)
	{
		this.content.setSize(innerWidth, this.content.getHeight());
	}

	public int getInnerHeight()
	{
		return this.content.getHeight();
	}

	public void setInnerHeight(int innerHeight)
	{
		this.content.setSize(this.content.getWidth(), innerHeight);
	}

	public RenderQueue getRenderQueue()
	{
		return this.renderQueue;
	}
}
