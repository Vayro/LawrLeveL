package com.lawranta.file;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.table.TableModel;

import com.lawranta.canvas.Zoom;
import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;
import com.lawranta.popups.ConfirmDialog;

public class Save {

	public static void save() {
		Zoom.zoomDefault();

		if (GLOBAL.fileInfo.getAbsPath() == null) {

			saveAs();

		} else {
			try {
				FileOutputStream f = new FileOutputStream(new File(GLOBAL.fileInfo.getAbsPath()));
				ObjectOutputStream o = new ObjectOutputStream(f);

				// Write objects to file
				o.writeObject(CanvasPanel.canvasContainer);
				// o.writeObject(p2);

				o.close();
				f.close();

				System.out.println("File saved");

			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	public static boolean saveAs() {
		Zoom.zoomDefault();
		String defaultFileName = GLOBAL.fileInfo.getFileName();

		String absPath = GLOBAL.fileInfo.getAbsPath();
		File f = null;
		boolean fileOverwrite=true;
		
		do {
			
		
		GLOBAL.filePathDialog.setSelectedFile(new File(defaultFileName));
		Integer opt = GLOBAL.filePathDialog.showSaveDialog(GLOBAL.CP);


		if (opt == JFileChooser.APPROVE_OPTION) {

			// get selected pathfile
			f = GLOBAL.filePathDialog.getSelectedFile();
			absPath = f.getAbsolutePath();

			if (!f.getAbsolutePath().endsWith(GLOBAL.EXTENSION)) {

				absPath = f.getAbsolutePath() + GLOBAL.EXTENSION;
			}
			if(new File(absPath).exists() && !new File(absPath).isDirectory()) {
				
				
				ConfirmDialog overwrite = new ConfirmDialog("Overwrite file: \n[" +  absPath + "]?");
				overwrite.setVisible(true);
				ActionEvent e= overwrite.getA();
				
				
				switch (e.getActionCommand()) {

				case "OK":
					System.out.println("Overwriting");
					fileOverwrite=true;
					break;
				case "Cancel":
					System.out.println("not Overwriting");
					fileOverwrite=false;
					break;

				}
			}}}
			
		while(fileOverwrite==false);
				
		
			// update file info
			System.out.println(absPath);
			GLOBAL.fileInfo.setVersion(GLOBAL.VERSION);
			GLOBAL.fileInfo.setFileName(f.getName());
			GLOBAL.fileInfo.setAbsPath(absPath);
			GLOBAL.fileInfo.setCanvasSize(new Dimension(GLOBAL.CANVAS_WIDTH, GLOBAL.CANVAS_HEIGHT));
			GLOBAL.fileInfo.setOffsetX(GLOBAL.OFFSETX);
			GLOBAL.fileInfo.setOffsetY(GLOBAL.OFFSETY);

		

//stuff canvasContainer and FileInfo into a new object array
		ArrayList<Object> fileContainer = new ArrayList<Object>();

		fileContainer.add(GLOBAL.fileInfo);
		fileContainer.add(CanvasPanel.canvasContainer);

		try {
			FileOutputStream file = new FileOutputStream(new File(absPath));
			ObjectOutputStream o = new ObjectOutputStream(file);

			// Write objects to file
			o.writeObject(fileContainer);
			// o.writeObject(p2);

			o.close();
			file.close();

			System.out.println("File saved");

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		GLOBAL.updateTitle();
		return false;

	}

}
