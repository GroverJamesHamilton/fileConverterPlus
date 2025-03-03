package fileConverterPlus;

import java.awt.Color;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.border.Border;

public class CustomButton extends JButton {

	private int radius = 10; //The radius for the rounded edges of the button
	private int height = 50; 
	private int width = 150;
	private String title;
	private Color color;
	
	//Default constructor
	CustomButton(String title, Color color){
		this.title = title;
		this.color = color;
		CustomButtonHelpFunction();
	}
	
	CustomButton(String title, Color color, int height, int width){
		this.title = title;
		this.color = color;
		this.height = height;
		this.width = width;
		CustomButtonHelpFunction();
	}
	
	CustomButton(String title, Color color, int height, int width, int radius){
		
		this.title = title;
		this.color = color;
		this.height = height;
		this.width = width;
		this.radius = radius;
		CustomButtonHelpFunction();
	}
	
	RoundedBorder roundEdges = new RoundedBorder(radius, color);
	
	private void CustomButtonHelpFunction() {
		
		this.setSize(width, height);
		this.setBorder(roundEdges);
		this.setText(title);
		this.setFocusable(false);
		//this.setOpaque(false);
		//this.setFocusPainted(true);
		//this.setBorderPainted(true);
		//this.setContentAreaFilled(true);
		;
	}
}
