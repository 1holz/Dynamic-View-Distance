package de.einholz.ehdynview.config;

import de.einholz.ehdynview.client.AvgFps;

public class Config {
    //TODO: should comments stay? they can't be translated or I just don't know how
    //@Comment("If FPS are above this value the render distance will be increased (default=60)")
    private int fpsMax = DefConfig.FPS_MAX_VALUE;
    //@Comment("If FPS are below this value the render distance will be decreased (default=30)")
    private int fpsMin = DefConfig.FPS_MIN_VALUE;
    private int bufferSize = DefConfig.BUFFER_SIZE_VALUE;
    private int samplingPeriod = DefConfig.SAMPLING_PERIOD_VALUE;

    public int getFpsMax() {
        return fpsMax;
    }

    public void setFpsMax(int fpsMax) {
        this.fpsMax = fpsMax;
    }

    public int getFpsMin() {
        return fpsMin;
    }

    public void setFpsMin(int fpsMin) {
        this.fpsMin = fpsMin;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        AvgFps.reinitBuffer();
    }

    public int getSamplingPeriod() {
        return samplingPeriod;
    }

    public void setSamplingPeriod(int samplingRate) {
        this.samplingPeriod = samplingRate;
    }
}
