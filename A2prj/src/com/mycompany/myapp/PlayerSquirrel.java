package com.mycompany.myapp;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class PlayerSquirrel extends Squirrel{
	//singleton pattern implementation
	private static PlayerSquirrel playerSquirrel;
	
	private PlayerSquirrel(float x, float y) { super(x, y); }
	
	public static PlayerSquirrel getPlayerSquirrel(float x, float y) {
		if(playerSquirrel == null) {
			if (x < 0 || x > 1000){
				System.out.println("Invalid X bound for Player");
			}
			else if (y < 0 || y > 1000){
				System.out.println("Invalid y bound for Player");
			}
			else { playerSquirrel = new PlayerSquirrel(x, y); }
		}
		return playerSquirrel;
	}
	public static PlayerSquirrel getPlayerSquirrel() {
		if(playerSquirrel == null) {
			System.out.println("PlayerSquirrel not instantiated");
		}
		return playerSquirrel;
	}
	//resets player to null
	public static void reset() {
		playerSquirrel = null;
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) pCmpRelPrnt.getX();
		int y = (int) pCmpRelPrnt.getY();
		g.setColor(ColorUtil.BLACK);
		g.drawRect(x + (int)this.getLocation().getX(), y + (int)this.getLocation().getY(), this.getSize(), this.getSize());
		g.setColor(this.getColor());
		g.fillRect(x + (int)this.getLocation().getX(), y + (int)this.getLocation().getY(), this.getSize(), this.getSize());
	}
}
