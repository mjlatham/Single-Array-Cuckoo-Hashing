/**
 * Linear Hash
 *
 * Provides a generic hashing number that takes a key and
 * performs a linear hash with a Mersene prime.
 */
public class LinearHash {
    // Mersene prime
    private long MERSENEPRIME = 2098960;
    // Range of the Hash
    private long N;
    // A for the linear hash
    private long A;
    // B for the linear hash
    private long B;

    /**
     * Initialises the hash function by generating the A and B parameters.
     * @param N - The range of the hash.
     * @param prng - The seeded PseudoRandomNumber generator instance.
     */
    public LinearHash(long N, PseudoRandomGenerator prng) {
        this.N = N;

        // Generate the A % P
        this.A = prng.generate() % this.MERSENEPRIME;

        // Keep generating A until it is not 0!
        while (this.A == 0) {
           this.A = prng.generate() % this.MERSENEPRIME;
        }

        // Generate B % P
        this.B = prng.generate() % this.MERSENEPRIME;
    }

    /**
     * Hash the key and return within the range.
     * @param key - The key to hash.
     * @return - The int number that is hashed and modded within RANGE.
     */
    public int Hash(long key) {
        return (int) (((this.A * key + this.B) % this.MERSENEPRIME) % this.N);
    }
}
