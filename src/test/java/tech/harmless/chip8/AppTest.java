package tech.harmless.chip8;

import static org.junit.jupiter.api.Assertions.assertEquals;

import manifold.ext.rt.api.Jailbreak;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void initChip8() {
        @Jailbreak Chip8 chip = new Chip8();
        @Jailbreak ChipFont chipFont = new ChipFont();
        chipFont.loadFont(chip.memory);

        int count = 0;
        for (int i = chipFont.startAddress - 10; i < chipFont.startAddress + 100; i++) {
            if (chip.memory[i] != 0) count++;
        }

        assertEquals(count, 80);
    }

    @Test
    public void testTimer() {
        @Jailbreak ChipTimer timer = new ChipTimer(60);
        assertEquals(timer.val, -68);
        assertEquals(timer.get(), 60);

        timer.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        timer.active = false;

        assertEquals(timer.val, Byte.MIN_VALUE);
        assertEquals(timer.get(), 0);

        timer.set(255);
        assertEquals(timer.val, Byte.MAX_VALUE);
        assertEquals(timer.get(), 255);
    }
}
