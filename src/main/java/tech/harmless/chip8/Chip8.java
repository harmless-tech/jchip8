package tech.harmless.chip8;

import java.util.Stack;

public class Chip8 {
    public static final int MEMORY_SIZE = 4096;

    private byte[] memory = new byte[MEMORY_SIZE]; // 4kb
    private byte[] display = new byte[256]; // 64x32 (black or white)
    private int programCounter = 0;
    private short regI = 0;
    private Stack<Short> stack = new Stack<>();
    private byte timer = Byte.MAX_VALUE;
    private byte soundTimer = 0;
    private Registers registers = new Registers();

    public Chip8() {}

    class Font {
        private final int startAddress = 0x050;
        private final int endAddress = 0x09F;

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

        protected boolean loadFont(byte[] memory) {
            if (memory.length < MEMORY_SIZE) return false;

            return true;
        }
    }
}
