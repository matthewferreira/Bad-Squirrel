package com.mycompany.myapp;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;

public class MapView extends Container implements Observer{
	
	public MapView(Observable myModel) {
		myModel.addObserver(this);
	}
	
	public MapView() {}
	
	public void update(Observable o, Object arg) {
		// code here to call the method in GameWorld (Observable) that output the
		// game object information to the console 
		
	}
}
