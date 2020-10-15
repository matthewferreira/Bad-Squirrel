package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command{
	GameWorld gameWorld;
	public SoundCommand(GameWorld gw) {
		super("Toggle Sound");
		gameWorld = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Toggling Sound");
		gameWorld.toggleSound();
	}
}
