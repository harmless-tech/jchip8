package tech.harmless.chip8;

import static org.junit.jupiter.api.Assertions.assertTrue;

import manifold.ext.rt.api.Jailbreak;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void initChip8() {
        @Jailbreak Chip8 chip = new Chip8();
        var font = chip.new Font();
        assertTrue(font.loadFont(chip.memory));
    }
}
