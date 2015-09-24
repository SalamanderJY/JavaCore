package thread;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BounceThread 
{
    public static void main(String[] args)
    {
    	EventQueue.invokeLater(new Runnable()
    	{
    		public void run()
    		{
    			JFrame frame = new BounceFrame();
    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			frame.setVisible(true);
    		}
    	});
    }
}

/**
 * A runnable that animates a bouncing ball
 */
class BallRunnable implements Runnable
{
    
	public BallRunnable(Ball aBall, Component aComponent)
	{
		ball = aBall;
		component = aComponent;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			for(int i = 0; i <= STEPS; i++)
			{
				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		}
		catch(InterruptedException e)
		{}	
	}
		
	private Ball ball;
	private Component component;
	public static final int STEPS = 1000;
	public static final int DELAY = 5;
}

/**
 * The frame with panel and buttons
 */
class BounceFrame extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	public BounceFrame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("BounceThread");
		
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				addBall();
			}
			
		});
		
		addButton(buttonPanel, "Close", new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void addButton(Container c, String title, ActionListener listener)
	{
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	/**
	 * Adds a bouncing ball to the canvas and starts a thread to make it bounce.
	 */
	public void addBall()
	{
		Ball b = new Ball();
		comp.add(b);
		Runnable r = new BallRunnable(b, comp);
		Thread t = new Thread(r);
		t.start();
	}
	
	private BallComponent comp;
	public static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HEIGHT = 350;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
}