package ru.TheHelpix.aap;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.TheHelpix.aap.commands.AAPCmd;
import ru.TheHelpix.aap.configurations.MsgConfig;
import ru.TheHelpix.aap.events.Events;
import ru.TheHelpix.aap.utils.Color;
import ru.TheHelpix.aap.utils.MsgConfigUtils;
import ru.TheHelpix.aap.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin implements Runnable {
    private static Main plugin;
    public HashMap<Player, Integer> login;
    Main helper;
    public static Map<String, String> codes;

    public Main() {
        this.login = new HashMap<>();
        this.helper = this;
    }

    @Override
    public void onEnable() {
        plugin = this;
        if(!Utils.isConfig()){
            Color.info("&2Создание конфига...");
            saveDefaultConfig();
        }
        MsgConfig.saveConfig();
        codes = Maps.newHashMap();
        this.login.clear();
        getCommand("aap").setExecutor(new AAPCmd());
        Color.info("&2Плагин включён!");
        Utils.enablePrint();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Utils.isPermissions(p)) {
                if (!this.login.containsKey(p)) {
                    p.sendMessage(Color.parseColors("&6[AAP] "+MsgConfigUtils.getAuthMsg("getCode")));
                    Utils.sendCode(p);
                }
            }
        }
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, this.helper, 10L, 10L);
        Bukkit.getPluginManager().registerEvents(new Events(), this.helper);
        if (getConfig().getString("vk.token").equals("токен")) {
            Color.info("&aНу Ты это, добавь свой токен в конфиг. А я пока что посплю ;D");
            Bukkit.getScheduler().cancelTasks(this);
            Bukkit.getPluginManager().disablePlugin(this);
        }
        Utils.checkUpdate(this);
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        this.login.clear();
        codes.clear();
        Color.info("&aПлагин выключен!");
    }

    public static Main getInstance() {
        return plugin;
    }

    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Utils.isPermissions(p)) {
                if (!this.login.containsKey(p)) {
                    p.sendMessage(Color.parseColors("&6[AAP] "+ MsgConfigUtils.getAuthMsg("getCode")));
                    Utils.sendCode(p);
                }
            }
        }
    }
}
