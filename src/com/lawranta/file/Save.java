package com.lawranta.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.table.TableModel;

import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;

public class Save {

	
	
	
	public static void save() {
		
		
		
		try {
			FileOutputStream f = new FileOutputStream(new File("v:\\niggahigga.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(CanvasPanel.canvasContainer);
			//o.writeObject(p2);

			o.close();
			f.close();



			System.out.println("File saved");

	

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		
		
		
	}

	
	public static boolean saveAs() {

		String defaultFileName = "Emilia.tan";


		String absPath = "Emilia.tan";

		GLOBAL.path.setSelectedFile(new File(defaultFileName));
		Integer opt = GLOBAL.path.showSaveDialog(GLOBAL.path);

		if (opt == JFileChooser.APPROVE_OPTION) {
			// get selected pathfile
			File f = GLOBAL.path.getSelectedFile();
			absPath = f.getAbsolutePath();

			if (!f.getAbsolutePath().endsWith(GLOBAL.EXTENSION)) {

				absPath = f.getAbsolutePath() + GLOBAL.EXTENSION;
			}
			
			

			System.out.println(absPath);

		}

//write to CSV

		try {
			FileOutputStream f = new FileOutputStream(new File(absPath));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(CanvasPanel.canvasContainer);
			//o.writeObject(p2);

			o.close();
			f.close();



			System.out.println("File saved");
			
			
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return false;

	}
	
	
	
	
	
	
}
