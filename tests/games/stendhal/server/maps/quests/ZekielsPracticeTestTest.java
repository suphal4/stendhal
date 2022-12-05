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
import utilities.ZonePlayerAndNPCTestImpl;

public class ZekielsPracticeTestTest  extends ZonePlayerAndNPCTestImpl{
	private static final int REQUIRED_IRON = 2;
	private static final int REQUIRED_BEESWAX = 6;
	private Player player = null;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		
	}

	@Override
	@Before
	public void setUp(){
		final StendhalRPZone zone = new StendhalRPZone("admin_test");

		new WizardsGuardStatueNPC().configureZone(zone, null);
		
		AbstractQuest quest = new ZekielsPracticalTestQuest();
		quest.addToWorld();
		
		
		//
		}
	@Test
	public void allowToDrop() {
		player = PlayerTestHelper.createPlayer("bob");
		final SpeakerNPC npc = SingletonRepository.getNPCList().get("Zekiel the guardian");	
		Engine en = npc.getEngine();
		
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
		
		assertEquals("Don't you dare drop any items until you have reached the top of the tower!", getReply(npc));
		
		
	}
	

}
