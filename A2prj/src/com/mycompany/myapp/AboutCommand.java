package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command{

	GameWorld gameWorld;
	public AboutCommand() {
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		String message = "Student: Matthew Ferreira\n Course: CSC133\n Version: 2.0";
		boolean about = Dialog.show("About",  message, "ok", null);
	}
}
