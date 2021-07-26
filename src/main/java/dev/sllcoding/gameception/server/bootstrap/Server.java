package dev.sllcoding.gameception.server.bootstrap;

import dev.sllcoding.gameception.server.GameceptionServer;
import net.minestom.server.MinecraftServer;

public class Server {

    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();
        GameceptionServer server = new GameceptionServer();
        server.init(args);
        minecraftServer.start("0.0.0.0", 25565);
        server.postInit(args);

        System.out.println("Gameception server online:");
        System.out.println("  Version: " + MinecraftServer.VERSION_NAME);
        System.out.println("  Address: 0.0.0.0");
        System.out.println("  Port: 25565");
    }

}
