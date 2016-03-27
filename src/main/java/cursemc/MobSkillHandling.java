package cursemc;

import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import org.bukkit.inventory.meta.FireworkMeta;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MobSkillHandling {
	String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "CurseMC" + ChatColor.WHITE + "]";
	  Logger log = Bukkit.getLogger();

public MobSkillHandling(){
	
	
}
//Beheading does not apply to mobs
/*void Beheading(Entity damaged, Player damager, int level)
{
	  Random r = new Random();

  float chance = r.nextFloat();
	//Bukkit.broadcastMessage("[DEBUG] Void called for Beheading " + level);
  if(level == 1){
//		Bukkit.broadcastMessage("[DEBUG] Level is 1");
  if (chance <= 0.20f){	
  	 damager.sendMessage(prefix +  " You hit someone using Behead!");
  damager.sendMessage(prefix +  " You got lucky and got the head of " + damaged.getName() + " !");
	ItemStack skull = new ItemStack(397, 1, (short) 3);
	SkullMeta meta = (SkullMeta) skull.getItemMeta();
	meta.setOwner(damaged.getName());
	skull.setItemMeta(meta);
	damager.getInventory().addItem(skull);
  }	//fin chance
}
  if(level == 2){
//		Bukkit.broadcastMessage("[DEBUG] Level is 1");
  if (chance <= 0.25f){	
  	 damager.sendMessage(prefix +  " You hit someone using Behead!");
  damager.sendMessage(prefix +  " You got lucky and got the head of " + damaged.getName() + " !");
	ItemStack skull = new ItemStack(397, 1, (short) 3);
	SkullMeta meta = (SkullMeta) skull.getItemMeta();
	meta.setOwner(damaged.getName());
	skull.setItemMeta(meta);
	damager.getInventory().addItem(skull);
  }	//fin chance
}
  
  
}
*/
void Freeze(LivingEntity damaged, Player damager, int level)
{
	  Random r = new Random();
		//Bukkit.broadcastMessage("[DEBUG] Void called for Freeze " + level);
  float chance = r.nextFloat();
  if(level == 1){
		//Bukkit.broadcastMessage("[DEBUG] Level is 1");
  if (chance <= 0.15f){		
  	//Potion Effect (20 ticks = 10 seconds
  damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and froze " + ChatColor.RED + "a mob" + ChatColor.GREEN + " !");
damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 5));

}
  }
  else if(level == 2){
		//Bukkit.broadcastMessage("[DEBUG] Level is 1");
  if (chance <= 0.20f){		
  	//Potion Effect (20 ticks = 10 seconds)
  damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and froze " + ChatColor.RED + "a mob" + ChatColor.GREEN + " !");
damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 5));

}
  }	    
  
  
}

void Vampiric(LivingEntity damaged, Player damager, int level)
{
	  Random r = new Random();
	//	Bukkit.broadcastMessage("[DEBUG] Void called for Vampiric " + level);
  float chance = r.nextFloat();
  if(level == 1){
		//Bukkit.broadcastMessage("[DEBUG] Level is 1");
  if (chance <= 0.25f){		
  	//Potion Effect (20 ticks = 10 seconds)
  	if (damager.getHealth() <= 19) {
  damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and regenerated half heart!");
  damager.setHealth(damager.getHealth() + 1);
//damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 5));

  	}
  }
}
  if(level == 2){
		//Bukkit.broadcastMessage("[DEBUG] Level is 1");
  if (chance <= 0.30f){		
  	//Potion Effect (20 ticks = 10 seconds)
  	if (damager.getHealth() <= 19) {
  damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and regenerated half heart!");
  damager.setHealth(damager.getHealth() + 1);
//damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 5));
  	}//end check	
  	
  }
}
  
  
}

void Venomous(LivingEntity damaged, Player damager, int level)
{
	  Random r = new Random();
		//Bukkit.broadcastMessage("[DEBUG] Void called for Venomous " + level);
  float chance = r.nextFloat();
  if(level == 1){
	//	Bukkit.broadcastMessage("[DEBUG] Level is 1");
  if (chance <= 0.15f){		
  	//Potion Effect (20 ticks = 10 seconds)
	    damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and poisoned " + ChatColor.RED + "a mob" + ChatColor.GREEN + " !");
damaged.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));

}
  
}//fin level
  
