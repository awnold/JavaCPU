public class CPU_test2 {
    public static void testNewInstructions() throws Exception {
        System.out.println("\nNow testing NEW functions of computer.java and Assembler.java:\n");
        String[] instructions = {
                "printregisters", // printregisters
                "jump 6", // jump to instructions at 6
                "halt", // halt that should be skipped
                "move r0 1", // r0 -> 1
                "move r1 2", // r1 -> 2
                "compare r0 r1", // compare r0 r1 -> not equal
                "branchifnotequal 2", // branch 2 if not equal (should run)
                "halt", // halt that should be skipped
                "move r2 1", // r2 -> 1
                "move r3 1", // r3 -> 1
                "compare r2 r3", // compare r2 r3 -> equal
                "branchifequal 2", // branch 2 if equal (should run)
                "halt", // halt that should be skipped
                "compare r2 r3", // compare r2 r3 -> equal
                "branchifgreaterorequal 2", // branch 2 if greater or equal (should run)
                "halt", // halt that should be skipped
                "move r4 2", // r4 -> 2
                "move r5 1", // r5 -> 1
                "compare r4 r5", // compare r4 r5 -> greater
                "branchifgreater 2", // branch 2 if greater (should run)
                "halt", // halt that should be skipped
                "printregisters", // prints registers (r0=1, r1=2, r2=1, r3=1, r4=2, r5=1)
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
        testNewInstructions();
    }
}
