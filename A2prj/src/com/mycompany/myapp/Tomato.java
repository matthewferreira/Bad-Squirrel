package com.mycompany.myapp;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Tomato extends Fixed{
	private static Random random = new Random();
	private int nutrition;
	private ArrayList<GameObject> collisionVec = new ArrayList<GameObject>();
	private boolean isSelected = false;
	
	public Tomato() {
		super(10 + random.nextInt(40), ColorUtil.rgb(255, 0, 0));
		nutrition = super.getSize();
	}
	public int getNutrition() {return nutrition;}
	public void collide() {
		nutrition = 0;
		super.setColor(255,204,203);
	}
	
	@Override
	public String toString() {
		return "Tomato: loc=" + this.getLocation().getX() + ", " + this.getLocation().getY() + " color=" + this.printColor() + " size=" + this.getSize() + " nutrition=" + this.getNutrition(); 
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		int x = (int) pCmpRelPrnt.getX();
		int y = (int) pCmpRelPrnt.getY();
		int radius = this.getSize()*2;
		if(this.isSelected()) {
			g.setColor(this.getColor());
			g.setColor(ColorUtil.BLACK);
			g.drawArc(x + (int)this.getLocation().getX(), y + (int)this.getLocation().getY(), 2*radius, 2*radius, 0, 360);
			g.drawString(this.getNutrition() + "", x + (int)this.getLocation().getX() + this.getSize(), y + (int)this.getLocation().getY() + this.getSize());
		}
		else {
			g.setColor(this.getColor());
			g.fillArc(x + (int)this.getLocation().getX(), y + (int)this.getLocation().getY(), 2*radius, 2*radius, 0, 360);
			g.setColor(ColorUtil.BLACK);
			g.drawArc(x + (int)this.getLocation().getX(), y + (int)this.getLocation().getY(), 2*radius, 2*radius, 0, 360);
			g.drawString(this.getNutrition() + "", x + (int)this.getLocation().getX() + this.getSize(), y + (int)this.getLocation().getY() + this.getSize());
		}
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
	
	public void setSelected(boolean b) {
		this.isSelected = b;
	}
	public boolean isSelected() {
		return this.isSelected;
	}
	
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		
		int thisCenterX = (int)(this.getLocation().getX()) + this.getSize();
		int thisCenterY = (int)(this.getLocation().getY()) + this.getSize();
		System.out.println("--------------------");
		System.out.println("CENTER: " + thisCenterX + ", " + thisCenterY);
		int px = (int) pPtrRelPrnt.getX(); // pointer location relative to
		int py = (int) pPtrRelPrnt.getY(); //parents origin
		System.out.println("POINTER: " + px + ", " + px);
		System.out.println("CMP REL PRNT: " + pCmpRelPrnt.getX() + ", " + pCmpRelPrnt.getY());
		
		int xLoc = (int) (pCmpRelPrnt.getX()+thisCenterX);// shape location relative
		int yLoc = (int) (pCmpRelPrnt.getY()+thisCenterY);// to parents origin
		System.out.println("SHAPE: " + xLoc + ", " + yLoc);
		if( (px >= xLoc - this.getSize()*3) && (px <= xLoc+this.getSize()*3) && (py >= yLoc - this.getSize()*3) && (py <= yLoc+this.getSize()*3) ) 
		{ return true; }
		else {return false;}
	}
	
	public void handleCollision(GameObject otherObject) {
		this.collide();
		this.getCollVec().add(otherObject);
	}
}
