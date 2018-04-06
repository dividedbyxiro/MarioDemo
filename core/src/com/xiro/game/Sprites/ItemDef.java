/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.Sprites;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author mbradley
 */
public class ItemDef
{
	public Vector2 position;
	public Class<?> type;

	public ItemDef(Vector2 position, Class<?> type)
	{
		this.position = position;
		this.type = type;
	}
	
	
}
