package com.lawranta.globals;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.lawranta.canvas.Zoom;
import com.lawranta.edit.Debug;
import com.lawranta.file.ExportPathDialog;
import com.lawranta.file.FileInfo;
import com.lawranta.file.FilePathDialog;
import com.lawranta.frames.MainFrame;
import com.lawranta.frames.internal.ColorChooser;
import com.lawranta.frames.internal.Menu;
import com.lawranta.panels.CanvasPanel;
import com.lawranta.popups.Preferences;

public class GLOBAL {
	public static final double VERSION=1.12;
	public static final String VERSION_NAME="Public Alpha";
	public static int GRIDWIDTH=(int) (32*Zoom.factor), GRIDHEIGHT=(int) (32*Zoom.factor),CANVAS_WIDTH=1024, CANVAS_HEIGHT=1024,  OFFSETX=0, OFFSETY=0, DEFAULTGRIDSIZE;
	public static CanvasPanel CP;
	public static Font toolFont=new Font("Monospaced", Font.PLAIN, 12);
	public static final String EXTENSION=".tan";
	public static final Image FAVICO = Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/images/favicon.png"));
	public static FilePathDialog filePathDialog;
	public static ExportPathDialog exportPathDialog;
	public static Preferences preferences;
	public static Debug DEBUGFRAME;
	public static FileInfo fileInfo;
	public static JFrame MAINFRAME;
	public static int origWidth=1024;
	public static int origHeight=768;
	public static Color darkColor=new Color(69,69,69), lightColor=new Color(200,200,200), gridColor, gridBGColor, bgColor;
	public static ColorChooser CC = new ColorChooser();
	public static Menu MENU;
	public static boolean ctrlPressed=false;
	
	//paths
	public static String cfgPath= "./global.cfg";
	public static String moveIconPath="/images/move.png";
	public static String exitIconPath="/images/exit.png";
	public static String clearIconPath="/images/clear.png";
	public static String openEyeIconPath="/images/openEye.png";
	public static String closedEyeIconPath="/images/closedEye.png";
	public static String trashEyeIconPath="/images/trash.png";
	public static String greyTrashIconPath="/images/greyTrash.png";
	public static String eyeDropperPath="/images/eyeDropperCursor.png";
	public static void setDefault(){
		GRIDHEIGHT=32;
		GRIDWIDTH=32;
		CANVAS_HEIGHT=CanvasPanel.canvasHeightDefault;
		CANVAS_WIDTH=CanvasPanel.canvasWidthDefault;
		OFFSETX=0;
		OFFSETY=0;
		CanvasPanel.hideGrid = false;
		fileInfo=new FileInfo();
		GLOBAL.updateTitle() ;

		
	}
	
	
	public static void updateTitle() {
		
		
		MAINFRAME.setTitle("LawrLeveler (" + GLOBAL.fileInfo.getFileName() + ")");
	}
	
	
}


