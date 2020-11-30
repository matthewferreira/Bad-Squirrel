package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BrakeCommand extends Command{
	
	GameWorld gameWorld;
	public BrakeCommand(GameWorld gw) {
		super("Brake");
		gameWorld = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(Game.getMode()) {
			System.out.println("Applying Brakes");
			gameWorld.brake();
		}

	}

}
