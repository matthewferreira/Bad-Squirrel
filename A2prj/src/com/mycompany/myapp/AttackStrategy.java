package com.mycompany.myapp;

import com.codename1.util.MathUtil;

public class AttackStrategy implements IStrategy{
	//public void apply() {}
	public void apply(NonPlayerSquirrel npc) {
		//code here
		int beta = 0;
		int betaDegrees = 0; 
		//gets player and npc locations
		int playerX = (int) PlayerSquirrel.getPlayerSquirrel().getLocation().getX();
		int playerY = (int) PlayerSquirrel.getPlayerSquirrel().getLocation().getY();
		int npcX = (int) npc.getLocation().getX();
		int npcY = (int) npc.getLocation().getY();
		
		//compute angle towards player squirrel
		beta = (int)MathUtil.atan2(playerY-npcY, playerX-npcX);
		betaDegrees = (int) Math.toDegrees(beta);
		
		//set heading towards player
		npc.setHeading(90-betaDegrees);
	}
}
