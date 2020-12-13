package ru.TheHelpix.aap.utils;

import org.bukkit.configuration.file.FileConfiguration;
import ru.TheHelpix.aap.Main;

import java.util.List;

public class ConfigUtils {
    private static final Main plugin = Main.getInstance();
    private final static FileConfiguration getConfig = Main.getInstance().getConfig();

    public static Boolean isAdmin(String p) {
        return getConfig.contains("admins."+p);
    }

    public static void addAdmin(String p, int id) {
        getConfig.set("admins."+p, id);
        plugin.saveConfig();
    }

    public static void removeAdmin(String p) {
        getConfig.set("admins."+p, null);
        plugin.saveConfig();
    }

    public static List<String> getPermsList() {
        return plugin.getConfig().getStringList("permissions");
    }

    public static int getIdAdmin(String p) {
        return getConfig.getInt("admins."+p);
    }
}
