package com.mycompany.myapp;

import java.util.ArrayList;

public class GameObjectCollection implements ICollection{
	
	private ArrayList<GameObject> gameObjectList;
	
	public GameObjectCollection() {
		gameObjectList = new ArrayList<>();
	}
	
	public void add(GameObject newGameObject) {
		gameObjectList.add(newGameObject);
	}
	
	public void remove(GameObject go) {
		gameObjectList.remove(go);
	}
	
	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	private class GameObjectIterator implements IIterator{
		private int currElementIndex;
		
		public GameObjectIterator() {
			currElementIndex = -1;
		}
		
		public boolean hasNext() {
			if(gameObjectList.isEmpty() || currElementIndex == gameObjectList.size() -1) {return false;}
			
			return true;
		}
		
		public GameObject getNext() {
			currElementIndex++;
			return gameObjectList.get(currElementIndex);
		}
		
		public GameObject get(int index) {
			return gameObjectList.get(index);
		}
		
	}
}
