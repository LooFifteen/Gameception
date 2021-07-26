package dev.sllcoding.gameception.server;

import com.github.christian162.EventAPI;
import com.github.christian162.EventAPIOptions;
import dev.sllcoding.gameception.server.commands.GameceptionCommand;
import dev.sllcoding.gameception.server.generators.StoneFlatWorldGenerator;
import dev.sllcoding.gameception.server.listeners.GameListener;
import dev.sllcoding.gameception.server.listeners.ServerListener;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.optifine.OptifineSupport;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;

public class GameceptionServer {

    private static GameceptionServer INSTANCE;
    public static GameceptionServer getInstance() {
        return INSTANCE;
    }

    private InstanceContainer instance;
    private EventAPI eventAPI;

    public void init(String[] args) {
        INSTANCE = this;

        // ENABLE SETTINGS
        MojangAuth.init();
        OptifineSupport.enable();


        // CREATE INSTANCES
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        instance = instanceManager.createInstanceContainer();
        instance.setChunkGenerator(new StoneFlatWorldGenerator());


        // CREATE EVENT MANAGER
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        EventAPIOptions eventAPIOptions = new EventAPIOptions();
        eventAPIOptions.setDefaultParentNode(globalEventHandler);
        eventAPIOptions.setRegisterInvalidChildren(false);
        eventAPI = new EventAPI(eventAPIOptions);


        // FINAL INITIALISATION
        registerListeners();
        registerCommands();
    }

    public void postInit(String[] args) {

    }

    private void registerListeners() {
        eventAPI.register(new ServerListener());
        eventAPI.register(new GameListener());
    }

    private void registerCommands() {
        MinecraftServer.getCommandManager().register(new GameceptionCommand());
    }

    public InstanceContainer getMainInstance() {
        return instance;
    }

    public EventAPI getEventAPI() {
        return eventAPI;
    }

}
