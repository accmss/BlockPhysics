package com.accmss.blockphysics;


//IMPORTS - JAVA
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


//IMPORTS - BUKKIT
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;


//IMPORTS - GOOGLE
import com.google.common.collect.Lists;




public class BlockPhysics extends JavaPlugin  {


	
public static BlockPhysics zPlugin;
protected static FileConfiguration zConfig;
public static Logger zLogger = Logger.getLogger("Minecraft");


public static Map<String, String> loadedChunks = new HashMap<String, String>();


//VARS
public static World    WORLD;
public static Player   PLAYA;
public static Location LOCTA;
//public static Vector   VECTA;
public static Block    BLOCK;



public static long thread = 0L;
public static long ticks = 0L;

static long idelay = 0L;
static long repeat = 1200L * 1L; // 1200 = 60 seconds

static long mod = 0; // 1200 = 60 seconds

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
	

	WORLD = this.getServer().getWorld(this.getServer().getWorlds().get(0).getName());
	LOCTA = WORLD.getSpawnLocation();
	

	
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


	//Build entity list
	BlockPhysicsLib.tl = Lists.newArrayList();
    for (EntityType t : EntityType.values())
    {
        if (t != EntityType.PLAYER && t != EntityType.FALLING_BLOCK)
        {
        BlockPhysicsLib.tl.add(t);
        }

    }


	zPlugin.getServer().getScheduler().runTaskTimerAsynchronously(zPlugin, new Runnable() {
		public void run()
		{
			//if ((mod % 12) == 0)
			//{
		BlockPhysicsLib.ClearEntities();
			//}
		//mod++;
		}
	}, 256L, 20L * 60L * 60L); //20 clicks to a second - 5 Minutes

	

}
@Override
public void onDisable() 
{

}


@EventHandler
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, final String[] args)
{


int icase = 0;


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
		if (cmd.getName().equalsIgnoreCase("flowon")) icase = 0;
		if (cmd.getName().equalsIgnoreCase("flowoff")) icase = 1;
		//if (cmd.getName().equalsIgnoreCase("redo"))	 icase = 2;

	

		switch (icase) 
		{
		case 0:
		BlockPhysics.zPlugin.getServer().broadcastMessage("[BlockPhysics] §9Liquid flow enabled.");
		BlockPhysicsConfig.NoGlobalFlowL = false;
		BlockPhysicsConfig.NoGlobalFlowW = false;
		BlockPhysics.zConfig.set("Lava.NoGlobalFlow", BlockPhysicsConfig.NoGlobalFlowL);
		BlockPhysics.zConfig.set("Water.NoGlobalFlow", BlockPhysicsConfig.NoGlobalFlowW);
		return true;
		
		case 1:
		BlockPhysics.zPlugin.getServer().broadcastMessage("[BlockPhysics] §9Liquid flow disabled.");
		BlockPhysicsConfig.NoGlobalFlowL = true;
		BlockPhysicsConfig.NoGlobalFlowW = true;
		BlockPhysics.zConfig.set("Lava.NoGlobalFlow", BlockPhysicsConfig.NoGlobalFlowL);
		BlockPhysics.zConfig.set("Water.NoGlobalFlow", BlockPhysicsConfig.NoGlobalFlowW);
		return true;
		


		//case 6:
		//BlockPhysicsLib.WaterW(sender);
		//CMDinProgrss = false;
		//return true;
		
		
		}
		
		return false; 

}
	



}




	

