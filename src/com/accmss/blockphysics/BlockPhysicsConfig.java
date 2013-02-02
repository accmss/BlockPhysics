package com.accmss.blockphysics;


//IMPORT - JAVA
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


/*
# BlockPhysics

Fire:
  NoFireSpread: ON
  ShowWarnings: ON
Lava:
  NoGlobalFlow: ON
  ObsidianFlow: ON
Water:
  NoGlobalFlow: ON
  ObsidianFlow: ON
Vines:
  VinesOnBlock: ON
  NoVinesInAir: ON
World:
  MeridianDays: OFF
  MeridianRain: OFF
 */


//SYNC TO VERSION: 4


// animal and monster limits?

public class BlockPhysicsConfig {

//VARS - SETTINGS
public static boolean NoFireSpread = true;
public static boolean ShowWarnings = true;

public static boolean NoGlobalFlowL = true;
public static boolean InfiniteFlowL = true;
public static boolean ObsidianFlowL = true;
public static boolean FireWalkLava = true;
public static boolean VaporizeLava = true;

public static boolean NoGlobalFlowW = true;
public static boolean InfiniteFlowW = true;
public static boolean ObsidianFlowW = true;
public static boolean VinesOnBlock = true;
public static boolean NoVinesInAir = true;

public static boolean MeridianDays = true;
public static boolean MeridianRain = true;


public static int ConfigYMLVer = 0;


//VARS - SETTINGS
public static String SlashChar = null;
public static int SyncVers = 4; //1-first //2-FireWalking //3-Meridian Climate
	
public static void LoadSettings(String file)
{

	//Slash
	SetSlash(file);

	//Ensure config
	EnsureConfig();

	//1 Fire
	NoFireSpread = BlockPhysics.zConfig.getBoolean("Fire.NoGlobalFlow", NoFireSpread);
	ShowWarnings = BlockPhysics.zConfig.getBoolean("Fire.ObsidianFlow", ShowWarnings);
	
	//2 Lava
	NoGlobalFlowL = BlockPhysics.zConfig.getBoolean("Lava.NoGlobalFlow", NoGlobalFlowL);
	InfiniteFlowL = BlockPhysics.zConfig.getBoolean("Lava.InfiniteFlow", InfiniteFlowL);
	ObsidianFlowL = BlockPhysics.zConfig.getBoolean("Lava.ObsidianFlow", ObsidianFlowL);
	FireWalkLava = BlockPhysics.zConfig.getBoolean("Lava.FireWalkLava", FireWalkLava);
	VaporizeLava = BlockPhysics.zConfig.getBoolean("Lava.VaporizeLava", VaporizeLava);
	
	//3 Water
	NoGlobalFlowW = BlockPhysics.zConfig.getBoolean("Water.NoGlobalFlow", NoGlobalFlowW);
	InfiniteFlowW = BlockPhysics.zConfig.getBoolean("Water.InfiniteFlow", InfiniteFlowW);
	ObsidianFlowW = BlockPhysics.zConfig.getBoolean("Water.ObsidianFlow", ObsidianFlowW);
	
	//4 Vines
	VinesOnBlock = BlockPhysics.zConfig.getBoolean("Vines.VinesOnBlock", VinesOnBlock);
	NoVinesInAir = BlockPhysics.zConfig.getBoolean("Vines.NoVinesInAir", NoVinesInAir);
	
	//5 Climate
	MeridianDays = BlockPhysics.zConfig.getBoolean("Climate.MeridianDays", MeridianDays);
	MeridianRain = BlockPhysics.zConfig.getBoolean("Climate.MeridianRain", MeridianRain);

}


public static void SetSlash(String file)
{

	if (file.contains("/"))
	{
	SlashChar = "/"; //Linux
	}
	else
	{
	SlashChar = "\\"; //Windows
	}
	
}
private static void EnsureConfig()
{

	File fileDir = new File("plugins" + SlashChar + "BlockPhysics");
	String zFile = "plugins" + SlashChar + "BlockPhysics" + SlashChar + "config.yml";
	File f = new File(zFile);

		//Directory
		if (!fileDir.exists())
		{
		fileDir.mkdir();
		}

		//File
		if(!f.isFile())
		{ 
		BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics", "§fWriting new configuration.yml.");
		CreateConfig(zFile);
		}
		else
		{
		BlockPhysics.zConfig = BlockPhysics.zPlugin.getConfig();
		}

		//Update
	    try
	    {ConfigYMLVer = BlockPhysics.zConfig.getInt("Version.ConfigYMLVer", ConfigYMLVer);}
		catch (Exception e)
		{ConfigYMLVer = 0;}
	    
		if(ConfigYMLVer != SyncVers)
		{
		BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics", "§fUpdating new configuration.yml...");
		CreateConfig(zFile);
		}

}
private static void CreateConfig(String file) 
{

	try
	{
	InputStream is = BlockPhysics.zPlugin.getClass().getResourceAsStream("/config.yml");
	OutputStream os = new FileOutputStream(file);  
	byte[] buffer = new byte[4096];  
	int bytesRead;  
		while ((bytesRead = is.read(buffer)) != -1)
		{  
		os.write(buffer, 0, bytesRead);  
		}  
	is.close();  
	os.close(); 
	BlockPhysics.zConfig = BlockPhysics.zPlugin.getConfig();
	} catch (Exception e) {
	BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics", "§fWriting new configuration.yml failed!");
	BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics", "§4" + e.getCause() + ": " +  e.getMessage());
	}
	

}


}
