/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xiro.game.MarioBros;
import com.xiro.game.Scenes.Hud;
import com.xiro.game.Sprites.Mario;
import com.xiro.game.Tools.B2WorldCreator;

/**
 *
 * @author Bradley
 */
public class PlayScreen implements Screen
{
	private MarioBros game;

	private OrthographicCamera gameCam;
	private Viewport gamePort;
	private Hud hud;
	
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	private World world;
	private Box2DDebugRenderer b2dr;
	
	private Mario mario;
	
	
	public PlayScreen(MarioBros game)
	{
		this.game = game;

		gameCam = new OrthographicCamera();
		gamePort = new FitViewport(MarioBros.V_WIDTH / Mario.PPM, MarioBros.V_HEIGHT / Mario.PPM, gameCam);
		hud = new Hud(game.batch);
		
		mapLoader = new TmxMapLoader();
		map = mapLoader.load("level1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / Mario.PPM);
		
		gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		
		world = new World(new Vector2(0, -10f), true);
		mario = new Mario(world);
		
		b2dr = new Box2DDebugRenderer();
		
		new B2WorldCreator(world, map);
		
		
	}
	
	public void handleInput(float dt)
	{
//		if(Gdx.input.isTouched())
//		{
//			gameCam.position.x += 100 * dt;
//		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
		{
//			mario.b2body.applyLinearImpulse(0, 4, mario.b2body.getWorldCenter().x - .5f, mario.b2body.getWorldCenter().y, true);
			mario.b2body.applyLinearImpulse(new Vector2(0, 4), mario.b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mario.b2body.getLinearVelocity().x < 2)
		{
			mario.b2body.applyLinearImpulse(new Vector2(.1f, 0), mario.b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && mario.b2body.getLinearVelocity().x > -2)
		{
			mario.b2body.applyLinearImpulse(new Vector2(-.1f, 0), mario.b2body.getWorldCenter(), true);
		}
	}
	
	public void update(float dt)
	{
		handleInput(dt);
		
		world.step(1/60f, 6, 2);
		gameCam.position.x = mario.b2body.getWorldCenter().x;
		gameCam.update();
		
		renderer.setView(gameCam);
	}

	@Override
	public void show()
	{
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void render(float delta)
	{
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
		
		b2dr.SHAPE_STATIC.set(1, 0, 0, 1);
		b2dr.SHAPE_AWAKE.set(1,0,0,1);
		b2dr.render(world, gameCam.combined);
	}

	@Override
	public void resize(int width, int height)
	{
		gamePort.update(width, height);
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void pause()
	{
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void resume()
	{
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void hide()
	{
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void dispose()
	{
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
