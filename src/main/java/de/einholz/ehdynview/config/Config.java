package de.einholz.ehdynview.config;

import blue.endless.jankson.Comment;

public class Config {
    @Comment("If FPS are above this value the render distance will be increased (default=60)")
    public int fpsMax = 60;
    @Comment("If FPS are below this value the render distance will be decreased (default=30)")
    public int fpsMin = 30;
}
