package com.mycompany.myapp;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.models.Point;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer{
	private GameWorld gw;
	
	public MapView(Observable myModel) {
		myModel.addObserver(this);
	}
	
	public MapView(GameWorld gameWorld) {
		gw = gameWorld;
		this.getUnselectedStyle().setBorder(Border.createLineBorder(4, 0xFF0000));
	}
	
	public void update(Observable o, Object arg) {
		// code here to call the method in GameWorld (Observable) that output the
		// game object information to the console 
		gw.printMap();
		this.repaint();
		this.revalidate();
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
	}
	
	public int[] getSize() {
		int[] size = { this.getWidth(), this.getHeight() };
		return size;
	}
	
	public void pointerPressed(int x, int y) {
		if(!Game.getMode()) {
			//make pointer location relative to parents origin
			x = x - getParent().getAbsoluteX();
			y = y - getParent().getAbsoluteY();
			
			Point pPtrRelPrnt = new Point(x, y); 
			Point pCmpRelPrnt = new Point(getX(), getY()); 
			IIterator gameObjects = gw.getObjectList();
			while(gameObjects.hasNext()) {
				GameObject go = gameObjects.getNext();
				if(go instanceof ISelectable) {
					ISelectable is = (ISelectable)go;
					if(PositionCommand.isActive() && is.isSelected()) {
						Fixed f = (Fixed)is;
						System.out.println("NEW POSITION: " + pPtrRelPrnt.getX() + ", " + pPtrRelPrnt.getY());
						f.setLocation(pPtrRelPrnt.getX(), pPtrRelPrnt.getY());
						f.setSelected(false);
						PositionCommand.toggleActive();
					}
					if(is.contains(pPtrRelPrnt, pCmpRelPrnt)) {
						is.setSelected(true);
					}
					else {
						is.setSelected(false);
					}

				}
				repaint();
			}
		}
	}
}
