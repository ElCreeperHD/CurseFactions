package cursemc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Logger;



import org.bukkit.Bukkit;

import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
	
    CommandListener listener = new CommandListener(this);
	public Logger log = Bukkit.getLogger();
    public EventListener eventos = new EventListener(this);
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(eventos,this);
		getCommand("fireworks").setExecutor(listener);
		getCommand("curse").setExecutor(listener);
		log.info("CurseMC V 1.0.0-STABLE is starting!");
		File statsFile = new File(getDataFolder(), "bounties.yml");
		Date date = new Date();
		if (!statsFile.exists()) {
		try {
		statsFile.createNewFile();
		} catch (IOException ex) {
		ex.printStackTrace();
		}
	 }

		try	{
		PrintWriter writer = new PrintWriter(getDataFolder() + "/log.txt", "UTF-8");
		writer.println(date.toString() + " Plugin Started");
		writer.close();
		
		}
		catch (Exception ex){	
			log.warning("An error occurred while enabling CurseMC logging system");
			
		}
		
	}//end onenable

	public void onDisable(){
		Date date = new Date();
		log.info("CurseMC By Mr_Matwin3 was disabled!");
		try	{
		PrintWriter writer = new PrintWriter(getDataFolder() + "/log.txt", "UTF-8");		
		writer.println(date.toString() + " Plugin Disabled");
		writer.close();
		
		}
		catch (Exception ex){	
			log.warning("An error occurred while disabling CurseMC logging system");
			
		}
		
		
		
		
		
		
		
	}
		
			

	
	
	
}