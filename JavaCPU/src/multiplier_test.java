public class multiplier_test {
    // Calls methods that test all possible combinations of multiplying two longwords
    public static void runTests() {
        System.out.println("\nNow testing functions of multiplier.java:\n");
        testZeroByZero(new longword(), new longword());
        testNegByNeg(new longword(), new longword());
        testNegByPos(new longword(), new longword());
        testPosByNeg(new longword(), new longword());
        testPosByPos(new longword(), new longword());
    }

    // Tests multiplying with zeros
    public static void testZeroByZero(longword l, longword l2) {
        try { // try to multiply 0 * 0, check if the result is 0, throw an exception if not
            longword result = multiplier.multiply(l, l2);
            if (!result.toString().equals(l.toString())) throw new Exception("0 * 0 Test Failed: Returned unexpected value");
            System.out.println("0 * 0 Test Passed: Returned 0"); // success message
        } catch (Exception e) { // catch exception if thrown
            System.out.println(e); // print exception message
        }
    }

    // Tests multiplying two negative longwords
    public static void testNegByNeg(longword l, longword l2) {
        l.set(-100);
        l2.set(-5);
        longword expected = new longword(); // longword to hold expected value after performing multiplication
        expected.set(500); // expected value of -100 * -5 = 500
        try { // try to multiply -100 * -5, check if the result is 500, throw an exception if not
            longword result = multiplier.multiply(l, l2);
            if (!result.toString().equals(expected.toString())) throw new Exception("-100 * -5 Test Failed: Returned unexpected value");
            System.out.println("-100 * -5 Test Passed: Returned 500");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Test multiplying a negative by a positive longword
    public static void testNegByPos(longword l, longword l2) {
        l.set(-30);
        l2.set(30);
        longword expected = new longword();
        expected.set(-900); // expected value of -30 * 30 = -900
        try { // try to multiply -30 * 30, check if the result is -900, throw an exception if not
            longword result = multiplier.multiply(l, l2);
            if (!result.toString().equals(expected.toString())) throw new Exception("-30 * 30 Test Failed: Returned unexpected value");
            System.out.println("-30 * 30 Test Passed: Returned -900");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Test multiplying a positive by a negative longword
    public static void testPosByNeg(longword l, longword l2) {
        l.set(10);
        l2.set(-20);
        longword expected = new longword();
        expected.set(-200); // expected value of 10 * -20 = -200
        try { // try to multiply 10 * -20, check if the result is -200, throw an exception if not
            longword result = multiplier.multiply(l, l2);
            if (!result.toString().equals(expected.toString())) throw new Exception("10 * -20 Test Failed: Returned unexpected value");
            System.out.println("10 * -20 Test Passed: Returned -200");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Test multiplying two positive longwords
    public static void testPosByPos(longword l, longword l2) {
        l.set(5);
        l2.set(20);
        longword expected = new longword();
        expected.set(100); // expected value of 5 * 20 = 100
        try { // try to multiply 5 * 20, check if the result is 100, throw an exception if not
            longword result = multiplier.multiply(l, l2);
            if (!result.toString().equals(expected.toString())) throw new Exception("5 * 20 Test Failed: Returned unexpected value");
            System.out.println("5 * 20 Test Passed: Returned 100");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // main method runs tests
    public static void main(String[] args) {
        runTests();
    }
}
