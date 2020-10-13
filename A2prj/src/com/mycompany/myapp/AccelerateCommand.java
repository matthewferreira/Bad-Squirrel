package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AccelerateCommand extends Command{
	GameWorld gameWorld;
	public AccelerateCommand(GameWorld gw) {
		super("Brake");
		gameWorld = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Applying Brakes");
		gameWorld.accelerate();
	}
}
