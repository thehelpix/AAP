package ru.TheHelpix.aap.configurations;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.TheHelpix.aap.Main;

import java.io.File;
import java.io.IOException;

public class MsgConfig {
    private final static Main plugin = Main.getInstance();
    public static FileConfiguration msgConfig;

    public static FileConfiguration getConfig() {
        return msgConfig;
    }

    public static void saveConfig() {
        File cenalConfigFile = new File(plugin.getDataFolder(), "messages.yml");
        if (!cenalConfigFile.exists()) {
            plugin.saveResource("messages.yml", false);
        }

        msgConfig = new YamlConfiguration();
        try {
            msgConfig.load(cenalConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
