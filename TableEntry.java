/**
 * Entry in the hash table.
 *
 * Stores the key, value and visited state.
 * @param <V> - The value type.
 */

public class TableEntry {
    private long key;
    private long value;

    /**
     * Initialises the table entry, assigns the key/value.
     * @param key - The key of the entry.
     * @param value - The value of the entry.
     */
    public TableEntry(long key, long value){
        this.value = value;
        this.key = key;
    }

    /**
     * Get the entry's key.
     * @return - The entry's key
     */
    public long getKey() {
        return key;
    }

    /**
     * Get the entry's value.
     * @return - The entry's value.
     */
    public long getValue() {
        return value;
    }
}
