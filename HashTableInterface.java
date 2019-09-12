/**
 * Interface for the HashTable
 *
 * Provides the minimum hashtable interface that will be used
 * to put, get and remove elements from the table.
 */

public interface HashTableInterface {
    /**
     * Puts the value into an entry into the table.
     * @param key - The key for the matching value.
     * @param value - The value to place into the table.
     * @return - success if entry was placed.
     */
    boolean put(long key, long value);

    /**
     * Gets the value of the element with the key from the table.
     * @param key - The element's key fetch.
     * @return - The "VALUE" of the matching element.
     */
    long get(long key);

    /**
     * Removes the element with the matching key from the table.
     * @param key - The key of the element to remove.
     * @return - The VALUE associated with the removed entry.
     */
    long remove(long key);
}
