package com.mycompany.myapp;

import java.util.ArrayList;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Nut extends Fixed{
	private static int objCount = 0;
	private int sequenceNumber;
	private ArrayList<GameObject> collisionVec = new ArrayList<GameObject>();
	private boolean isSelected = false;
	
	public Nut() {
		super(10, ColorUtil.CYAN);
		objCount++;
		sequenceNumber = objCount;
	}
	//constructor to specify nut locations
	public Nut(float x, float y) {
		super(10, ColorUtil.CYAN, x, y);
		objCount++;
		sequenceNumber = objCount;
	}
	
	public int getSeqNum() {
		return sequenceNumber;
	}
	
	//nuts cant change color
	@Override
	public void setColor(int r, int g, int b){}
	//used to determine last nut object
	public static int getObjCount() {
		return objCount;
	}
	
	@Override
	public String toString() {
		return "Nut: loc=" + this.getLocation().getX() + ", " +  this.getLocation().getY() + " color=" + this.printColor() + " size=" + this.getSize() + " seqNum=" + this.getSeqNum();
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		int x = (int) pCmpRelPrnt.getX();
		int y = (int) pCmpRelPrnt.getY();
		int[] xPoints = { x + (int)this.getLocation().getX() - 3*this.getSize(), x + (int)this.getLocation().getX() + 3*this.getSize(), x + (int)this.getLocation().getX()};
		int[] yPoints = { y + (int)this.getLocation().getY(), y + (int)this.getLocation().getY(), y + (int)this.getLocation().getY() + 10*this.getSize()};
		if(this.isSelected()) {
			g.setColor(this.getColor());
			g.drawPolygon(xPoints, yPoints, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawPolygon(xPoints, yPoints, 3);
			g.drawString(this.getSeqNum() + "", x + (int)this.getLocation().getX()- this.getSize(), y + (int)this.getLocation().getY());
		}
		else {
			g.setColor(this.getColor());
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawPolygon(xPoints, yPoints, 3);
			g.drawString(this.getSeqNum() + "", x + (int)this.getLocation().getX()- this.getSize(), y + (int)this.getLocation().getY());
		}
	}
	
	public static void resetObjCnt() {
		objCount=0;
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
	
public boolean isSelected() {return isSelected;}
	
	public void setSelected(boolean b) {this.isSelected = b;}
	
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		
		int thisCenterX = (int)(this.getLocation().getX());
		int thisCenterY = (int)(this.getLocation().getY());
		
		int px = (int) pPtrRelPrnt.getX(); // pointer location relative to
		int py = (int) pPtrRelPrnt.getY(); 
		
		int xLoc = (int) (pCmpRelPrnt.getX()+ thisCenterX) - 3*this.getSize();// shape location relative
		int yLoc = (int) (pCmpRelPrnt.getY()+ thisCenterY);//
		
		if ( (px >= xLoc-3*this.getSize()) && (px <= xLoc+3*this.getSize()) && (py >= yLoc ) && (py <= yLoc+10*this.getSize()) ) {
			return true; 
		}
		else 
		{
			return false;
		}
	}
	
}
