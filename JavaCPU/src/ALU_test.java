public class ALU_test {
    // Calls methods to test the functionality of all ALU operations
    public static void runTests() {
        System.out.println("\nNow testing functions of multiplier.java:\n");
        testMultiply(new longword(), new longword());
        testAnd(new longword(), new longword());
        testOr(new longword(), new longword());
        testXor(new longword(), new longword());
        testNot(new longword(), new longword());
        testLeftShift(new longword(), new longword());
        testRightShift(new longword(), new longword());
        testAdd(new longword(), new longword());
        testSubtract(new longword(), new longword());
    }

    // Tests the 'multiply' operation
    public static void testMultiply(longword l, longword l2) {
        // set the necessary bits for the operation = 0111
        bit[] op = {new bit(), new bit(true), new bit(true), new bit(true)};
        l.set(5);
        l2.set(7);
        try { // if 3 * 5 doesn't return 35, throw an exception, otherwise print success message
            if (ALU.doOp(op, l, l2).getSigned() != 35) throw new Exception("testMultiply Failed: Returned unexpected result.");
            System.out.println("testMultiply Passed: Returned expected result");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Tests the 'and' operation
    public static void testAnd(longword l, longword l2) {
        // set the necessary bits for the operation = 1000
        bit[] op = {new bit(true), new bit(), new bit(), new bit()};
        l.set(7);
        l2.set(5);
        try { // if 7 AND 5 doesn't return 5, throw an exception, otherwise print success message
            if (ALU.doOp(op, l, l2).getSigned() != l2.getSigned()) throw new Exception("testAnd Failed: Returned unexpected result.");
            System.out.println("testAnd Passed: Returned expected result");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Tests the 'or' operation
    public static void testOr(longword l, longword l2) {
        // set the necessary bits for the operation = 1001
        bit[] op = {new bit(true), new bit(), new bit(), new bit(true)};
        l.set(7);
        l2.set(5);
        try { // if 7 OR 5 doesn't return 7, throw an exception, otherwise print success message
            if (ALU.doOp(op, l, l2).getSigned() != l.getSigned()) throw new Exception("testOr Failed: Returned unexpected result.");
            System.out.println("testOr Passed: Returned expected result");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Tests the 'xor' operation
    public static void testXor(longword l, longword l2) {
        // set the necessary bits for the operation = 1010
        bit[] op = {new bit(true), new bit(), new bit(true), new bit()};
        l.set(7);
        l2.set(5);
        longword expected = new longword();
        expected.set(2);
        try { // if 7 XOR 5 doesn't return 2, throw an exception, otherwise print success message
            if (ALU.doOp(op, l, l2).getSigned() != expected.getSigned()) throw new Exception("testXor Failed: Returned unexpected result.");
            System.out.println("testXor Passed: Returned expected result");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Tests the 'not' operation
    public static void testNot(longword l, longword l2) {
        // set the necessary bits for the operation = 1011
        bit[] op = {new bit(true), new bit(), new bit(true), new bit(true)};
        l.setBit(0, new bit(true));
        longword expected = new longword();
        expected.set(2147483647);
        try { // if NOT 'l' doesn't return expected value, throw an exception, otherwise print success message
            if (ALU.doOp(op, l, l2).getSigned() != expected.getSigned()) throw new Exception("testNot Failed: Returned unexpected result.");
            System.out.println("testNot Passed: Returned expected result");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Tests the 'leftShift' operation
    public static void testLeftShift(longword l, longword l2) {
        // set the necessary bits for the operation = 1100
        bit[] op = {new bit(true), new bit(true), new bit(), new bit()};
        l.set(1);
        l2.set(1);
        longword expected = new longword();
        expected.set(2);
        try { // if 1 leftShifted 1 doesn't return 2, throw an exception, otherwise print success message
            if (ALU.doOp(op, l, l2).getSigned() != expected.getSigned()) throw new Exception("testLeftShift Failed: Returned unexpected result.");
            System.out.println("testLeftShift Passed: Returned expected result");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Tests the 'rightShift' operation
    public static void testRightShift(longword l, longword l2) {
        // set the necessary bits for the operation = 1101
        bit[] op = {new bit(true), new bit(true), new bit(), new bit(true)};
        l.set(2);
        l2.set(1);
        longword expected = new longword();
        expected.set(1);
        try { // if 2 rightShifted 1 doesn't return 1, throw an exception, otherwise print success message
            if (ALU.doOp(op, l, l2).getSigned() != expected.getSigned()) throw new Exception("testRightShift Failed: Returned unexpected result.");
            System.out.println("testRightShift Passed: Returned expected result");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Tests the 'add' operation
    public static void testAdd(longword l, longword l2) {
        // set the necessary bits for the operation = 1110
        bit[] op = {new bit(true), new bit(true), new bit(true), new bit()};
        l.set(2);
        l2.set(1);
        longword expected = new longword();
        expected.set(3);
        try { // if 2 + 1 doesn't return 3, throw an exception, otherwise print success message
            if (ALU.doOp(op, l, l2).getSigned() != expected.getSigned()) throw new Exception("testAdd Failed: Returned unexpected result.");
            System.out.println("testAdd Passed: Returned expected result");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Tests the 'subtract' operation
    public static void testSubtract(longword l, longword l2) {
        // set the necessary bits for the operation = 1111
        bit[] op = {new bit(true), new bit(true), new bit(true), new bit(true)};
        l.set(2);
        l2.set(1);
        longword expected = new longword();
        expected.set(1);
        try { // if 2 - 1 doesn't return 1, throw an exception, otherwise print success message
            if (ALU.doOp(op, l, l2).getSigned() != expected.getSigned()) throw new Exception("testSubtract Failed: Returned unexpected result.");
            System.out.println("testSubtract Passed: Returned expected result");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
