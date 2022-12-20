public class assembler_test {
    public static void testAssembler() throws Exception {
        // instructions to assemble
        String[] instructions = {
                "move r0 1", // r0 -> 1
                "move r1 2", // r1 -> 2
                "compare r0 r1", // compare r0 r1
                "branchifnotequal 2",
                "jump 24", // jump 24 (skip to printmemory instruction)
                "move r1 3", // r1 -> 3
                "xor r0 r1 r2", // r2 -> 2
                "or r0 r2 r3", // r3 -> 3
                "and r0 r1 r2", // r2 -> 1
                "rightshift r3 r0 r1", // r1 -> 1
                "leftshift r1 r0 r1", // r1 -> 2
                "multiply r1 r3 r2", // r2 -> 6
                "subtract r0 r2 r2", // r2 -> -5
                "add r2 r0 r2", // r2 -> -4
                "not r0 r3", // r3 -> -2
                "printmemory", // prints memory
                "printregisters", // prints registers (r0=1, r1=2, r2=-4, r3=-2)
                "halt" // halts the computer
        };
        // computer to run assembled instructions on
        computer cpu = new computer();
        // preload cpu with assembled instructions
        cpu.preload(Assembler.assemble(instructions));
        // run cpu
        cpu.run();
        // if no exceptions are thrown -> everything passes
    }
    public static void runTests() throws Exception {
        testAssembler();
    }
}
