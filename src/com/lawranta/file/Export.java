package com.lawranta.file;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import com.lawranta.canvas.Zoom;
import com.lawranta.globals.GLOBAL;

public class Export {

	public static boolean export() {
		Zoom.zoomDefault();
		String defaultFileName = ".png";


		String absPath = GLOBAL.fileInfo.getAbsPath();

		GLOBAL.exportPathDialog.setSelectedFile(new File(defaultFileName));
		Integer opt = GLOBAL.exportPathDialog.showSaveDialog(GLOBAL.CP);

		if (opt == JFileChooser.APPROVE_OPTION) {
			
			// get selected pathfile
			File f = GLOBAL.exportPathDialog.getSelectedFile();
			absPath = f.getAbsolutePath();

			if (!f.getAbsolutePath().endsWith(".png")) {

				absPath = f.getAbsolutePath() + ".png";
			}
			
			
			//update file info


		}

//stuff canvasContainer and FileInfo into a new object array

		

		try {
	

			// Write objects to file
		
			//o.writeObject(p2);

		


			System.out.println("File Exported");
			
			GLOBAL.CP.setSize(GLOBAL.CP.getPreferredSize());
			BufferedImage image = new BufferedImage(GLOBAL.CP.getWidth(), GLOBAL.CP.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			GLOBAL.CP.printAll(g);
			g.dispose();
	
			 ImageIO.write(image, "png", new File(absPath)); 
	
	
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return false;

	}

}
