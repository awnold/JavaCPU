public class memory_test {
    // Calls functions that tests methods of the longword object exhaustively
    public static void runTests() {
        System.out.println("\nNow testing functions of memory.java:\n");
        testFront();
        testEnd();
        testOverlap();
    }

    // tests read() and write() methods on memory object in the beginning of memory
    public static void testFront() {
        memory m = new memory();
        longword address = new longword();
        address.set(0); // address to write and read to is 0 -> first longword -> bits 0-31
        longword value = new longword();
        value.set(123456789); // 00000111010110111100110100010101
        try {
            m.write(address, value); // write 123456789 to address 0
            // if longword read at address 0 is not equal to 123456789, throw exception
            if (m.read(address).getSigned() != 123456789) throw new Exception("testFront Failed: Returned unexpected value.");
            System.out.println("testFront Passed: Returned expected value 123456789"); // success message
        } catch (Exception e) {
            System.out.println(e); // print exception message
        }
    }

    // tests read() and write() methods on memory object at the end of memory
    public static void testEnd() {
        memory m = new memory();
        longword address = new longword();
        address.set(1020); // address to write and read to is 1020 -> last longword -> bits 8160-8191
        longword value = new longword();
        value.set(987654321); // 00111010110111100110100010110001
        try {
            m.write(address, value); // write 987654321 to address 1020
            // if longword read at address 1020 is not equal to 987654321, throw exception
            if (m.read(address).getSigned() != 987654321) throw new Exception("testEnd Failed: Returned unexpected value.");
            System.out.println("testEnd Passed: Returned expected value 987654321"); // success message
        } catch (Exception e) {
            System.out.println(e); // print exception message
        }
    }

    // tests read() and write() methods on memory object after overlapping written bits
    public static void testOverlap() {
        memory m = new memory();
        longword address1 = new longword();
        address1.set(0);
        longword value1 = new longword();
        value1.set(-1); // 11111111111111111111111111111111
        longword address2 = new longword();
        address2.set(1);
        longword value2 = new longword();
        value2.set(16777215); // 00000000111111111111111111111111
        try {
            m.write(address1, value1); // write all 1s to address 0
            m.write(address2, value2); // overrides 2nd byte -> 00000000
            // longword at address 0 should now = 11111111000000001111111111111111 = -16711681
            // if longword read at address 1020 is not equal to -16711681, throw exception
            if (m.read(address1).getSigned() != -16711681) throw new Exception("testOverlap Failed: Returned unexpected value.");
            System.out.println("testOverlap Passed: Returned expected value -16711681"); // success message
        } catch (Exception e) {
            System.out.println(e); // print exception message
        }
    }

    // main method to runTests()
    public static void main(String[] args) {
        runTests();
    }
}
