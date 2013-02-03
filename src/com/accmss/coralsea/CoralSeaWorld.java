package com.accmss.coralsea;

import org.bukkit.World.Environment;


import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.BlockPopulator;


public class CoralSeaWorld implements Listener
{

	
public CoralSeaWorld(CoralSea xPlugin) 
{

}  


@EventHandler (priority = EventPriority.NORMAL)
public void WorldInit(WorldInitEvent event)
{

		if (!CoralSeaConfig.WildSeaCaves && !CoralSeaConfig.WildSeaCoral) {return;}
	
		for (BlockPopulator pop : CoralSea.WORLD.getPopulators())
		{
		    if ((pop instanceof CoralSeaPopulator))
		    {
			CoralSeaLib.Chat(CoralSea.zPlugin.getServer().getConsoleSender(), "CoralSea","§4World generator found!");
		    return;
		    }
		 }


	
		if(event.getWorld().getEnvironment() == Environment.NORMAL)
		{
		CoralSea.WORLD.getPopulators().add(new CoralSeaPopulator());
		CoralSeaLib.Chat(CoralSea.zPlugin.getServer().getConsoleSender(), "CoralSea","§eWorld generator enabled.");
		}
    
}

	
}
