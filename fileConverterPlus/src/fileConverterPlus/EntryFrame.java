package fileConverterPlus;

import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EntryFrame extends JFrame implements ActionListener, FileDropListener{

	JFrame frame = new JFrame();
	private Color color = new Color(93, 93, 93);
	//Temporary button to trigger a new window
	JButton button = new JButton("New Window");

	FileDropper fileDropper = new FileDropper("Drop File/s Here");
	//Button to trigger FileChooser
	CustomButton browseButton = new CustomButton("Browse", color, 35, 120);
	
	private int width = 600;
	private int height = 500;

	public EntryFrame() {
		
		button.setBounds(20,400,200,40);
		button.setFocusable(false);
		button.addActionListener(this);
		
		fileDropper.setLocation((width - fileDropper.getWidth())/2,75);
		fileDropper.addListener(this);
		
		browseButton.setLocation((width - fileDropper.getWidth())/2, 250);
		browseButton.setVisible(true);
		browseButton.addActionListener(this);
		
		this.setTitle("Entry Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(width, height);
		this.setVisible(true);

		//Icon Image (Top corner)
		ImageIcon image = new ImageIcon("ruler.png");
		this.setIconImage(image.getImage());
		
		this.add(button);
		this.add(browseButton);
		this.add(fileDropper);
		
		this.getContentPane().setBackground(color);

		//Initially sets frame in middle of screen
		this.setLocationRelativeTo(null); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == button) {
			MainFrame main = new MainFrame();
		}

		if(e.getSource() == browseButton) {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setMultiSelectionEnabled(true);
			//fileChooser.setCurrentDirectory(new File("."));
			//Temporarily setting the current directory to a test map 
			fileChooser.setCurrentDirectory(new File("C:\\Users\\Alexa\\Desktop\\TestMapp"));
			int response = fileChooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {

				File[] files = fileChooser.getSelectedFiles();
				//File files = new File(fileChooser.getSelectedFile().getAbsolutePath());
				System.out.println(files[0].getPath());
			} 
		}
	}

	@Override
	public void acceptedFilesDropped() {
		//MainFrame main = new MainFrame();
		//this.dispose();
	}
}
