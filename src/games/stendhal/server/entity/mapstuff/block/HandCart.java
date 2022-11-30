/* $Id$ */
/***************************************************************************
 *                      (C) Copyright 2003 - Marauroa                      *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.entity.mapstuff.block;

import games.stendhal.common.Direction;
import games.stendhal.server.entity.player.Player;

/**
 * A chest is an unmovable container. It can be opened and closed. While it is
 * open, every player can put items in and take them out later. A player can
 * take out items that another player put in.
 */
public class HandCart{
	
		
	

	/**
	 * Creates a new chest.
	 *
	 * @param object
	 *            RPObject
	 */
	

	public HandCart(int i, int j) {
		// TODO Auto-generated constructor stub
	}

	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setThePosition(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	public void push(Player player, Direction right) {
		// TODO Auto-generated method stub
		
	}

	public String getXAfterPush(Direction up) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getYAfterPush(Direction up) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	public void open() {
		// TODO Auto-generated method stub
		
	}

}
