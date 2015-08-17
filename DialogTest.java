import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class DialogTest 
{
    public static void main(String[] args)
    {
    	EventQueue.invokeLater(new Runnable()
    	{
    		public void run()
    		{
    			DialogFrame frame = new DialogFrame();
    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			frame.setVisible(true);
    		}
    	});
    }
}

/**
 * A frame with a menu whose file->about action shows a dialog.
 */
class DialogFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DialogFrame()
	{
		setTitle("DialogTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//construct a file menu
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		//add About and Exit menu items
		
		//The About item shows the About dialog.
		
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				if(dialog == null) //first time
					dialog = new AboutDialog(DialogFrame.this);
				dialog.setVisible(true); //pop up dialog.
			}
		});
		fileMenu.add(aboutItem);
		
		//The Exit item exits the program.
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);
	}
	
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	
	private AboutDialog dialog;
}

/**
 * A sample model dialog that displays a message and waits for the user to click the OK button.
 */
class AboutDialog extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AboutDialog(JFrame owner)
	{
		super(owner, "About DialogTest", true);
		
		//add HTML label to center
		add(
				new JLabel(
						"<html><h1><i>Core Java</i></h1><hr>By Cay Hostmann and Gary Cornell</html>"),
						BorderLayout.CENTER);
		//OK button closes the dialog
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
			
		});
		
		//add OK button to southern border
		JPanel panel = new JPanel();
		panel.add(ok);
		add(panel, BorderLayout.CENTER);
		
		setSize(250, 150);
	}
}
