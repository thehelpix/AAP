package ru.TheHelpix.aap.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.TheHelpix.aap.Main;

import java.net.InetAddress;

public class AdminMessageUtils {
    public static void joinAdmin(Player p) {
        InetAddress ip = p.getAddress().getAddress();
        for (Player pp : Bukkit.getOnlinePlayers()) {
            if (Main.getInstance().login.get(pp) != 0) {
                pp.sendMessage(Color.parseColors("&6[AAP] "+MsgConfigUtils.getAdminBc("joinAdmin").replace("%player%", p.getName()).replace("%ip%", String.valueOf(ip))));
            }
        }
    }

    public static void noAdmin(Player p) {
        InetAddress ip = p.getAddress().getAddress();
        for (Player pp : Bukkit.getOnlinePlayers()) {
            if (Utils.isPermissions(pp)) {
                pp.sendMessage(Color.parseColors("&6[AAP] "+MsgConfigUtils.getAdminBc("noAdminJoin").replace("%player%", p.getName()).replace("%ip%", String.valueOf(ip))));
            }
        }
    }

    public static void noAdminCode(Player p) {
        InetAddress ip = p.getAddress().getAddress();
        for (Player pp : Bukkit.getOnlinePlayers()) {
            if (Main.getInstance().login.get(pp) != 0) {
                pp.sendMessage(Color.parseColors("&6[AAP] "+MsgConfigUtils.getAdminBc("wrongCode").replace("%player%", p.getName()).replace("%ip%", String.valueOf(ip))));
            }
        }
    }

    public static void loginAdmin(Player p) {
        InetAddress ip = p.getAddress().getAddress();
        for (Player pp : Bukkit.getOnlinePlayers()) {
            if (Main.getInstance().login.get(pp) != 0) {
                pp.sendMessage(Color.parseColors("&6[AAP] "+MsgConfigUtils.getAdminBc("accessCode").replace("%player%", p.getName()).replace("%ip%", String.valueOf(ip))));
            }
        }
    }

    public static void kickRemoveAdmin(String p) {
        Player player = Bukkit.getPlayerExact(p);
        if (player != null) {
            Bukkit.getScheduler().runTask(Main.getInstance(), () -> player.kickPlayer(Color.parseColors("&6[AAP]\n"+MsgConfigUtils.getKickMsg("removeKick"))));
        }
    }
}
