package tech.harmless.chip8;

import static org.junit.jupiter.api.Assertions.assertEquals;

import manifold.ext.rt.api.Jailbreak;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void initChip8() {
        @Jailbreak Chip8 chip = new Chip8();
        @Jailbreak Chip8.ChipFont chipFont = chip.new ChipFont();
        chipFont.loadFont(chip.memory);

        int count = 0;
        for (int i = chipFont.startAddress - 10; i < chipFont.startAddress + 100; i++) {
            if (chip.memory[i] != 0) count++;
        }

        assertEquals(count, 80);
    }
}
