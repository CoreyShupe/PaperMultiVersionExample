package io.github.coreyshupe.multiversion;

import com.google.common.base.Preconditions;
import io.github.coreyshupe.multiversion.common.NmsHandler;
import io.github.coreyshupe.multiversion.nms.v1_18r1.NmsHandler1_18_R1;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.logging.Logger;

public enum VersionSupport {
    V1_18_R1(NmsHandler1_18_R1::new, 758, 757),
    Fallback(NmsHandler1_18_R1::new, 758, 757);

    private final @NotNull NmsHandlerProvider nmsProvider;
    private final int[] protocolVersions;

    VersionSupport(@NotNull NmsHandlerProvider nmsProvider, int... protocolVersions) {
        this.nmsProvider = nmsProvider;
        this.protocolVersions = protocolVersions;
    }

    @Contract("null -> fail; _ -> new")
    @NotNull
    public static NmsHandler getCurrentVersionSupport(Logger logger) {
        Preconditions.checkNotNull(logger, "Logger must not be null for loading version support.");
        //noinspection deprecation This is a proper way to get the local protocol version.
        int localProtocolVersion = Bukkit.getUnsafe().getProtocolVersion();
        for (VersionSupport value : VersionSupport.values()) {
            if (Arrays.stream(value.protocolVersions).anyMatch(i -> i == localProtocolVersion)) {
                logger.info("Loading NMS hook %s for received protocol version %d.".formatted(value.name(), localProtocolVersion));
                return value.nmsProvider.getNmsHandler();
            }
        }
        logger.warning("Failed to hook into local protocol version %d, unsupported. Loading latest version instead.".formatted(localProtocolVersion));
        return VersionSupport.Fallback.nmsProvider.getNmsHandler();
    }

    @FunctionalInterface
    private interface NmsHandlerProvider {
        @Contract("-> new")
        @NotNull
        NmsHandler getNmsHandler();
    }
}
