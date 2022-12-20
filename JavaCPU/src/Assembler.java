// assembler that converts readable instructions into strings that can be preloaded into our computer
public class Assembler {
    // helper method to check is a passed token is a register or not
    public static boolean isRegister(String token) {
        // possible registers
        String[] registers = {"r0", "r1", "r2", "r3", "r4", "r5", "r6", "r7",
                "r8", "r9", "r10", "r11", "r12", "r13", "r14", "r15"};
        // iterate through each register to see if token matches -> return true if match is found
        for (int i = 0; i < 16; i++) {
            if (token.equals(registers[i])) return true;
        }
        // return false if token is not a register
        return false;
    }

    // helper method to check if a passed token is an integer or not
    public static boolean isInteger(String token) {
        // try to parse the string for an integer
        try {
            Integer.parseInt(token);
            // if no exception is thrown, return true because it is an integer
            return true;
        } catch (NumberFormatException e) {
            // return false if a NumberFormatException is thrown, token is not an integer
            return false;
        }
    }

    // helper method to return the bit code respective to the register passed
    public static String getRegisterCode(String register) {
        // switch for different codes based on register case
        switch (register) {
            case "r0":
                return "0000";
            case "r1":
                return "0001";
            case "r2":
                return "0010";
            case "r3":
                return "0011";
            case "r4":
                return "0100";
            case "r5":
                return "0101";
            case "r6":
                return "0110";
            case "r7":
                return "0111";
            case "r8":
                return "1000";
            case "r9":
                return "1001";
            case "r10":
                return "1010";
            case "r11":
                return "1011";
            case "r12":
                return "1100";
            case "r13":
                return "1101";
            case "r14":
                return "1110";
            case "r15":
                return "1111";
        }
        // redundant
        return "0000";
    }

