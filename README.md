# CARFAX-ADT
Customized “Vehicle history Report listing” data structure called CVR. Keys of CVR entries are vehicle identification numbers, that are strings composed of 10-17 alphanumeric characters, and one can retrieve the key of a CVR or access a single element by its key. Furthermore, similar to sequences, given a vehicle identification number in a CVR one can access its predecessor or successor (if it exists). CVR adapts to its usage and keeps the balance between memory and runtime requirements. For instance, if a CVR contains only a small number of entries (e.g., few hundreds), it might use less memory overhead but slower (sorting) algorithms. On the other hand, if the number of contained entries is large (greater than 1000 or even in the range of tens of thousands of elements or more), it might have a higher memory requirement but faster (sorting) algorithms. CVR might be almost constant in size or might grow and/or shrink dynamically


The CVR implements the following methods:

• setThreshold(Threshold): where 100 ≤ Threshold ≤ ~900,000 is an integer number

that defines when a listing should be implemented with a data structure such as a Tree,
Hash Table, AVL tree, binary tree, if its size is greater than or equal to value of
Threshold. Otherwise it is implemented as a Sequence.

• setKeyLength(Length): where 10 ≤ Length ≤ 17 is an integer number that defines the
fixed string length of keys.

• generate(n): randomly generates a sequence containing n new non-existing keys of
alphanumeric characters.

• allKeys(): return all keys as a sorted sequence (lexicographic order)
• add(key,value2): add an entry for the given key and value

• remove(key): remove the entry for the given key

• getValues(key): return the values of the given key

• nextKey(key): return the key for the successor of key.

• prevKey(key): return the key for the predecessor of key

• prevAccids(key): returns a sequence (sorted in reverse chronological order) of accidents(previously) registered
with the given key (dates).
