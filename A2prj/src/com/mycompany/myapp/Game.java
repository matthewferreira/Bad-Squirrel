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
		AccelerateCommand accelerate = new AccelerateCommand(gw);
		TurnLeftCommand turnLeft = new TurnLeftCommand(gw);
		TurnRightCommand turnRight = new TurnRightCommand(gw);
		
		
		this.show();
		// code here to create Command objects for each command,
		// add commands to side menu and title bar area, bind commands to keys, create
		// control containers for the buttons, add buttons to the control containers,
		// add commands to the buttons, and add control containers, MapView, and
		// ScoreView to the form
		
		gw.init(); //initialize gameworld
		
	}
	
	private void play()
	{
		Label myLabel=new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		myTextField.addActionListener(new ActionListener(){
		boolean wantToExit = false;
			
			public void actionPerformed(ActionEvent evt) {
				String sCommand=myTextField.getText().toString();
				myTextField.clear();
				int totalNuts = gw.getObjsOfType("nut", gw.getObjectList()).size();
				if(sCommand.length() != 0)
					switch (sCommand.charAt(0)) {
					//player requesting to exit game
					case 'x':
						myLabel.setText("Exit? y/n");
						wantToExit = true;
						break;
						//confirm exiting of game
					case'y':
						if(wantToExit) {
							gw.exit();
						}
						break;
						//cancel exiting of game
					case 'n':
						wantToExit = false;
						myLabel.setText("Enter a Command:");
						break;
						//accelerating player squirrel
					case 'a':
						System.out.println("Accelerating player squirrel");
						gw.getPlayer().accelerate();
						break;
						//applying brakes to player squirrel
					case 'b':
						System.out.println("Applying Brakes");
						gw.getPlayer().brake();
						break;
						//turn player squirrel left
					case 'l':
						System.out.println("turning player squirrel left");
						gw.getPlayer().turnLeft();
						break;
						//turn player squirrel right
					case 'r':
						System.out.println("turning player squirrel right");
						gw.getPlayer().turnRight();
						break;
						//colliding player squirrel with self (since no other squirrels created)
					case 'c':
						System.out.println("player has collided with squirrel");
						gw.getPlayer().collide(gw.getPlayer());
						if(gw.getPlayer().getDamageLevel() >=10) {
							gw.loseLife();
						}
						break;
						//colliding player squirrel with random tomato object
					case 'e':
						System.out.println("player has collided with tomato");
						gw.getPlayer().collide(gw.collideTomato());
						break;
						//colliding player squirrel with bird object
					case 'g':
						System.out.println("player has collided with bird");
						gw.getPlayer().collide(gw.getObjsOfType("bird", gw.getObjectList()).get(0));
						if(gw.getPlayer().getDamageLevel() >=10) {
							gw.loseLife();
						}
						break;
						// increasing gameClock
					case 't':
						System.out.println("increased game clock");
						gw.tick();
						break;
						//display gameWorld stats
					case 'd':
						System.out.println("displaying game world stats");
						gw.display();
						break;
						// print map
					case 'm':
						gw.printMap();
						break;
						//retrieving all nut Objects, colliding player squirrel with given nut number
					case '1':
						if(totalNuts >= 1) { 
							System.out.println("collided with nut 1");
							gw.getPlayer().collide(gw.getObjsOfType("nut", gw.getObjectList()).get(0)); 
							}
						else {
							System.out.println("there are only " + totalNuts + " to collide with");
						}
						break;
					case '2':
						if(totalNuts >= 2) { 
							System.out.println("collided with nut 2");
							gw.getPlayer().collide(gw.getObjsOfType("nut", gw.getObjectList()).get(1)); 
							}
						else {
							System.out.println("there are only " + totalNuts + " to collide with");
						}
						break;
					case '3':
						if(totalNuts >= 3) { 
							System.out.println("collided with nut 3");
							gw.getPlayer().collide(gw.getObjsOfType("nut", gw.getObjectList()).get(2)); 
							}
						else {
							System.out.println("there are only " + totalNuts + " to collide with");
						}
						break;
					case '4':
						if(totalNuts >= 4) { 
							System.out.println("collided with nut 4");
							gw.getPlayer().collide(gw.getObjsOfType("nut", gw.getObjectList()).get(3)); 
							}
						else {
							System.out.println("there are only " + totalNuts + " to collide with");
						}
						break;
					case '5':
						if(totalNuts >= 5) { 
							System.out.println("collided with nut 5");
							gw.getPlayer().collide(gw.getObjsOfType("nut", gw.getObjectList()).get(4)); 
							}
						else {
							System.out.println("there are only " + totalNuts + " to collide with");
						}
						break;
					case '6':
						if(totalNuts >= 6) { 
							System.out.println("collided with nut 6");
							gw.getPlayer().collide(gw.getObjsOfType("nut", gw.getObjectList()).get(5)); 
							}
						else {
							System.out.println("there are only " + totalNuts + " to collide with");
						}
						break;
					case '7':
						if(totalNuts >= 7) { 
							System.out.println("collided with nut 7");
							gw.getPlayer().collide(gw.getObjsOfType("nut", gw.getObjectList()).get(6)); 
							}
						else {
							System.out.println("there are only " + totalNuts + " to collide with");
						}
						break;
					case '8':
						if(totalNuts >= 8) { 
							System.out.println("collided with nut 8");
							gw.getPlayer().collide(gw.getObjsOfType("nut", gw.getObjectList()).get(7)); 
							}
						else {
							System.out.println("there are only " + totalNuts + " to collide with");
						}
						break;
					case '9':
						if(totalNuts >= 9) { 
							System.out.println("collided with nut 9");
							gw.getPlayer().collide(gw.getObjsOfType("nut", gw.getObjectList()).get(8)); 
							}
						else {
							System.out.println("there are only " + totalNuts + " to collide with");
						}
						break;
						
					default:
						System.out.println("Improper input");
						break;
					} //switch
			} //actionPerformed
		} //new ActionListener()
		); //addActionListener
	} //play
}
