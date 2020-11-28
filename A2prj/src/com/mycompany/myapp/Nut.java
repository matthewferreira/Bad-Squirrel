package com.mycompany.myapp;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Nut extends Fixed{
	private static int objCount = 0;
	private int sequenceNumber;
	
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
		g.setColor(this.getColor());
		g.fillPolygon(xPoints, yPoints, 3);
		g.setColor(ColorUtil.BLACK);
		g.drawPolygon(xPoints, yPoints, 3);
		g.drawString(this.getSeqNum() + "", x + (int)this.getLocation().getX()- this.getSize(), y + (int)this.getLocation().getY());
		
	}
	
	public static void resetObjCnt() {
		objCount=0;
	}
	
}
