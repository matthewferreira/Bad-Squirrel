package com.mycompany.myapp;

import com.codename1.charts.util.ColorUtil;

public class Nut extends Fixed{
	private static int objCount = 0;
	private int sequenceNumber;
	
	public Nut() {
		super(10, ColorUtil.YELLOW);
		objCount++;
		sequenceNumber = objCount;
	}
	//constructor to specify nut locations
	public Nut(float x, float y) {
		super(10, ColorUtil.YELLOW, x, y);
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
	
}
