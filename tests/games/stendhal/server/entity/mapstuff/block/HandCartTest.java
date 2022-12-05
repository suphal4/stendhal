/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
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
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utilities.PlayerTestHelper;
import games.stendhal.common.Direction;
import games.stendhal.server.core.engine.StendhalRPZone;
import utilities.RPClass.BlockTestHelper;


public class HandCartTest {
	@Before
	public void setUp() throws Exception {
		BlockTestHelper.generateRPClasses();
		PlayerTestHelper.generatePlayerRPClasses();
		MockStendlRPWorld.get();
		
	}

	
	//test that the cart can be added to the zone and then pushed 
	@Test
	public void pushTest() {
		HandCart h = new HandCart(0,0);
		
		StendhalRPZone zone = new StendhalRPZone("test", 10, 10);
		
		Player player = PlayerTestHelper.createPlayer("pusher player");
		h.setPosition(0, 0);
		zone.add(h);
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getX()));
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getY()));
		//check if cart can be pushed, and coordinate value changes
		h.push(player, Direction.RIGHT);
		assertEquals(Integer.valueOf(1), Integer.valueOf(h.getX()));
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getY()));
		
			
	}

	
	//test that the coordinates of the pushed cart is correct
	//checks each type of push (left, right, up, down)
	@Test
	public final void coordsTest() {
		HandCart h = new HandCart(0,0);
		
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getXAfterPush(Direction.UP)));
		assertEquals(Integer.valueOf(-1), Integer.valueOf(h.getYAfterPush(Direction.UP)));

		assertEquals(Integer.valueOf(1), Integer.valueOf(h.getXAfterPush(Direction.RIGHT)));
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getYAfterPush(Direction.RIGHT)));

		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getXAfterPush(Direction.DOWN)));
		assertEquals(Integer.valueOf(1), Integer.valueOf(h.getYAfterPush(Direction.DOWN)));

		assertEquals(Integer.valueOf(-1), Integer.valueOf(h.getXAfterPush(Direction.LEFT)));
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getYAfterPush(Direction.LEFT)));

	}
	
	
	//test the handcarts reaction to a collision
	@Test
	public final void collisionTest() {
		HandCart h = new HandCart(0,0);
		h.setPosition(0,0);
		StendhalRPZone zone = new StendhalRPZone("test", 10, 10);
		Player player = PlayerTestHelper.createPlayer("pusher player");
		zone.add(h, false);
		
		h.push(player, Direction.RIGHT);
		assertEquals(Integer.valueOf(1), Integer.valueOf(h.getX()));

		
		//make collision happen
		//h doesn't move upwards any further if h2 is in its way
		HandCart h2 = new HandCart(0,0);
		h2.setPosition(2,0);
		zone.add(h2, false);
		h.push(player, Direction.RIGHT);
		assertEquals(Integer.valueOf(1), Integer.valueOf(h.getX()));
		
	}
	
	//test that the handcart cannot be pushed if it is open
	//test that the handcart can be opened and closed
	@Test
	public final void nopushifopenTest() {
		HandCart h = new HandCart(0,0);
		StendhalRPZone zone = new StendhalRPZone("test", 10, 10);
		Player player = PlayerTestHelper.createPlayer("pusher player");
		zone.add(h, false);
		
		assertFalse(h.isOpen());
		h.open();
		assertTrue(h.isOpen());
		
		h.push(player, Direction.UP);
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getY()));
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getX()));
	}
	
}
