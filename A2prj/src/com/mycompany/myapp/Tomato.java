package com.mycompany.myapp;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Tomato extends Fixed{
	private static Random random = new Random();
	private int nutrition;
	
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
		g.setColor(this.getColor());
		g.fillArc(x + (int)this.getLocation().getX(), y + (int)this.getLocation().getY(), 2*radius, 2*radius, 0, 360);
		g.setColor(ColorUtil.BLACK);
		g.drawArc(x + (int)this.getLocation().getX(), y + (int)this.getLocation().getY(), 2*radius, 2*radius, 0, 360);
		g.drawString(this.getNutrition() + "", x + (int)this.getLocation().getX() + this.getSize(), y + (int)this.getLocation().getY() + this.getSize());
		
	}
	
}
