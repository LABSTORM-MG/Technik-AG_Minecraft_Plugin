package net.labstorm.technik_ag.commands;

import net.labstorm.technik_ag.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VotekickCommand implements CommandExecutor {

    private static final ArrayList<Player> votedYes = new ArrayList<>(), votedNo = new ArrayList<>();
    private static boolean ongoingVote = false;

    // TODO wenn alle abgestimmt sofort beenden
    // TODO sehen wer wie abgestimmt hat
    // TODO feedback wie viele bereits abgestimmt haben / time remaining
    // TODO cooldown vote start cooldown (3 min)

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            // TODO: Fehlermeldung
            return true;
        }

        if (!(sender instanceof Player)) {
            // TODO: Fehlermeldung
            return true;
        }

        if (ongoingVote) {
            switch (args[0]) {
                case "yes" -> votedYes.add((Player) sender);
                case "no" -> votedNo.add((Player) sender);
            }
        }

        Player playerToKick = Bukkit.getPlayer(args[0]);
        if (playerToKick == null || !playerToKick.isOnline()) {
            // TODO: Fehlermeldung
            return true;
        }

        // start vote prozess
        if (ongoingVote) {
            // TODO: Fehlermeldung
            return true;
        }

        ongoingVote = true;
        // conclusion
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            // if less than 50% of all players voted the result is invalid
            if (votedYes.size() + votedNo.size() < Bukkit.getOnlinePlayers().size()) {
                // TODO: Nachricht

            } else if (votedYes.size() > votedNo.size()) {
                // TODO: Nachricht
                playerToKick.kickPlayer(""); // FIXME: kick message
            }

            votedYes.clear();
            votedNo.clear();
            ongoingVote = false;
        }, 20 * 30);

        // create message Fixme: Nachrichten
        TextComponent message = new TextComponent();

        TextComponent startMsg = new TextComponent("Soll " + playerToKick.getDisplayName() + " gekickt werden? \n");
        startMsg.setColor(ChatColor.YELLOW);

        TextComponent yesMsg = new TextComponent("[yes] ");
        yesMsg.setColor(ChatColor.GREEN);
        yesMsg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/votekick yes"));

        TextComponent noMsg = new TextComponent("[no]");
        noMsg.setColor(ChatColor.RED);
        noMsg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/votekick yes"));

        message.addExtra(startMsg);
        message.addExtra(yesMsg);
        message.addExtra(noMsg);

        // send message
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(message);
        }

        return true;
    }

}

