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


public class ZekielsPracticeTestTest {
	private static final String QUEST_SLOT = "zekiels_practical_test";
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
	public void setUp() {
		final StendhalRPZone zone = new StendhalRPZone("admin_test");

		new WizardsGuardStatueNPC().configureZone(zone, null);
		
		AbstractQuest quest = new ZekielsPracticalTestQuest();
		quest.addToWorld();
		
		player = PlayerTestHelper.createPlayer("bob");
		//
		}

	@Test
	public void enter1stStepWithNoCandlesTest() {
		final SpeakerNPC npc = SingletonRepository.getNPCList().get("Zekiel the guardian");	
		final Engine en = npc.getEngine();
		// set quest to "start"

		// -----------------------------------------------


		// -----------------------------------------------

		// [23:00] Admin kymara changed your state of the quest 'sad_scientist' from 'done' to 'null'
		// [23:00] Changed the state of quest 'sad_scientist' from 'done' to 'null'
		// [23:00] Script "AlterQuest.class" was successfully executed.
		//PlayerTestHelper.equipWithStackableItem(player, "iron", REQUIRED_IRON);
		//PlayerTestHelper.equipWithStackableItem(player, "beeswax", REQUIRED_BEESWAX);
		
		
		
		en.step(player, "hi");
		en.step(player, "quest");
		assertEquals("You haven't finished the practical test. You can #start with it, or you can learn more about the #wizards before you begin.", getReply(npc));
		en.step(player, "send");
		

}
}
