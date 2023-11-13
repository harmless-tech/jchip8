package tech.harmless.chip8.util;

public final class Calculate {
    public static long ipsTiming(final int ips) {
        final double timeSecs = 1.0 / ips;
        return (long) (timeSecs * 1000000000.0);
    }
}
