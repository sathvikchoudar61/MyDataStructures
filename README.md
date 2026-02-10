# My Data Structures Journey

This project documents my personal journey to deeply understand the internal workings of fundamental data structures. Instead of just using Java's built-in libraries, I challenged myself to build my own versions—`MyList`, `MyHashMap`, and `MyHashSet` to learn exactly how they function under the hood.

This codebase represents my exploration into memory management, hashing algorithms, resizing logic, and algorithm efficiency.

## 1. MyList<T>
This was my implementation of a dynamic array (like `ArrayList`). I wanted to understand how resizing works under the hood.

| Method | Return Type | Syntax | Description |
| :--- | :--- | :--- | :--- |
| `MyList()` | `void` (Constructor) | `new MyList<T>()` | Creates a new list instance with a default capacity of 10. |
| `add` | `void` | `list.add(T ele)` | My logic for appending an element. It checks if the array is full and doubles the size if needed. |
| `add` | `void` | `list.add(int idx, T ele)` | Inserts at a specific index. I learned to shift all subsequent elements to make space. |
| `addAll` | `void` | `list.addAll(MyList<T> other)` | Bulk adds elements from another list. |
| `set` | `void` | `list.set(int idx, T ele)` | Updates the element at the index. |
| `get` | `T` | `list.get(int idx)` | Retrieves the element. Taught me about index validation. |
| `contains` | `boolean` | `list.contains(T ele)` | Scans the array to find if an element exists. |
| `size` | `int` | `list.size()` | Returns the current count of elements. |
| `isEmpty` | `boolean` | `list.isEmpty()` | Simple check if size is 0. |
| `clear` | `void` | `list.clear()` | Resets the list to its initial state. |
| `indexOf` | `int` | `list.indexOf(T ele)` | Finds the first occurrence of an element. |
| `lastIndexOf` | `int` | `list.lastIndexOf(T ele)` | Finds the last occurrence of an element. |
| `remove` | `void` | `list.remove(int idx)` | Removes an element. I had to shift elements back to fill the gap. |
| `getFirstElement` | `T` | `list.getFirstElement()` | Helper to get the first item. |
| `getLastElement` | `T` | `list.getLastElement()` | Helper to get the last item. |
| `toArray` | `Object[]` | `list.toArray()` | Converts my list into a standard array. |
| `subArray` | `Object[]` | `list.subArray(int start_idx)` | Creating a slice of the array. |
| `subArray` | `Object[]` | `list.subArray(int start, int close)` | Creating a slice with start and end points. |
| `ReplaceAllOccurrences` | `void` | `list.ReplaceAllOccurrences(T ele, T val)` | Iterates and replaces all matching elements. |
| `ValueFreq` | `int` | `list.ValueFreq(T ele)` | Counts how many times an element appears. |
| `indicesOf` | `int[]` | `list.indicesOf(T ele)` | Finds all positions of an element. |
| `getMin` | `T` | `list.getMin()` | Finds the smallest element (if comparable). |
| `getMax` | `T` | `list.getMax()` | Finds the largest element (if comparable). |
| `reverse` | `void` | `list.reverse()` | Reverses the array in-place. |
| `reverseSubArray` | `void` | `list.reverseSubArray(int start, int close)` | Reverses only a portion of the array. |
| `rotateLeft` | `void` | `list.rotateLeft(int rotations)` | Rotates elements to the left. Tricky logic! |
| `rotateRight` | `void` | `list.rotateRight(int rotations)` | Rotates elements to the right. |

## 2. MyHashMap<T, V>
Implementation of a Hash Map using separate chaining. This was challenging to understand how collisions are handled using linked lists.

| Method | Return Type | Syntax | Description |
| :--- | :--- | :--- | :--- |
| `MyHashMap()` | `void` (Constructor) | `new MyHashMap<T, V>()` | Initializes the map with buckets. |
| `put` | `void` | `map.put(T key, V value)` | Maps a key to a value. Handles collisions and resizing when load factor exceeds 0.75. |
| `putIfAbsent` | `void` | `map.putIfAbsent(T key, V value)` | Adds only if the key doesn't exist. |
| `addAll` | `void` | `map.addAll(MyHashMap<T, V> other)` | Merges another map into this one. |
| `get` | `V` | `map.get(T key)` | Retrieves value by key using hashing. |
| `getOrDefault` | `V` | `map.getOrDefault(T key, V def)` | Safe retrieval with a default value. |
| `size` | `int` | `map.size()` | Returns total key-value pairs. |
| `isEmpty` | `boolean` | `map.isEmpty()` | Checks if map is empty. |
| `clear` | `void` | `map.clear()` | Clears all buckets. |
| `replace` | `boolean` | `map.replace(T key, V newValue)` | Updates value for a key. |
| `remove` | `void` | `map.remove(T key)` | Removes a key-value pair. |
| `remove` | `void` | `map.remove(T key, V value)` | Removes only if both key and value match. |
| `containsKey` | `boolean` | `map.containsKey(T key)` | Checks if a key exists in any bucket. |
| `containsValue` | `boolean` | `map.containsValue(V value)` | Iterates buckets to find a value. |

## 3. MyHashSet<T>
A Set implementation backed by a custom Hash Table. I learned that a Set is basically a Map where we only care about keys.

| Method | Return Type | Syntax | Description |
| :--- | :--- | :--- | :--- |
| `MyHashSet()` | `void` (Constructor) | `new MyHashSet<T>()` | Initializes the set with default capacity 10. |
| `add` | `void` | `set.add(T ele)` | Adds the specified element to the set if it is not already present. Resizes if load factor > 0.75. |
| `addAll` | `void` | `set.addAll(MyHashSet<T> other)` | Adds all elements from the specified set to this set. |
| `contains` | `boolean` | `set.contains(T ele)` | Returns `true` if the set contains the specified element. |
| `remove` | `void` | `set.remove(T ele)` | Removes the specified element from the set if it is present. |
| `size` | `int` | `set.size()` | Returns the number of elements in the set. |
| `isEmpty` | `boolean` | `set.isEmpty()` | Returns `true` if the set contains no elements. |
| `clear` | `void` | `set.clear()` | Removes all elements from the set. |
| `loadFactor` | `double` | `set.loadFactor()` | Returns the current load factor (length / size). |
