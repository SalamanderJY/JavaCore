package zipTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class ZipTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ZipTestFrame frame = new ZipTestFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}

}

class ZipTestFrame extends JFrame {
	private static final long serialVersionUID = -7718806626458854382L;
	
	public ZipTestFrame() {
		setTitle("ZipTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// add the menu and the Open and Exit menu items
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				int r = chooser.showOpenDialog(ZipTestFrame.this);
				if (r == JFileChooser.APPROVE_OPTION) {
					zipname = chooser.getSelectedFile().getPath();
					fileCombo.removeAllItems();
					scanZipFile();
				}
			}
		});
		
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		// add the text area and combo box
		fileText = new JTextArea();
		fileCombo = new JComboBox<String>();
		fileCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loadZipFile((String) fileCombo.getSelectedItem());
			}
		});
		
		add(fileCombo, BorderLayout.SOUTH);
		add(new JScrollPane(fileText), BorderLayout.CENTER);
	}
	
	/*
	 * Scans the contents of the ZIP archive and populates the combo box.
	 */
	public void scanZipFile() {
		new SwingWorker<Void, String>() {

			@Override
			protected Void doInBackground() throws Exception {
				// TODO Auto-generated method stub
				ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname));
				ZipEntry entry;
				while ((entry = zin.getNextEntry()) != null) {
					publish(entry.getName());
					zin.closeEntry();
				}
				zin.close();
				return null;
			}
			
			protected void process(List<String> names) {
				for (String string : names) {
					fileCombo.addItem(string);
				}
			}
			
		}.execute();
	}
	
	public void loadZipFile(final String name) {
		fileCombo.setEnabled(false);
		fileText.setText("");
		new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				// TODO Auto-generated method stub
				try {
					ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname));
					ZipEntry entry;
					
					// find entry with matching name archive
					while ((entry = zin.getNextEntry()) != null) {
						if(entry.getName().equals(name)) {
							// read entry into textarea
							Scanner in = new Scanner(zin);
							while(in.hasNext()) {
								fileText.append(in.nextLine());
								fileText.append("\n");
							}
							in.close();
						}
						zin.closeEntry();
					}
					zin.close();				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return null;
			}
			
			protected void done() {
				fileCombo.setEnabled(true);
			}
			
		}.execute();
	}
	
	public static int DEFAULT_WIDTH = 400;
	public static int DEFAULT_HEIGHT = 300;
	private JComboBox<String> fileCombo;
	private JTextArea fileText;
	private String zipname;
}