package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Toolbar;


public class Game extends Form{

	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	//game constructor
	public Game() {
		gw = new GameWorld(); // create Observable GameWorld
		
		mv = new MapView(gw); // create an Observer for the map
		sv = new ScoreView(); // create an Observer for the game/player-squirrel
		gw.init();			// init game world
		
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
		
		//toolbar config
		Toolbar topToolbar = new Toolbar();
		setToolbar(topToolbar);
		Toolbar.setOnTopSideMenu(false);
		CheckBox soundBox = new CheckBox("Sound");
	
		// creating side menu commands
		HelpCommand help = new HelpCommand();
		ExitCommand exit = new ExitCommand();
		SoundCommand toggleSound = new SoundCommand(gw);
		AboutCommand about = new AboutCommand();
		soundBox.setCommand(toggleSound);
						
		//adding side menu commands to side menu
		topToolbar.setTitle("Bad-Squirrel");
		topToolbar.addCommandToRightBar(help);
		topToolbar.addCommandToSideMenu(accelerate);
		topToolbar.addCommandToSideMenu(about);
		topToolbar.addComponentToSideMenu(soundBox);
		topToolbar.addCommandToSideMenu(exit);
		
		//creating keybinds for each command
		addKeyListener('b', brake);
		addKeyListener('a', accelerate);
		addKeyListener('l', turnLeft);
		addKeyListener('r', turnRight);
		addKeyListener('t', tick);
		addKeyListener('g', birdCollide);
		addKeyListener('e', tomatoCollide);
		
		//north side container config. adds ScoreView to north container
		Container northContainer = new Container(BoxLayout.xCenter());
		this.add(BorderLayout.NORTH, northContainer);
		northContainer.add(sv);
		
		// center container config
		// adds MapView to center 
		this.add(BorderLayout.CENTER, mv);
				
		//south side container configuration, creating buttons and adding respective commands and styles
		Button collideNPCButton = new Button("Collide with NPC");
		collideNPCButton.getUnselectedStyle().setBgColor(0x556B2F);
		collideNPCButton.getUnselectedStyle().setFgColor(0x81613C);
		collideNPCButton.getUnselectedStyle().setBgTransparency(128);
		collideNPCButton.getUnselectedStyle().setPadding(5, 5, 5, 5);
		collideNPCButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		collideNPCButton.setCommand(npcCollide);
		
		Button collideNutButton = new Button("Collide with Nut");
		collideNutButton.getUnselectedStyle().setBgColor(0x556B2F);
		collideNutButton.getUnselectedStyle().setFgColor(0x81613C);
		collideNutButton.getUnselectedStyle().setBgTransparency(128);
		collideNutButton.getUnselectedStyle().setPadding(5, 5, 5, 5);
		collideNutButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		collideNutButton.setCommand(nutCollide);
		
		Button collideTomatoButton = new Button("Collide with Tomato");
		collideTomatoButton.getUnselectedStyle().setBgColor(0x556B2F);
		collideTomatoButton.getUnselectedStyle().setFgColor(0x81613C);
		collideTomatoButton.getUnselectedStyle().setBgTransparency(128);
		collideTomatoButton.getUnselectedStyle().setPadding(5, 5, 5, 5);
		collideTomatoButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		collideTomatoButton.setCommand(tomatoCollide);
		
		Button collideBirdButton = new Button("Collide with Bird");
		collideBirdButton.getUnselectedStyle().setBgColor(0x556B2F);
		collideBirdButton.getUnselectedStyle().setFgColor(0x81613C);
		collideBirdButton.getUnselectedStyle().setBgTransparency(128);
		collideBirdButton.getUnselectedStyle().setPadding(5, 5, 5, 5);
		collideBirdButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		collideBirdButton.setCommand(birdCollide);
		
		Button tickButton = new Button("Tick");
		tickButton.getUnselectedStyle().setBgColor(0x556B2F);
		tickButton.getUnselectedStyle().setFgColor(0x81613C);
		tickButton.getUnselectedStyle().setBgTransparency(128);
		tickButton.getUnselectedStyle().setPadding(5, 5, 5, 5);
		tickButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		tickButton.setCommand(tick);
		
		// south container configuration
		Container southContainer = new Container(BoxLayout.xCenter());
		
		this.add(BorderLayout.SOUTH, southContainer);
		southContainer.add(collideNPCButton);
		southContainer.add(collideNutButton);
		southContainer.add(collideTomatoButton);
		southContainer.add(collideBirdButton);
		southContainer.add(tickButton);
				
		// creating buttons and assigning respective command for west container
		Button accelerateButton = new Button("Accelerate");
		accelerateButton.getUnselectedStyle().setBgColor(0x556B2F);
		accelerateButton.getUnselectedStyle().setFgColor(0x81613C);
		accelerateButton.getUnselectedStyle().setBgTransparency(128);
		accelerateButton.getUnselectedStyle().setPadding(5, 5, 0, 0);
		accelerateButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		accelerateButton.setCommand(accelerate);
		
		Button turnLeftButton = new Button("Turn Left");
		turnLeftButton.getUnselectedStyle().setBgColor(0x556B2F);
		turnLeftButton.getUnselectedStyle().setFgColor(0x81613C);
		turnLeftButton.getUnselectedStyle().setBgTransparency(128);
		turnLeftButton.getUnselectedStyle().setPadding(5, 5, 5, 5);
		turnLeftButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		turnLeftButton.setCommand(turnLeft);
		
		Button changeStratButton = new Button("Change Stategies");
		changeStratButton.getUnselectedStyle().setBgColor(0x556B2F);
		changeStratButton.getUnselectedStyle().setFgColor(0x81613C);
		changeStratButton.getUnselectedStyle().setBgTransparency(128);
		changeStratButton.getUnselectedStyle().setPadding(5, 5, 5, 5);
		changeStratButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		//ADD COMMAND HERE
		ChangeStratCommand changeStrat = new ChangeStratCommand(gw);
		changeStratButton.setCommand(changeStrat);
		
		// west container configuration
		Container westContainer = new Container(BoxLayout.yCenter());
		this.add(BorderLayout.WEST, westContainer);
		westContainer.add(accelerateButton);
		westContainer.add(turnLeftButton);
		westContainer.add(changeStratButton);
				
		//east side container configuration
		Button brakeButton = new Button("Brake");
		brakeButton.getUnselectedStyle().setBgColor(0x556B2F);
		brakeButton.getUnselectedStyle().setFgColor(0x81613C);
		brakeButton.getUnselectedStyle().setBgTransparency(128);
		brakeButton.getUnselectedStyle().setPadding(5, 5, 5, 5);
		brakeButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		brakeButton.setCommand(brake);
		
		Button turnRightButton = new Button("Turn Right");
		turnRightButton.getUnselectedStyle().setBgColor(0x556B2F);
		turnRightButton.getUnselectedStyle().setFgColor(0x81613C);
		turnRightButton.getUnselectedStyle().setBgTransparency(128);
		turnRightButton.getUnselectedStyle().setPadding(5, 5, 5, 5);
		turnRightButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		turnRightButton.setCommand(turnRight);
		
		Container eastContainer = new Container(BoxLayout.yCenter());
		this.add(BorderLayout.EAST, eastContainer);
		eastContainer.add(brakeButton);
		eastContainer.add(turnRightButton);
		
		this.show();
	}
}
