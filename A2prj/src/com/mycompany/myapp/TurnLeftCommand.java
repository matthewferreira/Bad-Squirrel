package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TurnLeftCommand extends Command{
	GameWorld gameWorld;
	public TurnLeftCommand(GameWorld gw) {
		super("Turn Left");
		gameWorld = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Turning Player Left");
		gameWorld.turnPlayerLeft();
	}

}
