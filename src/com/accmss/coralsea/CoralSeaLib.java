package com.accmss.coralsea;


//IMPORTS - JAVA
import java.text.DecimalFormat;
import java.util.List;


import org.bukkit.ChatColor;
//IMPORTS - BUKKIT
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;



public class CoralSeaLib
{

	

//VARS
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
	 
	CoralSea.zLogger.info("[PLAYER_COMMAND] " + player + ": " + command);

}



}