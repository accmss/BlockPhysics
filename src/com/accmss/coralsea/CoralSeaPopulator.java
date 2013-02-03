package com.accmss.coralsea;


//IMPORTS - BUKKIT
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;



public class CoralSeaPopulator extends BlockPopulator
{


private int max = 32;
	
public CoralSeaPopulator() 
{

}




public void populate(World world, Random random, Chunk chunk)
{

int x = 0;
int z = 0;
int y = max;
Block b1;
Block b2;
Boolean waterfound = false;

	    for (x = 0; x < 16; x++)
	    {
	    	
	        for (z = 0; z < 16; z++)
	        {
	            for(y = max; y > 0; y--)
	            {
		        b1 = chunk.getBlock(x, y, z);
		        
		        
		        	//BEDROCK BASE - prevents players from being trapped and dying)
		        	if (y < 3)
		        	{

		        		
			        	if (b1.getType() != Material.BEDROCK && b1.getType() != Material.SOUL_SAND)	
			        	{
			        		
			        		
				        	if (y == 2)
				        	{
				            //CoralSeaLib.Chat(CoralSea.zPlugin.getServer().getConsoleSender(), "CoralSea-NETHERWART:", b1.getLocation().toVector().toString());
					        b1.setTypeId(88, false);
				            b2 = b1.getRelative(BlockFace.UP);
				            b2.setTypeId(115, false);
				            continue;
				        	}
				        	else
				        	{
				        		
				        	}
				        b1.setTypeId(7, false);
			        	}
					waterfound = false;
				    continue;
		        	}


		        	//QUICK EXIT
	                if(b1.getType() != Material.STATIONARY_LAVA  && b1.getType() != Material.LAVA &&
	                   b1.getType() != Material.STATIONARY_WATER && b1.getType() != Material.WATER &&
	                   b1.getType() != Material.BROWN_MUSHROOM   && b1.getType() != Material.RED_MUSHROOM &&
	                   b1.getType() != Material.AIR) {continue; }
	                
		        	//KILL AIRPOCKETS
	            	if (waterfound && b1.getType() == Material.AIR) 
	            	{
		            b1.setTypeId(9, false);
		            continue;
	            	}
		        	
	            	//LAVA
	                if(b1.getType() == Material.STATIONARY_LAVA || b1.getType() == Material.LAVA)
	                {
			        waterfound = true; //lava is water anyways
	                b1.setTypeId(9, false);
	                continue;
	                }

	                //WATER
	                if(b1.getType() == Material.STATIONARY_WATER || b1.getType() == Material.WATER)
	                {
		            waterfound = true;
		                if(b1.getType() == Material.WATER)
		                {
			            b1.setTypeId(9, false);
			            continue;
		                }
	                }

	            	//NETHERWART
	                if(b1.getType() == Material.BROWN_MUSHROOM || b1.getType() == Material.RED_MUSHROOM)
	                {
	                //CoralSeaLib.Chat(CoralSea.zPlugin.getServer().getConsoleSender(), "CoralSea-NETHERWART:", b1.getLocation().toVector().toString());
	                b2 = b1.getRelative(BlockFace.DOWN);
	                b2.setTypeId(88, false);
		            b1.setTypeId(115, false);
	                continue;
	                }

				//waterfound = false;
	            }

			//waterfound = false;
	        }

		//waterfound = false;
	    }
}

   



}