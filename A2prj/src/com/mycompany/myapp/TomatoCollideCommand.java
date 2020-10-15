package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TomatoCollideCommand extends Command{
	GameWorld gameWorld;
	GameObject collideWith;
	public TomatoCollideCommand(GameWorld gw) {
		super("Collide with Tomato");
		gameWorld = gw;
		collideWith = gameWorld.getRandomObjOfType("Tomato");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Colliding player with Tomato");
		gameWorld.collidePlayer(collideWith);
	}
}