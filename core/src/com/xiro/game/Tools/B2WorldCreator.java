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
import com.badlogic.gdx.utils.Array;
import com.xiro.game.MarioBros;
import com.xiro.game.Scenes.Hud;
import com.xiro.game.Screens.PlayScreen;
import com.xiro.game.Sprites.Brick;
import com.xiro.game.Sprites.Coin;
import com.xiro.game.Sprites.Goomba;
import com.xiro.game.Sprites.Mario;

/**
 *
 * @author Bradley
 */
public class B2WorldCreator
{

	private Array<Goomba> goombas;


//	public B2WorldCreator(World world, TiledMap map, Hud hud)
	public B2WorldCreator(PlayScreen screen)
	{
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		World world = screen.getWorld();
		TiledMap map = screen.getMap();
		Hud hud = screen.getHud();

		
		
		for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Mario.PPM, (rect.getY() + rect.getHeight() / 2) / Mario.PPM);

			body = world.createBody(bdef);
			shape.setAsBox(rect.getWidth() / 2 / Mario.PPM, rect.getHeight() / 2 / Mario.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Mario.PPM, (rect.getY() + rect.getHeight() / 2) / Mario.PPM);

			body = world.createBody(bdef);
			shape.setAsBox(rect.getWidth() / 2 / Mario.PPM, rect.getHeight() / 2 / Mario.PPM);
			fdef.shape = shape;
			fdef.filter.categoryBits = MarioBros.OBJECT_BIT;
			body.createFixture(fdef);
		}
		for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Brick(screen, rect);
		}
		for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Coin(screen, rect);
		}
		
		goombas = new Array<Goomba>();
		for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			goombas.add(new Goomba(screen, rect.getX() / MarioBros.PPM, rect.getY() / MarioBros.PPM));
		}
	}

	public Array<Goomba> getGoombas()
	{
		return goombas;
	}

}
