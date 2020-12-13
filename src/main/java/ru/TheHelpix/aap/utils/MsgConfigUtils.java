package ru.TheHelpix.aap.utils;

import org.bukkit.configuration.file.FileConfiguration;
import ru.TheHelpix.aap.configurations.MsgConfig;

public class MsgConfigUtils {
    private final static FileConfiguration msgCfg = MsgConfig.getConfig();

    public static String getAdminBc(String str) {
        return msgCfg.getString("game_messages.admin_broadcast."+str);
    }

    public static String getKickMsg(String str) {
        return msgCfg.getString("game_messages.kick_messages."+str);
    }

    public static String getAuthMsg(String str) {
        return msgCfg.getString("game_messages.auth_messages."+str);
    }

    public static String getVKMsg(String str) {
        return msgCfg.getString("vk_messages."+str);
    }
}
