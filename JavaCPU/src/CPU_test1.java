public class CPU_test1 {
    public static void runTests() {
        System.out.println("\nNow testing functions of computer.java:\n");
        runCPU();
    }

    public static void runCPU() {
        computer cpu = new computer();
        String[] instructions = {
                "0001 0000 0000 1111", // move 15 to R0
                "0001 0001 0000 0010", // move 2 to R1
                "0001 0010 1110 1100", // move -20 to R2
                "0010 0000 0000 0000", // print registers
                "0111 0001 0010 0010", // multiply R0 by R1, store in R2
                "0010 0000 0000 0000", // print registers (again to see changes)
                "1011 0010 0000 0100", // not r2, store in r4
                "0010 0000 0000 0001", // Print memory to console
                "0010 0000 0000 0000", // print registers
                "0000 0000 0000 0000" }; // halt the computer
        cpu.preload(instructions); // preload the array of instructions to cpu
        cpu.run(); // run loop until halt code (last instruction)
    }
}
