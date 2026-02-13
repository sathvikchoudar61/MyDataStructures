# My Data Structures Journey

Welcome to my personal exploration of fundamental data structures! This project is a hands-on journey into the internal logic and mechanics of how computer science foundations work under the hood. 

Instead of relying on high-level abstractions, I've implemented these structures from scratch to understand **memory management**, **algorithmic efficiency**, and the **trade-offs** involved in each design.



## Data Structures Implemented

This project covers the implementation and testing of several core data structures: Arrays, Dynamic Lists, Stacks, Queues, Linked Lists, Trees, Binary Search Trees, and Hash Tables.



## Key Concepts Explored

Building these structures from scratch allowed me to dive deep into:

1.  **Dynamic Resizing**: Implementing `upgrade()` logic to handle array capacity changes efficiently.
2.  **Hashing Algorithms**: Managing hash codes and bucket indexing for fast O(1) lookups.
3.  **Collision Handling**: Using **Separate Chaining** with custom linked list nodes.
4.  **BST Balancing**: Implementing a **Self-Balancing strategy** (balance counter) to maintain O(log n) complexity in TreeMaps and TreeSets.
5.  **Generics**: Using Java Generics `<T>` to ensure type safety across all structures.



## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher.

### Running the Tests
The project includes a comprehensive test suite in Main.java that validates the functionality and performance of each data structure.

To run the tests, compile and execute the Main class:

```bash
# Compile the project
javac -d bin src/ds/*.java

# Run the test suite
java -cp bin ds.Main
```

The test suite includes functional tests for each structure, nested data structure tests, and stress tests to evaluate performance under load.

---

## Detailed Feature Overview
This was my implementation of a dynamic array (similar to `ArrayList`). I wanted to understand how resizing works under the hood and how to manage indices manually.

| Method | Return Type | Syntax | Description |
| :--- | :--- | :--- | :--- |
| `MyList()` | `void` (Constructor) | `new MyList<T>()` | Creates a new list instance with a default capacity of 10. |
| `add` | `void` | `list.add(T ele)` | Appends an element. Checks if the array is full and doubles the size if needed. |
| `add` | `void` | `list.add(int idx, T ele)` | Inserts at a specific index, shifting subsequent elements to the right. |
| `addAll` | `void` | `list.addAll(MyList<T> other)` | Bulk adds elements from another list. |
| `set` | `void` | `list.set(int idx, T ele)` | Updates the element at the specified index. |
| `get` | `T` | `list.get(int idx)` | Retrieves the element at the specified index. |
| `contains` | `boolean` | `list.contains(T ele)` | Scans the array to check if an element exists. |
| `size` | `int` | `list.size()` | Returns the current number of elements. |
| `isEmpty` | `boolean` | `list.isEmpty()` | Checks if the list is empty. |
| `clear` | `void` | `list.clear()` | Resets the list to its initial state. |
| `indexOf` | `int` | `list.indexOf(T ele)` | Finds the index of the first occurrence of an element. |
| `lastIndexOf` | `int` | `list.lastIndexOf(T ele)` | Finds the index of the last occurrence of an element. |
| `remove` | `void` | `list.remove(int idx)` | Removes an element at an index and shifts elements to fill the gap. |
| `getFirstElement` | `T` | `list.getFirstElement()` | Helper to get the first item. |
| `getLastElement` | `T` | `list.getLastElement()` | Helper to get the last item. |
| `toArray` | `Object[]` | `list.toArray()` | Converts the list into a standard array. |
| `subArray` | `Object[]` | `list.subArray(int start_idx)` | Creates a slice of the array from start_idx to the end. |
| `subArray` | `Object[]` | `list.subArray(int start, int close)` | Creates a slice with specific start and end points. |
| `ReplaceAllOccurrences` | `void` | `list.ReplaceAllOccurrences(T ele, T val)` | Replaces all instances of a value with a new value. |
| `ValueFreq` | `int` | `list.ValueFreq(T ele)` | Counts how many times an element appears in the list. |
| `indicesOf` | `int[]` | `list.indicesOf(T ele)` | Finds all indices where an element appears. |
| `getMin` | `T` | `list.getMin()` | Finds the smallest element (based on Comparable). |
| `getMax` | `T` | `list.getMax()` | Finds the largest element (based on Comparable). |
| `reverse` | `void` | `list.reverse()` | Reverses the array in-place. |
| `reverseSubArray` | `void` | `list.reverseSubArray(int start, int close)` | Reverses only a portion of the array. |
| `rotateLeft` | `void` | `list.rotateLeft(int rotations)` | Rotates elements to the left by `n` positions. |
| `rotateRight` | `void` | `list.rotateRight(int rotations)` | Rotates elements to the right by `n` positions. |

