package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BirdCollideCommand extends Command{
	GameWorld gameWorld;
	GameObject collideWith;
	public BirdCollideCommand(GameWorld gw) {
		super("Collide with Bird");
		gameWorld = gw;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		collideWith = gameWorld.getRandomObjOfType("Bird");
		System.out.println("Colliding player with Bird");
		gameWorld.collidePlayer(collideWith);
	}
}
