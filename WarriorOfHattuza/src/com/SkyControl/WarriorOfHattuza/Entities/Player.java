package com.SkyControl.WarriorOfHattuza.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor {
	
	private Vector2 velocity = new Vector2(0,0);
	private float speed = 400;
	private float jump = 400;
	private float AnimationTime = 0;
	private float gravity = 400;
	private TiledMapTileLayer collision;
	private boolean canjump;
	private boolean face;
	private Animation left, right;
	private Sound walk;
	
	public Player(Animation left, Animation right, Sound walk, TiledMapTileLayer tileLayer){
		super(right.getKeyFrame(0));
		this.collision = tileLayer;
		this.right = right;
		this.left = left;
		this.walk = walk;
	}

	@Override
	public void draw(Batch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
		
	}
	
	void update(float delta){
		velocity.y -= gravity * delta;
		if(velocity.y > speed)
			velocity.y = speed;
		else if(velocity.y < -speed)
			velocity.y = -speed;
		
		
		float oldX = getX();
		float oldY = getY();
		float tileHeight = collision.getTileHeight();
		float tileWidth = collision.getTileWidth();
		boolean collisionX = false, collisionY = false;
		
		
		setX(getX() + velocity.x * delta);
		
		if(velocity.x < 0){
			collisionX = collision.getCell((int)(getX() / tileWidth),(int)((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
			if(!collisionX){
				collisionX = collision.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight()/2) / tileHeight)).getTile().getProperties().containsKey("blocked");
			}
			if(!collisionX)
				collisionX = collision.getCell((int)(getX() / tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
			
		}else if(velocity.x > 0){
			collisionX = collision.getCell((int)((getX() + getWidth()) / tileWidth), (int)((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
			
			if(!collisionX){
				collisionX = collision.getCell((int)((getX() + getWidth()) / tileWidth), (int)((getY() + getHeight() / 2) / tileHeight)).getTile().getProperties().containsKey("blocked");
			}
			if(!collisionX)
				collisionX = collision.getCell((int)((getX() + getWidth())/ tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
		}else{
			face = true;
		}
		
		if(collisionX){
			setX(oldX);
			velocity.x = 0;
		}
		
		
		setY(getY() + velocity.y * delta);
		Gdx.app.debug("gravitacija", "kario kordinates" + getY() + " " + getX());
		if(velocity.y < 0){
			collisionY = collision.getCell((int)(getX() / tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
			
			if(!collisionY)
				collisionY = collision.getCell((int)((getX() + getWidth() / 2) / tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
			
			if(!collisionY)
				collisionY = collision.getCell((int)((getX() + getWidth()) / tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
			canjump = collisionY;
		}else if (velocity.y > 0){
			collisionY = collision.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight())/tileHeight)).getTile().getProperties().containsKey("blocked");
			
			if(!collisionY)
				collisionY = collision.getCell((int)((getX() + getWidth() / 2) / tileWidth), (int)((getY() + getHeight())/tileHeight)).getTile().getProperties().containsKey("blocked");
			
			if(!collisionY)
				collisionY = collision.getCell((int)((getX() + getWidth()) / tileWidth), (int)((getY() + getHeight())/tileHeight)).getTile().getProperties().containsKey("blocked");
		}
		Gdx.app.debug("gravitacija", "traukimas zemyn" + collisionX);
		if(collisionY){
			setY(oldY);
			velocity.y = 0;
		}
		
		AnimationTime += delta;
		setRegion(velocity.x < 0 ? left.getKeyFrame(AnimationTime) : velocity.x > 0 ? right.getKeyFrame(AnimationTime) : right.getKeyFrame(0) );
	}
	

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.D:
			//setFlip(false, false);
			
			velocity.x = speed;
			AnimationTime = 0;
			walk.loop(0.5f);
			break;
		case Keys.A:
		
			//setFlip(true, false);
			
			velocity.x = -speed;
			AnimationTime = 0;
			walk.loop(0.5f);
			break;
		case Keys.W:
			if(canjump){
			velocity.y = jump;
			canjump = false;
			}
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.A:
		case Keys.D:
			velocity.x = 0;
			AnimationTime = 0;
			walk.stop();
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
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
