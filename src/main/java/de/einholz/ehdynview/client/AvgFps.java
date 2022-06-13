package de.einholz.ehdynview.client;

import de.einholz.ehdynview.mixins.MinecraftClientM;

public interface AvgFps {
    public static int getFps() {
        if (FpsData.last + FpsData.delay > System.nanoTime()) return FpsData.avgFps;
        FpsData.last = System.nanoTime();
        if (FpsData.points.remainingCapacity() < 1) FpsData.points.poll();
        if (!FpsData.points.offer(MinecraftClientM.getCurrentFps())) System.out.println("Unable to push FPS"); //TODO add proper logger
        int sum = 1;
        for (Integer i : FpsData.points) sum += i;
        FpsData.avgFps = sum / FpsData.points.size();
        return FpsData.avgFps;
    }
}
