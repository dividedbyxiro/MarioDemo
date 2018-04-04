/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.xiro.game.Screens.PlayScreen;

/**
 *
 * @author mbradley
 */
public abstract class Enemy extends Sprite
{
	protected World world;
	protected PlayScreen screen;
	public Body b2body;
	public Vector2 velocity;
	
	public Enemy(PlayScreen screen, float x, float y)
	{
		this.world = screen.getWorld();
		this.screen = screen;
		setPosition(x, y);
		defineEnemy();
		velocity = new Vector2(.5f, 0);
	}
	
	protected abstract void defineEnemy();
	public abstract void hitOnHead();
	
	public void reverseVelocity(boolean x, boolean y)
	{
		if(x)
		{
			velocity.x *= -1;
		}
		if(y)
		{
			velocity.y *= -1;
		}
	}
}
