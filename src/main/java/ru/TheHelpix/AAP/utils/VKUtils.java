package ru.TheHelpix.AAP.utils;

import org.bukkit.entity.Player;
import ru.TheHelpix.AAP.Main;

import java.net.InetAddress;

public class VKUtils {
    private final static Main plugin = Main.getInstance();

    public static void sendMessage(int id, String msg) {
        HTTP.post("https://api.vk.com/method/messages.send", "access_token=" + plugin.getConfig().getString("vk.token") + "&user_id="+id+"&message="+msg+"&v=5.71");
    }

    public static void sendLoginAdmin(Player p) {
        InetAddress ip = p.getAddress().getAddress();
        String msg = "Администратор "+p.getName()+" зашёл на режим "+plugin.getConfig().getString("vk.server")+"\nIP: "+ip;
        HTTP.post("https://api.vk.com/method/messages.send", "access_token=" + plugin.getConfig().getString("vk.token") + "&peer_id="+plugin.getConfig().getInt("vk.chat_id")+"&message="+msg+"&v=5.71");
    }

    public static void sendCodeAdmin(Player p, String code, int id) {
        InetAddress ip = p.getAddress().getAddress();
        String msg = "Твой код: "+code+"\nПривет "+p.getName()+"!\n Кто-то зашёл с твоего аккаунта на режим "+plugin.getConfig().getString("vk.server")+"\n\nIP: "+ip;
        HTTP.post("https://api.vk.com/method/messages.send", "access_token=" + plugin.getConfig().getString("vk.token") + "&user_id="+id+"&message="+msg+"&v=5.71");
    }
}
