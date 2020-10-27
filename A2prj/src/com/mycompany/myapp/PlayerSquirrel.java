package com.mycompany.myapp;

public class PlayerSquirrel extends Squirrel{
	//singleton pattern implementation, still need to check for boundaries when instantiating
	private static PlayerSquirrel playerSquirrel;
	
	private PlayerSquirrel(float x, float y) { super(x, y); }
	
	public static PlayerSquirrel getPlayerSquirrel(float x, float y) {
		if(playerSquirrel == null) {
			playerSquirrel = new PlayerSquirrel(x, y);
		}
		return playerSquirrel;
	}
	public static PlayerSquirrel getPlayerSquirrel() {
		if(playerSquirrel == null) {
			System.out.println("PlayerSquirrel not instantiated");
		}
		return playerSquirrel;
	}
	
	public void reset() {
		playerSquirrel = null;
	}
	

}
