package com.accmss.blockphysics;


//IMPORT - JAVA
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/*
# BlockPhysics

Fire:
  NoFireSpread: ON
  LogToConsole: ON
  ShowWarnings: ON

Explosions:
  CancelAllTNT: ON
  CancelCreepa: ON
  CancelWither: ON
  CancelGhasts: ON

Lava:
  NoGlobalFlow: ON
  NoBucketFlow: ON
  InfinitePool: ON
  ObsidianFlow: ON
	
Water:
  NoGlobalFlow: ON
  NoBucketFlow: ON
  InfinitePool: ON
  ObsidianFlow: ON

Vines:
  NoVinesInAir: ON
  SheersAssist: ON
 */


//SYNC TO VERSION: 1


public class BlockPhysicsConfig {

	//VARS - SETTINGS
public static String MySQLMachine = null;
public static String MySQLTCPPort = null;
public static String MySQLDatabse = null;
public static String MySQLUserAcc = null;
public static String MySQLPasswrd = null;
                     
public static List<String>  Worlds = null;

public static String InfiniteOres = null;
public static int RegenOreDays = 0;

public static int ConfigYMLVer = 0;


//VARS - SETTINGS
public static String SlashChar = null;
public static int SyncVers = 1;
	
public static void LoadSettings(String file)
{

	//Slash
	SetSlash(file);

	//Ensure config
	EnsureConfig();

	//1 MySQL
	MySQLMachine = BlockPhysics.zConfig.getString("Database.MySQLMachine", MySQLMachine);
	MySQLTCPPort = BlockPhysics.zConfig.getString("Database.MySQLTCPPort", MySQLTCPPort);
	MySQLDatabse = BlockPhysics.zConfig.getString("Database.MySQLDatabse", MySQLDatabse);
	MySQLUserAcc = BlockPhysics.zConfig.getString("Database.MySQLUserAcc", MySQLUserAcc);
	MySQLPasswrd = BlockPhysics.zConfig.getString("Database.MySQLPasswrd", MySQLPasswrd);

	//2 World
	Worlds = BlockPhysics.zConfig.getStringList("Worlds");

	//3 Regeneration
	InfiniteOres = BlockPhysics.zConfig.getString("Regeneration.InfiniteOres", InfiniteOres);
	RegenOreDays = BlockPhysics.zConfig.getInt("Regeneration.RegenOreDays", RegenOreDays);

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

	String zFile = "plugins" + SlashChar + "BlockPhysics" + SlashChar + "config.yml";
	File f = new File(zFile);

		if(!f.isFile())
		{ 
		BlockPhysicsLib.Chat(BlockPhysics.zPlugin.getServer().getConsoleSender(), "BlockPhysics", "§fWriting new configuration.yml.");
		CreateConfig(zFile);
		}
		else
		{
		BlockPhysics.zConfig = BlockPhysics.zPlugin.getConfig();
		}

		//Update config
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
