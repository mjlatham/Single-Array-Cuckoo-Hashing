import java.util.Arrays;

/**
 * CuckooHashTable
 *
 * Implements a hash table which utilises CuckooHashing to place elements
 * into the table.
 */

public class CuckooHashTable implements HashTableInterface {
    private TableEntry[] table;
    private LinearHash h1;
    private LinearHash h2;

    /**
     * Initialises the hash table with correct hashes;
     * @param size - The size of the table.
     * @param prngOne - The Seeded Pseudo Random Number Generator for Hash1;
     * @param prngTwo - The Seeded Pseudo Random Number Generator for Hash2;
     */
    public CuckooHashTable(int size, PseudoRandomGenerator prngOne, PseudoRandomGenerator prngTwo) {
        this.table = new TableEntry[size];
        this.h1 = new LinearHash(size, prngOne);
        this.h2 = new LinearHash(size, prngTwo);
    }
	
	/**
	 * ToString method
	 * DO NOT MODIFY
	 */
	public String toString() {
        String s = "[";
        s += Arrays.stream(this.table)
                .map(te -> te != null ? Long.toString(te.getValue()) : "-")
                .reduce("", (a, b) -> a + b);
        s += "]";
        return  s;
    }

    /**
     * Puts the value into an entry into the table.
     * @param key - The key for the matching value.
     * @param value - The value to place into the table.
     * @return - success, true iff entry was placed.
     */
    public boolean put(long key, long value) {
		// Try to fit key into table using h1
		if (table[h1.Hash(key)] != null && table[h1.Hash(key)].getKey() == key) {
			table[h1.Hash(key)] = new TableEntry(key, value);
			return true;
		} else if (table[h1.Hash(key)] == null) {
			table[h1.Hash(key)] = new TableEntry(key, value);
			return true;
		}
		
		// Try to fit key into table using h2
		if (table[h2.Hash(key)] != null && table[h2.Hash(key)].getKey() == key) {
			table[h2.Hash(key)] = new TableEntry(key, value);
			return true;
		} else if (table[h2.Hash(key)] == null) {
			table[h2.Hash(key)] = new TableEntry(key, value);
			return true;
		}
		
		// If neither positions are available, start eviction sequence
		int numEvictions = 0;
		int i = 1;
		LinearHash h;
		
		while (numEvictions < table.length) {
			if (i == 1)
				h = h1;
			else
				h = h2;
			
			// if (table[h.Hash(key)] == null) {
			// 	table[h.Hash(key)] = new TableEntry(key, value);
			// 	return true;
			// }
			
			TableEntry temp = table[h.Hash(key)];
			table[h.Hash(key)] = new TableEntry(key, value);
			key = temp.getKey();
			value = temp.getValue();
			
			if (table[h.Hash(key)] == null) {
				table[h.Hash(key)] = new TableEntry(key, value);
				return true;
			}
			
			if (i == 1)
				i = 2;
			else
				i = 1;
			
			numEvictions++;
		}
		
        return false;
    }

    /**
     * Gets the value of the element with the key from the table.
     * @param key - The element's key fetch.
     * @return - The "VALUE" of the matching element.
     *              -1 if the entry could not be found.
     */
    public long get(long key) {
		if (table[h1.Hash(key)] != null && table[h1.Hash(key)].getKey() == key) {
			return table[h1.Hash(key)].getValue();
		}

		if (table[h2.Hash(key)] != null && table[h2.Hash(key)].getKey() == key) {
			return table[h2.Hash(key)].getValue();
		}

        return -1;
    }

    /**
     * Removes the element with the matching key from the table.
     * @param key - The key of the element to remove.
     * @return - The VALUE associated with the removed entry.
     *              -1 if the entry could not be found.
     */
    public long remove(long key) {
		long temp = -1;
		
		if (table[h1.Hash(key)] != null && table[h1.Hash(key)].getKey() == key) {
			temp = table[h1.Hash(key)].getValue();
			table[h1.Hash(key)] = null;
		}
		
		if (table[h2.Hash(key)] != null && table[h2.Hash(key)].getKey() == key) {
			temp = table[h2.Hash(key)].getValue();
			table[h2.Hash(key)] = null;
		}
		
        return temp;
    }
	
	/***********************
	This method is not tested
	************************/
	
	public static void main(String[] args) {
		// Please edit and use to test.
		System.out.println("Running Main");
		
		int size = 50;
		
		PseudoRandomGenerator gA = new PseudoRandomGenerator(93578243);
		PseudoRandomGenerator gB = new PseudoRandomGenerator(84979217);
		CuckooHashTable myTable = new CuckooHashTable(size, gA, gB);
		
		myTable.put(396, 1);
		myTable.put(280, 2);
		myTable.put(188, 3);
		//myTable.put(244, 5);
		//System.out.println(myTable.put(224, 4));
		//myTable.put(244, 4);
		
		for (int i = 0; i < size; i++) {
			try {
				System.out.print(myTable.table[i].getValue());
			}
			catch (Exception e) {
				System.out.print("-");
			}
				
		}
	}
}
