package com.mycompany.myapp;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer{
	
	public MapView(Observable myModel) {
		myModel.addObserver(this);
	}
	
	public MapView() {
		this.setWidth(1000);
		this.setHeight(1000);
		Label mvLabel = new Label("This is the map view");
		this.getUnselectedStyle().setBorder(Border.createLineBorder(4, 0xFF0000));
		this.add(mvLabel);
	}
	
	public void update(Observable o, Object arg) {
		// code here to call the method in GameWorld (Observable) that output the
		// game object information to the console 
		GameWorld x = (GameWorld)o;
		x.printMap();
	}
}
