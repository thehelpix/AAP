package ru.TheHelpix.AAP.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.TheHelpix.AAP.utils.AdminMessageUtils;
import ru.TheHelpix.AAP.utils.Color;
import ru.TheHelpix.AAP.utils.ConfigUtils;
import ru.TheHelpix.AAP.utils.Utils;

import java.util.regex.Pattern;

public class AAPCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage(Color.parseColors("&6[AAP] &cДоступно для консоли!"));
            return true;
        }
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(Color.parseColors("&6[AAP] &aИспользование:"));
            sender.sendMessage(Color.parseColors("&2/aap add (ник) (idvk) &7- добавление в протект."));
            sender.sendMessage(Color.parseColors("&2/aap remove (ник) &7- удаление из протекта."));
            sender.sendMessage(Color.parseColors("&2/aap get (ник) &7- информация об админе."));
            return true;
        }

        if (args[0].equalsIgnoreCase("add")) {
            if (args.length > 1) {
                if (ConfigUtils.isAdmin(args[1])) {
                    sender.sendMessage(Color.parseColors("&6[AAP] &cСемпааай! Такой админ уже есть в протекте!"));
                    return true;
                }
                if (args.length > 2) {
                    if(!Pattern.matches("[0-9]+", args[2])) {
                        sender.sendMessage(Color.parseColors("&6[AAP] &cСемпай! idvk должен состоять из чисел!"));
                        return true;
                    }
                    int id = Integer.parseInt(args[2]);
                    ConfigUtils.addAdmin(args[1], id);
                    sender.sendMessage(Color.parseColors("&6[AAP] &aАдминистратор &2"+args[1]+" https://vk.com/id"+id+"&a был добавлен в проект!"));
                    return true;
                }
                sender.sendMessage(Color.parseColors("&6[AAP] &aИспользование: &2/aap add"+args[1]+"(idvk)"));
                return true;
            }
            sender.sendMessage(Color.parseColors("&6[AAP] &aИспользование: &2/aap add (ник) (idvk)"));
            return true;
        }

        if (args[0].equalsIgnoreCase("remove")) {
            if (args.length > 1) {
                if (!ConfigUtils.isAdmin(args[1])) {
                    sender.sendMessage(Color.parseColors("&6[AAP] &cСемпаай! Такого Админа в протекте нету!"));
                    return true;
                }
                ConfigUtils.removeAdmin(args[1]);
                AdminMessageUtils.kickRemoveAdmin(args[1]);
                sender.sendMessage(Color.parseColors(Color.parseColors("&6[AAP] &cАдминистратор &4"+args[1]+"&c был удалён с протекта!")));
                return true;
            }
            sender.sendMessage(Color.parseColors("&6[AAP] &aИспользование: &2/aap remove (ник)"));
            return true;
        }

        if (args[0].equalsIgnoreCase("get")) {
            if (args.length > 1) {
                if (!ConfigUtils.isAdmin(args[1])) {
                    sender.sendMessage(Color.parseColors("&6[AAP] &cСемпаай! Такого Админа в протекте нету!"));
                    return true;
                }
                sender.sendMessage(Color.parseColors("&6[AAP] &aИнформация про Админа &2"+args[1]));
                sender.sendMessage(Color.parseColors("&aВ сети: "+ Utils.isOnlineAdmin(args[1])));
                sender.sendMessage(Color.parseColors("&aСсылка на &9ВК&a: &9https://vk.com/"+ConfigUtils.getIdAdmin(args[1])));
                return true;
            }
            sender.sendMessage(Color.parseColors("&6[AAP] &aИспользование: &2/aap get (ник)"));
            return true;
        }
        return false;
    }
}
