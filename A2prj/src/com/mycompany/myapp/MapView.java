package com.mycompany.myapp;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer{
	private GameWorld gw;
	private static int width;
	private static int height;
	
	public MapView(Observable myModel) {
		myModel.addObserver(this);
	}
	
	public MapView(GameWorld gameW) {
		gw = gameW;
		Label mvLabel = new Label("This is the map view");
		this.getUnselectedStyle().setBorder(Border.createLineBorder(4, 0xFF0000));
		this.add(mvLabel);
		width = this.getWidth();
		height = this.getHeight();
		gw.setSize(width, height);
	}
	
	public void update(Observable o, Object arg) {
		// code here to call the method in GameWorld (Observable) that output the
		// game object information to the console 
		
		//gw = (GameWorld)o;
		
		gw.printMap();
		this.repaint();
		//this.revalidate();
	}
	
	@Override
	 public void paint(Graphics g) {
		super.paint(g);
		
		Point pCmpRelPrnt = new Point(getX(), getY());
		IIterator gameObjects = gw.getObjectList();
		while(gameObjects.hasNext()) {
			GameObject go = gameObjects.getNext();
			go.draw(g, pCmpRelPrnt);
		}
		//gw.drawObjects(g);
		}
	}
