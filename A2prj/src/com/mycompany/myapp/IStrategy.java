package com.mycompany.myapp;

public interface IStrategy {
	//need to pass NPC as param since the location is needed
	public void apply(NonPlayerSquirrel s);
}
