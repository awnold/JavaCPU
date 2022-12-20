public class longword_test {
    static void runTests() {
        // Calls functions that tests methods of the longword object exhaustively
        System.out.println("\nNow testing functions of longword.java:\n");
        testGetBit(new longword());
        testSetBit(new longword());
        testToString(new longword());
        testAnd(new longword(), new longword());
        testOr(new longword(), new longword());
        testXor(new longword(), new longword());
        testSet(new longword());
        testGetUnsigned(new longword());
        testGetSigned(new longword());
        testNot(new longword());
        testRightShift(new longword());
        testLeftShift(new longword());
        testCopy(new longword(), new longword());



    }

    private static void testGetBit(longword l) { // tests to make sure getBit() returns the correct result
        try { // if returned bit's value is not false, throw an exception
            if (l.getBit(0).getValue() != false) throw new Exception("testGetBit failed: returned true not false.");
            System.out.println("testGetBit passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
        l.setBit(0, new bit(true)); // sets first bit to true to test both cases
        try { // if returned bit's value is not true, throw an exception
            if (l.getBit(0).getValue() != true) throw new Exception("testGetBit failed: returned false not true.");
            System.out.println("testGetBit passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void testSetBit(longword l) { // tests to make sure setBit() returns the correct result
        l.setBit(0, new bit(true)); // sets the first bit to true
        try { // if returned bit's value is not true, throw an exception
            if (l.getBit(0).getValue() != true) throw new Exception("testSetBit failed: returned false not true.");
            System.out.println("testSetBit passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
        l.setBit(0, new bit(false)); // re-sets the first bit to false to test both cases
        try { // if returned bit's value is not false, throw an exception
            if (l.getBit(0).getValue() != false) throw new Exception("testSetBit failed: returned true not false.");
            System.out.println("testSetBit passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void testAnd(longword l, longword l2) { // tests to make sure and() returns the correct result
        l.setBit(0, new bit(true)); // sets the first bit of l to true, l2 is all 0's
        try { // l AND l2 should be all 0's, since both longword do not have a true bit at index 0, throw an exception if not
            if (!l.and(l2).toString().equals(l2.toString())) throw new Exception("testAnd failed: returned unexpected result.");
            System.out.println("testAnd passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
        l2.setBit(0, new bit(true)); // sets the first bit of l2 to true, both l1 and l2 now have a true bit at index 0
        try { // l AND l2 should be 1 followed by 31 0's since both have a true bit at index 0, throw an exception if not
            if (!l.and(l2).toString().equals(l2.toString())) throw new Exception("testAnd failed: returned unexpected result.");
            System.out.println("testAnd passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void testOr(longword l, longword l2) { // tests to make sure or() returns the correct result
        l.setBit(0, new bit(true)); // sets the first bit of l to true, l2 is all 0's
        try { // l OR l2 should be be 1 followed by 31 0's, since l has a true bit at index 0, throw an exception if not
            if (!l.or(l2).toString().equals(l.toString())) throw new Exception("testOr failed: returned unexpected result.");
            System.out.println("testOr passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
        l2.setBit(1, new bit(true)); // set the second bit of l2 to true, l has a true bit at index 0
        longword expected = new longword(); // new longword to hold expected result
        expected.setBit(0, new bit(true)); // set bits 0 and 1 to true for expected result
        expected.setBit(1, new bit(true));
        try { // l OR l2 should return a longword that is equivalent to expected, throw an exception if not
            if (!l.or(l2).toString().equals(expected.toString())) throw new Exception("testOr failed: returned unexpected result.");
            System.out.println("testOr passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void testXor(longword l, longword l2) { // tests to make sure xor() returns the correct result
        l.setBit(0, new bit(true)); // sets the first bit of l to true, l2 is all 0's
        try { // l1 XOR l2 should be a 1 followed by 31 0's, since l has a true bit at index 0 and l2 doesn't, throw an exception if not
            if (!l.xor(l2).toString().equals(l.toString())) throw new Exception("testXor failed: returned unexpected result.");
            System.out.println("testXor passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
        l2.setBit(0, new bit(true)); // now, set bits 0 and 1 of l2 to true
        l2.setBit(1, new bit(true)); // this way XOR should return false for bit 0 but still true for bit 1
        longword expected = new longword(); // new longword to hold expected result
        expected.setBit(1, new bit(true)); // set bit 1 of expected to true
        try { // l XOR l2 should be 01 followed by 30 0's, throw an exception if not
            if (!l.xor(l2).toString().equals(expected.toString())) throw new Exception("testXor failed: returned unexpected result.");
            System.out.println("testXor passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void testNot(longword l) { // tests to make sure not() returns the correct result
        l.setBit(0, new bit(true)); // set the first bit of l to true
        longword expected = new longword();
        expected.set(2147483647); // set expected to expected result after performing not() on l (0 followed by 31 1's)
        try { // if l is not equivalent to the expected result, throw an exception
            if (l.not().getUnsigned()!=expected.getUnsigned()) throw new Exception("testNot failed: returned unexpected result.");
            System.out.println("testNot passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void testRightShift(longword l) { // tests to make sure rightShift() returns the correct result
        l.setBit(0, new bit(true)); // sets the first bit of l to true
        longword expected = new longword(); // expected result placeholder
        expected.setBit(1, new bit(true)); // sets the second bit of expected to true, what l should be after a rightshift of 1
        try { // if l is not equivalent to expected after a rightshift of 1, throw an exception
            if (!l.rightShift(1).toString().equals(expected.toString())) throw new Exception("testRightShift failed: returned unexpected result.");
            System.out.println("testRightShift passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void testLeftShift(longword l) { // tests the functionality of the leftShift() method
        l.setBit(1, new bit(true)); // sets the second bit of l to true
        longword expected = new longword(); // expected result placeholder
        expected.setBit(0, new bit(true)); // sets the first bit of expected to true, what l should be after a leftshift of 1
        try { // if l is not equivalent to expected after a leftshift of 1, throw an exception
            if (!l.leftShift(1).toString().equals(expected.toString())) throw new Exception("testLeftShift failed: returned unexpected result.");
            System.out.println("testLeftShift passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void testToString(longword l) { // tests the functionality of the toString() method
        // the expected string representation of an empty longword
        String expected = "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
        try { // if l.toString() is not equivalent to expected string, throw an exception
            if (!l.toString().equals(expected)) throw new Exception("testToString failed: returned unexpected result.");
            System.out.println("testToString passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
        expected = "[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
        l.setBit(0, new bit(true));
        try {
            if (!l.toString().equals(expected)) throw new Exception("testToString failed: returned unexpected result.");
            System.out.println("testToString passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void testGetUnsigned(longword l) { // tests the functionality of the getUnsigned() method
        l.setBit(31, new bit(true)); // set the last bit of l to true
        try { // if getUnsigned() on l does not return 1, throw an exception
            if (l.getUnsigned()!=1) throw new Exception("testGetUnsigned failed: returned unexpected result.");
            System.out.println("testGetUnsigned passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void testGetSigned(longword l) { // tests the functionality of the getSigned() method
        l.setBit(31, new bit(true)); // set the last bit of l to true
        try { // if getSigned() on l does not return 1, throw an exception
            if (l.getSigned()!=1) throw new Exception("testGetSigned failed: returned unexpected result.");
            System.out.println("testGetSigned passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
        l.setBit(0, new bit(true)); // set the first bit of l to true (indicates a negative number)
        try { // if getSigned() on l does not return the expected value -2147483647, throw an exception
            if (l.getSigned()!=-2147483647) throw new Exception("testGetSigned failed: returned unexpected result.");
            System.out.println("testGetSigned passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void testCopy(longword l, longword l2) { // tests the functionality of the copy() method
        l.setBit(0, new bit(true)); // sets the first bit of l to true
        l2.copy(l); // copy longword l to l2
        try { // if l2 is not equal to l after the copy(), throw an exception
            if (!l2.toString().equals(l.toString())) throw new Exception("testCopy failed: returned unexpected result.");
            System.out.println("testCopy passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void testSet(longword l) { // tests the functionality of the set() method
        l.set(1); // set l to be equivalent to 1 (31 0's followed by a 1)
        longword expected = new longword(); // expected result placeholder
        expected.setBit(31, new bit(true)); // set the last bit of expected to true
        try { // if l and expected are not equal after the set, throw an exception
            if (!l.toString().equals(expected.toString())) throw new Exception("testSet failed: returned unexpected result.");
            System.out.println("testSet passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
        l.set(-2147483647); // set l to -2147483647 to reflect [1,0,...,0,1]
        expected.setBit(0, new bit(true)); // to test negatives, set the first (sign) bit of expected to true
        try { // if l and expected are not equal after the set, throw an exception
            if (!l.toString().equals(expected.toString())) throw new Exception("testSet failed: returned unexpected result.");
            System.out.println("testSet passed: returned expected result.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) { // main method runs tests on both the bit and longword objects
        bit_test.runTests();
        runTests();
    }
}
