package com.SkyControl.WarriorOfHattuza;

import com.SkyControl.WarriorOfHattuza.Screens.GameScreen;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Mainclass extends Game {
	
	private GameScreen Screen;
	
	@Override
	public void create() {	
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Screen = new GameScreen();
		setScreen(Screen);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
