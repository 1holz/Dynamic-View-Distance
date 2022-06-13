package de.einholz.ehdynview.client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public abstract class FpsData {
    static int avgFps = 0;
    static BlockingQueue<Integer> points = new ArrayBlockingQueue<>(5);
    static final long delay = 1000000000; //1s
    static long last = System.nanoTime();
}
