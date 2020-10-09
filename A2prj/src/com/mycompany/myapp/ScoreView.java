package com.mycompany.myapp;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;

public class ScoreView extends Container implements Observer{
	
	public ScoreView(Observable myModel) {
		myModel.addObserver(this);
	}
	
	public ScoreView() {}
	
	public void update(Observable o, Object arg) {
		// code here to call the method in GameWorld (Observable) that output the
		// game object information to the console 
	}
}
