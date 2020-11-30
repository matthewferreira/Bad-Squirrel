package com.mycompany.myapp;
import java.util.ArrayList;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject implements IDrawable, ICollider{
	
	private int size;
	private Point location = new Point();
	private int color;
	private Random rando = new Random();
	private ArrayList<GameObject> collisionVec = new ArrayList<GameObject>();
	
	//constructor for objects with random location
	public GameObject(int sz, int clr) {
		size = sz;
		location.setX(rando.nextFloat()*GameWorld.getSize()[0]);
		location.setY(rando.nextFloat()*GameWorld.getSize()[1]);
		color = clr;
	}
	//constructor for objects with specific location
	public GameObject(int sz, int clr, float x, float y) {
		size = sz;
		color=clr;
		location.setX(x);
		location.setY(y);
	}
	//getters and setters
	public int getSize() {return size;}
	
	public Point getLocation() {return location;}
	
	//set location of object within 0 and 1000 x and y
	public void setLocation(float x, float y) {
		if(x >=0 && x<= GameWorld.getSize()[0] - 25) {
			location.setX(x);
		}
		if(y >= 0 && y<= GameWorld.getSize()[1] - 25) {
			location.setY(y);	
		}
	}
	
	public int getColor() {return color;}
	public void setColor(int r, int g, int b) {color = ColorUtil.rgb(r, g, b);}
	
	//method to print object color easily in rgb format
	public String printColor() {
		int red = ColorUtil.red(getColor());
		int green = ColorUtil.green(getColor());
		int blue = ColorUtil.blue(getColor());
		return "[" + red + ", " + green + ", " + blue + "]";
	}
	public ArrayList<GameObject> getCollVec(){
		return collisionVec;
	};
}
