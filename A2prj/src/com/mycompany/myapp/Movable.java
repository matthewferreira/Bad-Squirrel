package com.mycompany.myapp;

public abstract class Movable extends GameObject{

	private int heading;
	private int speed;
	
	//construcor for objects with random locations
	public Movable(int sz, int clr) {super(sz, clr);}
	
	//constructor for objects with specified locations
	public Movable(int sz, int clr, float x, float y) {super(sz, clr, x, y);}
	
	public void move() {
		int deltaX = (int) (Math.cos(Math.toRadians(90 - heading))*speed);
		int deltaY = (int) (Math.sin(Math.toRadians(90 - heading))*speed);
		int newX = (int) (super.getLocation().getX() + deltaX);
		int newY = (int) (super.getLocation().getY() + deltaY);
		super.setLocation(newX, newY);
	}
	
	public int getHeading() { return heading; }
	public void setHeading(int head) { heading = head; }
	public int getSpeed() { return speed; }
	public void setSpeed(int spd) { speed = spd; }
}
