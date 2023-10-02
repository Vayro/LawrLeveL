package com.lawranta.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import com.lawranta.canvas.Paint;
import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;

public class Open {

	public static void open() {

		int option = GLOBAL.path.showOpenDialog(GLOBAL.CP);
		if (option == JFileChooser.APPROVE_OPTION) {
			File f = GLOBAL.path.getSelectedFile();
			System.out.println("File Selected: " + f.getAbsolutePath());

			ArrayList<Paint> loadedTanFile;
			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				{
					loadedTanFile = (ArrayList<Paint>) ois.readObject();
					CanvasPanel.ReloadFromCanvasContainer(loadedTanFile);
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

	}
	
	
	

}
