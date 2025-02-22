package fileConverterPlus;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FileDropper extends JLabel implements MouseListener{
	
FileDropper(String name) {
	super(name, SwingConstants.CENTER);	
	this.setSize(300,170);
	this.setBackground(new java.awt.Color(240,240,230));
	this.setOpaque(true);
	this.setForeground(new java.awt.Color(0,0,0));
	setFont(new java.awt.Font("Times New Roman", Font.BOLD, 18));
	
	this.addMouseListener(this);
}

boolean inside = false;

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	System.out.println("Clicked");
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
	if(!inside) {
		inside = true;
		System.out.println("We entered label");
	}
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	if(inside) {
		inside = false;
		System.out.println("We exited label");
	}
}

}
