package de.einholz.ehdynview.config;

public class Config {
    //TODO: should comments stay? they can't be translated or I just don't know how
    //@Comment("If FPS are above this value the render distance will be increased (default=60)")
    private int fpsMax = 60;
    //@Comment("If FPS are below this value the render distance will be decreased (default=30)")
    private int fpsMin = 30;

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
}
