package org.Nokwok.Events;

import net.milkbowl.vault.chat.Chat;
import org.Nokwok.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class InventoryEvent implements Listener {

    Main plugin;
    private Chat chat = null;

    public InventoryEvent(Main main, Chat chat) {
        this.plugin = main;
        this.chat = chat;
    }

    @EventHandler
    public void onInventoryMove(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (p.hasPermission(plugin.getConfig().getString("Lobby.Interact"))) {
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onInventoryClick(PlayerInteractEvent e) {
        Player p =  e.getPlayer();
        Inventory serverinv = Bukkit.createInventory(p, 27, ChatColor.translateAlternateColorCodes('&', "&8Gamemode Selector"));
        ItemStack EventItem = new ItemStack(Material.NETHER_STAR);
        ItemMeta EventMeta = EventItem.getItemMeta();
        EventMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&B&lEvent"));
        ArrayList elist = new ArrayList<String>();
        elist.add(ChatColor.translateAlternateColorCodes('&', "&7 "));
        elist.add(ChatColor.translateAlternateColorCodes('&', "&7Event: &b100VMINI "));
        elist.add(ChatColor.translateAlternateColorCodes('&', "&7 "));
        EventMeta.setLore(elist);
        EventItem.setItemMeta(EventMeta);
        serverinv.setItem(11, EventItem);

        ItemStack SMPItem = new ItemStack(Material.GOLDEN_SHOVEL);
        ItemMeta SMPMeta = SMPItem.getItemMeta();
        SMPMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lSMP"));
        SMPItem.setItemMeta(SMPMeta);
        serverinv.setItem(15, SMPItem);

        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
        }

        if (p.getItemInHand() != null && p.getItemInHand().getType() == Material.COMPASS) {
            p.openInventory(serverinv);
        } else {
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        p.getInventory().clear();
        ItemStack server = new ItemStack(Material.COMPASS);
        ItemMeta servermeta = server.getItemMeta();
        servermeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&3Server List"));
        ArrayList<String> serverlore = new ArrayList<>();
        serverlore.add(ChatColor.translateAlternateColorCodes('&', "&3Server List"));
        servermeta.setLore(serverlore);
        server.setItemMeta(servermeta);
        p.getInventory().setItem(4, server);


    }

}
