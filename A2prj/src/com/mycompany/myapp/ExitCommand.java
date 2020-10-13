package com.mycompany.myapp;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command{
	
	public ExitCommand() {
		super("Exit");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Exiting");
		boolean quit = Dialog.show("Confirm quit",  "sure?", "ok",  "cancel");
		if(quit) {
			Display.getInstance().exitApplication();
		}
	}
}
