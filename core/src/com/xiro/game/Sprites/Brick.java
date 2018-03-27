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
import com.xiro.game.MarioBros;
import com.xiro.game.Scenes.Hud;

/**
 *
 * @author Bradley
 */
public class Brick extends InteractiveTileObject
{
	public Brick(World world, TiledMap map, Rectangle bounds, Hud hud)
	{
		super(world, map, bounds, hud);
		fixture.setUserData(this);
		setCategoryFilter(MarioBros.BRICK_BIT);
	}

	@Override
	public void onHeadHit()
	{
		Gdx.app.log("brick", "hit");
		setCategoryFilter(MarioBros.DESTROYED_BIT);
		getCell().setTile(null);
		hud.addScore(100);
	}
	
}
