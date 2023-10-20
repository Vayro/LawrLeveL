package com.lawranta.edit;

import java.awt.Color;
import java.util.Stack;

import com.lawranta.canvas.InkDrop;
import com.lawranta.canvas.TextNode;
import com.lawranta.canvas.Zoom;
import com.lawranta.panels.CanvasPanel;

public class DoListStack {
	public static Stack<DoListItem> undoList = new Stack<DoListItem>();
	public static Stack<DoListItem> redoList = new Stack<DoListItem>();

	public DoListStack() {
		// TODO Auto-generated constructor stub
	}

	public class UndoListStack {
		// TODO Auto-generated constructor stub

		public void undoListStack() {
			// TODO Auto-generated constructor stub
		}

		public void AddToStack(DoListItem e) {
			// TODO Auto-generated constructor stub
			undoList.add(e);
		}

		public static void PrintStack() {
			// TODO Auto-generated constructor stub
			System.out.println(undoList.toString());
		}

		public static void undo() {

			if (!undoList.isEmpty()) {
				PrintStack();
				DoListItem action = undoList.pop();

				RedoListStack.PrintStack();

				// check what was just popped (inkdrop, textnode, or neither)

				// actions accepted are inkCreated, nodeCreated, inkDeleted, nodeDeleted

				switch (action.action) {
				case "inkCreated":
					// remove created inknode
					System.out.println("undo ink create");

					action.inkDrop.destroy(false);
					RedoListStack.AddToStack(action);
					break;

				case "inkDeleted":
					// recreate inknode
					System.out.println("undo ink delete");

					InkDrop rebuilt = rebuildInkDrop(action.getInkDrop());
					action.setInkDrop(rebuilt);
					RedoListStack.AddToStack(action);
					break;

				case "nodeCreated":
					// remove created textNode
					System.out.println("undo text create");

					action.textNode.destroy(false);
					RedoListStack.AddToStack(action);
					break;
				case "nodeDeleted":
					// recreate textNode
					System.out.println("undo node delete");

					TextNode node = rebuildTextNode(action.getTextNode());
					action.setTextNode(node);
					RedoListStack.AddToStack(action);
					break;

				default:
					// do nothing
					System.out.println("Nothing to undo");
					break;

				}
			} else {

				System.out.println("Nothing to undo");
			}

		}

	}

	public class RedoListStack {

		public void undoListStack() {
			// TODO Auto-generated constructor stub
		}

		public static void AddToStack(DoListItem e) {
			// TODO Auto-generated constructor stub
			redoList.add(e);
		}

		public static void PrintStack() {
			// TODO Auto-generated constructor stub
			System.out.println("redolist: " + redoList.toString());
		}

		public static void redo() {

			if (!redoList.isEmpty()) {
				PrintStack();
				DoListItem action = redoList.pop();
				System.out.println("popped: " + action);

				switch (action.action) {
				case "inkCreated": {
					// re-create removed inknode
					System.out.println("redo ink create");

					InkDrop drop = rebuildInkDrop(action.getInkDrop());
					DoListItem item = new DoListItem("inkCreated", drop);
					
					
					
					
					drop.setX((int) (drop.getUnscaledX() * Zoom.factor));
					drop.setY((int) (drop.getUnscaledY() * Zoom.factor));
					drop.setxSize((int) (drop.getxSize() * Zoom.factor));
					drop.setySize((int) (drop.getySize() * Zoom.factor));
					drop.draw();
					
					
					
					break;
				}
				case "inkDeleted": {
					// re-remove created inknode
					System.out.println("undo ink delete");
					DoListItem item = new DoListItem("inkDeleted", action.getInkDrop());
					action.inkDrop.destroy(false);

					break;
				}

				case "nodeCreated": {
					// remove created textNode
					System.out.println("undo text create");

					TextNode node = rebuildTextNode(action.getTextNode());
					DoListItem item = new DoListItem("nodeCreated", node);			
					break;
				}
				case "nodeDeleted": {
					// redo delete textNode
					System.out.println("undo text create");
					DoListItem item = new DoListItem("inkDeleted", action.getTextNode());
					action.textNode.destroy(false);
					break;
				}
				default:
					// do nothing
					System.out.println("Nothing to redo nigga");
					break;

				}
			} else {

				System.out.println("Nothing to undo");
			}

		}

	}

	public static InkDrop rebuildInkDrop(InkDrop inkDrop) {
		// TODO Auto-generated method stub
		int x = inkDrop.getX();
		int y = inkDrop.getY();
		int ySize = inkDrop.getySize();
		int xSize = inkDrop.getxSize();
		int offsetX = inkDrop.getOffsetX();
		int offsetY = inkDrop.getOffsetY();
		int id = inkDrop.getId();
		Color color = inkDrop.getColor();
		InkDrop kkk =

				new InkDrop(x, y, ySize, xSize, offsetX, offsetY, color);

		CanvasPanel.canvasContainer.add(kkk);
		CanvasPanel.contentPanel.add(kkk, 1, 0);
		return kkk;
	}

	public static TextNode rebuildTextNode(TextNode textNode) {
		// TODO Auto-generated method stub
		int x = textNode.getX();
		int y = textNode.getY();
		String s = textNode.getText();
		TextNode node = new TextNode(x, y);
		node.setText(s);
		node.setVisible(true);
		CanvasPanel.contentPanel.add(node, 2, 0);
		CanvasPanel.canvasContainer.add(node);
		return node;
	}

}
