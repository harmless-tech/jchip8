package tech.harmless.chip8;

import tech.harmless.chip8.gui.Window;

import javax.swing.*;

/** Hello world! */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        final Chip8 chip = new Chip8();

         final var window = new Window(chip, true);
    }
}
