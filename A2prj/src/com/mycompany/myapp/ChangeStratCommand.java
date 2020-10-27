package com.mycompany.myapp;

import java.util.ArrayList;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ChangeStratCommand extends Command{
	GameWorld target;
	
	public ChangeStratCommand(GameWorld gw) {
		super("Change Strategies");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		//gets all NPCs, calls switch strategy function on each
		ArrayList<GameObject> npcs = target.getObjsOfType("NPC");
		for(int i = 0; i < npcs.size(); i ++) {
			if(npcs.get(i) instanceof NonPlayerSquirrel) {
				NonPlayerSquirrel npc = (NonPlayerSquirrel)npcs.get(i);
				npc.switchStrategy();
				int lastNut = npc.getLastNut();
				lastNut++;
				npc.setLastNut(lastNut);
			}
		}
		System.out.println("Changed NPC Strategies");
	}
}
