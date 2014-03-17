package com.SkyControl.WarriorOfHattuza.Screens;

import com.SkyControl.WarriorOfHattuza.Entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameScreen implements Screen, InputProcessor{

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private OrthographicCamera BGcamera;
	private OrthographicCamera playercam;
	private Texture bacground;
	private Player player;
	private boolean IsTouched = false;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.setView(BGcamera);
		update();
		move();
		renderer.getSpriteBatch().begin();
		renderer.getSpriteBatch().draw(bacground, 0, 0);
		renderer.getSpriteBatch().end();
		
		renderer.setView(camera);
		renderer.render();
		playercam.position.set(player.getX(), player.getY(), 0);
		playercam.update();
		//renderer.setView(playercam);
		renderer.getSpriteBatch().begin();
		player.draw(renderer.getSpriteBatch());
		renderer.getSpriteBatch().end();
		
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.position.set(width/2, height/2, 0);
		camera.update();
		BGcamera.viewportHeight = height;
		BGcamera.viewportWidth = width;
		BGcamera.position.set(width/2, height/2, 0);
		BGcamera.update();
		playercam.viewportHeight = height;
		playercam.viewportWidth = width;
		//playercam.position.set(player.getX(), player.getY(), 0);
		playercam.update();
		
	}

	@Override
	public void show() {
		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load("maps/level1.tmx");
			Gdx.app.debug("nu", "uzloadino" + map.getLayers().getCount());
		renderer = new OrthogonalTiledMapRenderer(map, 1/2f);
		camera = new OrthographicCamera();
		BGcamera = new OrthographicCamera();
		playercam = new OrthographicCamera();
		bacground = new Texture(Gdx.files.internal("maps/background.png"));
		Gdx.input.setInputProcessor(this);
		player = new Player(new Sprite(new Texture(Gdx.files.internal("entities/sonas1.png"))), (TiledMapTileLayer) map.getLayers().get(0));
		player.setPosition(50, 96);
	}

	@Override
	public void hide() {
		dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		bacground.dispose();
		player.getTexture().dispose();
	}
	
	void update()
	{
		if(Gdx.input.isKeyPressed(Keys.RIGHT) || IsTouched){
			camera.position.x = camera.position.x + 2;
			BGcamera.position.x = BGcamera.position.x + 1;
			BGcamera.update();
			camera.update();
			Gdx.app.debug("klavisas", "playeris" + camera.position.x);
		}	
		if(BGcamera.position.x + Gdx.graphics.getWidth() > 2400){
			BGcamera.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2, 0);
			BGcamera.update();
		}
			
	}
	
	void move()
	{
		if(IsTouched)
			player.MovePlayer();
	}

	@Override
	public boolean keyDown(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		IsTouched = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		IsTouched = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
