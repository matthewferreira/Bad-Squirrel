package com.mycompany.myapp;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class PlayerSquirrel extends Squirrel{
	//singleton pattern implementation
	private static PlayerSquirrel playerSquirrel;
	
	private PlayerSquirrel(float x, float y) { super(x, y); }
	
	public static PlayerSquirrel getPlayerSquirrel(float x, float y) {
		if(playerSquirrel == null) {
			if (x < 0 || x > 1000){
				System.out.println("Invalid X bound for Player");
			}
			else if (y < 0 || y > 1000){
				System.out.println("Invalid y bound for Player");
			}
			else { playerSquirrel = new PlayerSquirrel(x, y); }
		}
		return playerSquirrel;
	}
	public static PlayerSquirrel getPlayerSquirrel() {
		if(playerSquirrel == null) {
			System.out.println("PlayerSquirrel not instantiated");
		}
		return playerSquirrel;
	}
	//resets player to null
	public static void reset() {
		playerSquirrel = null;
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) pCmpRelPrnt.getX();
		int y = (int) pCmpRelPrnt.getY();
		g.setColor(ColorUtil.BLACK);
		g.drawRect(x + (int)this.getLocation().getX(), y + (int)this.getLocation().getY(), this.getSize(), this.getSize());
		g.setColor(this.getColor());
		g.fillRect(x + (int)this.getLocation().getX(), y + (int)this.getLocation().getY(), this.getSize(), this.getSize());
	}

public void collide(GameObject g) {
		
		int dmgLvl = this.getDamageLevel();
		int maxSpd = this.getMaximumSpeed();
		int lnr = this.getLastNut();
		int enerLvl = this.getEnergyLevel();
		System.out.println("DAMAGE: " + dmgLvl);
		System.out.println("MaxSpd: " + maxSpd);
		System.out.println("LNR: " + lnr);
		System.out.println("ENERGY: " + enerLvl);
		//if collides with bird, dmgLevel + 1, set new max speed, change speed, fade color
		if(g instanceof Bird) {
			dmgLvl += 1;
			this.setDamageLevel(dmgLvl);
			
			maxSpd = (int) (maxSpd*(1-dmgLvl*0.01));
			if(super.getSpeed() > getMaximumSpeed()) {
				this.setSpeed(maxSpd);
			}
			fadeColor();
		}
		//if collides with squirrel, dmgLevel + 2, set new max speed, change speed, fade color
		else if(g instanceof Squirrel) {
			dmgLvl += 2;
			this.setDamageLevel(dmgLvl);
			maxSpd = (int) (maxSpd*(1-dmgLvl*0.01));
			this.setMaximumSpeed(maxSpd);
			if(super.getSpeed() > getMaximumSpeed()) {
				this.setSpeed(maxSpd);
			}
			fadeColor();
		}
		//if collides with nut, check if nut is in order, check if nut is last nut and end game
		else if(g instanceof Nut) {
			Nut nut = (Nut)g;
			
			if(lnr + 1 == nut.getSeqNum()) {
				lnr = nut.getSeqNum();
				this.setLastNut(lnr);
			}
			if(lnr == Nut.getObjCount()) {
				GameWorld.youWin();
			}
		}
		//if collides with tomato, energyLevel + nutrition of tomato
		else if(g instanceof Tomato) {
			enerLvl += ((Tomato) g).getNutrition();
			this.setEnergyLevel(enerLvl);
		}
	}
}
