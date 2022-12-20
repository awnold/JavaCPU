public class multiplier {
    // multiply() returns a longword that represents the product of multiplying longwords l and l2
    public static longword multiply(longword l, longword l2) {
        longword result = new longword(); // longword result to store product
        rippleAdder adder = new rippleAdder(); // adder to iteratively add to result to achieve multiplication
        for (int i = 31; i >= 0; i--) {
            // for every on bit in l, leftShift l2 by the factor of 2 at which the bit is and add to result
            // doing addition for every on bit in this fashion effectively multiplies the two longwords
            if (l.getBit(i).getValue()) result = adder.add(result, l2.leftShift(31-i));
        }
        return result; // return the product
    }
}