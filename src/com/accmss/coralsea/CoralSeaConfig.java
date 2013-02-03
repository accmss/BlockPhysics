package com.accmss.coralsea;


//IMPORT - JAVA
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


/*
# CoralSea

Terrain:
  WildSeaCaves: ON
  WildSeaCoral: ON

 */


//SYNC TO VERSION: 4


// animal and monster limits?

public class CoralSeaConfig {

//VARS - SETTINGS
public static boolean WildSeaCaves = true;
public static boolean WildSeaCoral = true;


public static int ConfigYMLVer = 0;


//VARS - SETTINGS
public static String SlashChar = null;
public static int SyncVers = 1; //1-first //2-FireWalking //3-CoralSea Climate
	
public static void LoadSettings(String file)
{

	//Slash
	SetSlash(file);

	//Ensure config
	EnsureConfig();

	//1 Terrain
	WildSeaCaves = CoralSea.zConfig.getBoolean("Terrain.WildSeaCaves", WildSeaCaves);
	WildSeaCoral = CoralSea.zConfig.getBoolean("Terrain.WildSeaCoral", WildSeaCoral);



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

	File fileDir = new File("plugins" + SlashChar + "CoralSea");
	String zFile = "plugins" + SlashChar + "CoralSea" + SlashChar + "config.yml";
	File f = new File(zFile);

		//Directory
		if (!fileDir.exists())
		{
		fileDir.mkdir();
		}

		//File
		if(!f.isFile())
		{ 
		CoralSeaLib.Chat(CoralSea.zPlugin.getServer().getConsoleSender(), "CoralSea", "§fWriting new configuration.yml.");
		CreateConfig(zFile);
		}
		else
		{
		CoralSea.zConfig = CoralSea.zPlugin.getConfig();
		}

		//Update
	    try
	    {ConfigYMLVer = CoralSea.zConfig.getInt("Version.ConfigYMLVer", ConfigYMLVer);}
		catch (Exception e)
		{ConfigYMLVer = 0;}
	    
		if(ConfigYMLVer != SyncVers)
		{
		CoralSeaLib.Chat(CoralSea.zPlugin.getServer().getConsoleSender(), "CoralSea", "§fUpdating new configuration.yml...");
		CreateConfig(zFile);
		}

}
private static void CreateConfig(String file) 
{

	try
	{
	InputStream is = CoralSea.zPlugin.getClass().getResourceAsStream("/config.yml");
	OutputStream os = new FileOutputStream(file);  
	byte[] buffer = new byte[4096];  
	int bytesRead;  
		while ((bytesRead = is.read(buffer)) != -1)
		{  
		os.write(buffer, 0, bytesRead);  
		}  
	is.close();  
	os.close(); 
	CoralSea.zConfig = CoralSea.zPlugin.getConfig();
	} catch (Exception e) {
	CoralSeaLib.Chat(CoralSea.zPlugin.getServer().getConsoleSender(), "CoralSea", "§fWriting new configuration.yml failed!");
	CoralSeaLib.Chat(CoralSea.zPlugin.getServer().getConsoleSender(), "CoralSea", "§4" + e.getCause() + ": " +  e.getMessage());
	}
	

}


}
