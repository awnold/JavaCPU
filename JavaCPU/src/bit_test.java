public class bit_test {
    static void runTests() {
        System.out.println("\nNow testing functions of bit.java:\n");
        // test 1 (set true)
        bit bit1 = new bit();
        bit1.set(true); // set the bits state to on
        try {   // try-catch block to throw an exception in the case of logical fault
                // this logic is used throughout each test, I will not reiterate for the following tests
            if (!bit1.getValue()) // if the bits state is off after being set to on, or "true", throw an exception
                throw new Exception("Test 1 Failed: Expected \"true\", Returned \"false\"");
            else System.out.println("Test 1 Passed: Expected \"true\", Returned \"true\"");
        } catch (Exception e) { // catch potential exception and print its message to console
            System.out.println(e);
        }

        // test 2 (toggle false -> true)
        bit bit2 = new bit(); // initialization of a new bit object with default state off, or false
        bit2.toggle(); // toggle the state, off -> on
        try {
            if (!bit2.getValue()) // if the bits state is read as off, throw exception for logical error
                throw new Exception("Test 2 Failed: Expected \"true\", Returned \"false\"");
            else System.out.println("Test 2 Passed: Expected \"true\", Returned \"true\"");
        } catch (Exception e){
            System.out.println(e);
        }

        // test 3 (toggle true -> false) *same as above just checks for true->false*
        bit bit3 = new bit(true);
        bit3.toggle();
        try {
            if (bit3.getValue())
                throw new Exception("Test 3 Failed: Expected \"false\", Returned \"true\"");
            else System.out.println("Test 3 Passed: Expected \"false\", Returned \"false\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 4 (set w/ no params)
        bit bit4 = new bit();
        bit4.set(); // set method with no params changes the state to on
        try {
            if (!bit4.getValue()) // if the bits state is read as off, throw exception for logical error
                throw new Exception("Test 4 Failed: Expected \"true\", Returned \"false\"");
            else System.out.println("Test 4 Passed: Expected \"true\", Returned \"true\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 5 (clear)
        bit bit5 = new bit(true);
        bit5.clear(); // clear method changes the state to off
        try {
            if (bit5.getValue()) // if the bits state is read as on, throw exception for logical error
                throw new Exception("Test 5 Failed: Expected \"false\", Returned \"true\"");
            else System.out.println("Test 5 Passed: Expected \"false\", Returned \"false\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 6 (getValue) *redundant to test, if this method didn't work neither would any of the previous tests*
        bit bit6 = new bit(); // default state is off
        try {
            if (bit6.getValue()) // if the bits state is read as on, throw exception for logical error
                throw new Exception("Test 6 Failed: Expected \"false\", Returned \"true\"");
            else System.out.println("Test 6 Passed: Expected \"false\", Returned \"false\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // these next four test cases will initialize two new bits with the states stated in their leading comments,
        // each test checks for correct results in performing logical "AND" on the two, and throws an exception if
        // the desired result is not returned, indicating some logical error

        // test 7 (false and false)
        bit bit7 = new bit();
        bit bit8 = new bit();
        try {
            if (bit7.and(bit8).getValue())
                throw new Exception("Test 7 Failed: Expected \"false\", Returned \"true\"");
            else System.out.println("Test 7 Passed: Expected \"false\", Returned \"false\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 8 (false and true)
        bit bit9 = new bit();
        bit bit10 = new bit(true);
        try {
            if (bit9.and(bit10).getValue())
                throw new Exception("Test 8 Failed: Expected \"false\", Returned \"true\"");
            else System.out.println("Test 8 Passed: Expected \"false\", Returned \"false\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 9 (true and false)
        bit bit11 = new bit(true);
        bit bit12 = new bit();
        try {
            if (bit11.and(bit12).getValue())
                throw new Exception("Test 9 Failed: Expected \"false\", Returned \"true\"");
            else System.out.println("Test 9 Passed: Expected \"false\", Returned \"false\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 10 (true and true)
        bit bit13 = new bit(true);
        bit bit14 = new bit(true);
        try {
            if (!bit13.and(bit14).getValue())
                throw new Exception("Test 10 Failed: Expected \"true\", Returned \"false\"");
            else System.out.println("Test 10 Passed: Expected \"true\", Returned \"true\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // these next four test cases will initialize two new bits with the states stated in their leading comments,
        // each test checks for correct results in performing logical "OR" on the two, and throws an exception if
        // the desired result is not returned, indicating some logical error

        // test 11 (false or false)
        bit bit15 = new bit();
        bit bit16 = new bit();
        try {
            if (bit15.or(bit16).getValue())
                throw new Exception("Test 11 Failed: Expected \"false\", Returned \"true\"");
            else System.out.println("Test 11 Passed: Expected \"false\", Returned \"false\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 12 (false or true)
        bit bit17 = new bit();
        bit bit18 = new bit(true);
        try {
            if (!bit17.or(bit18).getValue())
                throw new Exception("Test 12 Failed: Expected \"true\", Returned \"false\"");
            else System.out.println("Test 12 Passed: Expected \"true\", Returned \"true\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 13 (true or false)
        bit bit19 = new bit(true);
        bit bit20 = new bit();
        try {
            if (!bit19.or(bit20).getValue())
                throw new Exception("Test 13 Failed: Expected \"true\", Returned \"false\"");
            else System.out.println("Test 13 Passed: Expected \"true\", Returned \"true\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 14 (true or true)
        bit bit21 = new bit(true);
        bit bit22 = new bit(true);
        try {
            if (!bit21.or(bit22).getValue())
                throw new Exception("Test 14 Failed: Expected \"true\", Returned \"false\"");
            else System.out.println("Test 14 Passed: Expected \"true\", Returned \"true\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 15 (not false)
        bit bit23 = new bit(false); // initializes a new bit with state set to off
        try {
            if (!bit23.not().getValue()) // if the bit returned from calling not() is still off, throw exception
                throw new Exception("Test 15 Failed: Expected \"true\", Returned \"false\"");
            else System.out.println("Test 15 Passed: Expected \"true\", Returned \"true\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 16 (not true)
        bit bit24 = new bit(true); // initializes a new bit with state set to on
        try {
            if (bit24.not().getValue()) // if the bit returned from calling not() is still on, throw exception
                throw new Exception("Test 16 Failed: Expected \"false\", Returned \"true\"");
            else System.out.println("Test 16 Passed: Expected \"false\", Returned \"false\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // these next four test cases will initialize two new bits with the states stated in their leading comments,
        // each test checks for correct results in performing logical "XOR" on the two, and throws an exception if
        // the desired result is not returned, indicating some logical error

        // test 17 (false xor false)
        bit bit25 = new bit();
        bit bit26 = new bit();
        try {
            if (bit25.xor(bit26).getValue())
                throw new Exception("Test 17 Failed: Expected \"false\", Returned \"true\"");
            else System.out.println("Test 17 Passed: Expected \"false\", Returned \"false\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 18 (false xor true)
        bit bit27 = new bit();
        bit bit28 = new bit(true);
        try {
            if (!bit27.xor(bit28).getValue())
                throw new Exception("Test 18 Failed: Expected \"true\", Returned \"false\"");
            else System.out.println("Test 18 Passed: Expected \"true\", Returned \"true\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 19 (true xor false)
        bit bit29 = new bit();
        bit bit30 = new bit(true);
        try {
            if (!bit29.xor(bit30).getValue())
                throw new Exception("Test 19 Failed: Expected \"true\", Returned \"false\"");
            else System.out.println("Test 19 Passed: Expected \"true\", Returned \"true\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 20 (true xor true)
        bit bit31 = new bit();
        bit bit32 = new bit();
        try {
            if (bit31.xor(bit32).getValue())
                throw new Exception("Test 20 Failed: Expected \"false\", Returned \"true\"");
            else System.out.println("Test 20 Passed: Expected \"false\", Returned \"false\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // the last two test cases ensure the overridden toString() method is returning "t" or "f"
        // respective to when the object bits current state is on or off, throwing an exception otherwise

        // test 21 (toString false)
        bit bit33 = new bit();
        try {
            if (bit33.toString().equals("t"))
                throw new Exception("Test 21 Failed: Expected \"f\", Returned \"t\"");
            else System.out.println("Test 21 Passed: Expected \"f\", Returned \"f\"");
        } catch (Exception e) {
            System.out.println(e);
        }

        // test 22 (toString true)
        bit bit34 = new bit(true);
        try {
            if (bit34.toString().equals("f"))
                throw new Exception("Test 22 Failed: Expected \"t\", Returned \"f\"");
            else System.out.println("Test 22 Passed: Expected \"t\", Returned \"t\"");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // main method that does nothing but call the runTests() method to exhaustively test methods of the bit object
    public static void main(String[] args) throws Exception {
        runTests();
    }
}