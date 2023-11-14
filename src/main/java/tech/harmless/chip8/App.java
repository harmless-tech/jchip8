package tech.harmless.chip8;

import tech.harmless.chip8.exceptions.RomLoadException;
import tech.harmless.chip8.gui.Window;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        final Chip8 chip = new Chip8();
        final var window = new Window(chip, true);

        try {
            chip.loadRom(new File("./tmp/2-ibm-logo.ch8"));
        } catch (IOException | RomLoadException e) {
            throw new RuntimeException(e);
        }

        chip.run();
    }
}
