package com.mycompany.myapp;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer{
	private GameWorld gw;
	
	public MapView(Observable myModel) {
		myModel.addObserver(this);
	}
	
	public MapView() {
		Label mvLabel = new Label("This is the map view");
		this.getUnselectedStyle().setBorder(Border.createLineBorder(4, 0xFF0000));
		this.add(mvLabel);
	}
	
	public void update(Observable o, Object arg) {
		// code here to call the method in GameWorld (Observable) that output the
		// game object information to the console 
		GameWorld x = (GameWorld)o;
		gw = x;
		int width = this.getWidth();
		int height = this.getHeight();
		x.setSize(width, height);
		x.printMap();
		//this.repaint();
	}
	/*
	private void paint() {
		IIterator gameObjects = gw.getIterator();
		while(gameObjects.hasNext()) {
			GameObject obj = gameObjects.getNext();
			obj.draw();
		}
	}*/
	
}
