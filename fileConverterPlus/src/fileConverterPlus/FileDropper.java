package fileConverterPlus;

import java.awt.Color;

import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FileDropper extends JLabel implements DropTargetListener{
	
FileDropper(String name) {
	
	new DropTarget(this, DnDConstants.ACTION_COPY, this);
	
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

boolean dropped = false;

@Override
public void drop(DropTargetDropEvent droppedFile) {
	// If any file or object is dropped within the JLabel
		System.out.println("Dropped");
		
		droppedFile.acceptDrop(DnDConstants.ACTION_COPY);
		Transferable transferredFiles = droppedFile.getTransferable();
		DataFlavor[] flavors = transferredFiles.getTransferDataFlavors();
		
		for (DataFlavor flavor : flavors) {
			
			try {
				
				if (flavor.equals(DataFlavor.javaFileListFlavor)) {
					
				List files = (List) transferredFiles.getTransferData(flavor);
				
				Iterator iter = files.iterator();
				
				while (iter.hasNext()) {
					
					File file = (File) iter.next();
					System.out.println(file.getName());
				}
				
				}
				
			} catch (Exception e) { System.out.println("Fix this exception later");	}			
		}
}

}
