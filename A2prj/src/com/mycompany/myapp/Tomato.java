package com.mycompany.myapp;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

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
}
