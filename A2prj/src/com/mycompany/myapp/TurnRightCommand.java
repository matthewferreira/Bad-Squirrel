package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TurnRightCommand extends Command{
	GameWorld gameWorld;
	
	public TurnRightCommand(GameWorld gw) {
		super("Turn Right");
		gameWorld = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Turning Player Right");
		gameWorld.turnPlayerRight();
	}

}
