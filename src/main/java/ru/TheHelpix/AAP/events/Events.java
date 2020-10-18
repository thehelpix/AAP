package ru.TheHelpix.AAP.events;

import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import ru.TheHelpix.AAP.Main;
import ru.TheHelpix.AAP.utils.*;

import static org.bukkit.Bukkit.getLogger;

public class Events implements Listener {
    private final static Main plugin = Main.getInstance();

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onBreak(final BlockBreakEvent event) {
        if (Utils.isHashAdmin(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onPlace(final BlockPlaceEvent event) {
        if (Utils.isHashAdmin(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onPlayerInteract(final PlayerInteractEvent event) {
        if (Utils.isHashAdmin(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void AsyncPlayerChatEvent(final AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (Utils.isHashAdmin(event.getPlayer())) {
            if (Utils.checkCode(event.getPlayer(), event.getMessage())) {
                event.setCancelled(true);
                AdminMessageUtils.loginAdmin(player);
                Main.getInstance().login.put(event.getPlayer(), 1);
                event.getPlayer().sendMessage(Color.parseColors("&6[AAP] &2Код правильный!"));
                getLogger().info("Администратор " + event.getPlayer().getName() + " авторизовался");
            }
            if (!Utils.checkCode(player, event.getMessage())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(Color.parseColors("&6[AAP] &cКод не правильный!"));
                getLogger().info("Администратор " + event.getPlayer().getName() + " ввёл не правильный код");
                AdminMessageUtils.noAdminCode(player);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void OnJoin(final PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (event.getPlayer().isOp() || event.getPlayer().hasPermission("litebans.*")) {
            if (ConfigUtils.isAdmin(p.getName())) {
                String randomCode = RandomStringUtils.randomNumeric(6);
                Main.codes.put(event.getPlayer().getName(), randomCode);
                VKUtils.sendCodeAdmin(p, randomCode, ConfigUtils.getIdAdmin(p.getName()));
                Main.getInstance().login.put(event.getPlayer(), 0);
                getLogger().info("idvk: " + ConfigUtils.getIdAdmin(p.getName()) + " ник: " + event.getPlayer().getName());
                p.sendMessage(Color.parseColors("&6[AAP] &aВведи код из ВК!"));
            } else {
                Utils.kickPlayer(p, "&6[AAP]\n&cПопытка входа.");
                getLogger().info("Попытка входа, ник: " + event.getPlayer().getName());
                AdminMessageUtils.noAdmin(p);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void DropItem(final PlayerDropItemEvent event) {
        if (Utils.isHashAdmin(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void OnLEave(final PlayerQuitEvent event) {
        if (Main.getInstance().login.containsKey(event.getPlayer())) {
            Main.getInstance().login.remove(event.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void OnKick(final PlayerKickEvent event) {
        if (Main.getInstance().login.containsKey(event.getPlayer())) {
            Main.getInstance().login.remove(event.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void Command(final PlayerCommandPreprocessEvent event) {
        if (Utils.isHashAdmin(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onMove(final PlayerMoveEvent event) {
        if (Utils.isHashAdmin(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        if (Utils.isHashAdmin(p)) {
            event.setCancelled(true);
        }
    }
}
