public class memory {
    private bit[] storage = new bit[8192]; // array of 8192 bits = 1024 bytes = 256 longwords of memory

    // default constructor
    public memory() {
        for (int i = 0; i < 8192; i++) { // fill storage with 0s
            storage[i] = new bit();
        }
    }

    // read a longword starting at the byte index passed as longword address
    public longword read(longword address) {
        int byteIndex = address.getSigned(); // getSigned to prevent overflow from all 1s
        try {
            if (byteIndex >= 0) { // 0 <= byteIndex <= 1020 or else invalid address
                if (byteIndex <= 1020) {
                    longword result = new longword(); // new longword to store result
                    // for each bit in result, set the bit to the correct in storage (8*index(+1 for every iteration))
                    for (int i = 0; i < 32; i++) result.setBit(i, storage[byteIndex * 8 + i]);
                    return result;
                }
            }
            // if 0 </= byteIndex </= 1020, index is out of bounds -> throw exception
            throw new Exception("Given address is not within the bounds of the memory. Returning empty longword.");
        } catch (Exception e) {
            System.out.println(e); // print exception message
        }
        return new longword();
    }

    // write a longword value starting at the byte index passed as longword address
    public void write(longword address, longword value) {
        int byteIndex = address.getSigned(); // getSigned to prevent overflow from all 1s
        try {
            if (byteIndex >= 0) { // 0 <= byteIndex <= 1020 or else invalid address
                if (byteIndex <= 1020) {
                    // for each bit in value, set the corresponding bit in storage to its value
                    for (int i = 0; i < 32; i++) storage[byteIndex * 8 + i] = value.getBit(i);
                    return; // return when done
                }
            }
            // if 0 </= byteIndex </= 1020, index is out of bounds -> throw exception
            throw new Exception("Given address is not within the bounds of the memory.");
        } catch (Exception e) {
            System.out.println(e); // print exception message
        }
    }
}
