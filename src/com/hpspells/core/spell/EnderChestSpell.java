package com.hpspells.core.spell;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.hpspells.core.HPS;
import com.hpspells.core.spell.Spell.SpellInfo;

@SpellInfo (
		name="Enderchest",
		description="descEnderChest",
		range=10,
		goThroughWalls=false,
		cooldown=0,
		icon=Material.ENDER_CHEST
		)
public class EnderChestSpell extends Spell{
    
    public EnderChestSpell(HPS instance) {
        super(instance);
    }
	
	@Override
	public boolean cast(Player p) {
		Inventory i = p.getEnderChest();
		p.openInventory(i);
		return true;
	}

}
