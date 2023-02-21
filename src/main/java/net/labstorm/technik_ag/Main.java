package net.labstorm.technik_ag;

import net.labstorm.technik_ag.commands.VotekickCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getCommand("votekick").setExecutor(new VotekickCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
