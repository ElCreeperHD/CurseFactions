package cursemc;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Bounty {
	private Main plugin;

	public Bounty(Main plugin)
	{
	  this.plugin = plugin;
	}
	
	public Bounty(){
	}
/*	public int getKills(String player) {
		File statsFile = new File(plugin.getDataFolder(), "stats.yml");
		FileConfiguration stats = YamlConfiguration.loadConfiguration(statsFile);
		Long kills = stats.get("Players." + player + ".Kills") != null ? stats.getInt("Players."+player+".Kills") : 0L;
		return kills;
		} */
	public double getBounty(Player player) {
	File statsFile = new File(plugin.getDataFolder(), "bounties.yml");
	FileConfiguration stats = YamlConfiguration.loadConfiguration(statsFile);
	double bounty = stats.get("Players." + player.getName() + ".Bounty") != null ? stats.getDouble("Players." + player.getName() + ".Bounty") : 0;
	return bounty;
	}
	
		 
		public void addBounty(Player player , double bounty) {
		File statsFile = new File(plugin.getDataFolder(), "bounties.yml");
		FileConfiguration stats =YamlConfiguration.loadConfiguration(statsFile);
		stats.set("Players." + player.getName() + ".Bounty", getBounty(player) + bounty);
		try {
		stats.save(statsFile);
		} catch (IOException ex) {
		ex.printStackTrace();
		}
		}
		 

		 
		public void setBounty(Player player, double bounty) {
		File statsFile = new File(plugin.getDataFolder(), "bounties.yml");
		FileConfiguration stats = YamlConfiguration.loadConfiguration(statsFile);
		stats.set("Players." + player.getName() + ".Bounty", bounty);
		try {
		stats.save(statsFile);
		} catch (IOException ex) {
		ex.printStackTrace();
		}
		}
}
