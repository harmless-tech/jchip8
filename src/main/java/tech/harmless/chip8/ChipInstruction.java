package tech.harmless.chip8;

import org.jetbrains.annotations.Range;

/** Automatically splits a 16-bit instruction into its parts. */
public class ChipInstruction {
    /** The raw 16-bit instruction. */
    public final char totalInstruction;

    /**
     * First four bytes of an instruction. (0b0_1111_0000_0000_0000)
     *
     * <p>Used to tell an instruction type.
     */
    public final int instruction;

    /**
     * Second four bytes of an instruction. (0b0_0000_1111_0000_0000)
     *
     * <p>Used to look up a register.
     */
    public final int snX;

    /**
     * Third four bytes of an instruction. (0b0_0000_0000_1111_0000)
     *
     * <p>Used to look up a register.
     */
    public final int tnY;

    /**
     * The Fourth and final four bytes of an instruction. (0b0_0000_0000_0000_1111)
     *
     * <p>Used as a 4-bit number.
     */
    public final int fnN;

    /**
     * The Third and Fourth four bytes of an instruction. (0b0_0000_0000_1111_1111)
     *
     * <p>Used as an 8-bit number.
     */
    public final int tfnNN;

    /**
     * The Second, Third, and Fourth four bytes of an instruction. (0b0_0000_1111_1111_1111)
     *
     * <p>Used as a 12-bit memory address.
     */
    public final int stfnNNN;

    public ChipInstruction(final char instruction) {
        this.totalInstruction = instruction;
        this.instruction = instruction >>> 12;
        this.snX = (instruction & 0b0_0000_1111_0000_0000) >>> 8;
        this.tnY = (instruction & 0b0_0000_0000_1111_0000) >>> 4;
        this.fnN = instruction & 0b0_0000_0000_0000_1111;
        this.tfnNN = instruction & 0b0_0000_0000_1111_1111;
        this.stfnNNN = instruction & 0b0_0000_1111_1111_1111;
    }
}
