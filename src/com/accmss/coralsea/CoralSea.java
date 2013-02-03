package com.accmss.coralsea;


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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;




public class CoralSea extends JavaPlugin  {


	
public static CoralSea zPlugin;
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
	
	//Listners
	getServer().getPluginManager().registerEvents(new CoralSeaWorld(this), this);
	
	WORLD = this.getServer().getWorld(this.getServer().getWorlds().get(0).getName());
	LOCTA = WORLD.getSpawnLocation();
	
	//Settings
	CoralSeaConfig.LoadSettings(zPlugin.getFile().getAbsolutePath());

		//Metrics
		try
		{
		CoralSeaMetricsLite metrics = new CoralSeaMetricsLite(this);
		metrics.start();
		} catch (IOException e) {
		CoralSeaLib.Chat(CoralSea.zPlugin.getServer().getConsoleSender(), "[MetricsLite]", e.getCause() + " : " + e.getMessage());
		}



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
		//CoralSeaLib.LogCommand(PLAYA.getName(), cmd.toString());
		}
		else
		{
		//CoralSeaLib.LogCommand(sender.getName(), cmd.toString());
		}


		//bounces commands back to user when database is offline
		//if (cmd.getName().equalsIgnoreCase("flowon")) icase = 0;
		//if (cmd.getName().equalsIgnoreCase("flowoff")) icase = 1;
		//if (cmd.getName().equalsIgnoreCase("redo"))	 icase = 2;

	

		switch (icase) 
		{
		case 0:
		//CoralSea.zPlugin.getServer().broadcastMessage("[CoralSea] §9Liquid flow enabled.");
		//CoralSeaConfig.NoGlobalFlowL = false;
		//CoralSeaConfig.NoGlobalFlowW = false;
		//CoralSea.zConfig.set("Lava.NoGlobalFlow", CoralSeaConfig.NoGlobalFlowL);
		//CoralSea.zConfig.set("Water.NoGlobalFlow", CoralSeaConfig.NoGlobalFlowW);
		//CMDinProgrss = true;
		return true;
		
		
		}
		
		return false; 

}
	



}




	

