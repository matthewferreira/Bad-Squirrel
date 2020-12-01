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
	private static boolean play = true;
	private UITimer timer;
	
	private Button playPauseButton;
	private Button accelerateButton;
	private Button turnLeftButton;
	private Button positionButton;
	private Button brakeButton;
	private Button turnRightButton;
	
	private BrakeCommand brake;
	private AccelerateCommand accelerate;
	private TurnLeftCommand turnLeft;
	private TurnRightCommand turnRight;
	

	//game constructor
	public Game() {
		gw = new GameWorld(); // create Observable GameWorld
		mv = new MapView(gw); // create an Observer for the map
		sv = new ScoreView(); // create an Observer for the game/player-squirrel
		gw.addObserver(mv); // register map observer
		gw.addObserver(sv); // register score observer 

		this.setTitle("Bad-Squirrel"); //set title of GUI window
		this.setLayout(new BorderLayout()); //set layout of GUI container
		
		//creating Command objects for each command
		brake = new BrakeCommand(gw);
		accelerate = new AccelerateCommand(gw);
		turnLeft = new TurnLeftCommand(gw);
		turnRight = new TurnRightCommand(gw);
		
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
		PositionCommand position = new PositionCommand(gw);
						
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
		southContainer.add(playPauseButton);
		
		positionButton = new Button("Position");
		positionButton.getUnselectedStyle().setBgColor(0x556B2F);
		positionButton.getUnselectedStyle().setFgColor(0x81613C);
		positionButton.getUnselectedStyle().setBgTransparency(128);
		positionButton.getUnselectedStyle().setPadding(5, 5, 5, 5);
		positionButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		positionButton.setCommand(position);
		southContainer.add(positionButton);
		this.add(BorderLayout.SOUTH, southContainer);
				
		// creating buttons and assigning respective command for west container
		accelerateButton = new Button("Accelerate");
		accelerateButton.getUnselectedStyle().setBgColor(0x556B2F);
		accelerateButton.getUnselectedStyle().setFgColor(0x81613C);
		accelerateButton.getUnselectedStyle().setBgTransparency(128);
		accelerateButton.getUnselectedStyle().setPadding(5, 5, 0, 0);
		accelerateButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		accelerateButton.setCommand(accelerate);
		
		turnLeftButton = new Button("Turn Left");
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
		brakeButton = new Button("Brake");
		brakeButton.getUnselectedStyle().setBgColor(0x556B2F);
		brakeButton.getUnselectedStyle().setFgColor(0x81613C);
		brakeButton.getUnselectedStyle().setBgTransparency(128);
		brakeButton.getUnselectedStyle().setPadding(5, 5, 5, 5);
		brakeButton.getUnselectedStyle().setBorder(Border.createLineBorder(2));
		brakeButton.setCommand(brake);
		
		turnRightButton = new Button("Turn Right");
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
		GameWorld.createSounds();
		revalidate();
		timer = new UITimer(this);//make the timer tick every second and bind it to this form
		timer.schedule(20, true, this);
		
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
				accelerateButton.setEnabled(true);
				turnLeftButton.setEnabled(true);
				positionButton.setEnabled(false);
				brakeButton.setEnabled(true);
				turnRightButton.setEnabled(true);
				addKeyListener('b', brake);
				addKeyListener('a', accelerate);
				addKeyListener('l', turnLeft);
				addKeyListener('r', turnRight);
				gw.getBgsound().play();
			}
		}
		else {
			if(timer != null) {
				timer.cancel();
				playPauseButton.setText("Play");
				accelerateButton.setEnabled(false);
				turnLeftButton.setEnabled(false);
				positionButton.setEnabled(true);
				brakeButton.setEnabled(false);
				turnRightButton.setEnabled(false);
				removeKeyListener('b', brake);
				removeKeyListener('a', accelerate);
				removeKeyListener('l', turnLeft);
				removeKeyListener('r', turnRight);
				gw.getBgsound().pause();
			}
		}
	}
	public static boolean getMode() {
		return play;
	}
}
