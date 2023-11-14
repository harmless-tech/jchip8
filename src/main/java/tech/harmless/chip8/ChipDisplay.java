package tech.harmless.chip8;

import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.util.Arrays;

public class ChipDisplay {
    public final int width = 64;
    public final int height = 32;
    public final byte[] buffer = new byte[width * height];
    public final MemoryImageSource source = new MemoryImageSource(width, height, ColorModel.getRGBdefault(), buffer, 0, width);

    public ChipDisplay() {
        Arrays.fill(buffer, Byte.MIN_VALUE);
    }
}
