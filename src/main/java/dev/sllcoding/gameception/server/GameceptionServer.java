package dev.sllcoding.gameception.server;

import com.github.christian162.EventAPI;
import com.github.christian162.EventAPIOptions;
import dev.sllcoding.gameception.games.tictactoe.TicTacToeGameContainer;
import dev.sllcoding.gameception.games.tictactoe.commands.TicTacToeCommand;
import dev.sllcoding.gameception.games.tictactoe.events.TicTacToeListener;
import dev.sllcoding.gameception.server.commands.GameceptionCommand;
import dev.sllcoding.gameception.server.generators.StoneFlatWorldGenerator;
import dev.sllcoding.gameception.server.listeners.ServerListener;
import dev.sllcoding.gameception.server.listeners.TestingListener;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.optifine.OptifineSupport;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;

public class GameceptionServer {
    private TicTacToeGameContainer ticTacToeGameContainer = new TicTacToeGameContainer();

    private static GameceptionServer INSTANCE;
    public static GameceptionServer getInstance() {
        return INSTANCE;
    }

    private InstanceContainer instance;
    private EventAPI eventAPI;

    public void init(String[] args) {
        // SLL IS UGLY
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
        eventAPI.register(new TicTacToeListener(ticTacToeGameContainer));
    }

    private void registerCommands() {
        CommandManager commandManager = MinecraftServer.getCommandManager();
        commandManager.register(new GameceptionCommand());
        commandManager.register(new TicTacToeCommand(ticTacToeGameContainer));
    }

    public InstanceContainer getMainInstance() {
        return instance;
    }

    public EventAPI getEventAPI() {
        return eventAPI;
    }

}
