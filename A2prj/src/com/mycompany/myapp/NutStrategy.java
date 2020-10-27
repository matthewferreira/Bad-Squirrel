package com.mycompany.myapp;

import com.codename1.util.MathUtil;

public class NutStrategy implements IStrategy {
	
	public void apply(NonPlayerSquirrel npc) {
		// this code uses predetermined Nut locations since it cant access gameworld objects
		int lastNut = npc.getLastNut();
		int beta = 0;
		int betaDegrees;
		int npcX = (int)npc.getLocation().getX();
		int npcY = (int)npc.getLocation().getY();
		if(lastNut == 0) {
			// set course for first nut
			beta = (int)MathUtil.atan2(200-npcY, 50-npcX);
		}
		else if(lastNut == 1) {
			//set course for nut 2
			beta = (int)MathUtil.atan2(275-npcY, 200-npcX);
		}
		else if(lastNut == 2) {
			//set course for nut 3
			beta = (int)MathUtil.atan2(500-npcY, 500-npcX);
		}
		else if(lastNut == 3) {
			//set course for nut 4
			beta = (int)MathUtil.atan2(750-npcY, 750-npcX);
		}
		else {
			lastNut = 0;
			beta = (int)MathUtil.atan2(200-npcY, 50-npcX);
		}
		betaDegrees = (int) Math.toDegrees(beta);
		npc.setHeading(90-betaDegrees);
	}
}
