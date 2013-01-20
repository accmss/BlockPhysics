package com.accmss.blockphysics;


//IMPORTS - BUKKIT
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class BlockPhysicsPlayer implements Listener 
{



public BlockPhysicsPlayer(BlockPhysics xPlugin) 
{

}



@EventHandler (priority = EventPriority.NORMAL)
public void onPlayerInteract(final PlayerInteractEvent event) {
	
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