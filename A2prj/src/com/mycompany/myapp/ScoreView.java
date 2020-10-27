package com.mycompany.myapp;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class ScoreView extends Container implements Observer{
	private Label livesRemainingLabel;
	private Label gameClockLabel;
	private Label lastNutLabel;
	private Label energyLabel;
	private Label damageLabel;
	private Label soundLabel;
	
	public ScoreView(Observable myModel) {
		myModel.addObserver(this);
	}
	
	public ScoreView() {
		livesRemainingLabel = new Label("Lives Remaining: 3");
		this.add(livesRemainingLabel);
		gameClockLabel = new Label("Game clock: 0");
		this.add(gameClockLabel);
		lastNutLabel = new Label("Last Nut: 0");
		this.add(lastNutLabel);
		energyLabel = new Label("Energy: 100");
		this.add(energyLabel);
		damageLabel = new Label("Damage Level: 0");
		this.add(damageLabel);
		soundLabel = new Label("Sound: OFF");
	}
	
	public void update(Observable o, Object arg) {
		// code here to call the method in GameWorld (Observable) that output the
		// game object information to the console 
		GameWorld x = (GameWorld)o;
		int[] stats = x.display();
		System.out.println("DISPLAY STATS");
		livesRemainingLabel.setText("Lives Remaining: " + stats[0]);		
		gameClockLabel.setText("Game clock: " + stats[1]);
		lastNutLabel.setText("Last Nut: " + stats[2]);
		energyLabel.setText("Energy: " + stats[3]);
		damageLabel.setText("Damage Level: " + stats[4]);
		if(stats[5] == 1) {
			soundLabel.setText("Sound: ON");
		}
		else {
			soundLabel.setText("SOUND: OFF");
		}
		
		this.revalidate();
		
	}
}
