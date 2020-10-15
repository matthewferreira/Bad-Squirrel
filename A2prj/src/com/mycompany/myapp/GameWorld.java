package com.mycompany.myapp;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class GameWorld extends Observable{
	private static int gameClock = 0;
	private GameObjectCollection gameObjectCollection;
	private int livesRemaining = 3;
	private boolean sound = false;
	
	public void init() {
		gameObjectCollection = new GameObjectCollection();
		
		gameObjectCollection.add(new Nut(50, 200));
		gameObjectCollection.add(new Nut(200, 275));
		gameObjectCollection.add(new Nut(500, 500));
		gameObjectCollection.add(new Nut(750, 750));
		gameObjectCollection.add(PlayerSquirrel.getPlayerSquirrel(50, 200));
		gameObjectCollection.add(new NonPlayerSquirrel());
		gameObjectCollection.add(new NonPlayerSquirrel());
		gameObjectCollection.add(new NonPlayerSquirrel());
		gameObjectCollection.add(new Bird());
		gameObjectCollection.add(new Bird());
		gameObjectCollection.add(new Tomato());
		gameObjectCollection.add(new Tomato());
	}
	
	//move all objects, reduce squirrel energy, increase gameclock, check if squirrel out of energy (if so, loseLife())
	public void tick() {
		moveAll();
		getPlayer().reduceEnergyLevel();
		System.out.println("Player loc=" + getPlayer().getLocation().getX() + "," + getPlayer().getLocation().getY() + " steerDirection=" + getPlayer().getSteeringDirection() + " speed=" + getPlayer().getSpeed() + " head=" + getPlayer().getHeading() + " energyLevel=" + getPlayer().getEnergyLevel() + " lastNut=" + getPlayer().getLastNut());
		gameClock++;
		System.out.println("gameClock increase to " + gameClock);
		if(getPlayer().getEnergyLevel() <= 0) {
			System.out.println("Ran out of energy!");
			loseLife();
			}
		printMap();
		display();
	}
	
	//move all objects in GameWorld
	public void moveAll() {
		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext()) {
			GameObject nextObject = elements.getNext();
			if(nextObject instanceof Movable) {
				Movable mObj = (Movable)nextObject;
				mObj.move();
			}
		}
		
	}
	
	public GameObject getRandomObjOfType(String type) {
		IIterator elements = gameObjectCollection.getIterator();
		ArrayList<GameObject> gameObjectsOfType = new ArrayList<>();
		Random random = new Random();
		if(type.equals("Bird")) {
			while(elements.hasNext()) {
				GameObject nextObject = elements.getNext();
				if(nextObject instanceof Bird) {
					gameObjectsOfType.add((Bird)nextObject);
				}
			}
		}
		else if(type.equals("Nut")) {
			while(elements.hasNext()) {
				GameObject nextObject = elements.getNext();
				if(nextObject instanceof Nut) {
					gameObjectsOfType.add((Nut)nextObject);
				}
			}
		}
		else if(type.equals("Tomato")) {
			while(elements.hasNext()) {
				GameObject nextObject = elements.getNext();
				if(nextObject instanceof Tomato) {
					gameObjectsOfType.add((Tomato)nextObject);
				}
			}
		}
		else if(type.equals("NPC")) {
			while(elements.hasNext()) {
				GameObject nextObject = elements.getNext();
				if(nextObject instanceof NonPlayerSquirrel) {
					gameObjectsOfType.add((NonPlayerSquirrel)nextObject);
				}
			}
		}
		int randomInt = random.nextInt(gameObjectsOfType.size());
		return gameObjectsOfType.get(randomInt);
	}
	
	// accelerate the player squirrel
	public void accelerate() {
		getPlayer().accelerate();
	}
	
	// apply brakes to player squirrel
	public void brake() {
		getPlayer().brake();
	}
	
	//turn player squirrel left
	public void turnPlayerLeft() {
		getPlayer().turnLeft();
	}
	//turn player squirrel right
	public void turnPlayerRight() {
		getPlayer().turnRight();
	}
	
	public void collidePlayer(GameObject go) {
		getPlayer().collide(go);
		if(getPlayer().getDamageLevel() >= 10) {
			loseLife();
		}
		if(go instanceof Tomato) {
			((Tomato)go).collide();
			addTomato();
		}
		else if(go instanceof NonPlayerSquirrel) {
			((NonPlayerSquirrel) go).collide(getPlayer());
		}
		setChanged();
		notifyObservers();
	}
	
	//display game stats
	public void display() {
		System.out.println("livesRemaining=" + getLivesRemaining());
		System.out.println("gameClock=" + getGameClock());
		System.out.println("lastNutReached=" + getPlayer().getLastNut());
		System.out.println("energyLevel=" + getPlayer().getEnergyLevel());
		System.out.println("damageLevel=" + getPlayer().getDamageLevel());
	}
	//print game map
	public void printMap() {
		System.out.println("Displaying Map");
		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext()) {
			GameObject nextGameObject = elements.getNext();
			if(nextGameObject instanceof Nut) {
				Nut nutObj = (Nut)nextGameObject;
				System.out.println("Nut: loc=" + nutObj.getLocation().getX() + ", " +  nutObj.getLocation().getY() + " color=" + nutObj.printColor() + " size=" + nutObj.getSize() + " seqNum=" + nutObj.getSeqNum());
			}
			else if(nextGameObject instanceof Bird) {
				Bird birdObj = (Bird)nextGameObject;
				System.out.println("Bird: loc=" + birdObj.getLocation().getX() + ", " + birdObj.getLocation().getY() + " color=" + birdObj.printColor() + " heading=" + birdObj.getHeading() + " speed=" + birdObj.getSpeed() + " size=" + birdObj.getSize());
				
			}
			else if(nextGameObject instanceof Squirrel) {
					Squirrel sqObj = (Squirrel)nextGameObject;
					System.out.println("Squirrel: loc=" + sqObj.getLocation().getX() + ", " + sqObj.getLocation().getY() + " color=" + sqObj.printColor() + " heading=" + sqObj.getHeading() + " speed=" + sqObj.getSpeed() + " size=" + sqObj.getSize() + " maxSpeed=" + sqObj.getMaximumSpeed() + " steeringDirection=" + sqObj.getSteeringDirection() + " energyLevel=" + sqObj.getEnergyLevel() + " damageLevel=" + sqObj.getDamageLevel());
			}
			else if(nextGameObject instanceof Tomato) {
				Tomato tomObj = (Tomato)nextGameObject;
				System.out.println("Tomato: loc=" + tomObj.getLocation().getX() + ", " + tomObj.getLocation().getY() + " color=" + tomObj.printColor() + " size=" + tomObj.getSize() + " nutrition=" + tomObj.getNutrition());
			}
		}
	}
	

	
	//add tomato to gameObjectCollection
	public void addTomato() {
		gameObjectCollection.add(new Tomato());
		setChanged();
		notifyObservers();
	}
	
	
	public int getLivesRemaining() {return livesRemaining;}
	
	//decrements remaining lives, re-inits GameWorld,  ends game if none left
	public void loseLife() {
		livesRemaining--;
		System.out.println("You lost a life! " + getLivesRemaining() + " lives remaining.");
		if(livesRemaining > 0) {
			init();
		}
		else {
			System.out.println("Game Over, you failed!");
			exit();
		}
	}
	//ends game with victory
	public static void youWin() {
		System.out.println("Game over, you win! Total Time: " + gameClock);
		exit();
	}
	public int getGameClock() {return gameClock;}
	public void toggleSound() {
		if(sound == false) { sound = true; }
		else { sound = false; }
	}
	
	//gets player squirrel
	public PlayerSquirrel getPlayer() {return PlayerSquirrel.getPlayerSquirrel();}
	public static void exit() {System.exit(0);}
}
