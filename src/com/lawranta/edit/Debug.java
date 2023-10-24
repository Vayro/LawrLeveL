package com.lawranta.edit;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.KeyboardFocusManager;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import com.lawranta.canvas.InkDrop;
import com.lawranta.canvas.TextNode;
import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;
import javax.swing.JTextPane;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;

public class Debug extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7009336912835329101L;
	private JPanel contentPane;
	JTableHeader header;
	JTable table;
	JScrollPane scroll, scroll2;
	Object[][] data;
	String[] headers = { "CC Index", "ID", "X", "Y", "Type" };
	private JTextArea textPane;;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Debug frame = new Debug();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Debug() {
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setIconImage(GLOBAL.FAVICO);
		setContentPane(contentPane);

		data = new Object[CanvasPanel.canvasContainer.size()][5];

		for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {

			if (CanvasPanel.canvasContainer.get(i).getClass() == InkDrop.class) {

				data[i][0] = i;
				data[i][1] = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getId();
				data[i][2] = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getX();
				data[i][3] = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getY();
				data[i][4] = "InkDrop";

				System.out.println("loaded " + i + 1 + " lines");
			}

			else if (CanvasPanel.canvasContainer.get(i).getClass() == TextNode.class) {

				data[i][0] = i;
				data[i][1] = ((TextNode) CanvasPanel.canvasContainer.get(i)).getId();
				data[i][2] = ((TextNode) CanvasPanel.canvasContainer.get(i)).getX();
				data[i][3] = ((TextNode) CanvasPanel.canvasContainer.get(i)).getY();
				data[i][4] = "TextNode";

			}

		}

		scroll = new JScrollPane();
		scroll.setMinimumSize(new Dimension(450, 200));
		scroll.setPreferredSize(new Dimension(450, 200));
		scroll2 = new JScrollPane();
		
		scroll2.setBorder(new TitledBorder(null, "Focus:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scroll2.setMinimumSize(new Dimension(450, 64));
		scroll2.setPreferredSize(new Dimension(450, 64));
		table = new JTable(data, headers) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7087360881226226551L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		};

		header = table.getTableHeader();
		getContentPane().setLayout(new BorderLayout(0, 0));

		scroll.setViewportView(table);


		contentPane.add(scroll, BorderLayout.NORTH);

		textPane = new JTextArea();
		textPane.setLineWrap(true);
		textPane.setRows(2);
		textPane.setMinimumSize(new Dimension(128, 128));
		contentPane.add(scroll2, BorderLayout.CENTER);

		scroll2.setViewportView(textPane);

		setVisible(true);


		 

		
		
		
		
		
		
	}

	public void refresh() {
		// TODO Auto-generated method stub
		data = new Object[CanvasPanel.canvasContainer.size()][5];

		for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {

			if (CanvasPanel.canvasContainer.get(i).getClass() == InkDrop.class) {

				data[i][0] = i;
				data[i][1] = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getId();
				data[i][2] = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getX();
				data[i][3] = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getY();
				data[i][4] = "InkDrop";

			}

			else if (CanvasPanel.canvasContainer.get(i).getClass() == TextNode.class) {

				data[i][0] = i;
				data[i][1] = ((TextNode) CanvasPanel.canvasContainer.get(i)).getId();
				data[i][2] = ((TextNode) CanvasPanel.canvasContainer.get(i)).getX();
				data[i][3] = ((TextNode) CanvasPanel.canvasContainer.get(i)).getY();
				data[i][4] = "TextNode";

			}

		}
		table = new JTable(data, headers) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7087360881226226551L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		};
		scroll.setViewportView(table);
		textPane.setText(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner().toString()

		);
		textPane.setCaretPosition(0);
		// System.out.println("refreshed deubugger");

	}
	
	
	

}
