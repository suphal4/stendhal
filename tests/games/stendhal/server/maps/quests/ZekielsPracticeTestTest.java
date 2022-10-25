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

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.semos.wizardstower.WizardsGuardStatueNPC;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;

public class ZekielsPracticeTestTest{
	private static final int REQUIRED_IRON = 2;
	private static final int REQUIRED_BEESWAX = 6;
	
	private Player player = null;
	private SpeakerNPC npc = null;
	private Engine en = null;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		
	}

	@Before
	public void setUp(){
		final StendhalRPZone zone = new StendhalRPZone("admin_test");

		new WizardsGuardStatueNPC().configureZone(zone, null);
		
		AbstractQuest quest = new ZekielsPracticalTestQuest();
		quest.addToWorld();
		
		
		//
		}
	@Test
	public void enterFloorWithNoCandlesTest() {
		player = PlayerTestHelper.createPlayer("bob");
		final SpeakerNPC npc = SingletonRepository.getNPCList().get("Zekiel the guardian");	
		Engine en = npc.getEngine();
		
		
		 
		
	}
	

	@Test
	public void enter2ndFloorWithNoCandlesTest() {
		player = PlayerTestHelper.createPlayer("bob");
		final SpeakerNPC npc = SingletonRepository.getNPCList().get("Zekiel the guardian");	
		 Engine en = npc.getEngine();
		// set quest to "start"

		en.step(player, "hi");
		assertEquals("Greetings Stranger! I am Zekiel the #guardian.", getReply(npc));
		en.step(player, "test");
		assertEquals("The practical test will be your #quest from me.", getReply(npc));
		en.step(player, "quest");
		assertEquals("First you need six magic candles. Bring me six pieces of #beeswax and two pieces of #iron, then I will summon the candles for you. After this you can start the practical test.", getReply(npc));
		en.step(player, "bye");
		assertEquals("So long!", getReply(npc));
		
		PlayerTestHelper.equipWithStackableItem(player, "iron", REQUIRED_IRON);
		PlayerTestHelper.equipWithStackableItem(player, "beeswax", REQUIRED_BEESWAX);
		
		en.step(player, "hi");
		assertEquals("Greetings, finally you have brought me all ingredients that I need to summon the magic candles. Now you can #start with the practical test.", getReply(npc));
		en.step(player, "send");

		player.setPosition(25,15);
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		assertTrue(player.isEquipped("candle"));
		//check if when player goes over sparkely section, candles in bag empty
		en.step(player, "/use #15");
		assertFalse(player.isEquipped("candle"));
}
	

	@Test
	public void enter3rdFloorWithNoCandlesTest() {
		player = PlayerTestHelper.createPlayer("bob");
		final SpeakerNPC npc = SingletonRepository.getNPCList().get("Zekiel the guardian");	
		Engine en = npc.getEngine();
		// set quest to "start"

		en.step(player, "hi");
		en.step(player, "quest");
		en.step(player, "bye");
		PlayerTestHelper.equipWithStackableItem(player, "iron", REQUIRED_IRON);
		PlayerTestHelper.equipWithStackableItem(player, "beeswax", REQUIRED_BEESWAX);
		
		en.step(player, "hi");
		
		en.step(player, "start");
		en.step(player, "send");
		en.step(player, "/use #12");
		en.step(player, "/use #15"); //go to 2nd floor
		
		en.step(player, "/use #12");
		PlayerTestHelper.equipWithStackableItem(player, "candle", 2);
		en.step(player, "/use #14"); //go to 3rd floor
		assertFalse(player.isEquipped("candle"));
}
	
	@Test
	public void enter4thFloorWithNoCandlesTest() {
		player = PlayerTestHelper.createPlayer("bob");
		final SpeakerNPC npc = SingletonRepository.getNPCList().get("Zekiel the guardian");	
		Engine en = npc.getEngine();
		// set quest to "start"

		en.step(player, "hi");
		assertEquals("", getReply(npc));
		en.step(player, "test");
		assertEquals("", getReply(npc));
		en.step(player, "quest");
		assertEquals("", getReply(npc));
		en.step(player, "bye");
		assertEquals("", getReply(npc));
		PlayerTestHelper.equipWithStackableItem(player, "iron", REQUIRED_IRON);
		PlayerTestHelper.equipWithStackableItem(player, "beeswax", REQUIRED_BEESWAX);
	
		en.step(player, "hi");
		assertEquals("", getReply(npc));
		en.step(player, "start");
		assertEquals("", getReply(npc));
		en.step(player, "send");
		assertEquals("", getReply(npc));
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
		final SpeakerNPC npc = SingletonRepository.getNPCList().get("Zekiel the guardian");	
		
		Engine en = npc.getEngine();
		// set quest to "start"

		en.step(player, "hi");
		en.step(player, "quest");
		PlayerTestHelper.equipWithStackableItem(player, "iron", REQUIRED_IRON);
		PlayerTestHelper.equipWithStackableItem(player, "beeswax", REQUIRED_BEESWAX);
		en.step(player, "bye");
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
		final SpeakerNPC npc = SingletonRepository.getNPCList().get("Zekiel the guardian");	
		
		Engine en = npc.getEngine();
		// set quest to "start"

		en.step(player, "hi");
		en.step(player, "quest");
		
		en.step(player, "bye");
		
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
		final SpeakerNPC npc = SingletonRepository.getNPCList().get("Zekiel the guardian");	
		
		Engine en = npc.getEngine();
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
