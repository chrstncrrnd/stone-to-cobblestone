package io.github.stonetocobblestone;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class Stone_to_cobblestone extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Logger logger = getLogger();
        // Plugin startup logic
        logger.info("Starting stone to cobblestone plugin");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (player.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")){
                assert block != null;
                if (block.getType() == Material.STONE) {
                    player.swingMainHand();
                    block.setType(Material.COBBLESTONE);
                    Sound sound = Sound.ITEM_AXE_STRIP;
                    Location location = Objects.requireNonNull(event.getClickedBlock()).getLocation();
                    player.getWorld().playSound(location, sound, SoundCategory.BLOCKS, 100, 1);
                }
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
