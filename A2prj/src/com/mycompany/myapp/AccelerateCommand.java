package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AccelerateCommand extends Command{
	GameWorld gameWorld;
	public AccelerateCommand(GameWorld gw) {
		super("Accelerate");
		gameWorld = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(Game.getMode()) {
			System.out.println("Accelerating");
			gameWorld.accelerate();
		}
	}
}
