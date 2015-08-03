import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ActionTest 
{
     public static void main(String[] args)
     {
    	 EventQueue.invokeLater(new Runnable()
    	 {
    		 public void run()
    		 {
    			 ActionFrame frame = new ActionFrame();
    			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			 frame.setVisible(true);
    		 }
    	 });
     }
}

/*
 *A frame with a panel that demonstrates color change actions.
*/
class ActionFrame extends JFrame
{
	public ActionFrame()
	{
		setTitle("ActionTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		buttonPanel = new JPanel();
		
		//define actions
		Action yellowAction = new ColorAction("Yellow", new ImageIcon("test.bmp"),Color.YELLOW);
		Action blueAction = new ColorAction("Blue", new ImageIcon("testblue.bmp"),Color.BLUE);
		Action redAction = new ColorAction("Red", new ImageIcon("testred.bmp"),Color.RED);
		
		//add buttons for these actions
		buttonPanel.add(new JButton(yellowAction));
		buttonPanel.add(new JButton(blueAction));
		buttonPanel.add(new JButton(redAction));
		
		//add panel to frame
		add(buttonPanel);
		
		//associate the Y,B,R keys with names
		InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
		imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
		
		//associate the names with actions
		ActionMap amap = buttonPanel.getActionMap();
		amap.put("panel.yellow", yellowAction);
		amap.put("panel.blue", blueAction);
		amap.put("panel.red", redAction);
	}
	
	
	public class ColorAction extends AbstractAction
	{
        public ColorAction(String name, Icon icon,  Color color)
        {
        	putValue(Action.NAME, name);
        	putValue(Action.SMALL_ICON, icon);
        	putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
        	putValue("color", color);
        }
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			Color c = (Color)getValue("color");
			buttonPanel.setBackground(c);
		}
		
	}
	
	private JPanel buttonPanel;
	
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
}
