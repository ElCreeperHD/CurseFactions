package com.github.elcreeperhd.CurseFactions;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;





public class CommandListener implements CommandExecutor{
	
	private Main plugin;

	public CommandListener(Main plugin)
	{
	  this.plugin = plugin;
	}
	   private Bounty bounty;
	   public CommandListener(Bounty bounty) {
	     this.bounty = bounty; 
	   }
	public Logger log = Bukkit.getLogger();
	String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "CurseMC" + ChatColor.WHITE + "]";
	

	

	public boolean onCommand(CommandSender cmds, Command cmd, String string, String[] args) {
		if(cmd.getName().equalsIgnoreCase("fireworks"))	{

	        if (args.length == 0)
	        { 	
	        	if(cmds.hasPermission("curse.firework")){	
				Player p = (Player)cmds; 	
                cmds.sendMessage(prefix + ChatColor.GREEN + "" + ChatColor.BOLD + " Fireworks fired!");
                
                 fireworks(p);
       
	        	}	
	        	else	{
	        		cmds.sendMessage(prefix + ChatColor.RED + "You don't have permission");
	        	}
	        }else if (args.length == 1){	
               Player p = Bukkit.getPlayer(args[0]);
               if(p != null){	
            	   cmds.sendMessage(prefix + ChatColor.GREEN + "" + ChatColor.BOLD + " Fireworks fired at the location of " + p.getName() + "!");
                   fireworks(p);   
               }else{	
            	   cmds.sendMessage(prefix + ChatColor.RED + "" + ChatColor.BOLD + " That player is not online!");
               }
	        	
	        	
	        }//close args length 1
	        else if(args.length >= 2){	
	        	cmds.sendMessage(prefix + ChatColor.RED + " Oops, I think this command is not valid!");
	        	cmds.sendMessage(prefix + ChatColor.AQUA + " -Mr_Matwin3"); 	
	        	
	        	
	        	
	        }//close not valid (more or equal 2) arg length
           
	        
	     return true;   
	        
		}
		else if(cmd.getName().equalsIgnoreCase("curse"))	{

	        if (args.length == 0)
	        { 	
			//	Player p = (Player)cmds; 	
                cmds.sendMessage(ChatColor.GREEN + "+++++++++++++++++++++++++++++");
                cmds.sendMessage(ChatColor.GREEN + "+" + "          " + ChatColor.YELLOW + "CurseMC V 1.1.0          " + ChatColor.GREEN + "+");
                cmds.sendMessage(ChatColor.GREEN + "+" + "    " + ChatColor.YELLOW + "Developed by Mr_Matwin3     " + ChatColor.GREEN + "+");
                cmds.sendMessage(ChatColor.GREEN + "+" + "            " + ChatColor.RED + "/curse help           " + ChatColor.GREEN +  "+");
                cmds.sendMessage(ChatColor.GREEN + "+++++++++++++++++++++++++++++");
	        }
	        else if (args.length == 1){
	         if(args[0].equalsIgnoreCase("skills"))	{
                cmds.sendMessage(ChatColor.RED + "Use /curse skills item Skill Level");
	        	}//close skills
	         else if(args[0].equalsIgnoreCase("building"))	{
	        	 if(cmds.hasPermission("curse.building")){	
	        	 if(cmds instanceof Player){
	        		 Player p = (Player)cmds; 
	        		 cmds.sendMessage(ChatColor.GREEN + "You added a block in your foot!");
	        		 Location loc = p.getLocation();
	        		 loc.setY(loc.getY() - 1);
	        		 Block b = loc.getBlock();
	        		 b.setType(Material.GLASS);
	        	 }else{
	        		 cmds.sendMessage(ChatColor.RED + "You need to be a player to execute this command!");
	        	 }
	        	 }//end perm
	        	 else{	
	        		 cmds.sendMessage(ChatColor.RED + "You need permissions!");
	        	 }
	         }
	         else if(args[0].equalsIgnoreCase("help"))	{
	          cmds.sendMessage(ChatColor.RED + "CurseMC Command Help:");
	          cmds.sendMessage(ChatColor.GOLD + "/curse skills" + ChatColor.GREEN + " to get item with skills");
	          cmds.sendMessage(ChatColor.GOLD + "/curse buliding" + ChatColor.GREEN + " to put GLASS block in your foot");
	          cmds.sendMessage(ChatColor.GOLD + "/curse developer" + ChatColor.RED + " ???");
	          cmds.sendMessage(ChatColor.GOLD + "/fireworks" + " to launch fireworks at your location");
		      }//close help
	         else if(args[0].equalsIgnoreCase("developer"))	{
	        	 if(cmds.hasPermission("curse.developer") && (cmds.getName().equalsIgnoreCase("Mr_Matwin3") || cmds.getName().equalsIgnoreCase("ElCreeperHD") || cmds.getName().equalsIgnoreCase("Bedan1") ) ){
		          cmds.sendMessage(ChatColor.RED + "HIGH SECURITY COMMAND");
		          cmds.sendMessage(ChatColor.GOLD + "/curse ???" + ChatColor.RED + " ???");
		          cmds.sendMessage(ChatColor.GOLD + "/curse debug" + ChatColor.RED + " ???");
		          cmds.sendMessage(ChatColor.GOLD + "/curse developer talk" + ChatColor.RED + " ???");
	        	 }else	{
	        		 cmds.sendMessage(ChatColor.RED + "You are not Mr_Matwin3 or/and you don't have permissions!");
	        	 }
			      }//close help
	        }//close args length 1
	        else if(args.length == 4){
	        	if(args[0].equalsIgnoreCase("SKILLS"))	{
	      
	        	if(cmds instanceof Player){
		           
	        	Player p = (Player)cmds; 	
      if(cmds.hasPermission("curse.skill.give"))	{

    	   


	      	     Material m = Material.valueOf(args[1]);
	      	     if(m != null)	{
	      	    	 String args2 = args[2].replaceAll("_", " ");
	 	    		ItemStack item = new ItemStack(Material.valueOf(args[1]), 1);
	 	        	cmds.sendMessage(prefix + ChatColor.RED + " You just got an item with skills!");
	    		ItemMeta meta = item.getItemMeta();
	    		List<String> lorelist = new ArrayList<String>();
	    		lorelist.add(ChatColor.GOLD + "Skill: " + getColor(args2) + args2 + " " + ChatColor.DARK_GREEN + "[" + args[3]+ "]");//This is the first line of lore
               meta.setLore(lorelist);
	    		item.setItemMeta(meta);
	    		p.getInventory().addItem(item);
	      	     }	
	      	     else{
	      	    	 cmds.sendMessage(ChatColor.RED + args[1] + " is not a valid item!");
	      	    	 cmds.sendMessage(ChatColor.RED + "Names should be used like: DIAMOND_SWORD");
	      	    	 cmds.sendMessage(ChatColor.RED + "I will try to find a list of Bukkit Material names, sorry!");
	      	     }
 }else{
	 cmds.sendMessage(prefix + ChatColor.RED + " Sorry, you need permissions!");
 }
      
	        	}//end player check
	        	}//fin if 
	        	else if(args[0].equalsIgnoreCase("developer")){	
	        		if(args[1].equalsIgnoreCase("talk"))	{
	        		 if(cmds.hasPermission("curse.developer") && (cmds.getName().equalsIgnoreCase("Mr_Matwin3") || cmds.getName().equalsIgnoreCase("ElCreeperHD") || cmds.getName().equalsIgnoreCase("Bedan1") ) ){
	   		          cmds.sendMessage(ChatColor.RED + "HIGH SECURITY COMMAND");
	                  Player p = Bukkit.getPlayer(args[2]);
                      Bukkit.broadcastMessage(p.getDisplayName() + args[3]);
	   	        	 }else	{
	   	        		 cmds.sendMessage(ChatColor.RED + "You are not Mr_Matwin3 or/and you don't have permissions!");
	   	        	 }
	        	}//end talk
	        	}
	        }
	        else if(args.length == 5)	{
	        	
	        	if(args[0].equalsIgnoreCase("SKILLS"))	{
	      	      
	        	
		           
	        	Player p = Bukkit.getPlayer(args[4]);	
	        	if(p != null){	
      if(cmds.hasPermission("curse.skill.give"))	{

    	   


	      	     Material m = Material.valueOf(args[1]);
	      	     if(m != null)	{
	      	  	 String args2 = args[2].replaceAll("_", " ");
	 	    		ItemStack item = new ItemStack(Material.valueOf(args[1]), 1);
	 	        	cmds.sendMessage(prefix + ChatColor.RED + " You just gave an item with skills to "  + ChatColor.GREEN + args[1]);
	 	        	plugin.log.info(args[1] + " with " + args2 + args[3] + " given to " + args[4]);
	    		ItemMeta meta = item.getItemMeta();
	    		List<String> lorelist = new ArrayList<String>();
	    		lorelist.add(ChatColor.GOLD + "Skill: " + getColor(args2) + args2 + " " + ChatColor.DARK_GREEN + "[" + args[3]+ "]");//This is the first line of lore
               meta.setLore(lorelist);
	    		item.setItemMeta(meta);
	    		p.getInventory().addItem(item);
	      	     }	
	      	     else{
	      	    	 cmds.sendMessage(ChatColor.RED + args[1] + " is not a valid item!");
	      	    	 cmds.sendMessage(ChatColor.RED + "Names should be used like: DIAMOND_SWORD");
	      	    	 cmds.sendMessage(ChatColor.RED + "I will try to find a list of Bukkit Material names, sorry!");
	      	     }
 }else{
	 cmds.sendMessage(prefix + ChatColor.RED + " Sorry, you need permissions!");
 }
	               }else{	
	  	      	  	 String args2 = args[2].replaceAll("_", " ");
	            	   cmds.sendMessage(prefix + ChatColor.RED + "" + ChatColor.BOLD + " That player is not online!");
		 	        	plugin.log.info("Error: Player not online. " + args[1] + " with " + args2 + args[3] + " given with errors to " + args[4]);
	               }//fin is not online
	        	}//fin if 
	        }//fin args
	        else if(args.length >= 6){	

	        	cmds.sendMessage(prefix + ChatColor.RED + " Oops, I think this command is not valid!");
	        	cmds.sendMessage(prefix + ChatColor.AQUA + " -Mr_Matwin3");
	        	
	        	
	        	
	        }//close not valid (more or equal 6) arg length
           
	        
	        
	        return true;
		}//end cmd
		else if(cmd.getName().equalsIgnoreCase("hub")){	
			if(cmds instanceof Player){	
				cmds.sendMessage(ChatColor.GREEN + "Sent to the hub! Thanks for playing :)");
			Player player = (Player)cmds;
			plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
			  ByteArrayDataOutput out = ByteStreams.newDataOutput();
			  out.writeUTF("ConnectOther");
			  out.writeUTF(player.getName());
			  out.writeUTF("lobby");
			  player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
			  return true;
			}
			return true;
			
		}
		
	/*	else if(cmd.getName().equalsIgnoreCase("bounty")) {	
			
	        if (args.length == 0)
	        { 	
	        	cmds.sendMessage(prefix + ChatColor.RED + " Bounty plugin for CurseMC! /bounty player");
	        	cmds.sendMessage(prefix + ChatColor.AQUA + " Coded by Mr_Matwin3");
	        	
	        }
	        if (args.length == 2)
	        { 
	        	if(args[0].equalsIgnoreCase("check")){
	        		Player p = Bukkit.getServer().getPlayer(args[1]);
	        		cmds.sendMessage(prefix + "The bounty for the player " + p.getName() + " is" + bounty.getBounty(p));
	        			
	        		
	        	}else{
	        		
	        	
	        	Player p = Bukkit.getServer().getPlayer(args[0]);
	        	double bounty = Double.valueOf(args[1]);
	        	if(p != null){	
	           bounty.addBounty(p, bounty);
	           
	        	}	
	        	}
	        }
	        
		}*/
		
		
		
		return false;
	}//end of onCommand

	public static void fireworks(Player p){
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
	ChatColor getColor(String skill)	{
		  if(skill.equalsIgnoreCase("Beheading")){
              return ChatColor.GREEN;
					  
				  }else if(skill.equalsIgnoreCase("Freeze"))	{
					  return ChatColor.AQUA;
				  }else if(skill.equalsIgnoreCase("Vampiric"))	{
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
				  else if(skill.equalsIgnoreCase("Venomous Thorns"))	{
					  return ChatColor.DARK_GREEN;
					  }		  
				  else if(skill.equalsIgnoreCase("ALL"))	{
					  return ChatColor.RED;
					  }
				  
		return ChatColor.WHITE;
	}
}
