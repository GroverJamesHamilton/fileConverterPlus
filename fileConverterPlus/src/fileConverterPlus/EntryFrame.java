package fileConverterPlus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EntryFrame extends JFrame  implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton button = new JButton("New Window");
	
	JButton browseButton = new JButton("Browse");
	
	EntryFrame() {
		
		button.setBounds(20,400,200,40);
		button.setFocusable(false);
		button.addActionListener(this);
		
		browseButton.setBounds(175,200,150,40);
		browseButton.setFocusable(false);
		browseButton.addActionListener(this);
		
		this.setTitle("Entry Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setResizable(false);
		this.setSize(500,500);
		this.setVisible(true);
		
		ImageIcon image = new ImageIcon("ruler.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(new Color(93, 93, 93));
		
		this.add(button);
		this.add(browseButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button) {
			
			MainFrame main = new MainFrame();
			
		}
		
		if(e.getSource() == browseButton) {
			
			JFileChooser fileChooser = new JFileChooser();

			//fileChooser.setCurrentDirectory(new File("."));
			
			int response = fileChooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				//System.out.println(file);
			}
			
		}
		
	}
	
}
