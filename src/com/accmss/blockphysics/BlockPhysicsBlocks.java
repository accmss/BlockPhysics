package com.accmss.blockphysics;


import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPhysicsEvent;

import org.bukkit.event.block.BlockPlaceEvent;


public class BlockPhysicsBlocks implements Listener{

//private int LastX = 0;
//private int LastY = 0;

//private int MinMerge = 2; //requires 2 liquid blocks to merge
private String Pyro  = "";
private int PyroC = 0;

public BlockPhysicsBlocks(BlockPhysics xPlugin) 
{

}  


@EventHandler (priority = EventPriority.NORMAL)
public void onBlockPlace(final BlockPlaceEvent event) {



	
}

@EventHandler (priority = EventPriority.NORMAL)
public void onBlockBreak(final BlockBreakEvent event)
{

	//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-onBlockBreak", event.getBlock().toString());
	
}

@EventHandler (priority = EventPriority.NORMAL)
public void onBlockFromTo(final BlockFromToEvent event)
{

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


@EventHandler (priority = EventPriority.NORMAL)
public void onBlockPhysics(BlockPhysicsEvent event)
{

		//VINES
		if (event.getChangedType() == Material.VINE)
		{
			if (event.getBlock().getType() == Material.VINE)
			{
				if (BlockPhysicsConfig.NoVinesInAir)
				{
				event.setCancelled(true);
				}
			}
			else
			{
				if (!BlockPhysicsConfig.VinesOnBlock)
				{
				event.setCancelled(true);
				}
			//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-Vines-Grow:   ", event.getBlock().getType().toString() + " : " + event.getBlock().getLocation().toVector().toString());
			}
		}
}




@EventHandler (ignoreCancelled = true)
public void onFireStart (BlockIgniteEvent event)
{	

		switch (event.getCause()) {
		//case LAVA:
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
			BlockPhysics.zPlugin.getServer().broadcastMessage(Pyro  + "§6 lit a fire!");
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
		default:
		BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics", "Unkown fire cause");
		break;

	}


}		
			



@EventHandler (ignoreCancelled = true)
public void onFireDestroyBlock (BlockBurnEvent event)
{
	
	if (!BlockPhysicsConfig.NoFireSpread) event.setCancelled(true);
	
}

	
	
		
/*
 * 
 * 
 @EventHandler (priority = EventPriority.NORMAL)
public void BlockGrowEvent(Block block, BlockState newState)
{

	BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "[BlockPhysics-BlockGrowEvent]", block.toString());

}
@EventHandler (priority = EventPriority.NORMAL)
public void onBlockFromToEvent(Block block, BlockFace face)
{
	
	BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "[BlockPhysics-onBlockFromToEvent]", block.toString());
	
}
@EventHandler (priority = EventPriority.NORMAL)
public void onBlockIgniteEvent(Block theBlock, IgniteCause cause, Player thePlayer)
{
	
	BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "[BlockPhysics-BlockGrowEvent]", theBlock.toString());

}
@EventHandler (priority = EventPriority.NORMAL)
public void BlockIgniteEvent(Block theBlock, IgniteCause cause, Player thePlayer)
{
	
	BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "[BlockPhysics-BlockGrowEvent]", theBlock.toString());

}
 * 
 */
}
