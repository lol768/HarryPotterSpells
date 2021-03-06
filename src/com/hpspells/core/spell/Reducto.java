package com.hpspells.core.spell;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.hpspells.core.HPS;
import com.hpspells.core.spell.Spell.SpellInfo;

@SpellInfo (
		name="Reducto",
		description="descReducto",
		range=50,
		goThroughWalls=false,
		cooldown=300
)
public class Reducto extends Spell {
    
    public Reducto(HPS instance) {
        super(instance);
    }

    public boolean cast(Player p) {
		Block b = p.getTargetBlock(null, this.getRange());
		if(b.getType() != Material.AIR)
			b.getWorld().createExplosion(b.getLocation(), 4, false);
		return true;
	}

}
