import java.sql.Array;
import java.util.Arrays;

public class longword {
    private bit bitArray[] = new bit[32]; // array of 32 bits that makes up 1 longword

    public longword() { // default constructor fills bitArray with all 0 bits
        for (int i = 0; i < 32; i++) {
            bitArray[i] = new bit();
        }
    }

    bit getBit(int i) { // returns the bit at index i of the bitArray
        return bitArray[i];
    }

    void setBit(int i, bit value) { // sets the bit at index i of the bitArray to the passed bit value
        bitArray[i] = value;
    }

    longword and(longword other) {
        longword result = new longword(); // initialize a new longword to return
        for (int i = 0; i < 32; i++) { // performs logical "AND" on each object bit and other bit respectively
            result.setBit(i, bitArray[i].and(other.getBit(i))); // stores the result in the new longword object
        }
        return result;
    }

    longword or(longword other) {
        longword result = new longword(); // initializes a new longword to return
        for (int i = 0; i < 32; i++) { // performs logical "OR" on each object bit and other bit respectively
            result.setBit(i, bitArray[i].or(other.getBit(i))); // stores the result in the new longword object
        }
        return result;
    }

    longword xor(longword other) {
        longword result = new longword(); // initializes a new longword to return
        for (int i = 0; i < 32; i++) { // performs logical "XOR" on each object bit and other bit respectively
            result.setBit(i, bitArray[i].xor(other.getBit(i))); // stores the result in the new longword object
        }
        return result;
    }

    longword not() { // returns a new longword with the values of the bits in the object longword inverted
        longword result = new longword();
        for (int i = 0; i < 32; i++) {
            result.setBit(i, bitArray[i].not()); // performs not on object longword's bits and stores in result
        }
        return result;
    }

    longword rightShift(int amount) { // shifts the bits of object longword to the right by "amount" places
        longword result = new longword();
        for (int i = amount; i < 32; i++) { // loops from leftmost index after shift to rightmost bit
            result.setBit(i, getBit(i - amount)); // sets the bits of the result longword to the bit in the object
        }                                           // longword "amount" places left of current index
        return result;
    }

    longword leftShift(int amount) { // shifts the bits of object longword to the left by "amount" places
        longword result = new longword();
        for (int i = 0; i < 32 - amount; i++) { // loops from leftmost bit to rightmost index after shift
            result.setBit(i, getBit(i + amount)); // sets the bits of the result longword to the bit in the object
        }                                           // longword "amount" places right of current index
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 32; i++) { // iterate through each bit, storing 't' or 'f' to the array
            result+= bitArray[i].getValue() ? "1" : "0"; // stores 't' at index i if bit i is true, 'f' if false
        }
        return result; // convert char array to string, return
    }

    long getUnsigned() { // return the value of this longword as an unsigned long
        long longValue = 0;
        double factor = Math.pow(2, 31); // factor = max value of most significant bit
        for (int i = 0; i < 32; i++) { // for every bit
            longValue += (bitArray[i].getValue() ? 1 : 0) * factor; // add current factor to longValue depending on bit state
            factor /= 2; // divide factor by 2 for next bit's factor
        }
        return longValue;
    }

    int getSigned() { // return the value of this longword as a signed int
        int value = 0; // start at 0
        double factor = Math.pow(2, 31); // factor = max value of most significant bit
        if (bitArray[0].getValue()) { // if sign bit is true
            value = -1; // start with -1 (must be <0)
            for (int i = 0; i < 32; i++) { // for each bit
                if (!bitArray[i].getValue()) { // if the bit is off
                    value-=factor; // subtract current factor from running value
                }
                factor/=2; // divide factor by 2 for next bit's factor
            }
        } else { // if sign bit is false
            return (int) getUnsigned(); // run getUnsigned() and return the result
        }
        return value;
    }

    void copy(longword other) { // copy the bitArray of longword other to the bitArray of the object longword
        bitArray = other.bitArray;
    }
    void set(int value) { // sets the bits of object longword to represent signed int value
        int currValue = Math.abs(value); // get the absolute value of value in order to calculate bit values
        for (int i = 31; i>=0; i--) { // for each bit, from least to most significant
            if (currValue % 2 == 1) bitArray[i].set(); // if currValue is odd, set bit at i to true
            else bitArray[i].clear(); // if currValue is even, set bit at i to false
            currValue /= 2; // divide int currValue by 2 for next bit
        }
        if (value < 0) { // now that we have the correct bits if the value were unsigned, if passed value is negative...
            for (int i = 0; i < 32; i++) {
                bitArray[i].toggle(); // toggle every bit
            }
            for (int i = 31; i >= 0; i--) { // for each bit, from least to most significant
                if (bitArray[i].getValue()) { // if current bit is true, set to false and effectively "carry the one"
                    bitArray[i].clear();
                } else { // if current bit is false
                    bitArray[i].set(); // set the bit to true
                    break;  // done carrying the one / adding one. the object longword should now represent
                }           // the two's complement form of the negative value passed into set()
            }
        }
    }
}