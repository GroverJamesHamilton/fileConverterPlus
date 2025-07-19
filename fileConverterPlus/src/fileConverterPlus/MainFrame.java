package fileConverterPlus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.util.List;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double screenWidth = screenSize.getWidth();
	double screenHeight = screenSize.getHeight();

	JFrame frame = new JFrame();
	JLabel label = new JLabel("Hello!");
	private Color glacuous = new Color(96, 130, 182);

	MainFrame(){

		this.setTitle("New Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setResizable(false);
		//System.out.println("Width " + screenWidth);
		//System.out.println("Height " + screenHeight);
		//Adjust for taskbar height: 
		//https://stackoverflow.com/questions/6844996/windows-taskbar-height-width
		this.setSize((int)screenWidth/2 - 75,(int)screenHeight - 50);
		this.setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu helpMenu = new JMenu("Help");
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		this.setJMenuBar(menuBar);
		
		ImageIcon image = new ImageIcon("ruler.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(glacuous);
		List<File> processedFiles = FileDropper.getFiles();
		System.out.println(processedFiles.size());
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		
		panel1.setBackground(Color.red);
		panel2.setBackground(glacuous);
		panel3.setBackground(Color.yellow);
		panel4.setBackground(Color.magenta);
		panel5.setBackground(Color.blue);
		
		panel1.setPreferredSize(new Dimension(100,100));
		panel2.setPreferredSize(new Dimension(100,100));
		panel3.setPreferredSize(new Dimension(100,100));
		panel4.setPreferredSize(new Dimension(100,100));
		panel5.setPreferredSize(new Dimension(100,100));
		
		this.add(panel1,BorderLayout.NORTH);
		this.add(panel2,BorderLayout.WEST);
		this.add(panel3,BorderLayout.EAST);
		this.add(panel4,BorderLayout.SOUTH);
		this.add(panel5,BorderLayout.CENTER);
		
	}
}
