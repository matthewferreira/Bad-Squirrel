package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PlayPauseCommand extends Command{
	public Game game;
	public PlayPauseCommand(Game g) {
		super("Pause");
		game = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		game.toggleMode();
	}
}