    // main method that reads an array of plain-text instructions and returns an array of bit strings
    public static String[] assemble(String[] instructions) throws Exception {
        // array of bit strings to return later -> same length as instructions passed
        String[] bits = new String[instructions.length];
        // for every instruction
        for (int i = 0; i < instructions.length; i++) {
            // split the instruction into its token delimited by a single space
            String[] tokens = instructions[i].split(" ");
            // if tokens length is >4, invalid instruction -> throw exception
            if (tokens.length > 4) throw new Exception("Error: Invalid instruction!");
            // if tokens length is 1, can only be halt, printmemory or printregisters
            if (tokens.length == 1) {
                if (tokens[0].equalsIgnoreCase("return")) bits[i] = "0110110000000000";
                // check for "halt" "printmemory" and "printregisters", store respective bit string in array
                else if (tokens[0].equalsIgnoreCase("halt")) bits[i] = "0000000000000000";
                else if (tokens[0].equalsIgnoreCase("printmemory")) bits[i] = "0010000000000001";
                else if (tokens[0].equalsIgnoreCase("printregisters")) bits[i] = "0010000000000000";
                // if token is none of those, invalid operation -> throw exception
                else throw new Exception("Error: Invalid operation!");
            } else if (tokens.length == 2) {
                if (tokens[0].equalsIgnoreCase("call")) {
                    // next token must be an integer for valid jump amount
                    if (isInteger(tokens[1])) {
                        // if not even, address is in the middle of an instruction, throw exception
                        if (Integer.parseInt(tokens[1]) % 2 != 0) throw new Exception("Error: Invalid call!");
                        // address can not be negative
                        if (Integer.parseInt(tokens[1]) < 0) throw new Exception("Error: Invalid call!");
                        // max address readable in memory is 1020
                        if (Integer.parseInt(tokens[1]) > 1020) throw new Exception("Error: Invalid call!");
                        longword temp = new longword();
                        temp.set(Integer.parseInt(tokens[1]));
                        // bit command is 0011 for jump followed by jump amount
                        bits[i] = "011010" + temp.toString().substring(22);
                    } else throw new Exception("Error: Invalid call!");
                }
                if (tokens[0].equalsIgnoreCase("push")) {
                    if (!isRegister(tokens[1].toLowerCase())) throw new Exception("Error: Invalid register!");
                    bits[i] = "011000000000" + getRegisterCode(tokens[1].toLowerCase());
                }
                if (tokens[0].equalsIgnoreCase("pop")) {
                    if (!isRegister(tokens[1].toLowerCase())) throw new Exception("Error: Invalid register!");
                    bits[i] = "011001000000" + getRegisterCode(tokens[1].toLowerCase());
                }
                if (tokens[0].equalsIgnoreCase("jump")) {
                    // next token must be an integer for valid jump amount
                    if (isInteger(tokens[1])) {
                        // if not even, address is in the middle of an instruction, throw exception
                        if (Integer.parseInt(tokens[1]) % 2 != 0) throw new Exception("Error: Invalid jump!");
                        // address can not be negative
                        if (Integer.parseInt(tokens[1]) < 0) throw new Exception("Error: Invalid jump!");
                        // max address readable in memory is 1020
                        if (Integer.parseInt(tokens[1]) > 1020) throw new Exception("Error: Invalid jump!");
                        longword temp = new longword();
                        temp.set(Integer.parseInt(tokens[1]));
                        // bit commany is 0011 for jump followed by jump amount
                        bits[i] = "0011" + temp.toString().substring(20);
                    } else throw new Exception("Error: Invalid jump!");
                } else if (tokens[0].equalsIgnoreCase("BranchIfEqual")) {
                    if (!isInteger(tokens[1])) throw new Exception("Error: Invalid branch!"); // branch amount must be a valid integer
                    if (Integer.parseInt(tokens[1]) < -512) throw new Exception("Error: Invalid branch!"); // branch must be >= -512
                    if (Integer.parseInt(tokens[1]) > 511) throw new Exception("Error: Invalid branch!"); // branch must be <= 511
                    longword temp = new longword();
                    temp.set(Integer.parseInt(tokens[1])); // set a longword to the branch amount
                    bits[i] = "010101" + temp.toString().substring(22); // opcode + 01 for = + branch amount
                } else if (tokens[0].equalsIgnoreCase("BranchIfNotEqual")) {
                    if (!isInteger(tokens[1])) throw new Exception("Error: Invalid branch!"); // branch amount must be a valid integer
                    if (Integer.parseInt(tokens[1]) < -512) throw new Exception("Error: Invalid branch!"); // branch must be >= -512
                    if (Integer.parseInt(tokens[1]) > 511) throw new Exception("Error: Invalid branch!"); // branch must be <= 511
                    longword temp = new longword();
                    temp.set(Integer.parseInt(tokens[1])); // set a longword to the branch amount
                    bits[i] = "010100" + temp.toString().substring(22); // opcode + 00 for != + branch amount
                } else if (tokens[0].equalsIgnoreCase("BranchIfGreater")) {
                    if (!isInteger(tokens[1])) throw new Exception("Error: Invalid branch!"); // branch amount must be a valid integer
                    if (Integer.parseInt(tokens[1]) < -512) throw new Exception("Error: Invalid branch!"); // branch must be >= -512
                    if (Integer.parseInt(tokens[1]) > 511) throw new Exception("Error: Invalid branch!"); // branch must be <= 511
                    longword temp = new longword();
                    temp.set(Integer.parseInt(tokens[1])); // set a longword to the branch amount
                    bits[i] = "010110" + temp.toString().substring(22); // opcode + 10 for > + branch amount
                } else if (tokens[0].equalsIgnoreCase("BranchIfGreaterOrEqual")) {
                    if (!isInteger(tokens[1])) throw new Exception("Error: Invalid branch!"); // branch amount must be a valid integer
                    if (Integer.parseInt(tokens[1]) < -512) throw new Exception("Error: Invalid branch!"); // branch must be >= -512
                    if (Integer.parseInt(tokens[1]) > 511) throw new Exception("Error: Invalid branch!"); // branch must be <= 511
                    longword temp = new longword();
                    temp.set(Integer.parseInt(tokens[1])); // set a longword to the branch amount
                    bits[i] = "010111" + temp.toString().substring(22); // opode + 11 for >= + branch amount
                }
            }
            // if tokens length is 3, can only be not or move
            else if (tokens.length == 3) {
                // if the second token is not a register, invalid instruction -> throw exception
                if (!isRegister(tokens[1].toLowerCase())) throw new Exception("Error: Invalid register!");
                // if operation is not
                if (tokens[0].equalsIgnoreCase("not")) {
                    // if third token is a register
                    if (isRegister(tokens[2].toLowerCase())) {
                        // construct bit string with opcode and register codes and store in bit array
                        bits[i] = "1011" + getRegisterCode(tokens[1].toLowerCase()) +
                                "0000" + getRegisterCode(tokens[2].toLowerCase());
                    }
                    // else if operation is compare
                } else if (tokens[0].equalsIgnoreCase("compare")) {
                    if (!isRegister(tokens[2].toLowerCase())) throw new Exception("Error: Invalid register!");
                    // opcode + r1 + r2 + 0000 to fill rest of instruction
                    bits[i] = "0100" + getRegisterCode(tokens[1].toLowerCase()) + getRegisterCode(tokens[2].toLowerCase()) + "0000";
                    // else if operation is move
                } else if (tokens[0].equalsIgnoreCase("move")) {
                    // if third token is an integer (move amount)
                    if (isInteger(tokens[2])) {
                        // make sure -128 <= move amount < = 127
                        if (!(Integer.parseInt(tokens[2]) < -128)) {
                            if (!(Integer.parseInt(tokens[2]) > 127)) {
                                // make a new longword, set to move amount
                                longword temp = new longword();
                                temp.set(Integer.parseInt(tokens[2]));
                                // construct bit string with opcode, register code, and 1 byte representing the move amount
                                bits[i] = "0001" + getRegisterCode(tokens[1].toLowerCase()) + temp.toString().substring(24);
                                // throw exceptions if any conditionals do not pass
                            } else throw new Exception("Error: Invalid move amount!");
                        } else throw new Exception("Error: Invalid move amount!");
                    } else throw new Exception("Error: Invalid move amount!");
                } else throw new Exception("Error: Invalid operation!");
                // else if tokens length if 4, can be add, subtract, multiply, leftshift, rightshift, and, or, xor
            } else if (tokens.length == 4) {
                // if any of the following tokens are not registers, invalid register -> throw exception
                if (!isRegister(tokens[1].toLowerCase())) throw new Exception("Error: Invalid register!");
                if (!isRegister(tokens[2].toLowerCase())) throw new Exception("Error: Invalid register!");
                if (!isRegister(tokens[3].toLowerCase())) throw new Exception("Error: Invalid register!");
                // string for storing opcode of current operation
                String opcode;
                // switch for different codes based on operation case
                switch (tokens[0].toLowerCase()) {
                    case "add":
                        opcode = "1110";
                        break;
                    case "subtract":
                        opcode = "1111";
                        break;
                    case "multiply":
                        opcode = "0111";
                        break;
                    case "leftshift":
                        opcode = "1100";
                        break;
                    case "rightshift":
                        opcode = "1101";
                        break;
                    case "and":
                        opcode = "1000";
                        break;
                    case "or":
                        opcode = "1001";
                        break;
                    case "xor":
                        opcode = "1010";
                        break;
                    default:
                        // if none match, invalid operation -> throw exception
                        throw new Exception("Error: Invalid operation!");
                }
                // construct bit string with opcode and register codes and store in array
                bits[i] = opcode + getRegisterCode(tokens[1].toLowerCase()) +
                        getRegisterCode(tokens[2].toLowerCase()) + getRegisterCode(tokens[3].toLowerCase());
            }
        }
        // when for loop finishes, return array of bit strings
        return bits;
    }
}
