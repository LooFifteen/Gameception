package dev.sllcoding.gameception.games.framework.server.instances;

import dev.sllcoding.gameception.games.framework.server.GameceptionServer;
import dev.sllcoding.gameception.games.framework.server.generators.GameWorldGenerator;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.DimensionType;

import java.util.UUID;

public class GameWorldInstance extends InstanceContainer {

    private static final DimensionType GAME_WORLD_DIMENSION = DimensionType.builder(NamespaceID.from("gameception", "luis_was_here")).fixedTime(18000L).ambientLight(15).build();

    static { // yes i wrote this, yes i dont care
        MinecraftServer.getDimensionTypeManager().addDimension(GAME_WORLD_DIMENSION);
    }

    public static GameWorldInstance newGameWorld() {
        GameWorldInstance instance = new GameWorldInstance();
        MinecraftServer.getInstanceManager().registerInstance(instance);
        return instance;
    }

    private GameWorldInstance() {
        super(UUID.randomUUID(), GAME_WORLD_DIMENSION);
        setChunkGenerator(new GameWorldGenerator());
    }

    public void delete() {
        for (Player player : players) {
            player.setInstance(GameceptionServer.getInstance().getMainInstance(), new Pos(0, 40, 0));
        }
        MinecraftServer.getInstanceManager().unregisterInstance(this);
    }

}
