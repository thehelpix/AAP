package ru.TheHelpix.aap.utils;

import org.bukkit.entity.Player;
import ru.TheHelpix.aap.Main;

import java.net.InetAddress;

public class VKUtils {
    private final static Main plugin = Main.getInstance();

    public static void sendMessage(int id, String msg) {
        HTTP.post("https://api.vk.com/method/messages.send", "access_token=" + plugin.getConfig().getString("vk.token") + "&user_id="+id+"&message="+msg+"&v=5.71");
    }

    public static void sendLoginPeerAdmin(Player p) {
        InetAddress ip = p.getAddress().getAddress();
        String msg = "Администратор %player% зашёл на режим %server%\nIP: %ip%".replace("%player%", p.getName()).replace("%ip%", String.valueOf(ip)).replace("%server%", plugin.getConfig().getString("vk.server"));
        HTTP.post("https://api.vk.com/method/messages.send", "access_token=" + plugin.getConfig().getString("vk.token") + "&peer_id="+plugin.getConfig().getInt("vk.chat_id")+"&message="+msg+"&v=5.71");
    }

    public static void sendCodeAdmin(Player p, String code, int id) {
        InetAddress ip = p.getAddress().getAddress();
        String msg = MsgConfigUtils.getVKMsg("sendCode").replace("%player%", p.getName()).replace("%ip%", String.valueOf(ip)).replace("%server%", plugin.getConfig().getString("vk.server")).replace("%code%", code).replace("%idvk%", String.valueOf(id));
        HTTP.post("https://api.vk.com/method/messages.send", "access_token=" + plugin.getConfig().getString("vk.token") + "&user_id="+id+"&message="+msg+"&v=5.71");
    }
}
