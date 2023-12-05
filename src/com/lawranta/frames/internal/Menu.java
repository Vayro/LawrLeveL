package com.lawranta.frames.internal;

import java.awt.Color;
import java.awt.EventQueue;

import com.lawranta.canvas.Paint;
import com.lawranta.edit.DoListStack;
import com.lawranta.file.ExportPreDialog;
import com.lawranta.file.Open;
import com.lawranta.file.Save;
import com.lawranta.globals.GLOBAL;
import com.lawranta.help.About;
import com.lawranta.initializer.MainClass;
import com.lawranta.panels.CanvasPanel;

import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.border.BevelBorder;

public class Menu extends JMenuBar implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JMenu fileMenu, editMenu, toolsMenu, helpMenu;
	ActionListener fileAction, editAction,  helpAction, toolsAction;
	@SuppressWarnings("unused")
	private JMenuItem AboutMenuItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(GLOBAL.darkColor);

		g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

	}



	@SuppressWarnings("unused")
	public Menu() {
		setForeground(new Color(255, 255, 204));
		// setForeground(new Color(255, 255, 255));
		setBackground(new Color(69, 69, 69));
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setOpaque(false);
		setFocusable(true);
		fileMenu = new JMenu("File");
		fileMenu.setBackground(new Color(69, 69, 69));
		fileAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				switch (e.getActionCommand()) {
				case "save":
					Save.save();
					break;
				case "saveAs":
					Save.saveAs();
					break;
				case "open":
					Open.open();
					break;
				case "new":

					newCanvas();

					break;
				case "export":
					JDialog j = new ExportPreDialog();
					j.setVisible(true);
					break;
				case "exit":
					System.exit(0);
					break;
				default:

					break;

				}
			}
		};

		editAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				switch (e.getActionCommand()) {

				case "undo":
					DoListStack.UndoListStack.undo();
					break;
				case "redo":
					DoListStack.RedoListStack.redo();
					break;
				case "debug":

					GLOBAL.DEBUGFRAME.setVisible(true);
					break;
				case "preferences":
					GLOBAL.preferences.setVisible(true);
					break;

				default:

					break;

				}
			}
		};

		toolsAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				switch (e.getActionCommand()) {
				case "inkdrop":
					Toolbar.setInkDrop();

					break;
				case "eraser":
					Toolbar.setEraser();

					break;
				case "text":
					Toolbar.setText();

					break;
				default:

					break;

				}
			}
		};

		helpAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				switch (e.getActionCommand()) {
				case "about":

					About about = new About();
					about.setVisible(true);
					break;

				default:

					break;

				}
			}
		};

		add(fileMenu);

		FileMenuItem newMenuItem = new FileMenuItem("New (ctrl+n)", "new");

		FileMenuItem openMenuItem = new FileMenuItem("Open (ctrl+O)", "open");

		FileMenuItem SaveMenuItem = new FileMenuItem("Save (ctrl+S)", "save");

		FileMenuItem SaveAsMenuItem = new FileMenuItem("Save As...", "saveAs");
		FileMenuItem ExportMenuItem = new FileMenuItem("Export as PNG", "export");

		JMenuItem refreshMenuItem = new JMenuItem("Refresh");
		fileMenu.add(refreshMenuItem);

		FileMenuItem exitMenuItem = new FileMenuItem("Exit :(", "exit");

		editMenu = new JMenu("Edit");
		add(editMenu);

		EditMenuItem undoMenuItem = new EditMenuItem("Undo (ctrl+Z)", "undo");
		editMenu.add(undoMenuItem);

		EditMenuItem redoMenuItem = new EditMenuItem("Redo (ctrl+Y)", "redo");
		editMenu.add(redoMenuItem);

		EditMenuItem debugMenuItem = new EditMenuItem("Debug", "debug");
		editMenu.add(debugMenuItem);

		EditMenuItem preferencesMenuItem = new EditMenuItem("Preferences", "preferences");
		editMenu.add(preferencesMenuItem);

		toolsMenu = new JMenu("Tools");
		add(toolsMenu);
		ToolsMenuItem BrushMenuItem = new ToolsMenuItem("Brush (ctrl+B)", "inkdrop");
		toolsMenu.add(BrushMenuItem);
		ToolsMenuItem EraserMenuItem = new ToolsMenuItem("Eraser (ctrl+E)", "eraser");
		toolsMenu.add(EraserMenuItem);
		ToolsMenuItem TextMenuItem = new ToolsMenuItem("Text Tool (ctrl+T)", "text");
		toolsMenu.add(TextMenuItem);


		
		
		helpMenu = new JMenu("Help");
		add(helpMenu);

		HelpMenuItem AboutMenuItem = new HelpMenuItem("About", "about");
		helpMenu.add(AboutMenuItem);

		// shortcuts
		{
			addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					switch (e.getKeyCode()) {
					case 90:
						// undo
						DoListStack.UndoListStack.undo();
						break;
					case 89:
						// redo
						DoListStack.RedoListStack.redo();
						break;

					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					if (e.getKeyCode() == 17) {
						GLOBAL.ctrlPressed = false;
					}
				}

			});

		}

	}
	

	public static void newCanvas() {
		/* TODO Auto-generated method stub
		CanvasPanel.canvasContainer.clear();
		CanvasPanel.contentPanel.removeAll();
		GLOBAL.setDefault();
		CanvasPanel.setCanvasSize();*/
		// actually let's just re-create the mainframe instead of just the panel...
		CanvasPanel.canvasContainer = new ArrayList<Paint>();
		Point locationholder = GLOBAL.MAINFRAME.getLocation();
		GLOBAL.MAINFRAME.dispose();
		GLOBAL.DEBUGFRAME.dispose();
		try {
			MainClass.main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GLOBAL.MAINFRAME.setLocation(locationholder);
	/*	GLOBAL.MAINFRAME = new MainFrame();
		GLOBAL.MAINFRAME.setLocation(locationholder);
		GLOBAL.MAINFRAME.setVisible(true);*/

	}

	public class FileMenuItem extends JMenuItem {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FileMenuItem(String text, String command) {
			setText(text);
			setActionCommand(command);
			addActionListener(fileAction);
			fileMenu.add(this);

		}
	}

	public class EditMenuItem extends JMenuItem {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public EditMenuItem(String text, String command) {
			setText(text);
			setActionCommand(command);
			addActionListener(editAction);
			editMenu.add(this);

		}
	}

	public class HelpMenuItem extends JMenuItem {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public HelpMenuItem(String text, String command) {
			setText(text);
			setActionCommand(command);
			addActionListener(helpAction);
			helpMenu.add(this);

		}
	}

	public class ToolsMenuItem extends JMenuItem {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ToolsMenuItem(String text, String command) {
			setText(text);
			setActionCommand(command);
			addActionListener(toolsAction);
			toolsMenu.add(this);

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
