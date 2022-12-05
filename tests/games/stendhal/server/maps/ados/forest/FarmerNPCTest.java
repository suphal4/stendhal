package games.stendhal.server.maps.ados.forest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;
import utilities.RPClass.BlockTestHelper;
import static utilities.SpeakerNPCTestHelper.*;





public class FarmerNPCTest extends ZonePlayerAndNPCTestImpl {
	private static final String ZONE_NAME = "0_ados_forest_w2";
	private Player player = null;
	private SpeakerNPC npc = null;
	private Engine en = null;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		BlockTestHelper.generateRPClasses();
		PlayerTestHelper.generatePlayerRPClasses();
		setupZone(ZONE_NAME);
		
	}
	
	@Before
	public void setUp() {
		final StendhalRPZone zone = new StendhalRPZone(ZONE_NAME, 200, 200);
		MockStendlRPWorld.get().addRPZone(zone);
		new FarmerNPC().configureZone(zone, null);
		player = PlayerTestHelper.createPlayer("miranda");
		setNpcNames("Karl");
		addZoneConfigurator(new FarmerNPC(), ZONE_NAME);
		
	}

	
	//TEST for only high level players
	@Test
	public void highLevelTest() {
		npc = getNPC("Karl");
		en = npc.getEngine();
		player.setLevel(10);
		en.step(player, "hello");
		en.step(player, "handcart");
		assertEquals("You're not at a high enough level to summon the cart. Keep working!", getReply(npc));
		en.step(player, "goodbye");
		
		player.setLevel(510);
		en.step(player, "hello");
		en.step(player, "handcart");
		assertEquals("Here is your hand cart!", getReply(npc));
		en.step(player, "goodbye");
	}
	
}
	