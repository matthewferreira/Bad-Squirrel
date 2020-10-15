package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TickCommand extends Command{
	GameWorld target;
	public TickCommand(GameWorld gw) {
		super("Tick");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.tick();
	}
}
