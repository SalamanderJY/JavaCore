import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BuggyButtonTest 
{
	public static void main(String[] args)
    {
    	EventQueue.invokeLater(new Runnable()
    	{
    		public void run()
    		{
    			BuggyButtonFrame frame = new BuggyButtonFrame();
    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			frame.setVisible(true);
    		}
    	});
    }
}

class BuggyButtonFrame extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	
	public BuggyButtonFrame()
	{
		setTitle("BuggyButtonTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//add panel to frame
		
		BuggyButtonPanel panel = new BuggyButtonPanel();
		add(panel);
	}
	
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
}

class BuggyButtonPanel extends JPanel
{

	private static final long serialVersionUID = 1L;

	public BuggyButtonPanel()
	{
		ActionListener listener = new ButtonListener();
		
		JButton yellowButton = new JButton("Yellow");
		add(yellowButton);
		yellowButton.addActionListener(listener);
		
		JButton blueButton = new JButton("Blue");
		add(blueButton);
		blueButton.addActionListener(listener);
		
		JButton redButton = new JButton("Red");
		add(redButton);
		redButton.addActionListener(listener);
	}
	
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			String arg = event.getActionCommand();
			if(arg.equals("Yellow")) 
				setBackground(Color.yellow);
			else if(arg.equals("Blue"))
				setBackground(Color.blue);
			else if(arg.equals("Red"))
				setBackground(Color.red);
		}	
	}
}
