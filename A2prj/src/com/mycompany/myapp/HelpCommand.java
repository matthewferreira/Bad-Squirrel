package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command{

	GameWorld gameWorld;
	public HelpCommand() {
		super("Help");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		String message = "KeyBinds:\n 'a' - Accelerate the Player Squirrel\n 'b' - Apply brakes\n 'l' - Turn Player Squirrel left\n 'r' - Turn Player Squirrel right\n 'e' - Collide with Tomato\n 'g' - Collide with Bird\n 't' - increase game clock\n";
		Dialog.show("Help",  message, "ok", null);
	}
}