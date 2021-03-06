package com.mycompany.myapp;

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
}
