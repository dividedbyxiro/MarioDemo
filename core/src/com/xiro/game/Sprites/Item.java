/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.xiro.game.MarioBros;
import com.xiro.game.Screens.PlayScreen;

/**
 *
 * @author mbradley
 */
public abstract class Item extends Sprite
{

	protected PlayScreen screen;
	protected World world;
	protected Vector2 velocity;

	protected boolean toDestroy;
	protected boolean destroyed;

	protected Body body;

	public Item(PlayScreen screen, float x, float y)
	{
		System.out.println("starting to make item");
		this.screen = screen;
		this.world = screen.getWorld();
		System.out.println("item.screen and world set");
		setPosition(x, y);
		System.out.println("Position set to " + x + ", " + y);
		System.out.println("getX() is " + getX() + " getY() is " + getY());

		setBounds(getX(), getY(), 16 / MarioBros.PPM, 16 / MarioBros.PPM);

		System.out.println("item setPosition and setRegion done");
		defineItem();
		toDestroy = destroyed = false;
		System.out.println("finishing item constructor");
	}

	public abstract void defineItem();

	public abstract void useItem(Mario mario);

	public void update(float dt)
	{
		if (toDestroy && !destroyed)
		{
			world.destroyBody(body);
			body = null;
//			screen.getItems().removeValue(this, true);
			destroyed = true;
		}
	}

	public void destroy()
	{
		toDestroy = true;
	}

	@Override
	public void draw(Batch batch)
	{
		if (!destroyed)
		{
			super.draw(batch);
		} //To change body of generated methods, choose Tools | Templates.
	}

	public void reverseVelocity(boolean x, boolean y)
	{
		if (x)
		{
			velocity.x *= -1;
		}
		if (y)
		{
			velocity.y *= -1;
		}
	}

}
