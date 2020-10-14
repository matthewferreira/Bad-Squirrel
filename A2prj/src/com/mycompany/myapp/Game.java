package com.mycompany.myapp;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class Game extends Form{

	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	//game constructor
	public Game() {
		gw = new GameWorld(); // create Observable GameWorld
		mv = new MapView(); // create an Observer for the map
		sv = new ScoreView(); // create an Observer for the game/player-squirrel
		//state data
		
		gw.addObserver(mv); // register map observer
		gw.addObserver(sv); // register score observer
		
		this.setTitle("Bad-Squirrel"); //set title of GUI window
		this.setLayout(new BorderLayout()); //set layout of GUI container
		
		//north side container 
		Label northLabel = new Label("North label");
		this.add(BorderLayout.NORTH, northLabel);
		
		
		//south side container configuration
		Button collideNPCButton = new Button("Collide with NPC");
		Button collideNutButton = new Button("Collide with Nut");
		Button collideTomatoButton = new Button("Collide with Tomato");
		Button collideBirdButton = new Button("Collide with Bird");
		Button tickButton = new Button("Tick");
		Container southContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		this.add(BorderLayout.SOUTH, southContainer);
		southContainer.add(collideNPCButton);
		southContainer.add(collideNutButton);
		southContainer.add(collideTomatoButton);
		southContainer.add(collideBirdButton);
		southContainer.add(tickButton);
		
		
		//west side container configuration
		Button accelerateButton = new Button("Accelerate");
		Button turnLeftButton = new Button("Turn Left");
		Button changeStratButton = new Button("Change Stategies");
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		this.add(BorderLayout.WEST, westContainer);
		westContainer.add(accelerateButton);
		westContainer.add(turnLeftButton);
		westContainer.add(changeStratButton);
		
		//east side container configuration
		Button breakButton = new Button("Break");
		Button turnRightButton = new Button("Turn Right");
		Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		this.add(BorderLayout.EAST, eastContainer);
		eastContainer.add(breakButton);
		eastContainer.add(turnRightButton);
		
		//center container
		Button exit = new Button("Exit Button");
		exit.setCommand(new ExitCommand());
		this.add(BorderLayout.CENTER, exit);
		
		
		//creating Command objects for each command
		BrakeCommand brake = new BrakeCommand(gw);
		addKeyListener('b', brake);
		
		AccelerateCommand accelerate = new AccelerateCommand(gw);
		addKeyListener('a', accelerate);
		
		TurnLeftCommand turnLeft = new TurnLeftCommand(gw);
		
		TurnRightCommand turnRight = new TurnRightCommand(gw);
		
		TickCommand tick = new TickCommand(gw);
		
		//initiates collision with a random bird when called
		CollideCommand birdCollide = new CollideCommand(gw, "bird");
		
		//initiates collision with a random nut when called
		CollideCommand nutCollide = new CollideCommand(gw, "nut");
		
		//initiates collision with a random tomato when called
		CollideCommand tomatoCollide = new CollideCommand(gw, "tomato");
		
		//initiates collision with a random NPC squirrel when called
		CollideCommand npcCollide = new CollideCommand(gw, "nonplayersquirrel");
		
		
		this.show();
		// code here to create Command objects for each command,
		// add commands to side menu and title bar area, bind commands to keys, create
		// control containers for the buttons, add buttons to the control containers,
		// add commands to the buttons, and add control containers, MapView, and
		// ScoreView to the form
		
		gw.init(); //initialize gameworld
		
	}
}
