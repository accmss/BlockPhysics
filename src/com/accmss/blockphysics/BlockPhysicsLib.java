package com.accmss.blockphysics;


//IMPORTS - JAVA
import java.text.DecimalFormat;
import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.Block;
//IMPORTS - BUKKIT
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;



public class BlockPhysicsLib
{



//VARS
public static String format_2zeros = "00";
public static List<EntityType> tl;



//SUPPORT
public static String GetNumber(int num, ChatColor color, String format, Boolean SpaceZeros)
{
	
DecimalFormat myFormatter = new DecimalFormat(format); 
String output = "";
String split[] = null;
int k = 0;

	//1 - format
    output = myFormatter.format(num); 
	split = output.split(",");
	output = "";
	
		//2 - rebuild output with spaces
		if (SpaceZeros)
		{
			
			for(k = 0; k < split.length; k++)
			{
				
	
				if (k < split.length-1)
				{
				split[k] = split[k].replaceAll("000", "   ");	
				}
				else
				{
				split[k] = split[k].replaceAll("000", "  0");		
				}
				
				if (split[k].equalsIgnoreCase("0"))
				{
				split[k] = " ";
				}

			output = output + split[k] + ",";
			}
			
		//3 - remove trailing ","
		output = output.substring(0, output.length()-1);
		}
		else
		{
		output = myFormatter.format(num); //NEW hack to get around output variable clearing
		}

	//4 - color numbers
	output = color + output;
	
	//4 - color commas
	output = output.replaceAll(",", ChatColor.GRAY + "," + color);
	return output;

}


//LOGGING
public static void Chat(CommandSender sender, String PluginName, String message)
{

	sender.sendMessage("[" + PluginName + "] " + message);
	
}
public static void LogCommand(String player, String command){
	 
	BlockPhysics.zLogger.info("[PLAYER_COMMAND] " + player + ": " + command);

}


public static void Vaporize(Block c)
{

	//if 	(LastX == loc.getX() && LastY == loc.getY() && LastZ == loc.getZ()) return;

	
		int score = 0;
		World world = c.getWorld();
		Block b;

		BlockPhysics.LOCTA.setWorld(c.getWorld());

		BlockPhysics.LOCTA.setX(c.getX() +1);
		BlockPhysics.LOCTA.setY(c.getY());
		BlockPhysics.LOCTA.setZ(c.getZ());
		b = world.getBlockAt(BlockPhysics.LOCTA);
			if (b.getTypeId() == 0) score++;
			
		BlockPhysics.LOCTA.setX(c.getX() -1);
		BlockPhysics.LOCTA.setY(c.getY());
		BlockPhysics.LOCTA.setZ(c.getZ());
		b = world.getBlockAt(BlockPhysics.LOCTA);
			if (b.getTypeId() == 0)	score++;

			if (score == 0) return;

		BlockPhysics.LOCTA.setX(c.getX());
		BlockPhysics.LOCTA.setY(c.getY());
		BlockPhysics.LOCTA.setZ(c.getZ() +1);
		b = world.getBlockAt(BlockPhysics.LOCTA);
			if (b.getTypeId() == 0)	score++;
			if (score == 1) return;

		BlockPhysics.LOCTA.setX(c.getX());
		BlockPhysics.LOCTA.setY(c.getY());
		BlockPhysics.LOCTA.setZ(c.getZ() -1);
		b = world.getBlockAt(BlockPhysics.LOCTA);
			if (b.getTypeId() == 0) score++;
		

		if (score>=3)
		{
		c.setTypeIdAndData(0, (byte)0, true);
		}

			

}

public static void ClearEntities()
{

int c0 = 0;
int c1 = 0;
int c2 = 0;
String color = null;
String graph1 = null;
String graph2 = null;

	for (EntityType zEntityType : tl)
	{
	c0 = CountEntities(zEntityType, 64);

	
		if (c0 == 0)	{continue;}

		if (c0 > 00)	{color = "§a";}
		if (c0 > 32)	{color = "§e";}
		if (c0 > 48)	{color = "§c";}
	c1 = c0 / 2;
	c2 = 32 - c1;
	graph1 = StringUtils.leftPad("", c1, "|");
	graph2 = StringUtils.leftPad("", c2, "|");
	BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(),  "BlockPhysics", color + graph1 + "§8"+ graph2 + " " + color + String.format("%02d", c0) + "§f " + zEntityType.getName());
	}

}


public static int CountEntities(EntityType zEntityType, int limit)
{
int c = 0;


	for (World world : BlockPhysics.zPlugin.getServer().getWorlds())
	{

		for (Entity entity : world.getEntities())
		{
						 
			if (entity.getType() == zEntityType)
			{
				if (c >= (limit -4)) //NEW allows for re-spawning
				{
				entity.remove();
				}
				else
				{
				c++;
				}

			}

		}
		
	}
	
return c;

}

}