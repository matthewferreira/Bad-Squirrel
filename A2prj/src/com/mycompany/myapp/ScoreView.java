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
	
	public ScoreView(Observable myModel) {
		myModel.addObserver(this);
	}
	
	public ScoreView() {
		livesRemainingLabel = new Label("Lives Remaining: ");
		this.add(livesRemainingLabel);
		gameClockLabel = new Label("Game clock: ");
		this.add(gameClockLabel);
		lastNutLabel = new Label("Last Nut: ");
		this.add(lastNutLabel);
		energyLabel = new Label("Energy: ");
		this.add(energyLabel);
		damageLabel = new Label("Damage Level: ");
		this.add(damageLabel);
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
		this.revalidate();
		
	}
}
