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
package games.stendhal.server.maps.quests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.entity.Entity;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.mapstuff.portal.HousePortal;
import games.stendhal.server.entity.mapstuff.portal.Portal;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.server.maps.semos.wizardstower.WizardsGuardStatueNPC;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;
import utilities.RPClass.EntityTestHelper;
import utilities.RPClass.PortalTestHelper;


public class ZekielsPracticeTestTest extends ZonePlayerAndNPCTestImpl{
	private static final int REQUIRED_IRON = 2;
	private static final int REQUIRED_BEESWAX = 6;
	
	private Player player = null;
	private SpeakerNPC npc = null;
	private Engine en = null;
	private static final String ZONE_NAME = "int_semos_wizards_tower_basement";
	private static final String ZONE_NAME1 = "int_semos_wizards_tower_1";
	private static final String ZONE_NAME2 = "int_semos_wizards_tower_2";
	private static final String ZONE_NAME3 = "int_semos_wizards_tower_3";
	private static final String ZONE_NAME4 = "int_semos_wizards_tower_4";
	private static final String ZONE_NAME5 = "int_semos_wizards_tower_5";
	private static final String ZONE_NAME6 = "int_semos_wizards_tower_6";
	private static final String ZONE_NAME7 = "int_semos_wizards_tower_7";
	private static final String ZONE_NAME8 = "int_semos_wizards_tower_8";
	private static final String ZONE_NAME9 = "int_semos_wizards_tower_9";
	
	private LinkedList<HousePortal> portals = new LinkedList<HousePortal>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		HousePortal.generateRPClass();
		setupZone(ZONE_NAME);
		setupZone(ZONE_NAME1);
		setupZone(ZONE_NAME2);
		setupZone(ZONE_NAME3);
		setupZone(ZONE_NAME4);
		setupZone(ZONE_NAME5);
		setupZone(ZONE_NAME6);
		setupZone(ZONE_NAME7);
		
	}

	@Override
	@Before
	public void setUp() throws Exception{
		super.setUp();
		final StendhalRPZone zone = new StendhalRPZone("admin_test");

		new WizardsGuardStatueNPC().configureZone(zone, null);
		
		quest = new ZekielsPracticalTestQuest();
		quest.addToWorld();
		
		
		
		
		//
		}

	@Test
	public void enter2ndFloorWithNoCandlesTest() {
		player = PlayerTestHelper.createPlayer("bob");
		final SpeakerNPC npc = SingletonRepository.getNPCList().get("Zekiel the guardian");	
		final Engine en = npc.getEngine();
		// set quest to "start"

		en.step(player, "hi");
		en.step(player, "quest");
		PlayerTestHelper.equipWithStackableItem(player, "iron", REQUIRED_IRON);
		PlayerTestHelper.equipWithStackableItem(player, "beeswax", REQUIRED_BEESWAX);
		en.step(player, "hi");
		en.step(player, "start");
		en.step(player, "send");
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
	
		//check if when player goes over sparkly section, candles in bag empty
		en.step(player, "/use #15");
		assertFalse(player.isEquipped("candle"));
		
	

}
	

	@Test
	public void enter3rdFloorWithNoCandlesTest() {
		player = PlayerTestHelper.createPlayer("bob");
		final Engine en = npc.getEngine();
		// set quest to "start"

		en.step(player, "hi");
		en.step(player, "quest");
		PlayerTestHelper.equipWithStackableItem(player, "iron", REQUIRED_IRON);
		PlayerTestHelper.equipWithStackableItem(player, "beeswax", REQUIRED_BEESWAX);
		en.step(player, "hi");
		en.step(player, "start");
		en.step(player, "send");
		en.step(player, "/use #12");
		en.step(player, "/use #15"); //go to 2nd floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 3rd floor
		assertFalse(player.isEquipped("candle"));
}
	
	@Test
	public void enter4thFloorWithNoCandlesTest() {
		player = PlayerTestHelper.createPlayer("bob");
		final Engine en = npc.getEngine();
		// set quest to "start"

		en.step(player, "hi");
		en.step(player, "quest");
		PlayerTestHelper.equipWithStackableItem(player, "iron", REQUIRED_IRON);
		PlayerTestHelper.equipWithStackableItem(player, "beeswax", REQUIRED_BEESWAX);
		en.step(player, "hi");
		en.step(player, "start");
		en.step(player, "send");
		en.step(player, "/use #12");
		en.step(player, "/use #15"); //go to 2nd floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 3rd floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 4th floor
		assertFalse(player.isEquipped("candle"));

}
	
	@Test
	public void enter5thFloorWithNoCandlesTest() {
		player = PlayerTestHelper.createPlayer("bob");
		final Engine en = npc.getEngine();
		// set quest to "start"

		en.step(player, "hi");
		en.step(player, "quest");
		PlayerTestHelper.equipWithStackableItem(player, "iron", REQUIRED_IRON);
		PlayerTestHelper.equipWithStackableItem(player, "beeswax", REQUIRED_BEESWAX);
		en.step(player, "hi");
		en.step(player, "start");
		en.step(player, "send");
		en.step(player, "/use #12");
		en.step(player, "/use #15"); //go to 2nd floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 3rd floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 4th floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 5th floor
		assertFalse(player.isEquipped("candle"));

}
	
	@Test
	public void enter6thFloorWithNoCandlesTest() {
		player = PlayerTestHelper.createPlayer("bob");
		final Engine en = npc.getEngine();
		// set quest to "start"

		en.step(player, "hi");
		en.step(player, "quest");
		PlayerTestHelper.equipWithStackableItem(player, "iron", REQUIRED_IRON);
		PlayerTestHelper.equipWithStackableItem(player, "beeswax", REQUIRED_BEESWAX);
		en.step(player, "hi");
		en.step(player, "start");
		en.step(player, "send");
		en.step(player, "/use #12");
		en.step(player, "/use #15"); //go to 2nd floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 3rd floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 4th floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 5th floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 6th floor
		assertFalse(player.isEquipped("candle"));

}
	@Test
	public void enterSpireWithNoCandlesTest() {
		player = PlayerTestHelper.createPlayer("bob");
		final Engine en = npc.getEngine();
		// set quest to "start"

		en.step(player, "hi");
		en.step(player, "quest");
		PlayerTestHelper.equipWithStackableItem(player, "iron", REQUIRED_IRON);
		PlayerTestHelper.equipWithStackableItem(player, "beeswax", REQUIRED_BEESWAX);
		en.step(player, "hi");
		en.step(player, "start");
		en.step(player, "send");
		en.step(player, "/use #12");
		en.step(player, "/use #15"); //go to 2nd floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 3rd floor
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 4th floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 5th floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to 6th floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #15"); //go to spire
		assertFalse(player.isEquipped("candle"));

}
	
	
}
