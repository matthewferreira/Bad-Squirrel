package com.mycompany.myapp;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class NonPlayerSquirrel extends Squirrel{
	
	private IStrategy curStrategy;
	
	//set strategy of npc
	public void setStrategy(IStrategy s) {
		this.curStrategy = s;
	}
	
	//switch to other npc strategy
	public void switchStrategy() {
		if(curStrategy instanceof NutStrategy) {
			this.setStrategy(new AttackStrategy());
		}
		else {
			this.setStrategy(new NutStrategy());
		}
	}
	
	//invoke npc strategy
	public void invokeStrategy() {
		curStrategy.apply(this);
	}
	
	// constructor sets random strategy
	public NonPlayerSquirrel() {
		super();
		Random rand = new Random();
		int randomStrat = rand.nextInt() % 2;
		if(randomStrat == 0) {
			this.setStrategy(new NutStrategy());
		}
		else {
			this.setStrategy(new AttackStrategy());
		}
		this.invokeStrategy();
	}
	
	//overridden move method to invoke strategy every time it moves
	@Override
	public void move(int elapsedTime) {
		this.invokeStrategy();
		if(this.getEnergyLevel() > 0 && this.getDamageLevel() < this.getMaxDamage()) {
			int head = this.getHeading();
			int deltaX = (int) (Math.cos(Math.toRadians(90 - head))*(super.getSpeed() + elapsedTime/5));
			int deltaY = (int) (Math.sin(Math.toRadians(90 - head))*(super.getSpeed() + elapsedTime/5));
			
			int newX = (int) (super.getLocation().getX() + deltaX);
			int newY = (int) (super.getLocation().getY() + deltaY);
			
			//keeping squirrel within 0 and 1000 boundaries
			if(newX > GameWorld.getSize()[0]) { newX = GameWorld.getSize()[0]; }
			else if(newX < 0) { newX = 0; }
			if(newY > GameWorld.getSize()[1]) { newY = GameWorld.getSize()[1]; }
			else if(newY < 0) { newY = 0; }
			
			super.setLocation(newX, newY);
		}
	}
	
	public void collide(GameObject g) {
		
		int dmgLvl = this.getDamageLevel();
		int maxSpd = this.getMaximumSpeed();
		int lnr = this.getLastNut();
		int enerLvl = this.getEnergyLevel();
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
				GameWorld.npcWin();
			}
		}
		//if collides with tomato, energyLevel + nutrition of tomato
		else if(g instanceof Tomato) {
			enerLvl += ((Tomato) g).getNutrition();
			this.setEnergyLevel(enerLvl);
		}
	}

	@Override
	public String toString() {
		return "NPSquirrel: loc=" + this.getLocation().getX() + ", " + this.getLocation().getY() + " color=" + this.printColor() +
				" heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size=" + this.getSize() + " maxSpeed=" + this.getMaximumSpeed() + 
				" steeringDirection=" + this.getSteeringDirection() + " energyLevel=" + this.getEnergyLevel() + " damageLevel=" + this.getDamageLevel() + 
				" LastNut=" + this.getLastNut() + " Strategy=" + this.curStrategy;
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) pCmpRelPrnt.getX();
		int y = (int) pCmpRelPrnt.getY();
		g.setColor(this.getColor());
		g.drawRect(x + (int)this.getLocation().getX(), y + (int)this.getLocation().getY(), this.getSize(), this.getSize());
		
	}
	/* 
	public boolean collidesWith(GameObject obj) {
		return false;
	}
	
	public void handleCollision(GameObject otherObject) {
		this.collide(otherObject);
		this.getCollVec().add(otherObject);
	}*/
	
}
