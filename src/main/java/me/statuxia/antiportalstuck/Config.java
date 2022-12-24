package me.statuxia.antiportalstuck;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {

    public static boolean CONFIG_FORCE_TP = false;
    public static int CONFIG_BLOCK_RANGE = 5;

    private static YamlConfiguration config = null;
    private static File configFile;

    public static boolean getFile() {
        try {
            Files.createDirectories(Paths.get("plugins/AntiPortalStuck/"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        configFile = new File("plugins/AntiPortalStuck/config.yml");
        try {
            if (configFile.createNewFile()) {
                updateConfig();
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateConfig() {
        YamlConfiguration update = new YamlConfiguration();
        update.set("force-tp", CONFIG_FORCE_TP);
        update.set("range", CONFIG_BLOCK_RANGE);
        config = update;
        save(update);
    }

    public static void updateConfig(boolean isOn) {
        config.set("force-tp", isOn);
        save(config);
        updateDefault();
    }

    public static void updateConfig(int range) {
        config.set("range", range);
        save(config);
        updateDefault();
    }

    public static void updateDefault() {
        CONFIG_FORCE_TP = config.getBoolean("force-tp");
        CONFIG_BLOCK_RANGE = config.getInt("range");
    }

    public static void loadConfig() {
        if (getFile()) {
            return;
        }

        config = YamlConfiguration.loadConfiguration(configFile);
        checkConfig();
        updateDefault();
    }

    public static void checkConfig() {
        if (!config.contains("force-tp")) {
            config.set("force-tp", CONFIG_FORCE_TP);
        }
        if (!config.contains("range")) {
            config.set("range", CONFIG_BLOCK_RANGE);
        }
    }

    public static void save(YamlConfiguration configuration) {
        try {
            configuration.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
