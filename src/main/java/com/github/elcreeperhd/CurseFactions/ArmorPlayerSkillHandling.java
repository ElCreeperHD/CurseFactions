package com.github.elcreeperhd.CurseFactions;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;



public class ArmorPlayerSkillHandling {
	private String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "CurseMC" + ChatColor.WHITE + "]";

	public ArmorPlayerSkillHandling(){	
		
	}
	public void Main(PlayerInventory inv, Player damager, Player damaged, ItemStack item){	
		  MPlayer damagedm = MPlayer.get(damaged);
		  MPlayer damagerm = MPlayer.get(damager);
		  Faction damagedf = damagedm.getFaction();
		  Faction damagerf = damagerm.getFaction();
		  FactionUtils fu = new FactionUtils();	
	      ItemMeta im = item.getItemMeta();		  	  
	  	Bukkit.broadcastMessage("DEBUG: STEP 3");		  		    
			 if(fu.AttackAllowed(damagerf, damagedf, damaged.getLocation())){	
				  
					Bukkit.broadcastMessage("DEBUG: STEP 4");		
				  if(im.hasLore() == true)	{
						Bukkit.broadcastMessage("DEBUG: STEP 5");
					 List<String> lorelist = im.getLore();
				  for (String lore : lorelist)	{
if(lore.contains(ChatColor.GOLD + "Skill")){
	Bukkit.broadcastMessage("DEBUG: STEP 6");
					String[] lore0dividido = lore.split(":"); 
					String habistring = lore0dividido[0]; // "Habilidad"
					String habilidad = lore0dividido[1]; // Nombre de la Habilidad  
					String[] habilidaddividido = habilidad.split(" " + ChatColor.DARK_GREEN + "\\[");
					String nivel = habilidaddividido[1];
					String habilidad2 = habilidaddividido[0];
					String nivelconvertido = nivel.replace("]","");
					int nivelint = Integer.parseInt(nivelconvertido);
					//Bukkit.broadcastMessage("[DEBUG] " + habistring + " " + habilidad + " " + nivel + " " + nivelconvertido + " " + nivelint);
				  if(habilidad2.equalsIgnoreCase(" " + ChatColor.GREEN + "Beheading")){	

           
					  
					  
				  }else if(habilidad2.equalsIgnoreCase(" " + ChatColor.AQUA + "Freeze"))	{

					  
				  }else if(habilidad2.equalsIgnoreCase(" " + ChatColor.DARK_RED + "Vampiric Regeneration"))	{
						 Vampiric(damaged, damager, nivelint);
							//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);
					  }
				  else if(habilidad2.equalsIgnoreCase(" " + ChatColor.DARK_GREEN + "Venomous Thorns"))	{
						Bukkit.broadcastMessage("DEBUG: STEP 7");
						 VenomousT(damaged, damager, nivelint);
							//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);
					  }
				  else if(habilidad2.equalsIgnoreCase(" " + ChatColor.DARK_GRAY + "Vanish"))	{
						 Vanish(damaged, damager, nivelint);
							//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);
					  }
				  else if(habilidad2.equalsIgnoreCase(" " + ChatColor.LIGHT_PURPLE + "Fireworks"))	{

					  }
				  else if(habilidad2.equalsIgnoreCase(" " + ChatColor.YELLOW + "Lightning"))	{

					  }
				  else if(habilidad2.equalsIgnoreCase(" " + ChatColor.RED + "ALL"))	{
						 Vampiric(damaged, damager, nivelint);
						 VenomousT(damaged, damager, nivelint);
						 Vanish(damaged, damager, nivelint);

					  }
				  
			  }//skill:
			  
				  
			  
			  
		 }//fin for 		  
		  
		  
		  
		  
		  
	}
	
			 }	//fin faction
			 
	}	//fin metodo
	
