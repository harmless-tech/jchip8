package tech.harmless.chip8;

public class AppTest {
    //    @Test
    //    public void initChip8() {
    //        @Jailbreak Chip8 chip = new Chip8();
    //        @Jailbreak ChipFont chipFont = new ChipFont();
    //        chipFont.loadFont(chip.memory);
    //
    //        int count = 0;
    //        for (int i = chipFont.startAddress - 10; i < chipFont.startAddress + 100; i++) {
    //            if (chip.memory[i] != 0) count++;
    //        }
    //
    //        assertEquals(count, 80);
    //    }
    //
    //    @Test
    //    public void testTimer() {
    //        @Jailbreak ChipTimer timer = new ChipTimer(60);
    //        assertEquals(timer.val, -68);
    //        assertEquals(timer.get(), 60);
    //
    //        timer.set(0);
    //        assertEquals(timer.val, Byte.MIN_VALUE);
    //        assertEquals(timer.get(), 0);
    //
    //        timer.set(255);
    //        assertEquals(timer.val, Byte.MAX_VALUE);
    //        assertEquals(timer.get(), 255);
    //    }
    //
    //    // TODO: Test by importing all instructions from a rom later.
    //    @Test
    //    public void testInstruction() {
    //        ChipInstruction max = new ChipInstruction((char) 65535);
    //        assertEquals(max.instruction, 0b0_1111);
    //        assertEquals(max.snX, 0b0_1111);
    //        assertEquals(max.tnY, 0b0_1111);
    //        assertEquals(max.fnN, 0b0_1111);
    //        assertEquals(max.tfnNN, 0b0_1111_1111);
    //        assertEquals(max.stfnNNN, 0b0_1111_1111_1111);
    //
    //        ChipInstruction in = new ChipInstruction((char) 0b0_1111_0000_0000_0000);
    //        assertEquals(in.instruction, 0b0_1111);
    //        assertEquals(in.snX, 0b0_0000);
    //        assertEquals(in.tnY, 0b0_0000);
    //        assertEquals(in.fnN, 0b0_0000);
    //        assertEquals(in.tfnNN, 0b0_0000_0000);
    //        assertEquals(in.stfnNNN, 0b0_0000_0000_0000);
    //
    //        in = new ChipInstruction((char) 0b0_0000_0000_1111_1111);
    //        assertEquals(in.instruction, 0b0_0000);
    //        assertEquals(in.snX, 0b0_0000);
    //        assertEquals(in.tnY, 0b0_1111);
    //        assertEquals(in.fnN, 0b0_1111);
    //        assertEquals(in.tfnNN, 0b0_1111_1111);
    //        assertEquals(in.stfnNNN, 0b0_0000_1111_1111);
    //
    //        in = new ChipInstruction((char) 0b0_0000_1111_0000_1111);
    //        assertEquals(in.instruction, 0b0_0000);
    //        assertEquals(in.snX, 0b0_1111);
    //        assertEquals(in.tnY, 0b0_0000);
    //        assertEquals(in.fnN, 0b0_1111);
    //        assertEquals(in.tfnNN, 0b0_0000_1111);
    //        assertEquals(in.stfnNNN, 0b0_1111_0000_1111);
    //    }
}
