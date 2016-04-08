import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class ColorChooserTest 
{
    public static void main(String[] args)
    {
    	EventQueue.invokeLater(new Runnable()
    	{
    		public void run()
    		{
    			ColorChooserFrame frame = new ColorChooserFrame();
    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			frame.setVisible(true);
    		}
    	});
    }
}

/**
 * A frame with a color chooser panel
 */
class ColorChooserFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ColorChooserFrame()
	{
		setTitle("ColorChooserTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//add color chooser panel to frame
		
		ColorChooserPanel panel = new ColorChooserPanel();
		add(panel);
	}
	
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
}

/**
 * A panel with buttons to pop up three types of color choosers.
 */
class ColorChooserPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColorChooserPanel()
	{
		JButton modalButton = new JButton("Modal");
		modalButton.addActionListener(new ModalListener());
		add(modalButton);
		
		JButton modelessButton = new JButton("Modeless");
		modelessButton.addActionListener(new ModelessListener());
		add(modelessButton);
		
		JButton immediateButton = new JButton("Immediate");
		immediateButton.addActionListener(new ImmediateListener());
		add(immediateButton);
	}
	
	/**
	 * This listener pops up a modal color chooser
	 */
	private class ModalListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			Color defaultColor = getBackground();
			Color selected = JColorChooser.showDialog(ColorChooserPanel.this, "Set background", defaultColor);
			if(selected == null)
				setBackground(selected);
		}
		
	}
	
	/**
	 * This listener pops up a modeless color chooser.This panel color is changed when the user clicks ok button
	 */
	private class ModelessListener implements ActionListener
	{

		public ModelessListener()
		{
			chooser = new JColorChooser();
			dialog = JColorChooser.createDialog(ColorChooserPanel.this, "Background Color", false, chooser, new ActionListener()
			{
			    public void actionPerformed(ActionEvent event)
			    {
			    	setBackground(chooser.getColor());
			    }
			}, null);
		}
		
		
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			chooser.setColor(getBackground());
			dialog.setVisible(true);
		}
		
		private JDialog dialog;
		private JColorChooser chooser;
		
	}
	
	/**
	 * This listener pops up a modeless color chooser. the panel color is changed immediately.
	 */
	private class ImmediateListener implements ActionListener
	{

		public ImmediateListener()
		{
			chooser = new JColorChooser();
			chooser.getSelectionModel().addChangeListener(new ChangeListener()
			{

				@Override
				public void stateChanged(ChangeEvent event) {
					// TODO Auto-generated method stub
					setBackground(chooser.getColor());
				}
				
			});
			
			dialog = new JDialog((Frame)null, false /*not modal*/);
			dialog.add(chooser);
			dialog.pack();
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			chooser.setColor(getBackground());
			dialog.setVisible(true);
		}
		
		private JDialog dialog;
		private JColorChooser chooser;
		
	}
}
