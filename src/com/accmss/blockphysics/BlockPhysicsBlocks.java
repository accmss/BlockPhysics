package com.accmss.blockphysics;


import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPhysicsEvent;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;


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

		/*	
		//Air passthrough
		if (event.getToBlock().getType() == Material.AIR)
		{
		return;
		}

	
		if (event.getToBlock().getType() == Material.STONE || event.getToBlock().getType() == Material.OBSIDIAN)
		{
		BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-Optimizing:   ", event.getBlock().getType().toString() + " : " + event.getBlock().getLocation().toVector().toString());
		return;
		}

final String chunkCode = "x"+  Integer.toString(event.getBlock().getChunk().getX()) + "z" + Integer.toString(event.getBlock().getChunk().getZ());
String s = BlockPhysics.loadedChunks.get(chunkCode);

		//NEW 60 second delay for all chunks
		if (s != null && s.equalsIgnoreCase("1")) 
		{
			//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-Delaying-flow:   ", chunkCode);
		return;	
		}

*/
		
		//LAVA
		if (BlockPhysicsConfig.NoGlobalFlowL)
		{	
			//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-S=:   ", s);
	
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
			BlockPhysics.zPlugin.getServer().broadcastMessage("§f" + Pyro  + " §eset fire to " + event.getBlock().getType().toString());
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
			



//cancels fire effects

@EventHandler (priority = EventPriority.NORMAL)
public void onFireBurnEntity (EntityCombustByBlockEvent event)
{
	Entity e = event.getEntity();

	if(!BlockPhysicsConfig.FireWalkLava) {return;}
	
	if (e instanceof Player)
	{
	//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-onFireBurnEntity", "cancelled duration: " );
	event.setDuration (0);
	event.setCancelled (true);
	return;	
	} 

}

@EventHandler (priority = EventPriority.NORMAL)
public void  onFireDamageEntity (EntityDamageEvent event) {

Entity damagee = event.getEntity();

		if (!BlockPhysicsConfig.FireWalkLava && !BlockPhysicsConfig.VaporizeLava) {return;}

	//BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics-onFireDamageEntity", "event fired!");
		
	if (event.getCause() != DamageCause.FIRE && event.getCause() != DamageCause.FIRE_TICK  &&
		event.getCause() != DamageCause.LAVA && event.getCause() != DamageCause.MELTING) 
		{
		return;
		}
	
	if (damagee instanceof Player) 
	{


		if(BlockPhysicsConfig.VaporizeLava)
		{
		Player p = ((Player) damagee);
		Block b = p.getWorld().getBlockAt(p.getLocation());
			//Fire walker
			if (b.getType() == Material.LAVA || b.getType() == Material.STATIONARY_LAVA)
			{
			p.playSound(b.getLocation(), Sound.FIZZ,  (float)100, (float)1);
			b.setType(Material.AIR);
			b.getRelative(BlockFace.UP);
			}

			if (b.getType() == Material.LAVA || b.getType() == Material.STATIONARY_LAVA)
			{
			b.setType(Material.AIR);
			}
		}
	
		if(BlockPhysicsConfig.FireWalkLava)
		{
			if (damagee.getFireTicks() > 0) 
			{
			damagee.setFireTicks(0); 
			}
		event.setDamage(0);
		event.setCancelled(true);
		}
		

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
