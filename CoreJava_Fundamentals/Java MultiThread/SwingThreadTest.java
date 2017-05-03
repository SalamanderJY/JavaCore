package Multithread;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingThreadTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
		{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SwingThreadFrame frame = new SwingThreadFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}
}

class SwingThreadFrame extends JFrame
{
	public SwingThreadFrame()
	{
		setTitle("SwingThreadTest");
		
		final JComboBox combo = new JComboBox();
		combo.insertItemAt(Integer.MAX_VALUE, 0);
		combo.setPrototypeDisplayValue(combo.getItemAt(0));
		combo.setSelectedIndex(0);
		
		JPanel panel = new JPanel();
		
		JButton goodButton = new JButton("Good");
		goodButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Thread(new GoodWorkerRunnable(combo)).start();
			}
			
		});
		
		panel.add(goodButton);
		JButton badButton = new JButton("Bad");
		badButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Thread(new BadWorkerRunnable(combo)).start();
			}
			
		});
		panel.add(badButton);
		
		panel.add(combo);
		add(panel);
		pack();
	}
}

class BadWorkerRunnable implements Runnable
{

	public BadWorkerRunnable(JComboBox aCombo)
	{
		combo = aCombo;
		generator = new Random();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			while(true)
			{
				int i = Math.abs(generator.nextInt());
				if(i % 2 == 0) combo.insertItemAt(i, 0);
				else if(combo.getItemCount() > 0) combo.removeItemAt(i % combo.getItemCount());
				Thread.sleep(1);
			}
		}
		catch(InterruptedException e)
		{
		}
		
	}
	
	private JComboBox combo;
	private Random generator;
}

class GoodWorkerRunnable implements Runnable
{

	public GoodWorkerRunnable(JComboBox aCombo) {
		// TODO Auto-generated constructor stub
		combo = aCombo;
		generator = new Random();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			while(true)
			{
				EventQueue.invokeLater(new Runnable()
				{

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int i = Math.abs(generator.nextInt());
						if(i % 2 == 0) combo.insertItemAt(i, 0);
						else if(combo.getItemCount() > 0) combo.removeItemAt(i % combo.getItemCount());
					}
					
				});
				Thread.sleep(1);
			}
		}
		catch(InterruptedException e)
		{
		}
	}
	
	private JComboBox combo;
	private Random generator;
}
