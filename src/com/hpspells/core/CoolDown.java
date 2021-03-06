package com.hpspells.core;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.hpspells.core.spell.Spell;

public class CoolDown extends BukkitRunnable {
    private HPS HPS;
	private String player;
	private Spell spell;

	public CoolDown(HPS instance, String playerName, Spell spellName) {
	    HPS = instance;
		player = playerName;
		spell = spellName;
	}

	@Override
	public void run() {
		coolDown();
	}

	public void coolDown() {
		Integer newCoolDown = HPS.SpellManager.getCoolDown(player, spell) - 1;
		if(newCoolDown == 0){
			newCoolDown = null;
		}
		HPS.SpellManager.setCoolDown(player, spell, newCoolDown);
		if(newCoolDown != null && newCoolDown >0){
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HPS, new CoolDown(HPS, player, spell), 20L);
		}
	}

}
