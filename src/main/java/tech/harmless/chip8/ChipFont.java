package tech.harmless.chip8;

public class ChipFont {
    private final int startAddress;

    private final byte[][] fontChars = {
        {(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0}, // 0
        {(byte) 0x20, (byte) 0x60, (byte) 0x20, (byte) 0x20, (byte) 0x70}, // 1
        {(byte) 0xF0, (byte) 0x10, (byte) 0xF0, (byte) 0x80, (byte) 0xF0}, // 2
        {(byte) 0xF0, (byte) 0x10, (byte) 0xF0, (byte) 0x10, (byte) 0xF0}, // 3
        {(byte) 0x90, (byte) 0x90, (byte) 0xF0, (byte) 0x10, (byte) 0x10}, // 4
        {(byte) 0xF0, (byte) 0x80, (byte) 0xF0, (byte) 0x10, (byte) 0xF0}, // 5
        {(byte) 0xF0, (byte) 0x80, (byte) 0xF0, (byte) 0x90, (byte) 0xF0}, // 6
        {(byte) 0xF0, (byte) 0x10, (byte) 0x20, (byte) 0x40, (byte) 0x40}, // 7
        {(byte) 0xF0, (byte) 0x90, (byte) 0xF0, (byte) 0x90, (byte) 0xF0}, // 8
        {(byte) 0xF0, (byte) 0x90, (byte) 0xF0, (byte) 0x10, (byte) 0xF0}, // 9
        {(byte) 0xF0, (byte) 0x90, (byte) 0xF0, (byte) 0x90, (byte) 0x90}, // A
        {(byte) 0xE0, (byte) 0x90, (byte) 0xE0, (byte) 0x90, (byte) 0xE0}, // B
        {(byte) 0xF0, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0xF0}, // C
        {(byte) 0xE0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xE0}, // D
        {(byte) 0xF0, (byte) 0x80, (byte) 0xF0, (byte) 0x80, (byte) 0xF0}, // E
        {(byte) 0xF0, (byte) 0x80, (byte) 0xF0, (byte) 0x80, (byte) 0x80}, // F
    };

    public ChipFont() {
        this(0x050);
    }

    public ChipFont(final int startAddress) {
        this.startAddress = startAddress;
    }

    public void loadFont(byte[] memory) {
        for (int x = 0; x < fontChars.length; x++) {
            var fc = fontChars[x];
            System.arraycopy(fc, 0, memory, startAddress + (x * fc.length), fc.length);
        }
    }
}
