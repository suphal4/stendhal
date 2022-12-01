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

	@Test
	public void pushTest() {
		HandCart h = new HandCart(0,0);
		StendhalRPZone zone = new StendhalRPZone("test", 10, 10);
	
		Player player = PlayerTestHelper.createPlayer("pusher player");
		zone.add(h);
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getX()));
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getY()));
		
		
		h.push(player, Direction.RIGHT);
		assertEquals(Integer.valueOf(1), Integer.valueOf(h.getX()));
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getY()));

		h.push(player, Direction.UP);
		assertEquals(Integer.valueOf(1), Integer.valueOf(h.getX()));
		assertEquals(Integer.valueOf(1), Integer.valueOf(h.getY()));
		
		h.push(player, Direction.LEFT);
		assertEquals(Integer.valueOf(1), Integer.valueOf(h.getX()));
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getY()));
		
		h.push(player, Direction.DOWN);
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getX()));
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getY()));
		
	}

	@Test
	public final void coordsTest() {
		HandCart h = new HandCart(0,0);
//		h.setThePosition(0,0);
		
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getXAfterPush(Direction.UP)));
		assertEquals(Integer.valueOf(-1), Integer.valueOf(h.getYAfterPush(Direction.UP)));

		assertEquals(Integer.valueOf(1), Integer.valueOf(h.getXAfterPush(Direction.RIGHT)));
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getYAfterPush(Direction.RIGHT)));

		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getXAfterPush(Direction.DOWN)));
		assertEquals(Integer.valueOf(1), Integer.valueOf(h.getYAfterPush(Direction.DOWN)));

		assertEquals(Integer.valueOf(-1), Integer.valueOf(h.getXAfterPush(Direction.LEFT)));
		assertEquals(Integer.valueOf(0), Integer.valueOf(h.getYAfterPush(Direction.LEFT)));

	}
	
	@Test
	public final void collisionTest() {
		HandCart h = new HandCart(0,0);
//		h.setThePosition(0,0);
		StendhalRPZone zone = new StendhalRPZone("test", 10, 10);
		Player player = PlayerTestHelper.createPlayer("pusher player");
		zone.add(h, false);
		
		h.push(player, Direction.UP);
		assertEquals(Integer.valueOf(1), Integer.valueOf(h.getY()));

		
		//make collision happen
		//h doesn't move upwards any further if h2 is in its way
		HandCart h2 = new HandCart(0,0);
//		h2.setThePosition(0,2);
		zone.add(h2, false);
		h.push(player, Direction.UP);
		assertEquals(Integer.valueOf(1), Integer.valueOf(h.getY()));
		
	}
	
	@Test
	public final void nopushifopenTest() {
		HandCart h = new HandCart(0,0);
//		h.setThePosition(0,0);
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
