import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.*;

public class PlafTest 
{
    public static void main(String[] args)
    {
    	EventQueue.invokeLater(new Runnable()
    	{
    		public void run()
    		{
    			PlafFrame frame = new PlafFrame();
    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			frame.setVisible(true);
    		}
    	});
    }
}

/*
 * A frame with a button panel for changing look and feel
 */
class PlafFrame extends JFrame
{
	public PlafFrame()
	{
		setTitle("PlatTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		buttonPanel = new JPanel();
		
		UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		for(UIManager.LookAndFeelInfo info : infos)
			makeButton(info.getName(), info.getClassName());
		
		add(buttonPanel);
	}
	
	void makeButton(String name, final String plafName)
	{
		//add button to panel
		JButton button = new JButton(name);
		buttonPanel.add(button);
		
		//set button action
		button.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event) 
			{
				// TODO Auto-generated method stub
				//button action :switch to the new look and feel
				try
				{
					UIManager.setLookAndFeel(plafName);
					SwingUtilities.updateComponentTreeUI(PlafFrame.this);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		});
	}
	
	private JPanel buttonPanel;
	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
}
