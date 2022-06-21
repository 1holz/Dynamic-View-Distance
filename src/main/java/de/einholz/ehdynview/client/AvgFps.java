package de.einholz.ehdynview.client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import net.fabricmc.api.EnvType;
import de.einholz.ehdynview.mixins.MinecraftClientAM;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public abstract class AvgFps {
    private static final int BUFFER_SIZE = 5;
    private static final long DELAY = 1000000000; //1s
    private static int avgFps = 0;
    private static BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(BUFFER_SIZE);
    private static long last = System.nanoTime();

    public static int getFps() {
        if (last + DELAY < System.nanoTime()) return avgFps;
        last = System.nanoTime();
        if (buffer.remainingCapacity() < 1) buffer.poll();
        if (!buffer.offer(MinecraftClientAM.getCurrentFps())) System.out.println("Unable to push FPS"); //TODO add proper logger
        int sum = 0;
        for (Integer i : buffer) sum += i;
        avgFps = sum / Math.max(buffer.size(), 1);
        return avgFps;
    }

    public static void delay() {
        last += buffer.size() * DELAY;
    }
}
