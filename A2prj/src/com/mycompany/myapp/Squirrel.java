package com.mycompany.myapp;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public abstract class Squirrel extends Movable implements ISteerable{
	
	private int steeringDirection = 0;
	private int energyLevel = 500;
	private int energyConsumptionRate = 1;
	private int damageLevel = 0;
	private int lastNutReached = 1;
	private int maximumSpeed = 40;
	private int maxDamage = 100;
	private int armor = 10;
	private ArrayList<GameObject> collisionVec = new ArrayList<GameObject>();
	private Sound hurtSound;
	private Sound deathSound;
	private Sound eatSound;
	
	// basic squirrel constructor for npc squirrels
	public Squirrel() {
		
		super(40, ColorUtil.GRAY);
		Random random = new Random();
		super.setSpeed(random.nextInt(1));
		super.setHeading(random.nextInt(359));
		// setting NPC location a couple squirrel lengths from first nut
		super.setLocation(50 + (20 + random.nextInt(100))*super.getSize(), 200 + (20 + random.nextInt(100))*super.getSize());
		//adding armor to NPC squirrels
		maxDamage = armor + maxDamage;
		//this.createSounds();
	}
	//constructor for player squirrel to specify start location
	public Squirrel(float x, float y) {
		super(40, ColorUtil.GRAY, x, y);
		super.setSpeed(5);
		super.setHeading(0);
		armor = 0;
		this.createSounds();
	}
	//getters and setters for squirrel vars
	public int getSteeringDirection() { return steeringDirection; }
	public void setSteeringDirection(int sd) { steeringDirection = sd; }
	public int getEnergyLevel() { return energyLevel; }
	public void setEnergyLevel(int el) { energyLevel = el; }
	public int getDamageLevel() { return damageLevel; }
	public void setDamageLevel(int dl) { damageLevel = dl; }
	public int getMaximumSpeed() { return maximumSpeed; }
	public void setMaximumSpeed(int ms) { maximumSpeed = ms; }
	public int getLastNut() { return lastNutReached; }
	public void setLastNut(int ls) { lastNutReached = ls; }
	public int getArmor() { return armor; }
	public void setArmor(int arm) { armor = arm; }
	
	//reduces energy level of squirrel by energyConsumptionRate
	public void reduceEnergyLevel() { energyLevel -= energyConsumptionRate; }
	
	//set speed of squirrel if within maxSpeed limit
	@Override
	public void setSpeed(int spd) {if(spd <= maximumSpeed) { super.setSpeed(spd);} }
	
	//method to move squirrel. keeps squirrel between 0 and 1000 on x and y coordinates
	@Override
	public void move(int elapsedTime) {
		
		if(energyLevel > 0 && damageLevel < maxDamage) {
			int head = super.getHeading() + steeringDirection;
			int deltaX = (int) (Math.cos(Math.toRadians(90 - head))*(super.getSpeed() + elapsedTime/5));
			int deltaY = (int) (Math.sin(Math.toRadians(90 - head))*( super.getSpeed() + elapsedTime/5));
			
			int newX = (int) (super.getLocation().getX() + deltaX);
			int newY = (int) (super.getLocation().getY() + deltaY);
			
			//keeping squirrel within 0 and 1000 boundaries
			if(newX > GameWorld.getSize()[0]) { newX = GameWorld.getSize()[0]; }
			else if(newX < 0) { newX = 0; }
			if(newY > GameWorld.getSize()[1]) { newY = GameWorld.getSize()[1]; }
			else if(newY < 0) { newY = 0; }
			
			super.setLocation(newX, newY);
		}
		//set speed to zero if at max damage or out of energy
		else if(energyLevel <= 0 || damageLevel >=maxDamage) {
			super.setSpeed(0);
		}
	}
	//turn squirrel left
	public void turnLeft() {
		steeringDirection -= 5;
		// check if steer direction < -40, if so then = -40
		if(steeringDirection < -40) {steeringDirection = -40;}
		super.setHeading(super.getHeading()+steeringDirection);
	}
	//turn squirrel right
	public void turnRight() {
		steeringDirection += 5;
		//check if steer direction > 40, if so then = 40
		if(steeringDirection > 40) {steeringDirection = 40;}
		super.setHeading(super.getHeading() + steeringDirection);
	}
	//collide squirrel with a game object, does different things based on which object
	public abstract void collide(GameObject g);
		
	//increase squirrel speed by 2
	public void accelerate() {
		this.setSpeed(super.getSpeed() + 5);
	}
	//decrease squirrel speed by 2
	public void brake() {
		if(super.getSpeed()-2 >= 0) { this.setSpeed(super.getSpeed()-2); }
	}
	//fade color of squirrel based on damage level
	public void fadeColor() {
		int newRed;
		int newBlue;
		int newGreen;
		if(getSpeed() == 0) {
			newRed = 0;
			newBlue = 0;
			newGreen = 0;
		}
		else {
			newRed = (int) (ColorUtil.red(getColor()) * (1-damageLevel*0.1));
			newBlue = (int) (ColorUtil.blue(getColor()) * (1-damageLevel*0.1));
			newGreen = (int) (ColorUtil.green(getColor()) * (1-damageLevel*0.1));
		}
		setColor(newRed, newGreen, newBlue);
	}
	
	public boolean collidesWith(GameObject obj) {
		boolean result = false;
		int thisCenterX = (int) (this.getLocation().getX() + (this.getSize())); // find centers
		int thisCenterY = (int) (this.getLocation().getY() + (this.getSize()));
		int otherCenterX = (int) (obj.getLocation().getX() + (obj.getSize()));
		int otherCenterY = (int) (obj.getLocation().getY() + (obj.getSize()));// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);// find square of sum of radii
		int thisRadius = this.getSize();
		int otherRadius = obj.getSize();
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius+ otherRadius*otherRadius);
		if (distBetweenCentersSqr <= radiiSqr && obj != this) { 
			result = true ; 
		}
		return result ;
	}
	
	
	public void handleCollision(GameObject otherObject) {
		this.collide(otherObject);
		this.getCollVec().add(otherObject);
	}
	
	public int getMaxDamage() {return maxDamage;}
	
	public void createSounds() {
		deathSound = new Sound("death2.wav");
		hurtSound = new Sound("pain100_1.wav");
		eatSound = new Sound("ra.wav");
		//sounds[2] = new Sound("ra.wav");
		//hurtSound = new Sound("pain100_1.wav");
		
	}
	
	public Sound[] getSounds() {
		Sound[] sounds = {hurtSound, deathSound, eatSound};
		return sounds;
	}
	
	@Override
	public String toString() {
		return "Squirrel: loc=" + this.getLocation().getX() + ", " + this.getLocation().getY() + " color=" + this.printColor() + " heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size=" + this.getSize() + " maxSpeed=" + this.getMaximumSpeed() + " steeringDirection=" + this.getSteeringDirection() + " energyLevel=" + this.getEnergyLevel() + " damageLevel=" + this.getDamageLevel();
	}
}
