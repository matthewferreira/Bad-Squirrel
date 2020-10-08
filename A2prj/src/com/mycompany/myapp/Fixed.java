package com.mycompany.myapp;

public abstract class Fixed extends GameObject{
	
	//constructor for objects with random locations
	public Fixed(int sz, int clr) {
		super(sz, clr);
	}
	
	//constructor for objects with specified locations
	public Fixed(int sz, int clr, float x, float y) {
		super(sz, clr, x, y);
	}
	//fixed objects can't move
	@Override
	public void setLocation(float x, float y){}
	
}
