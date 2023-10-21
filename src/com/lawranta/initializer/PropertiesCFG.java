package com.lawranta.initializer;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.lawranta.globals.GLOBAL;



public class PropertiesCFG {
	static Properties prop=new Properties();
	
	public static void cfgLoad() {
		
		
		
	try {
		FileInputStream ip= new FileInputStream(GLOBAL.cfgPath);
		prop.load(ip);
		System.out.println(prop.getProperty("gridColor"));
		System.out.println(prop.getProperty("gridBGColor"));
		System.out.println(prop.getProperty("bgColor"));
		setGlobals() ;
		
	} catch (FileNotFoundException e) {
		
		System.out.println(e);
		e.printStackTrace();
	} catch (IOException e) {
		System.out.println(e);
		e.printStackTrace();
	}
	
	}

	public static void setGlobals() {
		//gridColor, gridBGColor, bgColor
		
		System.out.println("GridColor: " + prop.getProperty("gridColor")
				+ "\n"
				+ "GridBGColor: " 
				+ prop.getProperty("gridBGColor")
				+ ""
				+ "\n"
				+ "bgColor: "
				+ prop.getProperty("bgColor")
				);
		
	
		
		
		
		GLOBAL.gridColor=Color.decode("#" + prop.getProperty("gridColor"));
		GLOBAL.gridBGColor=Color.decode("#" + prop.getProperty("gridBGColor"));
		GLOBAL.bgColor=Color.decode("#" + prop.getProperty("bgColor"));
		
		System.out.println("GridColor: " + GLOBAL.gridColor 
				+ "\n"
				+ "GridBGColor: " 
				+ GLOBAL.gridBGColor
				+ "\n"
				+ "bgColor: "
				+ GLOBAL.bgColor
				);
		
	}

	public static void mainCFG() throws IOException {
		Properties prop=new Properties();
		File cfg = new File(GLOBAL.cfgPath);
		cfg.createNewFile(); // if file already exists will do nothing 
		FileInputStream ip= new FileInputStream(GLOBAL.cfgPath);
		prop.load(ip);
		if(!prop.containsKey("gridColor") || !prop.containsKey("gridBGColor") || !prop.containsKey("bgColor"))
		{
			FileOutputStream op = new FileOutputStream(GLOBAL.cfgPath);
			System.out.println("setting default colors");
		
			prop.setProperty("gridColor", "FF2D00");
			prop.setProperty("gridBGColor", "FFFFFF");
			prop.setProperty("bgColor", "0F007C");
			prop.store(op, "Configurations");
			
	
			
		}
		
	}

	public static void persistSetting(String setting, String color) throws IOException {
		// TODO Auto-generated method stub
		Properties prop=new Properties();
		File cfg = new File(GLOBAL.cfgPath);
		cfg.createNewFile(); // if file already exists will do nothing 
		FileInputStream ip= new FileInputStream(GLOBAL.cfgPath);
		prop.load(ip);
		
			FileOutputStream op = new FileOutputStream(GLOBAL.cfgPath);
			System.out.println("persisting " + setting + "value: " + color);
		
			prop.setProperty(setting, color);
			prop.store(op, setting + " updated to" + color);
			System.out.println(setting + " updated to " + color);
			
		
	}

}
