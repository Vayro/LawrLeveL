package com.lawranta.edit;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import com.lawranta.canvas.InkDrop;
import com.lawranta.canvas.TextNode;
import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;

public class Debug extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7009336912835329101L;
	private JPanel contentPane;
	JTableHeader header;
	JTable table;
	JScrollPane scroll;
	Object[][] data;
	String[] headers={"CC Index","ID","X","Y","Type"};;

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
		setBounds(100, 100, 450, 300);
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

		getContentPane().add(scroll);

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
	//	System.out.println("refreshed deubugger");

	}

}
