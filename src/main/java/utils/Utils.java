package utils;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class Utils {

	public Utils(){	
		
	}
	public void Fireworks(Player p){
		//Code ported to function by ElCreeperHD
        //Spawn the Firework
        Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
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
	
	public void Fireworks(Location l){
		//Code ported to function by ElCreeperHD
        //Spawn the Firework
        Firework fw = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
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
	public ChatColor getStringColor(String skill)	{
		  if(skill.equalsIgnoreCase("Beheading")){
            return ChatColor.GREEN;
					  
				  }else if(skill.equalsIgnoreCase("Freeze"))	{
					  return ChatColor.AQUA;
				  }else if(skill.equalsIgnoreCase("Vampiric Regeneration"))	{
					  return ChatColor.DARK_RED;
					  }
				  else if(skill.equalsIgnoreCase("Venomous Strike"))	{
					  return ChatColor.DARK_GREEN;
					  }
				  else if(skill.equalsIgnoreCase("Vanish"))	{
					  return ChatColor.DARK_GRAY;
					  }
				  else if(skill.equalsIgnoreCase("Fireworks"))	{
					  return ChatColor.LIGHT_PURPLE;
					  }
				  else if(skill.equalsIgnoreCase("Lightning"))	{
					  return ChatColor.YELLOW;
					  }
				  else if(skill.equalsIgnoreCase("ALL"))	{
					  return ChatColor.RED;
					  }
				  
		return ChatColor.WHITE;
	}	
	
}