## 2. MyStack<T>
A Last-In-First-Out (LIFO) data structure implemented using a dynamic array.

| Method | Return Type | Syntax | Description |
| :--- | :--- | :--- | :--- |
| `MyStack()` | `void` (Constructor) | `new MyStack<T>()` | Initializes the stack. |
| `push` | `void` | `stack.push(T ele)` | Pushes an element onto the top of the stack. Resizes if full. |
| `pop` | `T` | `stack.pop()` | Removes and returns the top element of the stack. |
| `size` | `int` | `stack.size()` | Returns the number of elements in the stack. |
| `isEmpty` | `boolean` | `stack.isEmpty()` | Checks if the stack is empty. |
| `clear` | `void` | `stack.clear()` | Clears the stack. |
| `contains` | `boolean` | `stack.contains(T ele)` | Checks if the element exists in the stack. |

## 3. MyQueue<T>
A First-In-First-Out (FIFO) data structure implemented using a dynamic array with `front` and `rear` pointers.

| Method | Return Type | Syntax | Description |
| :--- | :--- | :--- | :--- |
| `MyQueue()` | `void` (Constructor) | `new MyQueue<T>()` | Initializes the queue. |
| `add` | `void` | `queue.add(T ele)` | Adds an element to the rear of the queue. Resizes if needed. |
| `poll` | `T` | `queue.poll()` | Removes and returns the element from the front of the queue. |
| `peekFirst` | `T` | `queue.peekFirst()` | Returns the front element without removing it. |
| `peekLast` | `T` | `queue.peekLast()` | Returns the rear element without removing it. |
| `size` | `int` | `queue.size()` | Returns the number of elements in the queue. |
| `isEmpty` | `boolean` | `queue.isEmpty()` | Checks if the queue is empty. |
| `clear` | `void` | `queue.clear()` | Clears the queue. |
| `contains` | `boolean` | `queue.contains(T ele)` | Checks if the element exists in the queue. |

## 4. MyHashMap<T, V>
Implementation of a Hash Map using separate chaining. This helped me learn how **Linked Lists** work by using them to handle collisions within each bucket.

| Method | Return Type | Syntax | Description |
| :--- | :--- | :--- | :--- |
| `MyHashMap()` | `void` (Constructor) | `new MyHashMap<T, V>()` | Initializes the map with buckets. |
| `put` | `void` | `map.put(T key, V value)` | Maps a key to a value. Handles collisions and resizing when load factor > 0.75. |
| `putIfAbsent` | `void` | `map.putIfAbsent(T key, V value)` | Adds the key-value pair only if the key doesn't already exist. |
| `addAll` | `void` | `map.addAll(MyHashMap<T, V> other)` | Merges another map into this one. |
| `get` | `V` | `map.get(T key)` | Retrieves value by key using hashing. |
| `getOrDefault` | `V` | `map.getOrDefault(T key, V def)` | Safe retrieval with a default value if key is not found. |
| `size` | `int` | `map.size()` | Returns total number of key-value pairs. |
| `isEmpty` | `boolean` | `map.isEmpty()` | Checks if map is empty. |
| `clear` | `void` | `map.clear()` | Clears all buckets. |
| `replace` | `boolean` | `map.replace(T key, V newValue)` | Updates value for a key if it exists. |
| `remove` | `void` | `map.remove(T key)` | Removes a key-value pair. |
| `remove` | `void` | `map.remove(T key, V value)` | Removes only if both key and value match. |
| `containsKey` | `boolean` | `map.containsKey(T key)` | Checks if a key exists. |
| `containsValue` | `boolean` | `map.containsValue(V value)` | Checks if a value exists. |

## 5. MyHashSet<T>
A Set implementation backed by a custom Hash Table.

