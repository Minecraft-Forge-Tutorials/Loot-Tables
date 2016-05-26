package net.darkhax.tutorial.loottables;

import net.minecraft.init.Items;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetDamage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "loottable", name = "Loot Table Tutorial", version = "1.9.4")
public class LootTablesMod {
    
    @Instance("loottable")
    public static LootTablesMod instance;
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent pre) {
        
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    // Creates a new event that is fired every time a new loot table is loaded.
    @SubscribeEvent
    public void onLootTablesLoaded (LootTableLoadEvent event) {
        
        // Checks to see if the loot table being loaded is the basic dungeon chest.
        if (event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {
            
            // Gets pool2 from the loot table. This pool is where common loot like zombie flesh
            // bones and string goes.
            final LootPool pool2 = event.getTable().getPool("pool2");
            
            // Makes sure the pool has not been deleted.
            if (pool2 != null) {
                
                // Adds cookies to the loot pool. Has a weight of 10 and spawns in stacks of 1
                // to 5.
                pool2.addEntry(new LootEntryItem(Items.COOKIE, 10, 0, new LootFunction[] { new SetCount(new LootCondition[0], new RandomValueRange(1, 5)) }, new LootCondition[0], "loottable:cookie"));
                
                // Adds Lime Green Dye to the loot pool. Has a weight of 10.
                pool2.addEntry(new LootEntryItem(Items.DYE, 10, 0, new LootFunction[] { new SetDamage(new LootCondition[0], new RandomValueRange(10, 10)) }, new LootCondition[0], "loottable:dyes"));
            }
        }
    }
}