	  private void Vampiric(Player damaged, Player damager, int level)
	  {
		  Random r = new Random();
		//	Bukkit.broadcastMessage("[DEBUG] Void called for Vampiric " + level);
	    float chance = r.nextFloat();
	    if(level == 1){
  		//Bukkit.broadcastMessage("[DEBUG] Level is 1");
	    if (chance <= 0.25f){		
	    	//Potion Effect (20 ticks = 10 seconds)
	    	if (damager.getHealth() <= 19) {
	    damaged.sendMessage(prefix + ChatColor.GREEN + " You got lucky and regenerated half heart!");
	    damaged.setHealth(damaged.getHealth() + 1);
//damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 5));

	    	}
	    }
	  }
	    if(level == 2){
  		//Bukkit.broadcastMessage("[DEBUG] Level is 1");
	    if (chance <= 0.30f){		
	    	//Potion Effect (20 ticks = 10 seconds)
	    	if (damager.getHealth() <= 19) {
	    damaged.sendMessage(prefix + ChatColor.GREEN + " You got lucky and regenerated half heart!");
	    damaged.setHealth(damaged.getHealth() + 1);
//damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 5));
	    	}//end check	
	    	
	    }
	  }
	    
	    
	  }
	  
	private void VenomousT(Player damaged, Player damager, int level)
	  {
		Bukkit.broadcastMessage("DEBUG: STEP 8");
		  Random r = new Random();
			//Bukkit.broadcastMessage("[DEBUG] Void called for Venomous " + level);
	    float chance = r.nextFloat();
	    if(level == 1){
  	//	Bukkit.broadcastMessage("[DEBUG] Level is 1");
	    if (chance <= 0.15f){		
	    	//Potion Effect (20 ticks = 10 seconds)
	    	Bukkit.broadcastMessage("DEBUG: STEP 9");
		    damaged.sendMessage(prefix + ChatColor.GREEN + " You got lucky and poisoned " + ChatColor.RED + damager.getName() + ChatColor.GREEN + " !");
		    damager.sendMessage(prefix + ChatColor.RED + " You were poisoned by " + ChatColor.RED + damaged.getName() + ChatColor.RED + " !");
   damager.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));

	  }
	    
	  }//fin level
	    
	 else if(level == 2){
	   //Bukkit.broadcastMessage("[DEBUG] Level is 1");
		 if (chance <= 0.20f){		
		   //Potion Effect (20 ticks = 10 seconds)
				Bukkit.broadcastMessage("DEBUG: STEP 9");
			    damaged.sendMessage(prefix + ChatColor.GREEN + " You got lucky and poisoned " + ChatColor.RED + damager.getName() + ChatColor.GREEN + " !");
			    damager.sendMessage(prefix + ChatColor.RED + " You were poisoned by " + ChatColor.RED + damaged.getName() + ChatColor.RED + " !");
	   damager.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));

}
		    
		  }//fin level
	    
	    
	    
}
	private void Vanish(Player damaged, Player damager, int level)
	  {
		  Random r = new Random();
			//Bukkit.broadcastMessage("[DEBUG] Void called for Venomous " + level);
	    float chance = r.nextFloat();
	    if(level == 1){
  	//	Bukkit.broadcastMessage("[DEBUG] Level is 1");
	    if (chance <= 0.10f){		
	    	//Potion Effect (20 ticks = 10 seconds)
		    damaged.sendMessage(prefix + ChatColor.GREEN + " You got lucky and got invisibilty for 3 seconds!");
   damaged.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60, 1));

	  }
	    
	  }//fin level
	    
	 else if(level == 2){
	   //Bukkit.broadcastMessage("[DEBUG] Level is 1");
		 if (chance <= 0.15f){		
		   //Potion Effect (20 ticks = 10 seconds)
			    damaged.sendMessage(prefix + ChatColor.GREEN + " You got lucky and got invisibilty for 3 seconds!");
			     damaged.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60, 1));

}
		    
		  }//fin level
	    
	    
	    
}	  
	
}
