package fileConverterPlus;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	JFrame frame = new JFrame();
	JLabel label = new JLabel("Hello!");

	MainFrame(){

		this.setTitle("New Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setResizable(false);
		this.setSize(500,500);
		this.setVisible(true);

		ImageIcon image = new ImageIcon("ruler.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(new Color(0, 200, 0));

	}

}
