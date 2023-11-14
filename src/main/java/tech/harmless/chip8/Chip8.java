package tech.harmless.chip8;

import org.jetbrains.annotations.NotNull;
import tech.harmless.chip8.exceptions.RomLoadException;

import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Stack;

// TODO: Make stuff final.
// TODO: Allow reset.
public class Chip8 {
    public static final int MEMORY_SIZE = 4096;

    private final byte[] memory = new byte[MEMORY_SIZE]; // 4kb
    public final ChipDisplay display = new ChipDisplay();
    private int pc = 0;
    private final ArrayList<ChipInstruction> instructions = new ArrayList<>();
    private final Stack<Short> stack = new Stack<>(); // TODO: Limit stack?
    private final ChipTimer timer = new ChipTimer(255); // TODO: Start at 0?
    private final ChipTimer soundTimer = new ChipTimer(0);
    private final ChipRegisters registers = new ChipRegisters();

    public void loadRom(@NotNull File rom) throws IOException, RomLoadException {
        long len = rom.length();
        if ((len | 1) == 1)
            throw new RomLoadException(RomLoadException.UNEVEN_BYTES);
        len /= 2;

        final char[] rawRom = new char[(int) len];
        BufferedReader reader = new BufferedReader(new FileReader(rom));
        int read = reader.read(rawRom);
        if (read != len)
            throw new RomLoadException(RomLoadException.IMPROPER_READ);

        for (char c : rawRom)
            instructions.add(new ChipInstruction(c));
    }

    // TODO: Start running rom!
    public void run() {
        while (instructions.size() < pc) {
            ChipInstruction in = instructions[pc++]; // TODO: Not bad?

            switch (in.instruction) {
                case 0x0 -> {
                    switch (in.stfnNNN) {
                        case 0x0E0 -> {
                            // Clear the screen.
                            display.setAllZero();
                        }
                        default -> throw new RuntimeException("Instruction not implemented. " + in.totalInstruction);
                    }
                }

                case 0x1 -> {
                    // Jump to NNN
                    pc = in.stfnNNN;
                }

                case 0x6 -> {
                    // Set register vX with NN.
                    registers.set(in.snX, in.tfnNN);
                }

                case 0x7 -> {
                    // Add to register vX with NN.
                    // If overflow then wrap around and do not set vF.
                    int val = registers.get(in.snX);
                    val += in.tfnNN;
                    val %= 256;
                    registers.set(in.snX, val);
                }

                case 0xA -> {
                    // Set index register i to NNN.
                    registers.i = (char) in.stfnNNN;
                }

                case 0xD -> {
                    // TODO: Display and Draw
                    throw new UnsupportedOperationException();
                }

                default -> throw new RuntimeException("Instruction not implemented. " + in.totalInstruction);
            }
        }
    }
}
