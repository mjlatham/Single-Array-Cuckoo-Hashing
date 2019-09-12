/**
 * GLib Pseudo Random Generator
 *
 * ======================
 * MAGIC NUMBERS WARNING!
 * A, B = MAGIC NUMBERS!!
 *  Note: These numbers were chosen.
 * ======================
 */

public class PseudoRandomGenerator {

    // The A parameter in the pseudo random number generator
    private long A = 1103515245;

    // The B parameter in the pseudo random number generator
    private long B = 12345;

    // The previous state of the generator.
    private long previous;

    // The base to divide
    private long base;

    /**
     * Initialises the PRNG with a default seed.
     */
    public PseudoRandomGenerator() {
        this.previous = 1804289383;
        this.base = (long) Math.pow(2, 32);
    }

    /**
     * Initialises the PRNG with the given seed.
     * @param seed - The seed for the PRNG.
     */
    public PseudoRandomGenerator(long seed) {
        this.previous = seed;
        this.base = (long) Math.pow(2, 32);
    }

    /**
     * Generates the next random number in the sequence.
     * @return - The number generated from random.
     */
    public long generate(){
        this.previous = (A * this.previous + B) % this.base;
        return this.previous;
    }

    /*
    **************************************************************************
    *
    * NOTE: The main function below is to show functionality and is not used
    * for testing. It can be used to understand the generated numbers.
    *
    **************************************************************************
     */

    public static void main(String[] args) {
        PseudoRandomGenerator G = new PseudoRandomGenerator(1234567896);
        System.out.println(G.generate());
        System.out.println(G.generate());
        System.out.println(G.generate());
        System.out.println(G.generate());
        System.out.println(G.generate());
        System.out.println(G.generate());
        System.out.println(G.generate());
    }
}
