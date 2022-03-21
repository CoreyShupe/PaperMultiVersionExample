package io.github.coreyshupe.multiversion.nms.v1_18r1;

import io.github.coreyshupe.multiversion.common.NmsHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NmsHandler1_18_R1 implements NmsHandler {
    private final MiniMessage miniMessage = MiniMessage.builder().build();

    @Override
    public Component getMessage(Player player) {
        if (player instanceof CraftPlayer craftPlayer) {
            return miniMessage.deserialize("<red>Your current entity id is: <white>%d".formatted(craftPlayer.getHandle().getId()));
        } else {
            throw new IllegalArgumentException("Could not resolve player as a `CraftPlayer`.");
        }
    }
}
