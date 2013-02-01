package com.accmss.blockphysics;


//IMPORTS - BUKKIT
import org.bukkit.event.Listener;



public class BlockPhysicsWorld implements Listener 
{



	
	
public BlockPhysicsWorld(BlockPhysics xPlugin) 
{

}

/*

@EventHandler (priority = EventPriority.NORMAL)
public void onChunkLoaded(ChunkLoadEvent event)
{

final String chunkCode = "x"+  Integer.toString(event.getChunk().getX()) + "z" + Integer.toString(event.getChunk().getZ());

	BlockPhysics.loadedChunks.put(chunkCode, "1");
	
	BlockPhysics.zPlugin.getServer().getScheduler().runTaskLaterAsynchronously(BlockPhysics.zPlugin, new Runnable() {
		
		public void run()
		{
		BlockPhysics.loadedChunks.remove(chunkCode);
		}
	}, 20L * 60); 

    
}
 * 
 */


}