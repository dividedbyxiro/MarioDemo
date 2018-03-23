/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Bradley
 */
public class Brick extends InteractiveTileObject
{
	public Brick(World world, TiledMap map, Rectangle bounds)
	{
		super(world, map, bounds);
		fixture.setUserData(this);
	}

	@Override
	public void onHeadHit()
	{
		Gdx.app.log("brick", "hit");
	}
	
}
