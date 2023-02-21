package net.labstorm.technik_ag.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class SleepListener implements Listener {

    @EventHandler
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent e) {
        // TODO: Nachricht: e.getPlayer + hat sich hingelegt
        int count = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.isSleeping();
            count++;
        }

        if (count >= Bukkit.getOnlinePlayers().size() / 2) {
            // TODO: Nachricht
            e.getPlayer().getWorld().setTime(0);
        }

    }

    @EventHandler
    public void onPlayerBedLeaveEvent(PlayerBedLeaveEvent e) {
        // TODO: Nachricht: e.getPlayer + ist aufgestanden
    }

}
