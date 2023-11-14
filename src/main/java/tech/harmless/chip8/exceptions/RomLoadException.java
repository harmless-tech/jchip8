package tech.harmless.chip8.exceptions;

public class RomLoadException extends Exception {

    public static final String UNEVEN_BYTES = "Tried to load a ROM, but the amount of bytes was uneven. This probably means a malformed ROM.";
    public static final String IMPROPER_READ = "Tried to load a ROM, but could not read in all of the instructions.";

    public RomLoadException(String errorMessage) {
        super(errorMessage);
    }
}
