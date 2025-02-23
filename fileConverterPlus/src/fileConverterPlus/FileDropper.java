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
import java.util.Arrays;
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

@Override
public void dragEnter(DropTargetDragEvent dtde) {
}
@Override
public void dragOver(DropTargetDragEvent dtde) {
}
@Override
public void dropActionChanged(DropTargetDragEvent dtde) {
}
@Override
public void dragExit(DropTargetEvent dte) {
}

boolean dropped = false;

//Lists all file paths in a Array
List listAllPaths(List<File> files) {	
	List allPaths = new ArrayList();
	File file;
	Iterator iter = files.iterator();
	while (iter.hasNext()) {
		
		file = (File) iter.next();
		
		if(file.isFile()) {
			allPaths.add(file.getPath());
			System.out.println(file.getName());
		}
		
		if(file.isDirectory()) {

			//String directoryPath = file.getPath();
			File directory = new File(file.getPath());
			File[] directoryFiles = directory.listFiles();
			List<File> directoryList = Arrays.asList(directoryFiles);
			allPaths.add(listAllPaths(directoryList));
		}
	}
	
	return allPaths;
}

// If any file or object is dropped within the JLabel
@Override
public void drop(DropTargetDropEvent droppedFile) {

		droppedFile.acceptDrop(DnDConstants.ACTION_COPY);
		Transferable transferredFiles = droppedFile.getTransferable();
		DataFlavor[] flavors = transferredFiles.getTransferDataFlavors();
		
		for (DataFlavor flavor : flavors) {
			
			try {
				
				if (flavor.equals(DataFlavor.javaFileListFlavor)) {	
				List<File> files = (List<File>) transferredFiles.getTransferData(flavor);
				
				listAllPaths(files);
				
				}
				
			} catch (Exception e) { System.out.println("Fix this exception later");	}			
		}
}

}
