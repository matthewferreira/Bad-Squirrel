package com.mycompany.myapp;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld {
	private static int gameClock = 0;
	private Random random = new Random();
	private GameObjectCollection gameObjectCollection;
	private ArrayList<GameObject> gameObjectList;
	private int livesRemaining = 3;
	private Squirrel player;
	
	public void init() {
		gameObjectCollection = new GameObjectCollection();
		
		gameObjectCollection.add(new Nut(50, 200));
		gameObjectCollection.add(new Nut(200, 275));
		gameObjectCollection.add(new Nut(500, 500));
		gameObjectCollection.add(new Nut(750, 750));
		gameObjectCollection.add(new Squirrel(50, 200));
		gameObjectCollection.add(new Bird());
		gameObjectCollection.add(new Bird());
		gameObjectCollection.add(new Tomato());
		gameObjectCollection.add(new Tomato());
	}
	
	//move all objects, reduce squirrel energy, increase gameclock, check if squirrel out of energy (if so, loseLife())
	public void tick() {
		moveAll();
		getPlayer().reduceEnergyLevel();
		System.out.println("Player loc=" + player.getLocation().getX() + "," + player.getLocation().getY() + " steerDirection=" + player.getSteeringDirection() + " speed=" + player.getSpeed() + " head=" + player.getHeading() + " energyLevel=" + player.getEnergyLevel() + " lastNut=" + player.getLastNut());
		gameClock++;
		System.out.println("gameClock increase to " + gameClock);
		if(getPlayer().getEnergyLevel() <= 0) {
			System.out.println("Ran out of energy!");
			loseLife();
			}
	}
	
	//move all objects in GameWorld
	public void moveAll() {
		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext()) {
			if(elements.getNext() instanceof Movable) {
				Movable mObj = (Movable)elements.getNext();
				mObj.move();
			}
		}
	}
	
	//returns list of all GameObjects
	public ArrayList<GameObject> getObjectList() {return gameObjectList;}
	
	//method returns list with all GameObjects of the specified type
	public ArrayList<GameObject> getObjsOfType(String type, ArrayList<GameObject> go){
		ArrayList<GameObject> allObjOfType = new ArrayList<GameObject>();
		switch(type) {
		case "bird":
			for(int i = 0; i < go.size(); i++) {
				if(go.get(i) instanceof Bird) {
					allObjOfType.add((Bird)go.get(i));
				}
			}
			break;
		case "squirrel":
			for(int i = 0; i < go.size(); i++) {
				if(go.get(i) instanceof Squirrel) {
					allObjOfType.add((Squirrel)go.get(i));
				}
			}
			break;
		case "tomato":
			for(int i = 0; i < go.size(); i++) {
				if(go.get(i) instanceof Tomato) {
					allObjOfType.add((Tomato)go.get(i));
				}
			}
			break;
		case "nut":
			for(int i = 0; i < go.size(); i++) {
				if(go.get(i) instanceof Nut) {
					allObjOfType.add((Nut)go.get(i));
				}
			}
			break;
			
		default:
			System.out.println("Invalid Input. Valid inputs are 'squirrel', 'bird', 'nut', 'tomato'");
		}
		return allObjOfType;
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
		for(int i = 0; i < gameObjectList.size(); i++) {
			if(gameObjectList.get(i) instanceof Nut) {
				Nut nutObj = (Nut)gameObjectList.get(i);
				System.out.println("Nut: loc=" + nutObj.getLocation().getX() + ", " +  nutObj.getLocation().getY() + " color=" + nutObj.printColor() + " size=" + nutObj.getSize() + " seqNum=" + nutObj.getSeqNum());
			}
			else if(gameObjectList.get(i) instanceof Bird) {
				Bird birdObj = (Bird)gameObjectList.get(i);
				System.out.println("Bird: loc=" + birdObj.getLocation().getX() + ", " + birdObj.getLocation().getY() + " color=" + birdObj.printColor() + " heading=" + birdObj.getHeading() + " speed=" + birdObj.getSpeed() + " size=" + birdObj.getSize());
				
			}
			else if(gameObjectList.get(i) instanceof Squirrel) {
					Squirrel sqObj = (Squirrel)gameObjectList.get(i);
					System.out.println("Squirrel: loc=" + sqObj.getLocation().getX() + ", " + sqObj.getLocation().getY() + " color=" + sqObj.printColor() + " heading=" + sqObj.getHeading() + " speed=" + sqObj.getSpeed() + " size=" + sqObj.getSize() + " maxSpeed=" + sqObj.getMaximumSpeed() + " steeringDirection=" + sqObj.getSteeringDirection() + " energyLevel=" + sqObj.getEnergyLevel() + " damageLevel=" + sqObj.getDamageLevel());
			}
			else if(gameObjectList.get(i) instanceof Tomato) {
				Tomato tomObj = (Tomato)gameObjectList.get(i);
				System.out.println("Tomato: loc=" + tomObj.getLocation().getX() + ", " + tomObj.getLocation().getY() + " color=" + tomObj.printColor() + " size=" + tomObj.getSize() + " nutrition=" + tomObj.getNutrition());
			}
		}
	}
	//add tomato to gameworld
	public void addTomato() {
		Tomato newTomato = new Tomato();
		getObjectList().add(newTomato);
	}
	//collide random tomato with squirrel, add new tomato
	public Tomato collideTomato() {
		
		int size = getObjsOfType("tomato", getObjectList()).size();
		int randomInt = random.nextInt(size);
		Tomato randomTomato = (Tomato) getObjsOfType("tomato", getObjectList()).get(randomInt);
		randomTomato.collide();
		addTomato();
		return randomTomato;
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
	
	//gets player squirrel
	public Squirrel getPlayer() {return player;}
	public static void exit() {System.exit(0);}
}
