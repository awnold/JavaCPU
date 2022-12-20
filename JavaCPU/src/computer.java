public class computer {
    // boolean representing whether the computer is halted
    private boolean halted;
    // available storage of the computer
    private memory storage;
    // program counter, current instructions to follow, mask for getting instructions, operands 1 and 2, result of computation
    private longword PC, currentInstruction, mask, op1, op2, result;
    // local variables called registers
    private longword[] registers;
    // conditional bits for branch
    private bit[] CC;
    // boolean for if cpu should branch
    private boolean shouldBranch;
    private longword branchAmount;
    // stack pointer
    private longword SP;

    // default constructor initializes all member variables
    public computer() {
        halted = false;
        storage = new memory();
        PC = new longword();
        currentInstruction = new longword();
        mask = new longword();
        mask.set(15); // set the mask to 15: last 4 bits set
        op1 = new longword();
        op2 = new longword();
        registers = new longword[16];
        for (int i = 0; i < 16; i++) {
            registers[i] = new longword();
        }
        result = new longword();
        CC = new bit[2];
        CC[0] = new bit(false);
        CC[1] = new bit(false);

        shouldBranch = false;
        branchAmount = new longword();
        branchAmount.set(0);
        SP = new longword();
        SP.set(1020);
    }

    public void fetch() {
        // read current instructions
        currentInstruction = storage.read(PC);
        longword increment = new longword();
        increment.set(2);
        // increment program counter by 2 for next instructions
        PC = rippleAdder.add(PC, increment);
    }

    public void decode() {
        if (currentInstruction.getBit(0).or(currentInstruction.getBit(1)).getValue()) { // if opcode is not a halt, move, or interrupt
            // copy operands from current instructions to op1 and op2 using rightshift and masking
            op1.copy(registers[currentInstruction.rightShift(24).and(mask).getSigned()]);
            op2.copy(registers[currentInstruction.rightShift(20).and(mask).getSigned()]);
        }
    }

    public void execute() {
        // make a bit array of the opcode in current instructions
        bit[] opcode = { currentInstruction.getBit(0), currentInstruction.getBit(1),
                currentInstruction.getBit(2), currentInstruction.getBit(3) };
        if (!opcode[0].getValue()) {
            if (opcode[1].getValue()) {
                if (opcode[2].getValue()) {
                    if (!opcode[3].getValue()) { // 0110 = call, return, push, pop
                        if (!currentInstruction.getBit(4).getValue()) { // 01100_ = push or pop
                            longword register = new longword();
                            register.copy(currentInstruction.rightShift(16).and(mask)); // last 4 for reg address
                            longword four = new longword();
                            four.set(4); // longword to increment/decrement SP
                            if (!currentInstruction.getBit(5).getValue()) { // 011000 = push
                                System.out.println("PUSHING TO STACK:");
                                storage.write(SP, registers[register.getSigned()]); // write passed register to current SP
                                SP.copy(rippleAdder.subtract(SP, four)); // decrement SP
                                return; // return early
                            } else { // 011001 = pop
                                System.out.println("POPPING FROM STACK:");
                                SP.copy(rippleAdder.add(SP, four)); // increment SP
                                registers[register.getSigned()].copy(storage.read(SP)); // store popped number in passed register
                                return; // return early
                            }
                        } else if (!currentInstruction.getBit(5).getValue()) { // 011010 = call
                            System.out.println("CALLING:");
                            storage.write(SP, PC); // write current PC to SP to store next instruction
                            longword four = new longword();
                            four.set(4); // longword to decrement SP after pushing next instruction to SP
                            SP.copy(rippleAdder.subtract(SP, four)); // decrement SP
                            longword tempmask = new longword();
                            tempmask.set(1023); // mask for the last 10 bits for jump amount
                            PC.copy(currentInstruction.rightShift(16).and(tempmask));
                            return; // return early
                        } else { // 011011 = return
                            System.out.println("RETURNING:");
                            longword four = new longword();
                            four.set(4); // longword to increment SP
                            SP.copy(rippleAdder.add(SP, four)); // increment SP
                            PC.copy(SP); // change PC to address in SP (presumably from a CALL)
                            return; // return early
                        }

                    }
                }
            }
        }
        if (!opcode[0].getValue()) {
            if (!opcode[1].getValue()) {
                if (!opcode[2].getValue()) {
                    if (!opcode[3].getValue()) { // opcode 0000 = halt
                        System.out.println("HALTING COMPUTER:");
                        halted = true; // set halted to true
                        return; // return early
                    }
                }
            }
        }
        if (!opcode[0].getValue()) {
            if (opcode[1].getValue()) {
                if (!opcode[2].getValue()) {
                    if (!opcode[3].getValue()) { // opcode 0100 = compare
                        System.out.println("COMPARING REGISTERS:");
                        return; // return early
                    } else { // else opcode 0101 = branch command
                        longword valuemask = new longword(); // new longword to mask 10-bit value in instructions
                        valuemask.set(1023); // 00000000000000000000001111111111 to mask only last 10 bits
                        longword temp = new longword();
                        temp.copy(currentInstruction.rightShift(16).and(valuemask)); // copy value to temp in bits of correct significance
                        if (currentInstruction.getBit(6).getValue() == true) { // if value in instructions is negative
                            longword negativemask = new longword(); // new longword to mask in case of negative value
                            negativemask.set(-1024); // 11111111111111111111110000000000
                            temp.copy(temp.or(negativemask)); // sign extend with OR to preserve sign and value
                        }
                        if (currentInstruction.getBit(4).and(currentInstruction.getBit(5)).getValue()) { // if >=
                            if (CC[0].or(CC[1]).getValue()) { // if previous compare was >=, branch given amount
                                shouldBranch = true;
                                branchAmount.copy(temp);
                                return; // return early
                            }
                        } if (!currentInstruction.getBit(4).getValue()) {
                            if (currentInstruction.getBit(5).getValue()) { // if =
                                if (!CC[0].getValue()) {
                                    if (CC[1].getValue()) { // if previous compare was =, branch given amount
                                        shouldBranch = true;
                                        branchAmount.copy(temp);
                                        return; // return early
                                    }
                                }
                            } else if (!CC[1].getValue()) { // if != & previous compare was !=, branch given amount
                                shouldBranch = true;
                                branchAmount.copy(temp);
                                return; // return early
                            }
                        } else if (CC[0].getValue()) { // if > & previous compare was >, branch given amount
                            if (!CC[1].getValue()) {
                                shouldBranch = true;
                                branchAmount.copy(temp);
                                return; // return early
                            }
                        } // if the CC's dont match, dont branch
                        shouldBranch = false;
                        branchAmount.set(0);
                        return; // return early
                    }
                }
            }
        }
        if (!opcode[0].getValue()) {
            if (!opcode[1].getValue()) {
                if (!opcode[2].getValue()) {
                    if (opcode[3].getValue()) { // opcode 0001 = move
                        System.out.println("MOVING A REGISTER VALUE:");
                        longword valuemask = new longword(); // new longword to mask 8-bit value in instructions
                        valuemask.set(255); // 00000000000000000000000011111111 to mask only last 8 bits
                        longword temp = new longword();
                        temp.copy(currentInstruction.rightShift(16).and(valuemask)); // copy value to temp in bits of correct significance
                        if (currentInstruction.getBit(8).getValue() == true) { // if value in instructions is negative
                            longword negativemask = new longword(); // new longword to mask in case of negative value
                            negativemask.set(-256); // 11111111111111111111111100000000
                            temp.copy(temp.or(negativemask)); // sign extend with OR to preserve sign and value
                        }
                        registers[currentInstruction.rightShift(24).and(mask).getSigned()].copy(temp); // store in register
                        return; // return early
                    }
                }
            }
        }
        if (!opcode[0].getValue()) {
            if (!opcode[1].getValue()) {
                if (opcode[2].getValue()) {
                    if (!opcode[3].getValue()) { // opcode 0010 = interrupt
                        if (currentInstruction.getBit(15).getValue() == true) { // check for second interrupt
                            System.out.println("PRINTING MEMORY:");
                            for (int i = 0; i < 1021; i += 4) { // for every byte in memory
                                longword address = new longword();
                                address.set(i); // set the address to read
                                System.out.println(storage.read(address)); // print longword at i
                            }
                            return; // return early
                        } else { // first interrupt case
                            System.out.println("PRINTING REGISTERS:");
                            for (int i = 0; i < 16; i++) { // for each register in registers[]
                                System.out.println(registers[i]); // print out the register
                            }
                        }
                        return; // return early
                    } else if (opcode[3].getValue()) { // opcode 0011 = jump
                        return; // return early
                    }
                }
            }
        }
        // run doOp with opcode and operands 1 and 2
        System.out.println("PERFORMING ALU OPERATION:");
        result = ALU.doOp(opcode, op1, op2);
    }

    public void store() {
        bit[] opcode = { currentInstruction.getBit(0), currentInstruction.getBit(1),
                currentInstruction.getBit(2), currentInstruction.getBit(3) };
        if (!opcode[0].getValue()) {
            if (!opcode[1].getValue()) {
                if (opcode[2].getValue()) {
                    if (opcode[3].getValue()) { // opcode 0011 = jump
                        System.out.println("JUMPING:");
                        longword tempmask = new longword();
                        tempmask.set(4095); // mask last 12 bits for the jump amount
                        PC.copy(currentInstruction.rightShift(16).and(tempmask));
                        return; // return early
                    }
                }
            } else if (!opcode[2].getValue()) {
                if (!opcode[3].getValue()) { // opcode 0100 = compare
                    longword difference = rippleAdder.subtract(op1, op2); // subtract op2 from op1
                    int diff = difference.getSigned(); // signed int difference
                    if (diff == 0) { // equals -> 01
                        CC[0] = new bit(false);
                        CC[1] = new bit(true);
                        return; // return early
                    }
                    if (diff > 0) { // greater -> 10
                        CC[0] = new bit(true);
                        CC[1] = new bit(false);
                        return; // return early
                    }
                    if (diff < 0) { // less than -> 00 (not equals)
                        CC[0] = new bit(false);
                        CC[1] = new bit(false);
                        return; // return early
                    }
                } else {
                    if (shouldBranch) { // if should branch
                        PC = rippleAdder.add(PC, branchAmount); // add the branch amount to the PC
                        shouldBranch = false; // reset to false
                        System.out.println("BRANCHING:");
                    } else {
                        System.out.println("NOT BRANCHING:");
                    } // reset CC to 00
                    CC[0] = new bit(false);
                    CC[1] = new bit(false);
                    return; // return early
                }
            } else if (!opcode[3].getValue()) {
                return; // return early;
            }
        }
        if (currentInstruction.getBit(0).or(currentInstruction.getBit(1)).getValue() == true) {  // if opcode is not a halt, move, or interrupt
            // get index of register to store result of computation
            int i = currentInstruction.rightShift(16).and(mask).getSigned();
            // store result in index
            registers[i] = result;
        }
    }

    public void run() {
        // while computer is running: fetch, decode, execute, store, and repeat
        while (!halted) {
            fetch();
            decode();
            execute();
            store();
        }
    }
    // preload instructions for the cpu
    public void preload(String[] instructions) {
        longword address = new longword(); // address to increment as instructions are parsed
        for (int i = 0; i < instructions.length; i++) { // for every instruction
            String curr = instructions[i].replaceAll("\\s", ""); // cut out whitespace
            longword temp = new longword(); // define temp longword for storage
            for (int j = 0; j < 16; j++) { // for all 16 bits
                int bit = Integer.parseInt(curr.substring(j,j+1)); // parse string for ints
                if ((bit == 1)) temp.setBit(j, new bit(true)); // get set/unset bits from int values
                else temp.setBit(j, new bit());
            }
            storage.write(address, temp); // write the instruction just read to current address in storage
            longword increment = new longword();
            increment.set(2);
            address.copy(rippleAdder.add(address, increment)); // increment storage address for next instruction
        }
    }
}
