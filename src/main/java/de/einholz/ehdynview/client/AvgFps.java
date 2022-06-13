package de.einholz.ehdynview.client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import de.einholz.ehdynview.mixins.MinecraftClientAM;

public abstract class AvgFps {
    private static int avgFps = 0;
    private static BlockingQueue<Integer> points = new ArrayBlockingQueue<>(5);
    private static final long DELAY = 1000000000; //1s
    private static long last = System.nanoTime();

    public static int getFps() {
        if (last + DELAY > System.nanoTime()) return avgFps;
        last = System.nanoTime();
        if (points.remainingCapacity() < 1) points.poll();
        if (!points.offer(MinecraftClientAM.getCurrentFps())) System.out.println("Unable to push FPS"); //TODO add proper logger
        int sum = 0;
        for (Integer i : points) sum += i;
        avgFps = sum / Math.max(points.size(), 1);
        return avgFps;
    }

    public static void delay() {
        last += points.size() * 1000000000;
    }
}
