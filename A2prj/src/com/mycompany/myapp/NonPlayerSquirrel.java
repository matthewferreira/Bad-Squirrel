package com.mycompany.myapp;

import java.util.Random;

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
	public void move() {
		this.invokeStrategy();
		if(this.getEnergyLevel() > 0 && this.getDamageLevel() < this.getMaxDamage()) {
			int head = this.getHeading();
			int deltaX = (int) (Math.cos(Math.toRadians(90 - head))*super.getSpeed());
			int deltaY = (int) (Math.sin(Math.toRadians(90 - head))*super.getSpeed());
			
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

	@Override
	public String toString() {
		return "NPSquirrel: loc=" + this.getLocation().getX() + ", " + this.getLocation().getY() + " color=" + this.printColor() +
				" heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size=" + this.getSize() + " maxSpeed=" + this.getMaximumSpeed() + 
				" steeringDirection=" + this.getSteeringDirection() + " energyLevel=" + this.getEnergyLevel() + " damageLevel=" + this.getDamageLevel() + 
				" LastNut=" + this.getLastNut() + " Strategy=" + this.curStrategy;
	}
	
}
