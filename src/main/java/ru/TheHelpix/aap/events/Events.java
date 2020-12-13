package ru.TheHelpix.aap.events;

import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import ru.TheHelpix.aap.Main;
import ru.TheHelpix.aap.utils.*;

public class Events implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (HashUtils.isHashAdmin(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (HashUtils.isHashAdmin(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (HashUtils.isHashAdmin(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void AsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (HashUtils.isHashAdmin(player)) {
            if (Utils.checkCode(player, event.getMessage())) {
                event.setCancelled(true);
                AdminMessageUtils.loginAdmin(player);
                Main.getInstance().login.put(player, 1);
                event.getPlayer().sendMessage(Color.parseColors("&6[AAP] "+MsgConfigUtils.getAuthMsg("accessCode")));
            }
            if (!Utils.checkCode(player, event.getMessage())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(Color.parseColors("&6[AAP] "+MsgConfigUtils.getAuthMsg("wrongCode")));
                AdminMessageUtils.noAdminCode(player);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Utils.isPermissions(player)) {
            if (ConfigUtils.isAdmin(player.getName())) {
                String randomCode = RandomStringUtils.randomNumeric(6);
                Main.codes.put(player.getName(), randomCode);
                VKUtils.sendCodeAdmin(player, randomCode, ConfigUtils.getIdAdmin(player.getName()));
                Main.getInstance().login.put(player, 0);
                player.sendMessage(Color.parseColors("&6[AAP] "+MsgConfigUtils.getAuthMsg("getCode")));
                AdminMessageUtils.joinAdmin(player);
            } else {
                Utils.kickPlayer(player, "&6[AAP]\n"+MsgConfigUtils.getKickMsg("noIsAdmin"));
                AdminMessageUtils.noAdmin(player);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void DropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (HashUtils.isHashAdmin(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void OnLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (HashUtils.isHashAdmin(player)) {
            HashUtils.removeHashAdmin(player);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void OnKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        if (HashUtils.isHashAdmin(player)) {
            HashUtils.removeHashAdmin(player);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void Command(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (HashUtils.isHashAdmin(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (HashUtils.isHashAdmin(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (HashUtils.isHashAdmin(player)) {
            event.setCancelled(true);
        }
    }
}
