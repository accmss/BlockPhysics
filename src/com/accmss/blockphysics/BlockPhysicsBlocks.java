package com.accmss.blockphysics;


import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPhysicsEvent;



public class BlockPhysicsBlocks implements Listener{

//private int LastX = 0;
//private int LastY = 0;

//private int MinMerge = 2; //requires 2 liquid blocks to merge
private String Pyro  = "";
private int PyroC = 0;

public BlockPhysicsBlocks(BlockPhysics xPlugin) 
{

}  


//FLOW
@EventHandler (priority = EventPriority.NORMAL)
public void onBlockFromTo(final BlockFromToEvent event)
{

		//config
		if (!BlockPhysicsConfig.NoGlobalFlowL && !BlockPhysicsConfig.NoGlobalFlowW)
		{
		return;
		}
		
		
		//Source need to be liquid
		if (event.getBlock().getTypeId() !=  8 && event.getBlock().getTypeId() != 9  &&
			event.getBlock().getTypeId() != 10 && event.getBlock().getTypeId() != 11 &&
			event.getBlock().getTypeId() != 79 )
		{
		return;
		}


		//LAVA
		if (BlockPhysicsConfig.NoGlobalFlowL)
		{	
	
			if (event.getBlock().getType() == Material.STATIONARY_LAVA || event.getBlock().getType() == Material.LAVA)
			{
			event.setCancelled(true);
			return;
			}
		}
		
		
		//LAVA
		if (BlockPhysicsConfig.NoGlobalFlowW)
		{	
			if (event.getBlock().getType() == Material.STATIONARY_WATER || event.getBlock().getType() == Material.WATER)
			{
			event.setCancelled(true);
			return;
			}
		}

}

//VINES
@EventHandler (priority = EventPriority.NORMAL)
public void onBlockPhysics(BlockPhysicsEvent event)
{

	//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-Event1:   ", event.getBlock().getType().toString() + " : " + event.getBlock().getLocation().toVector().toString());
	
		//VINES
		if (event.getChangedType() == Material.VINE)
		{
			//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-Event2:   ", event.getBlock().getType().toString() + " : " + event.getBlock().getLocation().toVector().toString());
			
			
			if (event.getBlock().getType() == Material.VINE)
			{
				if (BlockPhysicsConfig.NoVinesInAir)
				{
				//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-Event3:   ", event.getBlock().getType().toString() + " : " + event.getBlock().getLocation().toVector().toString());
				event.getBlock().setType(Material.AIR);
				//event.setCancelled(false);
				//return;
				}
				//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-ToVines:   ", event.getBlock().getType().toString() + " : " + event.getBlock().getLocation().toVector().toString());
			}
			//else
			//{
				//if (!BlockPhysicsConfig.VinesOnBlock)
				//{
				//event.getBlock().setType(Material.AIR);
				//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-Event3:   ", event.getBlock().getType().toString() + " : " + event.getBlock().getLocation().toVector().toString());
				//event.getBlock().setType(Material.AIR);
				//event.setCancelled(true);
				//return;
				//}
				
			//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-ToUnkown:   ", event.getBlock().getType().toString() + " : " + event.getBlock().getLocation().toVector().toString());
			//}
			

		}
	
	//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-EventZ:   ", event.getBlock().getType().toString() + " : " + event.getBlock().getLocation().toVector().toString());
	return;
}

//FIRE - ShowWarnings
@EventHandler (priority = EventPriority.NORMAL)
public void onFireStart (BlockIgniteEvent event)
{	

		switch (event.getCause()) {
		//case LAVA:
		//event.setCancelled(true);
			
		//case FIREBALL: 
		case FLINT_AND_STEEL:
			if (BlockPhysicsConfig.ShowWarnings)
			{

				if (Pyro.equalsIgnoreCase(event.getPlayer().getName().toString()))
				{
				PyroC++;
				}
				else
				{
				PyroC = 0;
				}

			Pyro = event.getPlayer().getName().toString();
			//screen
			BlockPhysics.zPlugin.getServer().broadcastMessage("§f" + Pyro  + " §eset fire to " + event.getBlock().getRelative(BlockFace.DOWN) .getType().toString());
			//BlockPhysics.zPlugin.getServer().broadcast(event.getPlayer().getName().toString()  + "§6 lit §f" + event.getBlock().getType().toString()  + "§6 on fire!","");
			//console
			//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics", event.getPlayer().getName().toString()  + "§6 lit a fire!");
			
				if (PyroC > 8)
				{
				PyroC = 0;
				event.getPlayer().kickPlayer("Lighting fires!");
				}

		
			}

		break;
		//case LIGHTNING:

		case SPREAD:
		//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "[onFireStart-SpreadCancel]", "");
			if (BlockPhysicsConfig.NoFireSpread)
			{
			event.setCancelled(true);
			}

		break;
		//default:
		//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics", "Unkown fire cause"); = BLAZE!
		//break;
		default:
			break;

	}


}				



//FIRE - NoFireSpread
@EventHandler (ignoreCancelled = true)
public void onFireDestroyBlock (BlockBurnEvent event)
{
	
	if (!BlockPhysicsConfig.NoFireSpread) event.setCancelled(true);
	
}

	


}