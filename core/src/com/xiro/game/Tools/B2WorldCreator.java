/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.xiro.game.Sprites.Brick;
import com.xiro.game.Sprites.Coin;
import com.xiro.game.Sprites.Mario;

/**
 *
 * @author Bradley
 */
public class B2WorldCreator
{

	public B2WorldCreator(World world, TiledMap map)
	{
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject)object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Mario.PPM, (rect.getY() + rect.getHeight() / 2) / Mario.PPM);
			
			body = world.createBody(bdef);
			shape.setAsBox(rect.getWidth() / 2 / Mario.PPM, rect.getHeight() / 2 / Mario.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject)object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Mario.PPM, (rect.getY() + rect.getHeight() / 2) / Mario.PPM);
			
			body = world.createBody(bdef);
			shape.setAsBox(rect.getWidth() / 2 / Mario.PPM, rect.getHeight() / 2 / Mario.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject)object).getRectangle();
			new Brick(world, map, rect);
		}
		for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject)object).getRectangle();
			new Coin(world, map, rect);
		}
	}
	
	
}
