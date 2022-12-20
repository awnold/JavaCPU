public class rippleAdder {
    public static longword add(longword a, longword b) {
        longword result = new longword(); // the sum after the addition of the two longwords
        bit carry = new bit(); // represents whether there is currently a carry bit - false by default
        for (int i = 31; i >= 0; i--) {
            bit currBit = a.getBit(i).xor(b.getBit(i)).xor(new bit(carry.getValue()));  // result bit will only be set if exclusively 1 or
            result.setBit(i, currBit);                                                  // all 3 relevant bits are set (a[i], b[i] & carry bit)
            carry = a.getBit(i).and(b.getBit(i)).or(a.getBit(i).xor(b.getBit(i)).and(new bit(carry.getValue())));
            // carry bit is set if both a[i] & b[i] are set, or if only a[i] or b[i] is set while carry bit is also set
        }
        return result; // return the sum after the addition of longwords a and b
    }

    public static longword subtract(longword a, longword b) {
        longword one = new longword(); // placeholder longword to hold a value of 1
        one.set(1); // set longword 'one' to represent 1
        return add(a, add(b.not(), one)); // flip all bits of b & add 1 to negate b, add to a to find the difference
    }
}
