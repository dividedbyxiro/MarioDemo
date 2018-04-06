/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.xiro.game.MarioBros;
import com.xiro.game.Scenes.Hud;
import com.xiro.game.Screens.PlayScreen;

/**
 *
 * @author Bradley
 */
public class Coin extends InteractiveTileObject
{
	private static TiledMapTileSet tileSet;
	private final int BLANK_COIN = 28;

//	public Coin(World world, TiledMap map, Rectangle bounds, Hud hud)
	public Coin(PlayScreen screen, Rectangle bounds)
	{
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(MarioBros.COIN_BIT);
//		tileSet = map.getTileSets().getTileSet("tileset_gutter");
		tileSet = map.getTileSets().getTileSet(0);
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
//		Gdx.app.log("coin", "hit");
		if(getCell().getTile().getId() == BLANK_COIN)
		{
			MarioBros.assetManager.get("audio/sounds/bump.wav", Sound.class).play();
			return;
		}
		getCell().setTile(tileSet.getTile(BLANK_COIN));
		MarioBros.assetManager.get("audio/sounds/coin.wav", Sound.class).play();
		Gdx.app.log("spawn", "mushroom");
		screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16 / MarioBros.PPM), Mushroom.class));
		System.out.println("Mushroom spawned");
		hud.addScore(200);
	}
}