else if(level == 2){
 //Bukkit.broadcastMessage("[DEBUG] Level is 1");
	 if (chance <= 0.20f){		
	   //Potion Effect (20 ticks = 10 seconds)
	damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and poisoned " + ChatColor.RED + "a mob" + ChatColor.GREEN + " !");
damaged.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));

}
	    
	  }//fin level
  
  
  
}
void Vanish(LivingEntity damaged, Player damager, int level)
{
	  Random r = new Random();
		//Bukkit.broadcastMessage("[DEBUG] Void called for Venomous " + level);
  float chance = r.nextFloat();
  if(level == 1){
	//	Bukkit.broadcastMessage("[DEBUG] Level is 1");
  if (chance <= 0.10f){		
  	//Potion Effect (20 ticks = 10 seconds)
	    damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and got invisibilty for 3 seconds!");
damager.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60, 1));

}
  
}//fin level
  
else if(level == 2){
 //Bukkit.broadcastMessage("[DEBUG] Level is 1");
	 if (chance <= 0.15f){		
	   //Potion Effect (20 ticks = 10 seconds)
		    damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and got invisibilty for 3 seconds!");
		     damager.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60, 1));

}
	    
	  }//fin level
  
  
  
}	  

void Fireworks(LivingEntity damaged, Player damager, int level)
{
	  Random r = new Random();
		//Bukkit.broadcastMessage("[DEBUG] Void called for Venomous " + level);
  float chance = r.nextFloat();
  if(level == 1){
	//	Bukkit.broadcastMessage("[DEBUG] Level is 1");
  if (chance <= 0.50f){		
  	//Potion Effect (20 ticks = 10 seconds)
	   FireworksD(damager);

}
  
}//fin level
  
else if(level == 2){
 //Bukkit.broadcastMessage("[DEBUG] Level is 1");
	 if (chance <= 0.55f){		
	   //Potion Effect (20 ticks = 10 seconds)
FireworksD(damager);
}
	    
	  }//fin level
  
  
  
}	  	  

void Lightning(LivingEntity damaged, Player damager, int level)
{
	  Random r = new Random();
		//Bukkit.broadcastMessage("[DEBUG] Void called for Venomous " + level);
  float chance = r.nextFloat();
  if(level == 1){
	//	Bukkit.broadcastMessage("[DEBUG] Level is 1");
  if (chance <= 0.10f){		
  	//Potion Effect (20 ticks = 10 seconds)
  	 damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and thrown a Lightning at " + ChatColor.RED + damaged.getName() + " location!");
  	 damager.sendMessage(prefix + ChatColor.RED + " " + damager.getName() + ChatColor.GREEN + " used Lightening!");
	   damaged.getWorld().strikeLightning(damaged.getLocation());

}
  
}//fin level
  
else if(level == 2){
 //Bukkit.broadcastMessage("[DEBUG] Level is 1");
	 if (chance <= 0.15f){		
	   //Potion Effect (20 ticks = 10 seconds)
  	 damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and thrown a Lightning at " + ChatColor.RED + damaged.getName() + " location!");
  	 damager.sendMessage(prefix + ChatColor.RED + " " + damager.getName() + ChatColor.GREEN + " used Lightening!");
		   damaged.getWorld().strikeLightning(damaged.getLocation());
}
	    
	  }//fin level
  
  
  
}	  








	public static void FireworksD(LivingEntity e){
		//Code ported to function by ElCreeperHD
      //Spawn the Firework
      Firework fw = (Firework) e.getWorld().spawnEntity(e.getLocation(), EntityType.FIREWORK);
      FireworkMeta fwm = fw.getFireworkMeta();

      //random
      Random ra = new Random();

      //sets type
      int rt = ra.nextInt(5) + 1;
      Type type = Type.BALL;
      if (rt == 1) type = Type.BALL;
      if (rt == 2) type = Type.BALL_LARGE;
      if (rt == 3) type = Type.BURST;
      if (rt == 4) type = Type.CREEPER;
      if (rt == 5) type = Type.STAR;

      //colors
      int r = ra.nextInt(256);
      int b = ra.nextInt(256);
      int g = ra.nextInt(256);
      Color c1 = Color.fromRGB(r, g, b);

      r = ra.nextInt(256);
      b = ra.nextInt(256);
      g = ra.nextInt(256);
      Color c2 = Color.fromRGB(r, g, b);


      //effect
      FireworkEffect effect = FireworkEffect.builder().flicker(ra.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(ra.nextBoolean()).build();

      //applied effects
      fwm.addEffect(effect);

      //random power!
      int rp = ra.nextInt(2) + 1;
      fwm.setPower(rp);

      //set it
      fw.setFireworkMeta(fwm);     
		
		
	}	


	
}
