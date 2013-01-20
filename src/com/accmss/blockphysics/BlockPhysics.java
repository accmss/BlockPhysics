package com.accmss.blockphysics;


//IMPORTS - JAVA
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;


//IMPORTS - BUKKIT
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;



public class BlockPhysics extends JavaPlugin  {

	
public static BlockPhysics zPlugin;
protected static FileConfiguration zConfig;
public static Logger zLogger = Logger.getLogger("Minecraft");


//VARS
public static World    WORLD;
public static Player   PLAYA;
public static Location LOCTA;
//public static Vector   VECTA;
public static Block    BLOCK;

//CONST
public static int blocks_cache_redo = 7;



public static boolean CMDinProgrss  = false; 

public static String format_7zeros = "0,000,000";
public static String format_4zeros = "0000";


public static long thread = 0L;
public static long ticks = 0L;

static long idelay = 0L;
static long repeat = 1200L * 1L; // 1200 = 60 seconds

@Override
public void onEnable() {

	Calendar calendar = new GregorianCalendar();
	int ss = calendar.get(Calendar.SECOND);
	int xx = calendar.get(Calendar.MILLISECOND);
	idelay = (1000 - xx) / 50;
	idelay = idelay + ((60 - ss) * 20);
	idelay = idelay - 16; 
	
	zPlugin = this;

	//Settings
	BlockPhysicsConfig.LoadSettings(zPlugin.getFile().getAbsolutePath());
	


	//Every 1 minute we divide clicks by idleM 
	getServer().getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
		public void run()
		{

		ticks++;
			if (ticks > 59)
			{

			ticks = 0L;
			}
	    }
	}, idelay + 4, repeat); //20 clicks to a second


	
		//Metrics
		try
		{
		BlockPhysicsMetricsLite metrics = new BlockPhysicsMetricsLite(this);
		metrics.start();
		} catch (IOException e) {
		BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "[MetricsLite]", e.getCause() + " : " + e.getMessage());
		}

	//Listners
	getServer().getPluginManager().registerEvents(new BlockPhysicsPlayer(this), this);
	getServer().getPluginManager().registerEvents(new BlockPhysicsBlocks(this), this);
		
}
@Override
public void onDisable() 
{

}


@EventHandler
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, final String[] args){


int icase = 0;

	CMDinProgrss = true;



		if(sender instanceof Player)
		{
		PLAYA = (Player)sender;
		//BlockPhysicsLib.LogCommand(PLAYA.getName(), cmd.toString());
		}
		else
		{
		//BlockPhysicsLib.LogCommand(sender.getName(), cmd.toString());
		}


		//bounces commands back to user when database is offline
		//if (cmd.getName().equalsIgnoreCase("blockundo")) icase = 0;
		//if (cmd.getName().equalsIgnoreCase("undo")) icase = 1;
		//if (cmd.getName().equalsIgnoreCase("redo"))	 icase = 2;

	

		switch (icase) 
		{
		case 0:
			if (args.length == 1) //assume reload
			{
			reloadConfig();
			}
		CMDinProgrss = false;
		return true;
		
		case 1:
		//if (args.length == 1) BlockPhysicsLib.UndoW(sender, args[0].toLowerCase());
		CMDinProgrss = false;
		return true;
		
		case 2:
		//if (args.length == 1) BlockPhysicsLib.RedoW(sender, args[0].toLowerCase());
		CMDinProgrss = false;
		return true;
		
		case 3:
		//BlockPhysicsLib.GiveQ(sender, PLAYA);
		CMDinProgrss = false;
		return true;


		//case 6:
		//BlockPhysicsLib.WaterW(sender);
		//CMDinProgrss = false;
		//return true;
		
		
		}
		
		return false; 

	}
		


}