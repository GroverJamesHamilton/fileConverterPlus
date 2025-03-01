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
	
	private List<FileDropListener> listeners = new ArrayList();
    public void addListener(FileDropListener toAdd) {
        listeners.add(toAdd);
    }
    public void acceptedFiles() {
        System.out.println("Files are accepted");
    
        for (FileDropListener listener : listeners)
            listener.acceptedFilesDropped();
    }
	
	FileDropper(String title) {
		
		new DropTarget(this, DnDConstants.ACTION_COPY, this);
		this.setHorizontalAlignment(SwingConstants.CENTER);  
		this.setText(title);
		this.setSize(300,170);
		this.setBackground(new java.awt.Color(240,240,230));
		this.setOpaque(true);
		this.setForeground(new java.awt.Color(0,0,0));
		setFont(new java.awt.Font("Times New Roman", Font.BOLD, 22));
	}
	
	//Getting the file type in string format,
	//Credit to https://www.baeldung.com/java-file-extension
	String getFileType(String fileName) {
		
		int dotIndex = fileName.lastIndexOf(".");
	    if (dotIndex >= 0) {
	        return fileName.substring(dotIndex + 1);
	    }
	    else
	    {
	    	return " ";
	    }
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {}
	@Override
	public void dragOver(DropTargetDragEvent dtde) {}
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {}
	@Override
	public void dragExit(DropTargetEvent dte) {}

	//Lists all file paths in a Array
	//A bit wonky function due to many conversion steps
	List<File> listImgPaths(List<File> files) {	
		List<File> imgPaths = new ArrayList<File>();
		File file;
		Iterator<File> iter = files.iterator();
		while (iter.hasNext()) {

			file = (File) iter.next();

			if(file.isFile() && 
					getFileType(file.getName()).equals("png") || 
					getFileType(file.getName()).equals("jpg")) {
				
				imgPaths.add(file);
				//System.out.println(imgPaths.size());
			}

			if(file.isDirectory()) {
				File directory = new File(file.getPath());
				File[] directoryFiles = directory.listFiles();
				List<File> directoryList = Arrays.asList(directoryFiles);
				imgPaths.addAll(listImgPaths(directoryList));
			}
		}
		return imgPaths;
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
					List imgFilesPaths = listImgPaths(files);
					
					
					System.out.println(imgFilesPaths.size());
					//acceptedFiles();
				}			
			} catch (Exception e) { System.out.println("Fix this exception later");	}			
		}
	}
}
