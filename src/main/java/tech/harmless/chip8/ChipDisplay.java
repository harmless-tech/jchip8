package tech.harmless.chip8;

import java.awt.*;
import java.util.Arrays;

public class ChipDisplay {
    public final int width = 64;
    public final int height = 32;
    public final int[] buffer = new int[width * height];

    public ChipDisplay() {
        Arrays.fill(buffer, Color.BLACK.getRGB());
    }
}
