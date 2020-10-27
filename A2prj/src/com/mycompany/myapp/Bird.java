package com.mycompany.myapp;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class Bird extends Movable{
	
	private static Random random = new Random();
	
	public Bird() {
		super(10 + random.nextInt(40), ColorUtil.BLUE);
		super.setHeading(random.nextInt(359));
		super.setSpeed(2 + random.nextInt(8));
	}
	
	// custom move function for bird
	@Override
	public void move() {
		
		int heading = super.getHeading() - 5 + random.nextInt(10);
		super.setHeading(heading);
		
		int speed = super.getSpeed();
		int deltaX = (int) (Math.cos(Math.toRadians(90 - heading))*speed);
		int deltaY = (int) (Math.sin(Math.toRadians(90 - heading))*speed);
		
		int newX = (int) (super.getLocation().getX() + deltaX);
		int newY = (int) (super.getLocation().getY() + deltaY);
		
		if(newX >= 1000) {
			super.setHeading(random.nextInt(359));
			heading = super.getHeading();
			deltaX = (int) (Math.cos(Math.toRadians(90 - heading))*speed);
			newX = (int) (super.getLocation().getX() + deltaX);
		}
		else if(newX <= 0) {
			super.setHeading(random.nextInt(359));
			heading = super.getHeading();
			deltaX = (int) (Math.cos(Math.toRadians(90 - heading + 45))*speed);
			newX = (int) (super.getLocation().getX() + deltaX);
		}
		
		if(newY >= 1000) {
			super.setHeading(random.nextInt(359));
			heading = super.getHeading();
			deltaY = (int) (Math.sin(Math.toRadians(90 - heading))*speed);
			newX = (int) (super.getLocation().getY() + deltaY);
		}
		else if(newY <= 0) {
			super.setHeading(random.nextInt(359));
			heading = super.getHeading();
			deltaY = (int) (Math.cos(Math.toRadians(90 - heading + 45))*speed);
			newY = (int) (super.getLocation().getY() + deltaY);
		}
		super.setLocation(newX, newY);
	}
	
	@Override
	public String toString() {
		return "Bird: loc=" + this.getLocation().getX() + ", " + this.getLocation().getY() + " color=" + this.printColor() + " heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size=" + this.getSize();
	}
	
}
