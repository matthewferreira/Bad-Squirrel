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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;

public class Game extends Form{

	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	//game constructor
	public Game() {
		gw = new GameWorld(); // create Observable GameWorld
		gw.init();			// init game world
		mv = new MapView(); // create an Observer for the map
		sv = new ScoreView(); // create an Observer for the game/player-squirrel
		
		gw.addObserver(mv); // register map observer
		gw.addObserver(sv); // register score observer
		
		this.setTitle("Bad-Squirrel"); //set title of GUI window
		this.setLayout(new BorderLayout()); //set layout of GUI container
		
		//creating Command objects for each command
		BrakeCommand brake = new BrakeCommand(gw);
		AccelerateCommand accelerate = new AccelerateCommand(gw);
		TurnLeftCommand turnLeft = new TurnLeftCommand(gw);
		TurnRightCommand turnRight = new TurnRightCommand(gw);
		TickCommand tick = new TickCommand(gw);
		BirdCollideCommand birdCollide = new BirdCollideCommand(gw);
		NutCollideCommand nutCollide = new NutCollideCommand(gw);
		TomatoCollideCommand tomatoCollide = new TomatoCollideCommand(gw);
		NpcCollideCommand npcCollide = new NpcCollideCommand(gw);
		
		//creating keybinds for each command
		addKeyListener('b', brake);
		addKeyListener('a', accelerate);
		addKeyListener('l', turnLeft);
		addKeyListener('r', turnRight);
		addKeyListener('t', tick);
		addKeyListener('g', birdCollide);
		addKeyListener('e', tomatoCollide);
		
		//north side container 
		Label northLabel = new Label("North label");
		this.add(BorderLayout.NORTH, northLabel);
				
				
		//south side container configuration, creating buttons and adding respective commands
		Button collideNPCButton = new Button("Collide with NPC");
		collideNPCButton.setCommand(npcCollide);
		
		Button collideNutButton = new Button("Collide with Nut");
		collideNutButton.setCommand(nutCollide);
		
		Button collideTomatoButton = new Button("Collide with Tomato");
		collideTomatoButton.setCommand(tomatoCollide);
		
		Button collideBirdButton = new Button("Collide with Bird");
		collideBirdButton.setCommand(birdCollide);
		
		Button tickButton = new Button("Tick");
		tickButton.setCommand(tick);
		
		// south container configuration
		Container southContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		this.add(BorderLayout.SOUTH, southContainer);
		southContainer.add(collideNPCButton);
		southContainer.add(collideNutButton);
		southContainer.add(collideTomatoButton);
		southContainer.add(collideBirdButton);
		southContainer.add(tickButton);
				
		// creating buttons and assigning respective command for west container
		Button accelerateButton = new Button("Accelerate");
		accelerateButton.setCommand(accelerate);
		
		Button turnLeftButton = new Button("Turn Left");
		turnLeftButton.setCommand(turnLeft);
		
		Button changeStratButton = new Button("Change Stategies");
		//ADD COMMAND HERE
		
		
		// west container configuration
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		this.add(BorderLayout.WEST, westContainer);
		westContainer.add(accelerateButton);
		westContainer.add(turnLeftButton);
		westContainer.add(changeStratButton);
				
		//east side container configuration
		Button brakeButton = new Button("Brake");
		brakeButton.setCommand(brake);
		
		Button turnRightButton = new Button("Turn Right");
		turnRightButton.setCommand(turnRight);
		
		Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		this.add(BorderLayout.EAST, eastContainer);
		eastContainer.add(brakeButton);
		eastContainer.add(turnRightButton);
				
		
		//toolbar config
		Toolbar topToolbar = new Toolbar();
		setToolbar(topToolbar);
		Toolbar.setOnTopSideMenu(false);
		
		// creating side menu commands
		HelpCommand help = new HelpCommand();
		ExitCommand exit = new ExitCommand();
		SoundCommand toggleSound = new SoundCommand(gw);
		AboutCommand about = new AboutCommand();
		
		//adding side menu commands to side menu
		topToolbar.addCommandToRightBar(help);
		topToolbar.addCommandToSideMenu(accelerate);
		topToolbar.addCommandToSideMenu(about);
		topToolbar.addCommandToSideMenu(toggleSound);
		topToolbar.addCommandToSideMenu(exit);
		
		
		
		this.show();
		// code here to create Command objects for each command,
		// add commands to side menu and title bar area, bind commands to keys, create
		// control containers for the buttons, add buttons to the control containers,
		// add commands to the buttons, and add control containers, MapView, and
		// ScoreView to the form
		
		 //initialize gameworld
		
	}
}
