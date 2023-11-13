package tech.harmless.chip8;

import org.jetbrains.annotations.Range;
import tech.harmless.chip8.util.Calculate;

public class ChipTimer implements Runnable {
    private final Object sync = new Object();
    private byte val;

    private Thread handle; // TODO?
    private boolean active;

    public ChipTimer(@Range(from = 0, to = 255) final int startVal) {
        set(startVal);
    }

    public void start() {
        handle = Thread.startVirtualThread(this);
        active = true;
    }

    public void set(@Range(from = 0, to = 255) final int setVal) {
        synchronized (sync) {
            val = (byte) (setVal + Byte.MIN_VALUE);
        }
    }

    @Range(from = 0, to = 255)
    public int get() {
        return val + Byte.MAX_VALUE + 1;
    }

    public void stop() {
        synchronized (sync) {
            active = false;
        }
    }

    @Override
    public void run() {
        final long delta = Calculate.ipsTiming(60);
        long lastTime = 0;

        while (active) {
            while (System.nanoTime() - lastTime < delta) {
                try {
                    Thread.sleep(0, 100);
                } catch (InterruptedException ignored) {
                }
            }
            lastTime = System.nanoTime();

            if (val != Byte.MIN_VALUE) {
                synchronized (sync) {
                    val = (byte) (val - 1);
                }
            }
        }
    }
}
