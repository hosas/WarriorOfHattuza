package com.SkyControl.WarriorOfHattuza.Screens;

import com.SkyControl.WarriorOfHattuza.Entities.MinionRank1;
import com.SkyControl.WarriorOfHattuza.Entities.MinionRank2;
import com.SkyControl.WarriorOfHattuza.Entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameScreen implements Screen{

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private OrthographicCamera BGcamera;
	private OrthographicCamera playercam;
	private Texture bacground;
	private Player player;
	private MinionRank1 rank1;
	private MinionRank2 rank2;
	private boolean IsTouched = false;
	private TextureAtlas PlayerAtlas;
	private Sound walk;
	Animation right, left;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.setView(BGcamera);
		update();
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
		rank1.draw(renderer.getSpriteBatch());
		rank2.draw(renderer.getSpriteBatch());
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
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		BGcamera = new OrthographicCamera();
		playercam = new OrthographicCamera();
		bacground = new Texture(Gdx.files.internal("maps/background.png"));
		PlayerAtlas = new TextureAtlas("entities/Player.pack");
		right = new Animation(1/20f, PlayerAtlas.findRegions("right"));
		left = new Animation(1/20f, PlayerAtlas.findRegions("left"));
		right.setPlayMode(Animation.LOOP);
		left.setPlayMode(Animation.LOOP);
		walk = Gdx.audio.newSound(Gdx.files.internal("entities/zingsnis.wav"));
		player = new Player(left, right, walk, (TiledMapTileLayer) map.getLayers().get("collision"));
		rank1 = new MinionRank1(new Sprite(new Texture(Gdx.files.internal("entities/MRsonas.png"))), (TiledMapTileLayer) map.getLayers().get("collision"));
		rank2 = new MinionRank2(new Sprite(new Texture(Gdx.files.internal("entities/MR2sonas.png"))), (TiledMapTileLayer) map.getLayers().get("collision"));
		player.setPosition(500, 400);
		rank1.setPosition(200, 400);
		rank2.setPosition(800, 400);
		Gdx.input.setInputProcessor(player);
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
		PlayerAtlas.dispose();
		walk.dispose();
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
	

	

}
