package tech.harmless.chip8;

import org.jetbrains.annotations.Range;

public class ChipRegisters {
    @Range(from = 0, to = 255) public int v0 = 0;
    @Range(from = 0, to = 255) public int v1 = 0;
    @Range(from = 0, to = 255) public int v2 = 0;
    @Range(from = 0, to = 255) public int v3 = 0;
    @Range(from = 0, to = 255) public int v4 = 0;
    @Range(from = 0, to = 255) public int v5 = 0;
    @Range(from = 0, to = 255) public int v6 = 0;
    @Range(from = 0, to = 255) public int v7 = 0;
    @Range(from = 0, to = 255) public int v8 = 0;
    @Range(from = 0, to = 255) public int v9 = 0;
    @Range(from = 0, to = 255) public int vA = 0;
    @Range(from = 0, to = 255) public int vB = 0;
    @Range(from = 0, to = 255) public int vC = 0;
    @Range(from = 0, to = 255) public int vD = 0;
    @Range(from = 0, to = 255) public int vE = 0;
    @Range(from = 0, to = 255) public int vF = 0;

    @Range(from = 0, to = 4095) public char i = 0;

    public void set(@Range(from = 0x0, to = 0xF) final int reg, @Range(from = 0, to = 255) final int val) {
        switch (reg) {
            case 0x0 -> v0 = val;
            case 0x1 -> v1 = val;
            case 0x2 -> v2 = val;
            case 0x3 -> v3 = val;
            case 0x4 -> v4 = val;
            case 0x5 -> v5 = val;
            case 0x6 -> v6 = val;
            case 0x7 -> v7 = val;
            case 0x8 -> v8 = val;
            case 0x9 -> v9 = val;
            case 0xA -> vA = val;
            case 0xB -> vB = val;
            case 0xC -> vC = val;
            case 0xD -> vD = val;
            case 0xE -> vE = val;
            case 0xF -> vF = val;
            default -> throw new IndexOutOfBoundsException("Register " + reg + "is not a valid register.");
        }
    }

    @Range(from = 0, to = 255)
    public int get(@Range(from = 0x0, to = 0xF) final int reg) {
        switch (reg) {
            case 0x0 -> {
                return v0;
            }
            case 0x1 -> {
                return v1;
            }
            case 0x2 -> {
                return v2;
            }
            case 0x3 -> {
                return v3;
            }
            case 0x4 -> {
                return v4;
            }
            case 0x5 -> {
                return v5;
            }
            case 0x6 -> {
                return v6;
            }
            case 0x7 -> {
                return v7;
            }
            case 0x8 -> {
                return v8;
            }
            case 0x9 -> {
                return v9;
            }
            case 0xA -> {
                return vA;
            }
            case 0xB -> {
                return vB;
            }
            case 0xC -> {
                return vC;
            }
            case 0xD -> {
                return vD;
            }
            case 0xE -> {
                return vE;
            }
            case 0xF -> {
                return vF;
            }
            default -> throw new IndexOutOfBoundsException("Register " + reg + "is not a valid register.");
        }
    }
}
