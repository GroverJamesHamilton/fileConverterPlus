package fileConverterPlus;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.border.Border;

public class CustomButton extends JButton implements Border {

	private int radius = 10; //The radius for the rounded edges of the button
	private int height = 50; 
	private int width = 150;
	private String title;
	
	//Default constructor
	CustomButton(String title){
		this.title = title;
		
		CustomButtonHelpFunction();
	}
	
	CustomButton(String title, int height, int width){
		this.title = title;
		this.height = height;
		this.width = width;
		
		CustomButtonHelpFunction();
	}
	
	CustomButton(String title, int height, int width, int radius){
		this.title = title;
		this.height = height;
		this.width = width;
		this.radius = radius;
		
		CustomButtonHelpFunction();
	}
	
	private void CustomButtonHelpFunction() {
		
		this.setSize(width, height);
		this.setText(title);
		this.setFocusable(false);
		//this.setOpaque(false);
		//this.setFocusPainted(true);
		//this.setBorderPainted(true);
		//this.setContentAreaFilled(true);
	}
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	}

	@Override
	public boolean isBorderOpaque() {
		return true;
	}

}
