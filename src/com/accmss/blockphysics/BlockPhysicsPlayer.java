package com.accmss.blockphysics;


//IMPORTS - BUKKIT
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockPhysicsPlayer implements Listener 
{



public BlockPhysicsPlayer(BlockPhysics xPlugin) 
{

}


@EventHandler (priority = EventPriority.NORMAL)
public void onPlayerBucketEmpty(final PlayerBucketEmptyEvent event)
{

int x = event.getBlockClicked().getX();
int y = event.getBlockClicked().getY();
int z = event.getBlockClicked().getZ();


			
	
	//BlockPhysics.BLOCK = event.getBlockClicked();
	//Block block;
	if (event.getBlockFace() == BlockFace.DOWN)			BlockPhysics.BLOCK = event.getBlockClicked().getWorld().getBlockAt(x, y - 1, z);
	else if (event.getBlockFace() == BlockFace.UP)		BlockPhysics.BLOCK = event.getBlockClicked().getWorld().getBlockAt(x, y + 1, z);
	else if (event.getBlockFace() == BlockFace.NORTH)	BlockPhysics.BLOCK = event.getBlockClicked().getWorld().getBlockAt(x, y, z - 1);
	else if (event.getBlockFace() == BlockFace.SOUTH)	BlockPhysics.BLOCK = event.getBlockClicked().getWorld().getBlockAt(x, y, z + 1);
	else if (event.getBlockFace() == BlockFace.WEST)	BlockPhysics.BLOCK = event.getBlockClicked().getWorld().getBlockAt(x - 1, y, z);
	else if (event.getBlockFace() == BlockFace.EAST)	BlockPhysics.BLOCK = event.getBlockClicked().getWorld().getBlockAt(x + 1, y, z);
	
	
		//Fast Lava Obsidian 
		if (BlockPhysicsConfig.ObsidianFlowL)
		{	
			if (BlockPhysics.BLOCK.getType() == Material.STATIONARY_LAVA)
			{
				if (event.getBucket() == Material.WATER_BUCKET)
				{
				event.setCancelled(true);
				BlockPhysics.BLOCK.setTypeIdAndData(49, (byte)0, true);
				event.getPlayer().playSound(BlockPhysics.BLOCK.getLocation(), Sound.FIZZ,  (float)100, (float)1);
				return;
				}
			}	
		}
	
		
		//Fast Water Obsidian 
		if (BlockPhysicsConfig.ObsidianFlowW)
		{	
			if ( BlockPhysics.BLOCK.getType() == Material.STATIONARY_WATER)
			{
				if (event.getBucket() == Material.LAVA_BUCKET)
				{
				event.setCancelled(true);
				BlockPhysics.BLOCK.setTypeIdAndData(49, (byte)0, true);
				event.getPlayer().playSound(BlockPhysics.BLOCK.getLocation(), Sound.FIZZ,  (float)100, (float)1);
				return;
				}
			}	
		}

	
	
		BlockPhysics.zPlugin.getServer().getScheduler().runTaskLaterAsynchronously(BlockPhysics.zPlugin, new Runnable()
		{
			public void run()
			{
			BlockPhysicsLib.Vaporize(BlockPhysics.BLOCK);	
			}
		}, 2L); 


}
@EventHandler (priority = EventPriority.NORMAL)
public void onPlayerInteract(final PlayerInteractEvent event)
{
	
	//World MyWorld = event.getPlayer().getWorld();


		if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{


			switch (event.getPlayer().getItemInHand().getType())
			{
			case GOLD_PICKAXE:

				if (event.getPlayer().hasPermission("blockundo.player"))
				{
				//BlockPhysicsLib.estimatedTime = System.nanoTime() - BlockPhysicsLib.startTime;
					
			    new Thread(new Runnable() { 
			        public void run() { 
			        	try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						//BlockPhysicsLib.Query(event.getPlayer(), event.getClickedBlock().getLocation());
			        	} 
			    }).start(); 
			    


				//NEW cool off (manipulates variable from 1 const thread)
				//BlockPhysicsLib.startTime = System.nanoTime();        
				}
			
				

				default:
				break;
				}
		
		}
			
			

}


}