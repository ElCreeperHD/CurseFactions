package com.github.elcreeperhd.CurseFactions;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

public class FactionUtils {
/*
 * Faction Skills coded by Mr_Matwin3 @2016 All rights reserved.
 */
	public FactionUtils(){
		
	}
	
	String getLocFaction(Location loc){	
		Faction f = BoardColl.get().getFactionAt(PS.valueOf(loc));
		return f.getName();
	}
	boolean isSafeZone(Location loc)	{
		Faction f = BoardColl.get().getFactionAt(PS.valueOf(loc));
		Faction safezone = FactionColl.get().getSafezone();
		String safezonename = safezone.getName();
		if(f.getName().equals(safezonename)){	
			return true;
		}
		
return false;
		
	}
	
	boolean isPlayerTerritory(Player p , Location loc)	{
		  MPlayer mp = MPlayer.get(p);
		  Faction mf = mp.getFaction();
			Faction f = BoardColl.get().getFactionAt(PS.valueOf(loc));
			if(f.getName().equals(mf.getName())){	
				return true;
			}
		  
		return false;
	}
	
	boolean isFactionTerritory(Faction f , Location loc)	{

			Faction fl = BoardColl.get().getFactionAt(PS.valueOf(loc));
			if(f.getName().equals(fl.getName())){	
				return true;
			}
		  
		return false;
	}
	boolean CanAttack(Faction f1, Faction f2){	
		if (f1.getRelationTo(f2) == Rel.ALLY || f1.getRelationTo(f2) == Rel.TRUCE){	
			return false;
		}else if (f1.getName().equals(f2.getName())){	
			return false;
		}
		return true;
	}
	boolean SameFaction(Faction f1, Faction f2){
		 if (f1.getName().equals(f2.getName())){	
				return true;
			}
		return false;
	}
	boolean CanAttackInFactionTerritory(Faction f1, Faction f2){	
		if (f1.getRelationTo(f2) == Rel.ENEMY){	
			return true;
		}
		return false;
	}
	boolean AttackAllowed(Faction f1, Faction f2, Location loc){	
		if(isSafeZone(loc) == true){	
			return false;
		}else{	
            if(SameFaction(f1,f2)){	
            	return false;
            }else	{
            	if(isFactionTerritory(f2, loc) == true){	
            		if(CanAttackInFactionTerritory(f1, f2) == true){	
            			return true;
            		}
            	}else{	
            	if(CanAttack(f1, f2) == true){	
            		return true;
            	}//end canattack
            	else{	
            		return false;
            	}//end else can attack
            	}
            	
            }//fin else
		}//fin else
		return true;
	}	
	

	}

