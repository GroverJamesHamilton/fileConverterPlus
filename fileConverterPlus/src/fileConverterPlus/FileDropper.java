package fileConverterPlus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class FileDropper extends JLabel implements DropTargetListener, ActionListener{
	
	final int PANEL_WIDTH = 350;
	final int PANEL_HEIGHT = 165;
	final int PANEL_WIDTH_EXPANSION = 50;
	final int PANEL_ROUND_EDGE_RADIUS = 30;
	final int NR_PANEL_SHAKES  = 1;
	final String DROP_ZONE_TEXT = "Drop Image/s Here";
	
	Timer timer;
	Image dropZone;
	
	private int x = 0;
	private int iter = 0;
	
	final Font TEXT_FONT = new java.awt.Font("Times New Roman", Font.BOLD, 30);
	private Boolean paintComponentsInitiated = false;
	private int textWidth, textHeight;
	
	private List<File> imgPaths = new ArrayList<File>();
	
	final int PANEL_SHAKE_DISTANCE = 10;
	final int PANEL_SHAKE_VELOCITY = 30;
	
	//The files we pass onto the MainFrame class 
	//when this class along with EntryFrame is shut down
	private static List<File> imgFilesPaths;
	
	private List<FileDropListener> listeners = new ArrayList();
    public void addListener(FileDropListener toAdd) {
        listeners.add(toAdd);
    }
    
    public void acceptedFiles() {
        //System.out.println("Files are accepted");
    
        for (FileDropListener listener : listeners)
            listener.acceptedFilesDropped();
    }
    
    public static List<File> getFiles() 
    { 
        return imgFilesPaths; 
    } 
    
    //Return resulting file path names
    public List<File> getPaths(){
    	return imgPaths;
    }
	
	FileDropper(String title) {
		new DropTarget(this, DnDConstants.ACTION_COPY, this);
		this.setHorizontalAlignment(SwingConstants.CENTER);  
		this.setText(title);
		this.setSize(PANEL_WIDTH,PANEL_HEIGHT);
		this.setBackground(new java.awt.Color(240,240,230));
		this.setOpaque(false);
		this.setForeground(new java.awt.Color(0,0,0));
		dropZone = new ImageIcon("dropZone.png").getImage();
		timer = new Timer(10, this);
	}
	
	//Getting the file type in string format,
	//Credit to https://www.baeldung.com/java-file-extension
	String getFileType(String fileName) {
		//Returns the file type in string format i.e. the file name after the dot
		int dotIndex = fileName.lastIndexOf(".");
	    if (dotIndex >= 0) {
	        return fileName.substring(dotIndex + 1);
	    }
	    else {
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
		//List<File> imgPaths = new ArrayList<File>();
		File file;
		Iterator<File> iter = files.iterator();
		while (iter.hasNext()) {

			file = (File) iter.next();

			if(file.isFile() && 
					getFileType(file.getName()).equals("png") || 
					getFileType(file.getName()).equals("jpg")) {
				imgPaths.add(file);
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
	public void paint(Graphics g) {

		super.paint(g);	
		Graphics2D g2D = (Graphics2D) g;
		g2D.setFont(TEXT_FONT);
		
		//Variables that only needs initiating once
		//E.g. text dimensions
		if (!paintComponentsInitiated) {
			FontRenderContext context = g2D.getFontRenderContext();
			textWidth = (int) TEXT_FONT.getStringBounds(DROP_ZONE_TEXT, context).getWidth();
	        LineMetrics ln = TEXT_FONT.getLineMetrics(DROP_ZONE_TEXT, context);
	        textHeight = (int) (ln.getAscent() + ln.getDescent());
			paintComponentsInitiated = true;
		}
		
		//Anti-alising
	    RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
		qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
		g2D.setRenderingHints(qualityHints);  
		
		g2D.setPaint(Color.WHITE);
		g2D.fill(new RoundRectangle2D.Double(x, 0, PANEL_WIDTH, PANEL_HEIGHT, PANEL_ROUND_EDGE_RADIUS, PANEL_ROUND_EDGE_RADIUS));
		g2D.setPaint(Color.BLACK);
		g2D.drawString("Drop Image/s Here", (PANEL_WIDTH - textWidth)/2, (PANEL_HEIGHT + textHeight)/2);
	}
	
	@Override
	public void drop(DropTargetDropEvent droppedFile) {

		droppedFile.acceptDrop(DnDConstants.ACTION_COPY);
		Transferable transferredFiles = droppedFile.getTransferable();
		DataFlavor[] flavors = transferredFiles.getTransferDataFlavors();

		for (DataFlavor flavor : flavors) {			
			try {		
				if (flavor.equals(DataFlavor.javaFileListFlavor)) {	
					List<File> files = (List<File>) transferredFiles.getTransferData(flavor);
					imgFilesPaths = listImgPaths(files);
					if(imgFilesPaths.size() > 0){
						acceptedFiles();						
					}
					else {
						shakePanel();
					}
				}
			} catch (Exception e) { 
				System.out.println("Fix this exception later");	
			}			
		}
	}
	
	//Calls timer function which triggers 
	//implemented actionPerformed
	public void shakePanel() {
		//Expands the panel (not image) and center
		this.setSize(PANEL_WIDTH + PANEL_WIDTH_EXPANSION, PANEL_HEIGHT);
		this.setHorizontalAlignment(SwingConstants.CENTER); 
		
		x = PANEL_SHAKE_DISTANCE;
		this.setLocation(this.getLocation().x, this.getLocation().y);
		timer.start();
	}
	
	@Override //Shakes the panel
	public void actionPerformed(ActionEvent e) {
		//Shakes the panel by following the Y-axis of a sine wave
		if (iter <= NR_PANEL_SHAKES*360) {
			x = PANEL_SHAKE_DISTANCE - (int) (PANEL_SHAKE_DISTANCE*Math.sin(Math.toRadians(iter)));
			iter += PANEL_SHAKE_VELOCITY;
			repaint();
		}
		else {
			//Stop timer and restore altered panel
			iter = 0;
			timer.stop();
			//Restores panel location and size
			this.setLocation(this.getLocation().x, this.getLocation().y);
			this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
			x = 0;
		} 
	} 
}
