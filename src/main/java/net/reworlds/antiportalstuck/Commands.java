package net.reworlds.antiportalstuck;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commands implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length < 1) {
            return false;
        }

        switch (args[0]) {
            case "reload" -> {
                Config.loadConfig();
                return true;
            }
            case "range" -> {
                int range;
                if (args.length < 2) {
                    sender.sendMessage(AntiPortalStuck.PREFIX.concat("§cSelect the block search radius (from 1 to 5 inclusive)"));
                    return false;
                }
                try {
                    range = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(AntiPortalStuck.PREFIX.concat("§c").concat(args[1]).concat(" is not a number."));
                    return false;
                }
                if (!(range > 0 && range <= 5)) {
                    sender.sendMessage(AntiPortalStuck.PREFIX.concat("§cWrong radius. The radius must be in the range from 1 to 5 inclusive"));
                    return false;
                }
                Config.updateConfig(range);
                return true;
            }
            case "forcetp" -> {
                if (args.length < 2) {
                    sender.sendMessage(AntiPortalStuck.PREFIX.concat("§cSelect the state (true or false)"));
                    return false;
                }
                if ("true".equalsIgnoreCase(args[1])) {
                    Config.updateConfig(true);
                    return true;
                } else if ("false".equalsIgnoreCase(args[1])) {
                    Config.updateConfig(false);
                    return true;
                } else {
                    sender.sendMessage(AntiPortalStuck.PREFIX.concat("§cWrong state. The state must be true or false"));
                    return false;
                }
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> commands = Arrays.asList("forcetp", "range", "reload");
        List<String> forceTP = Arrays.asList("true", "false");
        List<String> range = Arrays.asList("1", "2", "3", "4", "5");

        if (!sender.isOp()) {
            return null;
        }

        if (args.length == 1) {
            return commands;
        } else if (args.length == 2) {
            if ("forcetp".equalsIgnoreCase(args[0])) {
                return forceTP;
            } else if ("range".equalsIgnoreCase(args[0])) {
                return range;
            }
        }
        return new ArrayList<>();
    }
}
