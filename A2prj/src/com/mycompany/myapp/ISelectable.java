package com.mycompany.myapp;

import com.codename1.charts.models.Point;

public interface ISelectable {
	
	boolean isSelected();
	
	void setSelected(boolean b);
	
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	// a way to draw the object that knows about drawing
	// different ways depending on isSelected
	//public void draw(Graphics g, Point pCmpRelPrnt);
}
