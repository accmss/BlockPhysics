package com.accmss.blockphysics;


//IMPORTS - BUKKIT
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;


public class BlockPhysicsPlayer implements Listener 
{



public BlockPhysicsPlayer(BlockPhysics xPlugin) 
{

}


@EventHandler (priority = EventPriority.NORMAL)
public void onPlayerBucketFill(final PlayerBucketFillEvent event)
{

//NEW needs 3 minimum surround lava from a search pattern -|- in basic 3d (6) .:. 50% or more surrounding fluid before infinite water block is set
	
final Material m;
Block b1;
Block b2;
int c = 0;

	if (!BlockPhysicsConfig.InfiniteFlowW && !BlockPhysicsConfig.InfiniteFlowL) {return; }

	m = event.getBlockClicked().getType();
	BlockPhysics.BLOCK = event.getBlockClicked().getWorld().getBlockAt(event.getBlockClicked().getX(), event.getBlockClicked().getY(), event.getBlockClicked().getZ());

	b1 = event.getBlockClicked();
	b2 = b1.getRelative(BlockFace.NORTH);

	    // 1 > 1 = -1 in java
		if (b2.getTypeId() < 7 || b2.getTypeId() > 11) 
		{c++; }

	b2 = b1.getRelative(BlockFace.SOUTH);

		if (b2.getTypeId() < 7 || b2.getTypeId() > 11) 
		{c++; }

	b2 = b1.getRelative(BlockFace.EAST);
		
		if (b2.getTypeId() < 7 || b2.getTypeId() > 11)  
		{c++; }

		if (c >= 3) {FillFluid(m);}
		
	b2 = b1.getRelative(BlockFace.WEST);

		if (b2.getTypeId() < 7 || b2.getTypeId() > 11) 
		{c++; }
		
		if (c <= 0) {return; } //EXIT ON 0
		if (c >= 3) {FillFluid(m);}
		
	b2 = b1.getRelative(BlockFace.UP);

		if (b2.getTypeId() < 7 || b2.getTypeId() > 11) 
		{c++; }

		if (c <= 1) {return; } //EXIT ON 1
		if (c >= 3) {FillFluid(m);}
		
	b2 = b1.getRelative(BlockFace.DOWN);

		if (b2.getTypeId() < 7 || b2.getTypeId() > 11) 
		{c++; }

		if (c <= 2) {return; } //EXIT ON 2
		if (c >= 3) {FillFluid(m);}

}

public void FillFluid(final Material mat)
{

	//INFINITE WATER/LAVA
	BlockPhysics.zPlugin.getServer().getScheduler().runTaskLaterAsynchronously(BlockPhysics.zPlugin, new Runnable()
	{
		public void run()
		{

			if (mat == Material.STATIONARY_LAVA || mat == Material.LAVA)
			{
				if (BlockPhysicsConfig.InfiniteFlowL)
				{
				BlockPhysics.BLOCK.setTypeId(11, false);
				}
			}
			else
			{
				if (BlockPhysicsConfig.InfiniteFlowW)
				{
				BlockPhysics.BLOCK.setTypeId(9, false);
				}
			}

		}
	}, 1L); 


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
		}, 1L); 


}



}