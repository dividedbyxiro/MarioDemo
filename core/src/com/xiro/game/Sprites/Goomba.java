/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.xiro.game.MarioBros;
import com.xiro.game.Screens.PlayScreen;
import static com.xiro.game.Sprites.Mario.PPM;

/**
 *
 * @author mbradley
 */
public class Goomba extends Enemy
{

	private float stateTime;
	private Animation walkAnimation;
	private Array<TextureRegion> frames;
	private boolean setToDestroy;
	private boolean destroyed;
	
	public Goomba(PlayScreen screen, float x, float y)
	{
		super(screen, x, y);
		frames = new Array<TextureRegion>();
		for(int i = 0; i < 2; i++)
		{
			frames.add(new TextureRegion(screen.getAtlas().findRegion("goomba"), i * 16, 0, 16, 16));
		}
		walkAnimation = new Animation(0.4f, frames);
		stateTime = 0;
		setBounds(getX(), getY(), 16 / PPM, 16 / PPM);
		setToDestroy = destroyed = false;
	}

	@Override
	protected void defineEnemy()
	{
		BodyDef bdef = new BodyDef();
//		bdef.position.set(32 / PPM, 50 / PPM);
		bdef.position.set(getX(), getY());
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
//		PolygonShape shape = new PolygonShape();
//		shape.setAsBox(50/PPM, 5/PPM);
		shape.setRadius(6 / PPM);
		fdef.filter.categoryBits = MarioBros.ENEMY_BIT;
		fdef.filter.maskBits
				= (MarioBros.GROUND_BIT
				| MarioBros.COIN_BIT
				| MarioBros.BRICK_BIT
				| MarioBros.ENEMY_BIT
				| MarioBros.OBJECT_BIT
				| MarioBros.MARIO_BIT);
		fdef.shape = shape;
//		fdef.density = 10;
		b2body.createFixture(fdef).setUserData(this);
		
		PolygonShape head = new PolygonShape();
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-5, 8).scl(1 / PPM);
		vertice[1] = new Vector2(5, 8).scl(1 / PPM);
		vertice[2] = new Vector2(-3, 3).scl(1 / PPM);
		vertice[3] = new Vector2(3, 3).scl(1 / PPM);
		head.set(vertice);
		fdef.shape = head;
		fdef.restitution = .5f;
		fdef.filter.categoryBits = MarioBros.ENEMY_HEAD_BIT;
		b2body.createFixture(fdef).setUserData(this);
	}

	@Override
	public void update(float dt)
	{
		stateTime += dt;
		if(setToDestroy && !destroyed)
		{
			world.destroyBody(b2body);
			destroyed = true;
			setRegion(new TextureRegion(screen.getAtlas().findRegion("goomba"), 32, 0, 16, 16));
			stateTime = 0;
		}
		else if (!destroyed)
		{
			b2body.setLinearVelocity(velocity);
			setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
			setRegion((TextureRegion) walkAnimation.getKeyFrame(stateTime, true));
		}
	}

	@Override
	public void hitOnHead()
	{
		setToDestroy = true;
	}

	@Override
	public void draw(Batch batch)
	{
		if(!destroyed || stateTime < 1)
		{
			super.draw(batch);
		} //To change body of generated methods, choose Tools | Templates.
	}
	
	
	
}
