package dev.sllcoding.gameception.server.generators;

import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.ChunkGenerator;
import net.minestom.server.instance.ChunkPopulator;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;
import net.minestom.server.world.biomes.Biome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class GameWorldGenerator implements ChunkGenerator {

    @Override
    public void generateChunkData(@NotNull ChunkBatch batch, int chunkX, int chunkY) {
        for (byte x = 0; x < Chunk.CHUNK_SIZE_X; x++)
            for (byte z = 0; z < Chunk.CHUNK_SIZE_Z; z++)
                for (byte y = 0; y < 40; y++)
                    batch.setBlock(x, y, z, Block.BLACK_CONCRETE);
    }

    @Override
    public void fillBiomes(@NotNull Biome[] biomes, int i, int i1) {
        Arrays.fill(biomes, Biome.PLAINS);
    }

    @Override
    public @Nullable List<ChunkPopulator> getPopulators() {
        return null;
    }

}
