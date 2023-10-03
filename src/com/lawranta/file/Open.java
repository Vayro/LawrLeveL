package com.lawranta.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import com.lawranta.canvas.Paint;
import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;

public class Open {

	public static void open() {

		int option = GLOBAL.filePathDialog.showOpenDialog(GLOBAL.CP);
		if (option == JFileChooser.APPROVE_OPTION) {
			File f = GLOBAL.filePathDialog.getSelectedFile();
			System.out.println("File Selected: " + f.getAbsolutePath());

			List<Object> fileContainer;
			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				{
					fileContainer = (ArrayList<Object>) (ois.readObject());
					GLOBAL.fileInfo=(FileInfo) fileContainer.get(0);
					CanvasPanel.ReloadFromCanvasContainer((ArrayList<Paint>) fileContainer.get(1));
				}

			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else

		{
			System.out.println("Open command canceled");
		}

//write to CSV
		GLOBAL.updateTitle() ;

	}
	
	
	

}
