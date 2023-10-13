package com.lawranta.edit;

import java.util.Stack;

public class DoListStack {

	public DoListStack() {
		// TODO Auto-generated constructor stub
	}
	
	public class UndoListStack {
		// TODO Auto-generated constructor stub
		static Stack<Object> undoList = new Stack<Object>();
		
		public void undoListStack() {
			// TODO Auto-generated constructor stub
		}
		
		public void AddToStack(Object e) {
			// TODO Auto-generated constructor stub
			undoList.add(e);
		}
		
		public void PrintStack() {
			// TODO Auto-generated constructor stub
			System.out.println(undoList.toString());
		}
	
		
		
		
	}
	
	
	
	

}
