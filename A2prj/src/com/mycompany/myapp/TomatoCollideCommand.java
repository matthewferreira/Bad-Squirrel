package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TomatoCollideCommand extends Command{
	GameWorld gameWorld;
	GameObject collideWith;
	public TomatoCollideCommand(GameWorld gw) {
		super("Collide with Tomato");
		gameWorld = gw;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		collideWith = gameWorld.getRandomObjOfType("Tomato");
		System.out.println("Colliding player with Tomato");
		gameWorld.collidePlayer(collideWith);
	}
}