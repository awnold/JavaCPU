public class rippleAdder_test {
    public static void runTests() {
        System.out.println("\nNow testing functions of rippleAdder.java:\n");
        testAdd(new longword(), new longword());
        testSubtract(new longword(), new longword());
    }

    // tests the rippleAdder.add() method with all different combinations of longword values.
    // Ex: ++, +-, --, -+, 00
    private static void testAdd(longword a, longword b) {
        a.set(1000);
        b.set(500);
        try { // if add() does not yield the expected result, throw an exception stating its failure
            if (rippleAdder.add(a,b).getSigned()!=1500) throw new Exception("testAdd() Failed: " + rippleAdder.add(a,b).getSigned() + " != 1500");
            else System.out.println("testAdd() Passed: " + rippleAdder.add(a,b).getSigned() + " == 1500"); // if it passes, print passing message to console
        } catch (Exception e) { // catch potential exception, print its message to console
            System.out.println(e);
        }
        b.set(-200);
        try {
            if (rippleAdder.add(a,b).getSigned()!=800) throw new Exception("testAdd() Failed: " + rippleAdder.add(a,b).getSigned() + " != 800");
            else System.out.println("testAdd() Passed: " + rippleAdder.add(a,b).getSigned() + " == 800");
        } catch (Exception e) {
            System.out.println(e);
        }
        a.set(-300);
        try {
            if (rippleAdder.add(a,b).getSigned()!=-500) throw new Exception("testAdd() Failed: " + rippleAdder.add(a,b).getSigned() + " != -500");
            else System.out.println("testAdd() Passed: " + rippleAdder.add(a,b).getSigned() + " == -500");
        } catch (Exception e) {
            System.out.println(e);
        }
        b.set(100);
        try {
            if (rippleAdder.add(a,b).getSigned()!=-200) throw new Exception("testAdd() Failed: " + rippleAdder.add(a,b).getSigned() + " != -200");
            else System.out.println("testAdd() Passed: " + rippleAdder.add(a,b).getSigned() + " == -200");
        } catch (Exception e) {
            System.out.println(e);
        }
        a.set(0);
        b.set(0);
        try {
            if (rippleAdder.add(a,b).getSigned()!=0) throw new Exception("testAdd() Failed: " + rippleAdder.add(a,b).getSigned() + " != 0");
            else System.out.println("testAdd() Passed: " + rippleAdder.add(a,b).getSigned() + " == 0");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // tests the rippleAdder.subtract() method with all different combinations of longword values.
    // Ex: ++, +-, --, -+, 00
    private static void testSubtract(longword a, longword b) {
        a.set(1000);
        b.set(400);
        try { // if add() does not yield the expected result, throw an exception stating its failure
            if (rippleAdder.subtract(a,b).getSigned()!=600) throw new Exception("testSubtract() Failed: " + rippleAdder.subtract(a,b).getSigned() + " != 600");
            else System.out.println("testSubtract() Passed: " + rippleAdder.subtract(a,b).getSigned() + " == 600"); // if it passes, print passing message to console
        } catch (Exception e) { // catch potential exception, print its message to console
            System.out.println(e);
        }
        b.set(-500);
        try {
            if (rippleAdder.subtract(a,b).getSigned()!=1500) throw new Exception("testSubtract() Failed: " + rippleAdder.subtract(a,b).getSigned() + " != 1500");
            else System.out.println("testSubtract() Passed: " + rippleAdder.subtract(a,b).getSigned() + " == 1500");
        } catch (Exception e) {
            System.out.println(e);
        }
        a.set(-700);
        try {
            if (rippleAdder.subtract(a,b).getSigned()!=-200) throw new Exception("testSubtract() Failed: " + rippleAdder.subtract(a,b).getSigned() + " != -200");
            else System.out.println("testSubtract() Passed: " + rippleAdder.subtract(a,b).getSigned() + " == -200");
        } catch (Exception e) {
            System.out.println(e);
        }
        b.set(200);
        try {
            if (rippleAdder.subtract(a,b).getSigned()!=-900) throw new Exception("testSubtract() Failed: " + rippleAdder.subtract(a,b).getSigned() + " != -900");
            else System.out.println("testSubtract() Passed: " + rippleAdder.subtract(a,b).getSigned() + " == -900");
        } catch (Exception e) {
            System.out.println(e);
        }
        a.set(0);
        b.set(0);
        try {
            if (rippleAdder.subtract(a,b).getSigned()!=0) throw new Exception("testSubtract() Failed: " + rippleAdder.subtract(a,b).getSigned() + " != 0");
            else System.out.println("testSubtract() Passed: " + rippleAdder.subtract(a,b).getSigned() + " == 0");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) { // main method runs tests on the bit, longword, and rippleAdder objects
        bit_test.runTests();
        longword_test.runTests();
        runTests();
    }
}

