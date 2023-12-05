package com.lawranta.initializer;

import javax.swing.UIManager;

import com.lawranta.edit.Debug;
import com.lawranta.file.*;
import com.lawranta.frames.MainFrame;
import com.lawranta.frames.ShortcutKeyListener;
import com.lawranta.globals.GLOBAL;
import com.lawranta.layers.LayerContainer;
import com.lawranta.popups.Preferences;



public class MainClass {
	private static boolean run = true;

	public MainClass() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		UIManager.put("Menu.foreground", GLOBAL.lightColor);
		UIManager.put("MenuItem.background", GLOBAL.darkColor);
		UIManager.put("MenuItem.foreground", GLOBAL.lightColor);
		UIManager.put("MenuItem.opaque", true);

		System.out.println("initializing..");


		//create first layer
		LayerContainer.initialize();
		
		GLOBAL.fileInfo = new FileInfo();
		
		//create e CGF file if it does not exist
		PropertiesCFG.mainCFG();
		PropertiesCFG.cfgLoad();
		
		GLOBAL.MAINFRAME = new MainFrame();
		GLOBAL.DEBUGFRAME = new Debug();
		GLOBAL.DEBUGFRAME.setVisible(false);
		GLOBAL.preferences= new Preferences();
		GLOBAL.filePathDialog = new FilePathDialog();
		GLOBAL.exportPathDialog = new ExportPathDialog();
		GLOBAL.MAINFRAME.setVisible(true);
		GLOBAL.MAINFRAME.addKeyListener(new ShortcutKeyListener());
		


	}
	
	
}
