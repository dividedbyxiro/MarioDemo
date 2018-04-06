/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.xiro.game.MarioBros;
import com.xiro.game.Screens.PlayScreen;
import static com.xiro.game.Sprites.Mario.PPM;

/**
 *
 * @author mbradley
 */
public class Mushroom extends Item
{

	public Mushroom(PlayScreen screen, float x, float y)
	{
		super(screen, x, y);
		System.out.println("mushroom.super complete");
		setRegion(screen.getAtlas().findRegion("mushroom"), 0, 0, 16, 16);
		velocity = new Vector2(0, 0);
		System.out.println("created mushroom " + x + ", " + y);
	}

	@Override
	public void defineItem()
	{
		BodyDef bdef = new BodyDef();
//		bdef.position.set(32 / PPM, 50 / PPM);
		bdef.position.set(getX(), getY());
		bdef.type = BodyDef.BodyType.DynamicBody;
		body = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
//		PolygonShape shape = new PolygonShape();
//		shape.setAsBox(50/PPM, 5/PPM);
		shape.setRadius(6 / PPM);

		fdef.shape = shape;
//		fdef.density = 10;
		body.createFixture(fdef).setUserData(this);
		System.out.println("mushroom defined");
	}

	@Override
	public void useItem()
	{
		destroy();
	}

	@Override
	public void update(float dt)
	{
		super.update(dt); //To change body of generated methods, choose Tools | Templates.
		setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
		body.setLinearVelocity(velocity);
	}

}
