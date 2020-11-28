package com.mycompany.myapp;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Bird extends Movable{
	
	private static Random random = new Random();
	
	
	public Bird() {
		super(10 + random.nextInt(40), ColorUtil.BLUE);
		super.setHeading(random.nextInt(359));
		super.setSpeed(5 + random.nextInt(8));
	}
	
	// custom move function for bird
	@Override
	public void move(int elapsedTime) {
		
		int heading = super.getHeading() - 5 + random.nextInt(10);
		super.setHeading(heading);
		
		int speed = super.getSpeed();
		int deltaX = (int) (Math.cos(Math.toRadians(90 - heading))*(super.getSpeed() + elapsedTime/5));
		int deltaY = (int) (Math.sin(Math.toRadians(90 - heading))*(super.getSpeed() + elapsedTime/5));
		
		int newX = (int) (super.getLocation().getX() + deltaX);
		int newY = (int) (super.getLocation().getY() + deltaY);
		
		// GameWorld.getSize()[0] is the width of the game world
		if(newX >= GameWorld.getSize()[0]) {
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
		//GameWorld.getSize()[1] is the height of the game world
		if(newY >= GameWorld.getSize()[1]) {
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
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) pCmpRelPrnt.getX();
		int y = (int) pCmpRelPrnt.getY();
		int[] xPoints = { x + (int)this.getLocation().getX() - this.getSize(), x + (int)this.getLocation().getX() + this.getSize(), x + (int)this.getLocation().getX()};
		int[] yPoints = { y + (int)this.getLocation().getY(), y + (int)this.getLocation().getY(), y + (int)this.getLocation().getY() + 5*this.getSize()};
		g.setColor(this.getColor());
		g.drawPolygon(xPoints, yPoints, 3);
		
	}
	
	public boolean collidesWith(GameObject obj) {
		
		boolean result = false;
		
		int thisCenterX = (int) (this.getLocation().getX() + (this.getSize()/2)); // find centers
		int thisCenterY = (int) (this.getLocation().getY() + (this.getSize()/2));
		int otherCenterX = (int) (obj.getLocation().getX() + (obj.getSize()/2));
		int otherCenterY = (int) (obj.getLocation().getY() + (obj.getSize()/2));// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);// find square of sum of radii
		int thisRadius = this.getSize()/2;
		int otherRadius = obj.getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius+ otherRadius*otherRadius);
		if (distBetweenCentersSqr <= radiiSqr && obj != this) { 
			result = true ; 
		}
		return result ;
	}
	
	
	public void handleCollision(GameObject otherObject) {
		this.getCollVec().add(otherObject);
	}
	
}
