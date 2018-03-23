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
import com.xiro.game.Sprites.InteractiveTileObject;
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

		if (fixA.getUserData() == "head" || fixB.getUserData() == "head")
		{
			Fixture head = fixA.getUserData().equals("head") ? fixA : fixB;
			Fixture object = head == fixA ? fixB : fixA;

			if (object.getUserData() != null && object.getUserData() instanceof InteractiveTileObject)
			{
				((InteractiveTileObject) object.getUserData()).onHeadHit();
			}
		}
//		Fixture head;
//		Fixture object = fixB;
//		if(fixA.getUserData().equals("head"))
//		{
//			head = fixA;
//			object = fixB;
//		}
//		else if(fixB.getUserData().equals("head"))
//		{
//			head = fixB;
//			object = fixA;
//		}
//		else
//		{
//			return;
//		}
//		
//		if(object.getUserData() instanceof InteractiveTileObject)
//		{
//			((InteractiveTileObject)object.getUserData()).onHeadHit();
//			
//		}
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
