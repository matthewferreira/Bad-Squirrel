package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PositionCommand extends Command{
	public GameWorld gw;
	private static boolean active = false;
	public PositionCommand(GameWorld g) {
		super("Position");
		gw = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {

		if(!Game.getMode()) {
			toggleActive();
		}
	}
	
	public static boolean isActive() {
		return active;
	}
	public static void toggleActive() {
		active = !active;
	}
}
