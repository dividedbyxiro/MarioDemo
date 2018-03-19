/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.xiro.game.MarioBros;
import com.xiro.game.Screens.PlayScreen;

/**
 *
 * @author Bradley
 */
public class Mario extends Sprite
{
	public World world;
	public Body b2body;
	
	private TextureRegion marioStand;
	
	public static final float PPM = 100;
	
	public Mario(World world, PlayScreen screen)
	{
		super(screen.getAtlas().findRegion("little_mario"));
		this.world = world;
		defineMario();
		marioStand = new TextureRegion(getTexture(), 1, 11, 16, 16);
		setBounds(0, 0, 16 / MarioBros.PPM, 16 / MarioBros.PPM);
		setRegion(marioStand);
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
		shape.setRadius(6 / PPM);
		fdef.shape = shape;
//		fdef.density = 10;
		b2body.createFixture(fdef);
		
	}
	
	public void update(float dt)
	{
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
	}
		
	
	
}
