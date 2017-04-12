import java.awt.*;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

public class SizedFrameTest 
{
    public static void main(String[] args) throws InvocationTargetException, InterruptedException
    {
    	EventQueue.invokeAndWait(new Runnable()	
    			{
    		        public void run()
    		        {
    		        	SizedFrame frame = new SizedFrame();
    		        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		        	frame.setVisible(true);
    		        }
    			});
    }
}

class SizedFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SizedFrame()
	{
		//get screen dimensions
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		//set from width, height and let platform pick screen location
		
		setSize(screenWidth/2, screenHeight/2);
		setLocationByPlatform(true);
		
		//see frame icon and title
		
		Image img = kit.getImage("icon.gif");
		setIconImage(img);
		setTitle("SizedFrame");
	}
	
}
