package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Toolbar;


public class Game extends Form implements Runnable{

	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private boolean play = true;
	private UITimer timer;
	private Button playPauseButton;
	
	//game constructor
	public Game() {
		gw = new GameWorld(); // create Observable GameWorld
		mv = new MapView(gw); // create an Observer for the map
		sv = new ScoreView(); // create an Observer for the game/player-squirrel
		gw.addObserver(mv); // register map observer
		gw.addObserver(sv); // register score observer 
		
		timer = new UITimer(this);//make the timer tick every second and bind it to this form
		timer.schedule(20, true, this);
		
		this.setTitle("Bad-Squirrel"); //set title of GUI window
		this.setLayout(new BorderLayout()); //set layout of GUI container
		
		//creating Command objects for each command
		BrakeCommand brake = new BrakeCommand(gw);
		AccelerateCommand accelerate = new AccelerateCommand(gw);
		TurnLeftCommand turnLeft = new TurnLeftCommand(gw);
		TurnRightCommand turnRight = new TurnRightCommand(gw);
		TickCommand tick = new TickCommand(gw);
		
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
		
		PlayPauseCommand playPause = new PlayPauseCommand(this);
		if(playPause.game == this) {System.out.println("AAAAAA");}
		else {System.out.println("BBBBBB");}
						
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
		//addKeyListener('p', playPause);
		
		//north side container config. adds ScoreView to north container
		Container northContainer = new Container(BoxLayout.xCenter());
		this.add(BorderLayout.NORTH, northContainer);
		northContainer.add(sv);
		
		// center container config
		// adds MapView to center 
		this.add(BorderLayout.CENTER, mv);

		
		// south container configuration
		Container southContainer = new Container(BoxLayout.xCenter());
		playPauseButton = new Button("Pause");
		playPauseButton.getUnselectedStyle().setBgColor(0x556B2F);
		playPauseButton.getUnselectedStyle().setFgColor(0x81613C);
		playPauseButton.getUnselectedStyle().setBgTransparency(128);
		playPauseButton.getUnselectedStyle().setPadding(5, 5, 5, 5);
		playPauseButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		playPauseButton.setCommand(playPause);
		this.add(BorderLayout.SOUTH, southContainer);
		southContainer.add(playPauseButton);

				
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
		GameWorld.setSize(mv.getSize()[0], mv.getSize()[1]-25);
		gw.init();			// init game world
	}

	@Override
	public void run() {
		gw.tick();
	}
	
	public void toggleMode() {
		play = !play;
		if(play) {
			if(timer != null) {
				timer.schedule(20, true, this);
				playPauseButton.setText("Pause");
			}
		}
		else {
			if(timer != null) {
				timer.cancel();
				playPauseButton.setText("Play");
			}
		}
	}
	
}
