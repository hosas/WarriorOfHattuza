package com.SkyControl.WarriorOfHattuza;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "WarriorOfHattuza";
		cfg.width = 800;
		cfg.height = 640;
		
		new LwjglApplication(new Mainclass(), cfg);
	}
}
