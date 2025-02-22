package fileConverterPlus;

import java.awt.Color;
import java.awt.Font;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FileDropper extends JLabel implements DropTargetListener{
	
FileDropper(String name) {
	super(name, SwingConstants.CENTER);	
	
	//new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this);
	new DropTarget(this, DnDConstants.ACTION_NONE, this);
	
	this.setSize(300,170);
	this.setBackground(new java.awt.Color(240,240,230));
	this.setOpaque(true);
	this.setForeground(new java.awt.Color(0,0,0));
	setFont(new java.awt.Font("Times New Roman", Font.BOLD, 18));
	
}

boolean inside = false;

@Override
public void dragEnter(DropTargetDragEvent dtde) {
	// TODO Auto-generated method stub
	if(!inside) {
		inside = true;
		System.out.println("Inside");
	}
}

@Override
public void dragOver(DropTargetDragEvent dtde) {
	// TODO Auto-generated method stub
	
}

@Override
public void dropActionChanged(DropTargetDragEvent dtde) {
	// TODO Auto-generated method stub
	
}

@Override
public void dragExit(DropTargetEvent dte) {
	// TODO Auto-generated method stub
	
}

@Override
public void drop(DropTargetDropEvent dtde) {
	// TODO Auto-generated method stub
	
}



}
