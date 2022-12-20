public class bit {
    private boolean state; // the bits status where true or false == on or off *respectively*; false by default

    public bit() { // default constructor, keeps default value of state (false cause its type boolean)
    }

    public bit(boolean value) { // overloaded constructor that takes a value as a param to set the state of the bit to
        state = value;
    }

    public void set(boolean value) { // set a bits state to the passed param
        state = value;
    }

    public void toggle() { // swaps the state of the bit
        if (state) { // if on -> off
            state = false;
        } else { // else off -> on
            state = true;
        }
    }

    public void set() { // overloaded set method takes no param, simply sets a bits state to on
        state = true;
    }

    public void clear() { // sets a bits state to off
        state = false;
    }

    public boolean getValue() { // returns the current state of a bit
        return state;
    }

    public bit and(bit other) { // performs logical "AND" on object bit and another bit
        if (state) { // if the object bit is on...
            if (other.state) return new bit(true); // if the other bit is also on, return a new bit that is on
        }
        return new bit(false); // else, return a new bit that is off *all other cases*
    }

    public bit or(bit other) { // performs logical "OR" on object bit and another bit
        if (state) return new bit(true); // if the object bit is on return a new bit that is on
        if (other.state) return new bit(true); // or, if the other bit is on, return a new bit that is on (only takes 1)
        return new bit(false); // else, return a new bit that is off *neither are on*
    }

    public bit not() { // performs logical "NOT" on a bit
        if (state) return new bit(false); // if object bit is on, return a new bit that is off
        return new bit(true); // else, object bit is off, return a new bit that is on
    }

    public bit xor(bit other) { // performs logical "XOR" on object bit and another bit
        if (state) { // if object bit is on...
            if (other.state) return new bit(false); // and other bit is on, return a new bit that is off (not exclusively or)
        return new bit(true); // else, other bit is off, return a new bit that is on (exclusively or)
        }
        if (other.state) return new bit(true); // similarly, if other bit is on while object bit is off, return a new bit that is on
        return new bit(false); // else, both are off, return a new bit that is off
    }

    @Override
    public String toString() { // returns a string representation of the current state of a bit
        if (state) { // if the bit is on, return "t" for true
            return "t";
        }
        return "f"; // if the bit is off, return "f" for false
    }
}
