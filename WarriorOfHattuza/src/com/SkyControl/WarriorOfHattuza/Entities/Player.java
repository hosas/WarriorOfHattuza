package com.SkyControl.WarriorOfHattuza.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {
	
	private Vector2 velocity = new Vector2(0,0);
	private float speed = 400;
	private float gravity = 150;
	private TiledMapTileLayer collision;
	
	
	public Player(Sprite sprite, TiledMapTileLayer tileLayer){
		super(sprite);
		this.collision = tileLayer;
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
		
//		if(velocity.x < 0){
//			
//			collisionX = collision.getCell((int)(getX() / tileWidth),(int)((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
//			
//			if(!collisionX)
//				collisionX = collision.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight()/2) / tileHeight)).getTile().getProperties().containsKey("blocked");
//			if(!collisionX)
//				collisionX = collision.getCell((int)(getX() / tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
//			
//		}else if(velocity.x > 0){
//			collisionX = collision.getCell((int)((getX() + getWidth()) / tileWidth), (int)((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
//			
//			if(!collisionX)
//				collisionX =collision.getCell((int)(getX() + getWidth() / tileWidth), (int)((getY() + getHeight() / 2) / tileHeight)).getTile().getProperties().containsKey("nlocked");
//			
//			if(!collisionX)
//				collisionX =collision.getCell((int)(getX() + getWidth()/ tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
//		}
//		
//		if(collisionX){
//			setX(oldX);
//			velocity.x = 0;
//		}
		setY(getY() + velocity.y * delta);
		Gdx.app.debug("gravitacija", "traukimas zemyn" + getY() + velocity.y * delta);
//		if(velocity.y < 0){
//			collisionY = collision.getCell((int)(getX() / tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
//			
//			if(!collisionY)
//				collisionY = collision.getCell((int)((getX() + getWidth() / 2) / tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
//			
//			if(!collisionY)
//				collisionY = collision.getCell((int)((getX() + getWidth()) / tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
//		}else if (velocity.y > 0){
//			collisionY = collision.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight())/tileHeight)).getTile().getProperties().containsKey("blocked");
//			
//			if(!collisionY)
//				collisionY = collision.getCell((int)((getX() + getWidth() / 2) / tileWidth), (int)((getY() + getHeight())/tileHeight)).getTile().getProperties().containsKey("blocked");
//			
//			if(!collisionY)
//				collisionY = collision.getCell((int)((getX() + getWidth()) / tileWidth), (int)((getY() + getHeight())/tileHeight)).getTile().getProperties().containsKey("blocked");
//		}
//		Gdx.app.debug("gravitacija", "traukimas zemyn" + collisionY);
//		if(collisionY){
//			setY(oldY);
//			velocity.y = 0;
//		}
	}
	
	public void MovePlayer()
	{
		setX(getX() + 2);
	}
}
