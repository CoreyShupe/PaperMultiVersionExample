package io.github.coreyshupe.multiversion.common;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;

public interface NmsHandler {
    @Contract("null -> fail; !null -> new")
    Component getMessage(Player player);
}
