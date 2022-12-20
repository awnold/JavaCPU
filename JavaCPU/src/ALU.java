public class ALU {
    public static longword doOp(bit[] operation, longword a, longword b) {
        try { // if operation is not 4 bits long, it is inherently invalid, throw an exception
            if (operation.length != 4) throw new Exception("Invalid operation. Returning empty longword object.");
        } catch (Exception e) {
            System.out.println(e);
            return new longword(); // return empty longword
        }
        if(!operation[0].getValue()) { // operation 0___ = can only potentially be multiplication
            try { // if following 3 bits are not 1, throw an exception and return an empty longword for invalid operation, else multiply
                if (!operation[1].and(operation[2].and(operation[3])).getValue()) throw new Exception("lolInvalid operation. Returning empty longword object.");
                return multiplier.multiply(a, b); // operation 0111 = multiply, return a * b
            } catch(Exception e) {
                System.out.println(e);
                return new longword(); // return empty longword
            }
        } // operation 1___ = and, or, xor, not, leftShift, rightShift, add, subtract
        if (!operation[1].getValue()) { // operation 10__ = and, or, xor, not
            if (!operation[2].getValue()) { // operation 100_ = and, or
                if (!operation[3].getValue()) { // operation 1000 = and, return a and b
                    return a.and(b);
                } // operation 1001 = or, return a or b
                return a.or(b);
            } // operation 101_ = xor, not
            if (!operation[3].getValue()) { // operation 1010 = xor, return a xor b
                return a.xor(b);
            } // operation 1011 = not, return a not
            return a.not();
        } // operation 11__ = leftShift, rightShift, add, subtract
        if (!operation[2].getValue()) { // operation 110_ = leftShift, rightShift
            longword bitMask = new longword();
            bitMask.set(31); // create a bit mask for only the last 5 bits
            if (!operation[3].getValue()) { // operation 1100 = leftShift, return a leftShifted by the value of the last 5 bits in b
                return a.leftShift(b.and(bitMask).getSigned());
            } // operation 1101 = rightShift, return a rightShifted by the value of the last 5 bits in b
            return a.rightShift(b.and(bitMask).getSigned());
        } // operation 111_ = add, subtract
        if (!operation[3].getValue()) { // operation 1110 = add, return a + b
            return rippleAdder.add(a, b);
        } // operation 1111 = subtract, return a - b
        return rippleAdder.subtract(a, b);
    }
}