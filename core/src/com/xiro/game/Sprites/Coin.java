/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.xiro.game.MarioBros;

/**
 *
 * @author Bradley
 */
public class Coin extends InteractiveTileObject
{

	public Coin(World world, TiledMap map, Rectangle bounds)
	{
		super(world, map, bounds);
		fixture.setUserData(this);
		setCategoryFilter(MarioBros.COIN_BIT);
//		BodyDef bdef = new BodyDef();
//		FixtureDef fdef = new FixtureDef();
//		PolygonShape shape = new PolygonShape();
//
//		bdef.type = BodyDef.BodyType.StaticBody;
//		bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / Mario.PPM, (bounds.getY() + bounds.getHeight() / 2) / Mario.PPM);
//
//		body = world.createBody(bdef);
//		shape.setAsBox(bounds.getWidth() / 2 / Mario.PPM, bounds.getHeight() / 2 / Mario.PPM);
//		fdef.shape = shape;
//		body.createFixture(fdef);
	}

	@Override
	public void onHeadHit()
	{
		Gdx.app.log("coin", "hit");
	}
}
