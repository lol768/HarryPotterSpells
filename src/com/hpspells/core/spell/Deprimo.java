package com.hpspells.core.spell;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.hpspells.core.HPS;
import com.hpspells.core.spell.Spell.SpellInfo;
import com.hpspells.core.util.Targeter;

@SpellInfo (
		name="Deprimo",
		description="descDeprimo",
		range=20,
		goThroughWalls=false,
		cooldown=180
)
public class Deprimo extends Spell implements Listener {
	private static List<String> players = new ArrayList<String>();
	
    public Deprimo(HPS instance) {
        super(instance);
    }

	public boolean cast(Player p) {
		if(Targeter.getTarget(p, this.getRange(), this.canBeCastThroughWalls()) instanceof Player) {
			
			LivingEntity target = Targeter.getTarget(p, this.getRange(), this.canBeCastThroughWalls());
			
			int duration = (int) getTime("duration", 100l);
			
			target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, 1));
			
			if (target instanceof Player) {
				final Player player = (Player) target;
				Deprimo.players.add(player.getName());
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HPS, new Runnable() {
					   
					@Override
					public void run() {
					   Deprimo.players.remove(player.getName());
					}
					   
				}, 400L);
			}
			return true;
			
		} else {
			HPS.PM.warn(p, HPS.Localisation.getTranslation("spellLivingEntityOnly"));
			return false;
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if(Deprimo.players.contains(e.getPlayer().getName())) {
			e.getPlayer().setSneaking(true);
			if(e.getFrom().getY() < e.getTo().getY()) {
				e.getPlayer().getLocation().setY(e.getFrom().getY());
			}	
		}
	}

}
