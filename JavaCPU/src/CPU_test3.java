public class CPU_test3 {
    public static void testNewInstructions() throws Exception {
        System.out.println("\nNow testing NEW functions of computer.java and Assembler.java:\n");
        String[] instructions = {
                "move r0 7", // move 7 to r0
                "push r0", // push 7 to end of memory
                "printmemory", //
                "pop r1", // pop 7 to r1
                "call 12", // calls 12
                "halt", // halts the computer
                "printregisters", // printregisters to check if 7 is in r1
                "return" // return to halt
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

