package com.mycompany.myapp;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.codename1.ui.Graphics;

public class GameWorld extends Observable{
	private static int gameClock = 0;
	private GameObjectCollection gameObjectCollection;
	private int livesRemaining = 3;
	private static boolean sound = false;
	private static int[] size = new int[2];
	private static Sound hurtSound;
	private static Sound deathSound;
	private static Sound eatSound;
	private static BGSound bgSound;
	
	
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
	
	public static void setSize(int w, int h) {
		size[0] = w;
		size[1] = h;
	}
	
	public static int[] getSize() {
		return size;
	}
	
	//move all objects, reduce squirrel energy, increase gameclock, check if squirrel out of energy (if so, loseLife())
	public void tick() {
		//if(sound && Game.getMode()) {
			//bgSound.play();
		//}
		moveAll();
		checkCollisions();
		getPlayer().reduceEnergyLevel();
		System.out.println("Player loc=" + getPlayer().getLocation().getX() + "," + getPlayer().getLocation().getY() + " steerDirection=" + getPlayer().getSteeringDirection() + " speed=" + getPlayer().getSpeed() + " head=" + getPlayer().getHeading() + " energyLevel=" + getPlayer().getEnergyLevel() + " lastNut=" + getPlayer().getLastNut());
		gameClock++;
		System.out.println("gameClock increase to " + gameClock);
		if(getPlayer().getEnergyLevel() <= 0) {
			System.out.println("Ran out of energy!");
			loseLife();
			}
		setChanged();
		notifyObservers();
	}
	
	//move all objects in GameWorld
	public void moveAll() {
		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext()) {
			GameObject nextObject = elements.getNext();
			if(nextObject instanceof Movable) {
				Movable mObj = (Movable)nextObject;
				mObj.move(20);
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public void checkCollisions() {
		IIterator elements = gameObjectCollection.getIterator();
		IIterator elements2 = gameObjectCollection.getIterator();
		while(elements.hasNext()) {
			GameObject current = elements.getNext();
			for(int i = 0; i < elements2.length(); i++) {
				GameObject collideOb = elements2.get(i);
				if(current.collidesWith(collideOb)) {
					if(!current.getCollVec().contains(collideOb)) {
						current.handleCollision(collideOb);
						current.handleCollision(current);
						if(current instanceof Tomato) {
							if(((Tomato) current).getNutrition() != 0) {
								addTomato();
							}
						}
						else if(collideOb instanceof Tomato) {
							if(((Tomato) collideOb).getNutrition() != 0) {
								addTomato();
							}
						}
					}
				}
				else {
					if(current.getCollVec().contains(collideOb)) {
						current.getCollVec().remove(collideOb);
					}
				}
			}
		}
		if(getPlayer().getDamageLevel() >= getPlayer().getMaxDamage()) {
			loseLife();
		}
		setChanged();
		notifyObservers();
	}
	
	//gets all objects of given type
	public ArrayList<GameObject> getObjsOfType(String type){
		IIterator elements = gameObjectCollection.getIterator();
		ArrayList<GameObject> gameObjectsOfType = new ArrayList<>();
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
		return gameObjectsOfType;
	}
	
	// accelerate the player squirrel
	public void accelerate() {
		getPlayer().accelerate();
		setChanged();
		notifyObservers();
	}
	
	// apply brakes to player squirrel
	public void brake() {
		getPlayer().brake();
		setChanged();
		notifyObservers();
	}
	
	//turn player squirrel left
	public void turnPlayerLeft() {
		getPlayer().turnLeft();
		setChanged();
		notifyObservers();
	}
	//turn player squirrel right
	public void turnPlayerRight() {
		getPlayer().turnRight();
		setChanged();
		notifyObservers();
	}
	
	//collides player with given gameobject
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
	
	//pass game stats to ScoreView for display
	public int[] display() {
		int[] stats = new int[6];
		int soundOn = 0;
		if(sound) { soundOn = 1; }
		else { soundOn = 0; }
		stats[0] = getLivesRemaining();
		stats[1] = getGameClock();
		stats[2] = getPlayer().getLastNut();
		stats[3] = getPlayer().getEnergyLevel();
		stats[4] = getPlayer().getDamageLevel();
		stats[5] = soundOn;
		return stats;
	}
	
	//print game map
	public void printMap() {
		System.out.println("Displaying Map");
		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext()) {
			GameObject go = elements.getNext();
			 System.out.println(go.toString());
		}
	}
	
	//add tomato to gameObjectCollection
	public void addTomato() {
		gameObjectCollection.add(new Tomato());
		setChanged();
		notifyObservers();
	}
	
	public int getLivesRemaining() {
		return livesRemaining;
	}
	
	//decrements remaining lives, re-inits GameWorld,  ends game if none left
	public void loseLife() {
		livesRemaining--;
		Nut.resetObjCnt();
		PlayerSquirrel.reset();
		System.out.println("You lost a life! " + getLivesRemaining() + " lives remaining.");
		if(livesRemaining > 0) {
			init();
		}
		else {
			System.out.println("Game Over, you failed!");
			exit();
		}
		setChanged();
		notifyObservers();
	}
	//ends game with victory
	public static void youWin() {
		System.out.println("Game over, you win! Total Time: " + gameClock);
		exit();
	}
	public static void npcWin() {
		System.out.println("Game over, a NPC wins");
		exit();
	}
	
	//returns gameClock
	public int getGameClock() {
		return gameClock;
		}
	//toggles sound variable
	public void toggleSound() {
		if(sound == false) { sound = true; }
		else { sound = false; }
		setChanged();
		notifyObservers();
	}
	public static boolean getSound() {
		return sound;
	}

	public IIterator getObjectList() {
		IIterator elements = gameObjectCollection.getIterator();
		return elements;
	}
	
	public static Sound[] getSounds() {
		Sound[] sounds = {hurtSound, deathSound, eatSound};
		return sounds;
	}
	public static void createSounds() {
		hurtSound = new Sound("pain.wav");
		deathSound = new Sound("death2.wav");
		eatSound = new Sound("eat.wav");
		bgSound = new BGSound("bgSound.wav");
	}
	public BGSound getBgsound() {
		return bgSound;
	}
	
	//gets player squirrel
	public PlayerSquirrel getPlayer() {return PlayerSquirrel.getPlayerSquirrel();}
	public static void exit() {System.exit(0);}
}
