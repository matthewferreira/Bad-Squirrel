package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TickCommand extends Command{
	GameWorld gameWorld;
	public TickCommand(GameWorld gw) {
		super("Tick");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gameWorld.tick();
	}
}
