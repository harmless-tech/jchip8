package tech.harmless.chip8;

import java.awt.*;
import java.util.Arrays;

public class ChipDisplay {
    public final int width = 64;
    public final int height = 32;
    public final int[] buffer = new int[width * height];

    public final int zeroColor = Color.BLACK.getRGB();
    public final int oneColor = Color.WHITE.getRGB();

    public ChipDisplay() {
        setAllZero();
    }

    public void setZero(int x, int y) {

    }

    public void setAllZero() {
        Arrays.fill(buffer, zeroColor);
    }
}
