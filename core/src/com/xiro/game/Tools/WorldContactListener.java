/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.xiro.game.MarioBros;
import com.xiro.game.Sprites.Enemy;
import com.xiro.game.Sprites.InteractiveTileObject;
import com.xiro.game.Sprites.Item;
import com.xiro.game.Sprites.Mario;
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author mbradley
 */
public class WorldContactListener implements ContactListener
{

	@Override
	public void beginContact(Contact contact)
	{
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

		if (fixA.getUserData() == "head" || fixB.getUserData() == "head")
		{
			Fixture head = fixA.getUserData().equals("head") ? fixA : fixB;
			Fixture object = head == fixA ? fixB : fixA;

			if (object.getUserData() != null && object.getUserData() instanceof InteractiveTileObject)
			{
				((InteractiveTileObject) object.getUserData()).onHeadHit();
			}
		}
		else switch(cDef)
		{
			case MarioBros.ENEMY_HEAD_BIT | MarioBros.MARIO_BIT:
				if(fixA.getFilterData().categoryBits == MarioBros.ENEMY_HEAD_BIT)
				{
					((Enemy)fixA.getUserData()).hitOnHead();
				}
				else
				{
					((Enemy)fixB.getUserData()).hitOnHead();
				}
				break;
			case MarioBros.ENEMY_BIT | MarioBros.OBJECT_BIT:
				if(fixA.getFilterData().categoryBits == MarioBros.ENEMY_BIT)
				{
					((Enemy)fixA.getUserData()).reverseVelocity(true, false);
				}
				else
				{
					((Enemy)fixB.getUserData()).reverseVelocity(true, false);
				}
				break;
			case MarioBros.ENEMY_BIT | MarioBros.MARIO_BIT:
				Gdx.app.log("Mario", "ded");
				break;
			case MarioBros.ENEMY_BIT:
				((Enemy)fixA.getUserData()).reverseVelocity(true, false);
				((Enemy)fixB.getUserData()).reverseVelocity(true, false);
				break;
			case MarioBros.ITEM_BIT | MarioBros.OBJECT_BIT:
				if(fixA.getFilterData().categoryBits == MarioBros.ITEM_BIT)
				{
					((Item)fixA.getUserData()).reverseVelocity(true, false);
				}
				else
				{
					((Item)fixB.getUserData()).reverseVelocity(true, false);
				}
				break;
			case MarioBros.ITEM_BIT | MarioBros.MARIO_BIT:
				if(fixA.getFilterData().categoryBits == MarioBros.ITEM_BIT)
				{
//					System.out.println("fixA " + fixA + "collided with fixB " + fixB);
					((Item)fixA.getUserData()).useItem((Mario)fixB.getUserData());
				}
				else
				{
//					System.out.println("fixB " + fixB + "collided with fixA " + fixA);
					((Item)fixB.getUserData()).useItem((Mario)fixA.getUserData());
				}
				break;
				
		}
	}

	@Override
	public void endContact(Contact contact)
	{

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold)
	{
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse)
	{
	}

}
