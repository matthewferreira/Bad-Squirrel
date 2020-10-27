package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class NutCollideCommand extends Command{
	GameWorld gameWorld;
	GameObject collideWith;
	public NutCollideCommand(GameWorld gw) {
		super("Collide with Nut");
		gameWorld = gw;
		
	}
	// not sure how to get text input from user with a textfield, colliding with random nuts instead
	@Override
	public void actionPerformed(ActionEvent ev) {
		collideWith = gameWorld.getRandomObjOfType("Nut");
		System.out.println("Colliding player with Nut");
		gameWorld.collidePlayer(collideWith);
	}
}