/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.xiro.game.MarioBros;
import com.xiro.game.Screens.PlayScreen;

/**
 *
 * @author Bradley
 */
public class Mario extends Sprite
{
	public enum State {FALLING, JUMPING, STANDING, RUNNING};
	public State currentState;
	public State previousState;
	public World world;
	public Body b2body;
	
	private TextureRegion marioStand;
	private Animation marioRun;
	private Animation marioJump;
	private boolean runningRight;
	private float stateTimer;
	
	
	public static final float PPM = 100;
	
	public Mario(World world, PlayScreen screen)
	{
		super(screen.getAtlas().findRegion("little_mario"));
//		super(screen.getAtlas().findRegion("goomba"));
//		super(new TextureRegion(new Texture("Mario_and_Enemies.png"), 0, 0, 20, 20));
		this.world = world;
		defineMario();
		marioStand = new TextureRegion(getTexture(), 1, 11, 16, 16);
		currentState = previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;

//		marioStand = new TextureRegion(getTexture(), 8, 20, 16, 16);
		setBounds(0, 0, 16 / MarioBros.PPM, 16 / MarioBros.PPM);
		setRegion(marioStand);
		
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int i = 1; i < 4; i++)
		{
			frames.add(new TextureRegion(getTexture(), i * 16, 11, 16, 16));
		}
		marioRun = new Animation(.1f, frames);
		frames.clear();
		
		for(int i = 4; i < 6; i++)
		{
			frames.add(new TextureRegion(getTexture(), i * 16, 11, 16, 16));
		}
		marioJump = new Animation(.1f, frames);
		frames.clear();
		
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
		
		EdgeShape head = new EdgeShape();
		head.set(new Vector2(-2 / PPM, 7 / PPM), new Vector2(2 / PPM, 7 / PPM));
		fdef.shape = head;
		fdef.isSensor = true;
		b2body.createFixture(fdef).setUserData("head");
		
		
	}
	
	public void update(float dt)
	{
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
		setRegion(getFrame(dt));
		
	}
	
	public TextureRegion getFrame(float deltaTime)
	{
		currentState = getState();
		
		TextureRegion region;
		
		switch (currentState)
		{
			case JUMPING:
				region = (TextureRegion) marioJump.getKeyFrame(stateTimer);
				break;
			case RUNNING:
				region = (TextureRegion) marioRun.getKeyFrame(stateTimer, true);
				break;
			case FALLING:
			case STANDING:
			default:
				region = marioStand;
				break;
		}
		
		if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX())
		{
			region.flip(true, false);
			runningRight = false;
		}
		else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX())
		{
			region.flip(true, false);
			runningRight = true;
		}
		
		stateTimer = currentState == previousState ? stateTimer + deltaTime : 0;
		previousState = currentState;
		return region;
	}
	
	public State getState()
	{
		if(b2body.getLinearVelocity().y > 0.1 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
		{
			return State.JUMPING;
		}
		if(b2body.getLinearVelocity().y < 0)
		{
			return State.FALLING;
		}
		if(b2body.getLinearVelocity().x != 0)
		{
			return State.RUNNING;
		}
		return State.STANDING;
	}
		
	
	
}
