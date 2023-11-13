package tech.harmless.chip8;

import java.util.Stack;

// TODO: Make stuff final.
public class Chip8 {
    public static final int MEMORY_SIZE = 4096;

    private final byte[] memory = new byte[MEMORY_SIZE]; // 4kb
    private Display display = new Display();
    private int programCounter = 0;
    private short regI = 0;
    private Stack<Short> stack = new Stack<>(); // TODO: Limit stack?
    private ChipTimer timer = new ChipTimer(255); // TODO: Start at 0?
    private ChipTimer soundTimer = new ChipTimer(0);
    private Registers registers = new Registers();
}
