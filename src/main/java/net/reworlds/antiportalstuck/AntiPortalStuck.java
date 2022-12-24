package net.reworlds.antiportalstuck;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiPortalStuck extends JavaPlugin {

    public static final String PREFIX = "§7[§6APS§7] §e";
    @Override
    public void onEnable() {
        Config.loadConfig();
        register();
    }

    public void register() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new Events(), this);
        Bukkit.getPluginCommand("antiportalstuck").setExecutor(new Commands());
    }
}
