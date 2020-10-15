package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class NpcCollideCommand extends Command{
	GameWorld gameWorld;
	GameObject collideWith;
	public NpcCollideCommand(GameWorld gw) {
		super("Collide with NPC");
		gameWorld = gw;
		collideWith = gameWorld.getRandomObjOfType("NPC");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Colliding player with NPC");
		gameWorld.collidePlayer(collideWith);
	}
}
