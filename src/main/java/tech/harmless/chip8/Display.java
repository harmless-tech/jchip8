package tech.harmless.chip8;

public class Display {
    private final int width = 64;
    private final int height = 32;
    private final int displaySize = width * height;
    private final int bufferSize = displaySize / 8;
    private final byte[] buffer = new byte[bufferSize];
}
