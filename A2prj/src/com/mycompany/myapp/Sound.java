package com.mycompany.myapp;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {
	private Media m;
	public Sound(String fileName) {
		if (Display.getInstance().getCurrent() == null){
			System.out.println("Error: Create sound objects after calling show()!");
			System.exit(0);
			}
		try{
			System.out.println("FILENAME: " + fileName + "GET CLASS: " + getClass());
			System.out.println(Display.getInstance());
			
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
			m = MediaManager.createMedia(is, "audio/wav");
			System.out.println("Media: " + m);
			}
		catch(Exception e){
			e.printStackTrace();
			}
		}
	
	public void play() {
		//start playing the sound from time zero (beginning of the sound file)
		
			m.setTime(0); 
			m.play();
		
	}
}