| Method | Return Type | Syntax | Description |
| :--- | :--- | :--- | :--- |
| `MyHashSet()` | `void` (Constructor) | `new MyHashSet<T>()` | Initializes the set. |
| `add` | `void` | `set.add(T ele)` | Adds the element to the set if it is not already present. |
| `addAll` | `void` | `set.addAll(MyHashSet<T> other)` | Adds all elements from another set. |
| `contains` | `boolean` | `set.contains(T ele)` | Returns `true` if the set contains the element. |
| `remove` | `void` | `set.remove(T ele)` | Removes the element from the set. |
| `size` | `int` | `set.size()` | Returns the number of elements. |
| `isEmpty` | `boolean` | `set.isEmpty()` | Returns `true` if the set has no elements. |
| `clear` | `void` | `set.clear()` | Removes all elements. |
| `loadFactor` | `double` | `set.loadFactor()` | Returns the current load factor. |

## 6. MyTreeMap<T, V>
A Map implementation based on a **Binary Search Tree (BST)**. This implementation helped me understand tree traversal and self-balancing mechanisms (using a balance counter strategy).

| Method | Return Type | Syntax | Description |
| :--- | :--- | :--- | :--- |
| `MyTreeMap()` | `void` (Constructor) | `new MyTreeMap<T, V>()` | Initializes the empty tree map. |
| `put` | `void` | `map.put(T key, V value)` | Inserts a key-value pair, maintaining BST property. Triggers rebalance if needed. |
| `putIfAbsent` | `void` | `map.putIfAbsent(T key, V value)` | Inserts only if existing key is not found. |
| `get` | `V` | `map.get(T key)` | Retrieves the value associated with the key. |
| `get` | `V` | `map.get(T key, V default_val)` | Retrieves value or returns default if not found. |
| `containsKey` | `boolean` | `map.containsKey(T key)` | Checks if the key exists in the tree. |
| `containsValue` | `boolean` | `map.containsValue(V value)` | Traverses the tree to check if a value exists. |
| `replace` | `boolean` | `map.replace(T key, V val)` | Replaces the value for a given key. |
| `remove` | `void` | `map.remove(T key)` | Deletes a node from the BST. |
| `peekFirstKey` | `T` | `map.peekFirstKey()` | Returns the smallest key (leftmost node) without removing it. |
| `peekLastKey` | `T` | `map.peekLastKey()` | Returns the largest key (rightmost node) without removing it. |
| `pollFirstKey` | `void` | `map.pollFirstKey()` | Removes the smallest key from the map. |
| `pollLastKey` | `void` | `map.pollLastKey()` | Removes the largest key from the map. |
| `getCeil` | `T` | `map.getCeil(T key)` | Returns the least key greater than or equal to the given key. |
| `getFloor` | `T` | `map.getFloor(T key)` | Returns the greatest key less than or equal to the given key. |
| `size` | `int` | `map.size()` | Returns the number of nodes. |
| `isEmpty` | `boolean` | `map.isEmpty()` | Checks if the map is empty. |
| `clear` | `void` | `map.clear()` | Clears the map. |

## 7. MyTreeSet<T>
A Set implementation based on a **Binary Search Tree (BST)**, ensuring elements are sorted and unique.

| Method | Return Type | Syntax | Description |
| :--- | :--- | :--- | :--- |
| `MyTreeSet()` | `void` (Constructor) | `new MyTreeSet<T>()` | Initializes the tree set. |
| `add` | `void` | `set.add(T ele)` | Adds an element while maintaining sort order. |
| `contains` | `boolean` | `set.contains(T ele)` | Checks if an element exists. |
| `remove` | `void` | `set.remove(T ele)` | Removes an element from the BST. |
| `peekFirst` | `T` | `set.peekFirst()` | Returns the smallest element. |
| `peekLast` | `T` | `set.peekLast()` | Returns the largest element. |
| `pollFirst` | `void` | `set.pollFirst()` | Removes the smallest element. |
| `pollLast` | `void` | `set.pollLast()` | Removes the largest element. |
| `getCeil` | `T` | `set.getCeil(T ele)` | Finds the ceiling of the given element. |
| `getFloor` | `T` | `set.getFloor(T ele)` | Finds the floor of the given element. |
| `toList` | `MyList<T>` | `set.toList()` | Converts the set to a sorted `MyList`. |
| `toDescendingList` | `MyList<T>` | `set.toDescendingList()` | Converts the set to a reverse-sorted `MyList`. |
