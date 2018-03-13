/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Bradley
 */
public class Mario extends Sprite
{
	public World world;
	public Body b2body;
	
	public static final float PPM = 100;
	
	public Mario(World world)
	{
		this.world = world;
		defineMario();
	}
	
	public void defineMario()
	{
		BodyDef bdef = new BodyDef();
		bdef.position.set(32 / PPM, 50 / PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
//		PolygonShape shape = new PolygonShape();
//		shape.setAsBox(50/PPM, 5/PPM);
		shape.setRadius(5 / PPM);
		fdef.shape = shape;
//		fdef.density = 10;
		b2body.createFixture(fdef);
		
	}
	
		
	
	
}
