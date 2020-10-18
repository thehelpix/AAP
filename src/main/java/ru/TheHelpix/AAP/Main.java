package ru.TheHelpix.AAP;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.TheHelpix.AAP.commands.AAPCmd;
import ru.TheHelpix.AAP.events.Events;
import ru.TheHelpix.AAP.utils.Color;
import ru.TheHelpix.AAP.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin implements Listener, Runnable {
    private static Main plugin;
    public HashMap<Player, Integer> login;
    Main helper;
    public static Map<String, String> codes;

    public Main() {
        this.login = new HashMap<Player, Integer>();
        this.helper = this;
    }

    @Override
    public void onEnable() {
        plugin = this;
        if(!Utils.isConfig()){
            Color.info("&2Создание конфига...");
            saveDefaultConfig();
        }
        codes = Maps.newHashMap();
        this.login.clear();
        getCommand("aap").setExecutor(new AAPCmd());
        Color.info("&2Плагин включён!");
        Utils.enablePrint();
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (p.getPlayer().isOp() || p.getPlayer().hasPermission("litebans.*")) {
                if (!this.login.containsKey(p)) {
                    p.sendMessage(Color.parseColors("&6[AAP] &2Введи код с вк!"));
                    Utils.sendCode(p);
                }
            }
        }
        Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin) this, (Runnable) this.helper, 10L, 10L);
        Bukkit.getPluginManager().registerEvents((Listener) this, (Plugin) this.helper);
        Bukkit.getPluginManager().registerEvents(new Events(), this.helper);
        if (getConfig().getString("vk.token").equals("токен")) {
            Bukkit.getScheduler().cancelTasks((Plugin) this);
            Bukkit.getPluginManager().disablePlugin(this);
            Color.info("&aНу Ты это, добавь свой токен в конфиг. А я пока что посплю ;D");
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks((Plugin) this);
        this.login.clear();
        codes.clear();
        Color.info("&aПлагин выключен!");
    }

    public static Main getInstance() {
        return plugin;
    }

    public void run() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (p.getPlayer().isOp() || p.getPlayer().hasPermission("litebans.*")) {
                if (!this.login.containsKey(p)) {
                    p.sendMessage(Color.parseColors("&6[AAP] &2Введи код с вк!"));
                    Utils.sendCode(p);
                }
            }
        }
    }
}
