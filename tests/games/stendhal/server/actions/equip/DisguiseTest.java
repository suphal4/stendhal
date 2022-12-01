// $Id$
package games.stendhal.server.actions.equip;

import static org.junit.Assert.assertEquals;

import org.junit.Test;



import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;
import java.util.Random;
import games.stendhal.server.entity.item.*;

public class DisguiseTest{

	@Test
	
	/*This test checks if the player is made invisible while wearing the elvish armor in a nalwor zone
	 * 
	 * */
	public void WearingArmorInNalworTest(){
		//picks a nalwor zone at random and loads it
		String[] zoneList= {"-1_nalwor_caves", "-1_nalwor_caves_e", "-1_nalwor_caves_w", "-1_nalwor_drows_tunnel", "-1_nalwor_drows_tunnel_n", "-1_nalwor_drows_tunnel_ne", "-1_nalwor_drows_tunnel_nw", "-2_nalwor_drows_tunnel_nw, 0_nalwor_city", "0_nalwor_forest_e", "0_nalwor_forest_e2", "0_nalwor_forest_n", "0_nalwor_forest_n_e2", "0_nalwor_forest_ne", "0_nalwor_forest_nw", "0_nalwor_forest_w", "0_nalwor_river_s", "0_nalwor_river_s_e2", "0_nalwor_river_se", "0_nalwor_river_sw", "int_nalwor_assassinhq_0", "int_nalwor_assassinhq_1", "int_nalwor_assassinhq_cellar", "int_nalwor_bank", "int_nalwor_bank_1", "int_nalwor_flowershop", "int_nalwor_house1", "int_nalwor_house1_basement", "int_nalwor_house2", "int_nalwor_house3", "int_nalwor_house4", "int_nalwor_house4_1", "int_nalwor_house4_2", "int_nalwor_house_67", "int_nalwor_house_68", "int_nalwor_house_69", "int_nalwor_house_70", "int_nalwor_inn", "int_nalwor_inn_1", "int_nalwor_inn_basement", "int_nalwor_library", "int_nalwor_library_1", "int_nalwor_postoffice", "int_nalwor_pottery", "int_nalwor_prison", "int_nalwor_royal_hall", "int_nalwor_royal_hall_1", "int_nalwor_secret_room", "int_nalwor_tower_0", "int_nalwor_tower_1", "int_nalwor_tower_2", "int_nalwor_tower_3", "int_nalwor_tower_4", "int_nalwor_tower_5", "int_nalwor_tower_6", "int_nalwor_weaponshop"};
		Random rand = new Random();
		String zoneName= zoneList[rand.nextInt(55)];
		final StendhalRPZone zone = new StendhalRPZone(zoneName);
		//creates player frank and adds them to previously loaded zone
		final Player frank = PlayerTestHelper.createPlayer("frank");
		zone.add(frank);
		//create a piece of elvish armor and equips it to frank
		Item Armor= SingletonRepository.getEntityManager().getItem("elvish_armor");
		frank.equip("armor", Armor);
		//checks if frank is invisible
		assertEquals(frank.isInvisibleToCreatures(),true);
	}
	@Test
	
	/*This test checks if the player is made invisible while wearing the elvish armor not in a nalwor zone
	 * 
	 * */
	public void WearingArmorNotInNalworTest(){
		final StendhalRPZone zone = new StendhalRPZone('0_semos_city');
		//creates player frank and adds them to previously loaded zone
		final Player frank = PlayerTestHelper.createPlayer("frank");
		zone.add(frank);
		//create a piece of elvish armor and equips it to frank
		Item Armor= SingletonRepository.getEntityManager().getItem("elvish_armor");
		frank.equip("armor", Armor);
		//checks if frank is invisible
		assertEquals(frank.isInvisibleToCreatures(),false);
	}
}