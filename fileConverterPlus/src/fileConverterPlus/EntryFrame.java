package fileConverterPlus;

import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EntryFrame extends JFrame implements ActionListener{

	JFrame frame = new JFrame();
	
	//Temporary button to trigger a new window
	JButton button = new JButton("New Window");
	//Button to trigger FileChooser
	JButton browseButton = new JButton("Browse");
	FileDropper fileDropper = new FileDropper("Drop File/s Here");

	int entryFrameWidth = 600;
	int entryFrameHeight = 500;

	EntryFrame() {

		button.setBounds(20,400,200,40);
		button.setFocusable(false);
		button.addActionListener(this);

		browseButton.setBounds(175,275,150,40);
		//browseButton.setBorder(new RoundedBorder(10));
		browseButton.setFocusable(false);
		browseButton.addActionListener(this);

		fileDropper.setLocation((entryFrameWidth - fileDropper.getWidth())/2,75);

		this.setTitle("Entry Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(entryFrameWidth, entryFrameHeight);
		this.setVisible(true);

		//Icon Image (Top corner)
		ImageIcon image = new ImageIcon("ruler.png");
		this.setIconImage(image.getImage());

		this.getContentPane().setBackground(new Color(93, 93, 93));

		this.add(button);
		this.add(browseButton);
		this.add(fileDropper);

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

}
