package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideCommand extends Command{
	GameWorld gameWorld;
	GameObject collideWith;
	public CollideCommand(GameWorld gw, String type) {
		super("Brake");
		gameWorld = gw;
		collideWith = gameWorld.getRandomObjOfType(type);
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Colliding player with object of type " + collideWith.getClass());
		gameWorld.collidePlayer(collideWith);
	}
}
