package tech.harmless.chip8;

import java.util.ArrayList;
import java.util.Stack;

// TODO: Make stuff final.
public class Chip8 {
    public static final int MEMORY_SIZE = 4096;

    private final byte[] memory = new byte[MEMORY_SIZE]; // 4kb
    public final ChipDisplay display = new ChipDisplay();
    private int programCounter = 0;
    private final ArrayList<ChipInstruction> instructions = new ArrayList<>();
    private short regI = 0;
    private final Stack<Short> stack = new Stack<>(); // TODO: Limit stack?
    private final ChipTimer timer = new ChipTimer(255); // TODO: Start at 0?
    private final ChipTimer soundTimer = new ChipTimer(0);
    private final ChipRegisters registers = new ChipRegisters();
}
