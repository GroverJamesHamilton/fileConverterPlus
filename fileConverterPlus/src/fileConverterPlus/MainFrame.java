package fileConverterPlus;

import java.awt.Color;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MainFrame extends JFrame {

	JFrame frame = new JFrame();
	JLabel label = new JLabel("Hello!");
	private Color glacuous = new Color(96, 130, 182);

	MainFrame(){

		this.setTitle("New Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setResizable(false);
		this.setSize(500,500);
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
	}

}
