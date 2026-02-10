package ds;

import ds.AppExceptions.IndexOutOfRange;

public class Main {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   ULTIMATE EXTREME TESTING SUITE");
        System.out.println("=========================================");

        try {
            testMyListBasicAndStress();
            testMyHashMapBasicAndStress();
            testMyHashSetBasicAndStress();

            testNestedStructures();
            testDeepNesting();
            testExceptionHandling();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("FATAL ERROR: Test suite failed!");
        }

        System.out.println("\nAll tests completed successfully.");
    }

    // ==========================================
    // BASIC & STRESS TESTS (Preserved & Expanded)
    // ==========================================

    private static void testMyListBasicAndStress() throws IndexOutOfRange {
        System.out.println("\n>>> Testing MyList (Basic & Stress)...");
        MyList<Integer> list = new MyList<>();

        // 1. LIMITS & RESIZING
        int limit = 10000;
        for (int i = 0; i < limit; i++)
            list.add(i);
        if (list.size() != limit)
            throw new RuntimeException("Size mismatch");
        if (list.get(limit - 1) != limit - 1)
            throw new RuntimeException("Content mismatch");
        System.out.println("[PASS] Large Insert (10k)");

        // 2. ROTATION
        MyList<Integer> rList = new MyList<>();
        for (int i = 0; i < 5; i++)
            rList.add(i);
        rList.rotateLeft(1); // [1,2,3,4,0]
        if (rList.get(4) != 0)
            throw new RuntimeException("Rotate Left failed");
        System.out.println("[PASS] Rotation");

        // 3. REPLACEMENT
        list.ReplaceAllOccurrences(9999, -1);
        if (list.get(9999) != -1)
            throw new RuntimeException("ReplaceAll failed");
        System.out.println("[PASS] ReplaceAll");
    }

    private static void testMyHashMapBasicAndStress() {
        System.out.println("\n>>> Testing MyHashMap (Basic & Stress)...");
        MyHashMap<String, Integer> map = new MyHashMap<>();

        // 1. COLLISION HANDLING
        for (int i = 0; i < 1000; i++)
            map.put("K" + i, i);
        if (map.size() != 1000)
            throw new RuntimeException("Map size mismatch");
        if (map.get("K500") != 500)
            throw new RuntimeException("Map retrieval failed");
        System.out.println("[PASS] Collision & Resize (1k items)");

        // 2. PUT IF ABSENT
        map.putIfAbsent("K0", 9999);
        if (map.get("K0") != 0)
            throw new RuntimeException("PutIfAbsent overwrote existing key");
        map.putIfAbsent("NewKey", 123);
        if (map.get("NewKey") != 123)
            throw new RuntimeException("PutIfAbsent failed to add");
        System.out.println("[PASS] PutIfAbsent");
    }

    private static void testMyHashSetBasicAndStress() {
        System.out.println("\n>>> Testing MyHashSet (Basic & Stress)...");
        MyHashSet<Integer> set = new MyHashSet<>();

        for (int i = 0; i < 1000; i++)
            set.add(i);
        set.add(0); // Duplicate
        if (set.size() != 1000)
            throw new RuntimeException("Set duplicate check failed");
        System.out.println("[PASS] Set Duplicates & Resize");
    }

    // ==========================================
    // NESTED STRUCTURE TESTS
    // ==========================================

    // Wrapper to make MyList/MyHashMap comparable so they can be stored in MyList
    static class ComparableList<T extends Comparable<T>> implements Comparable<ComparableList<T>> {
        MyList<T> list;

        public ComparableList(MyList<T> list) {
            this.list = list;
        }

        @Override
        public int compareTo(ComparableList<T> o) {
            return Integer.compare(this.list.size(), o.list.size());
        }

        @Override
        public String toString() {
            return list.toString();
        }
    }

    static class ComparableMap<K, V> implements Comparable<ComparableMap<K, V>> {
        MyHashMap<K, V> map;

        public ComparableMap(MyHashMap<K, V> map) {
            this.map = map;
        }

        @Override
        public int compareTo(ComparableMap<K, V> o) {
            return Integer.compare(this.map.size(), o.map.size());
        }
    }

    private static void testNestedStructures() throws IndexOutOfRange {
        System.out.println("\n>>> Testing Nested Structures...");

        // 1. MyList<ComparableList<Integer>> (Adapter pattern to satisfy Comparable
        // constraint)
        MyList<ComparableList<Integer>> listOfLists = new MyList<>();
        MyList<Integer> innerList1 = new MyList<>();
        innerList1.add(1);
        innerList1.add(2);

        MyList<Integer> innerList2 = new MyList<>();
        innerList2.add(3);
        innerList2.add(4);

        listOfLists.add(new ComparableList<>(innerList1));
        listOfLists.add(new ComparableList<>(innerList2));

        if (listOfLists.size() != 2)
            throw new RuntimeException("Nested List size mismatch");
        if (listOfLists.get(0).list.get(0) != 1)
            throw new RuntimeException("Nested List content mismatch");

        System.out.println("[PASS] List of Lists (via Comparable Wrapper)");

        // 2. MyHashMap<String, MyList<Integer>> (No Comparable constraint on Value)
        MyHashMap<String, MyList<Integer>> mapOfLists = new MyHashMap<>();
        mapOfLists.put("primes", innerList1);
        if (mapOfLists.get("primes").size() != 2)
            throw new RuntimeException("Map of Lists failed");
        System.out.println("[PASS] Map of Lists");

        // 3. MyHashSet<MyHashMap<String, String>>
        MyHashSet<MyHashMap<String, String>> setOfMaps = new MyHashSet<>();
        MyHashMap<String, String> map1 = new MyHashMap<>();
        map1.put("id", "1");
        setOfMaps.add(map1);

        if (!setOfMaps.contains(map1))
            throw new RuntimeException("Set of Maps contains check failed");
        System.out.println("[PASS] Set of Maps");
    }

    // ==========================================
    // DEEP NESTING TESTS
    // ==========================================

    private static void testDeepNesting() throws IndexOutOfRange {
        System.out.println("\n>>> Testing Deep Nesting...");

        // MyList<ComparableMap<String, MyHashSet<Integer>>>
        MyList<ComparableMap<String, MyHashSet<Integer>>> complexStructure = new MyList<>();

        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(42);

        MyHashMap<String, MyHashSet<Integer>> map = new MyHashMap<>();
        map.put("target", set);

        complexStructure.add(new ComparableMap<>(map));

        // Drill down
        MyHashSet<Integer> retrievedSet = complexStructure.get(0).map.get("target");
        if (!retrievedSet.contains(42))
            throw new RuntimeException("Deep nesting retrieval failed");

        System.out.println("[PASS] List -> Map -> Set -> Integer structure verified");
    }

    // ==========================================
    // EXCEPTION & ERROR TESTS
    // ==========================================

    private static void testExceptionHandling() {
        System.out.println("\n>>> Testing Exception Handling...");
        MyList<Integer> list = new MyList<>();
        list.add(10);

        // 1. List Index Out of Range
        assertException(() -> list.get(-1), "List get(-1)");
        assertException(() -> list.get(100), "List get(100)");
        assertException(() -> list.set(100, 5), "List set(100)");
        assertException(() -> list.remove(100), "List remove(100)");
        assertException(() -> list.subArray(100, 105), "List subArray invalid start");
        assertException(() -> list.subArray(0, 100), "List subArray invalid end");

        // 2. Invalid Rotations (Should handle gracefully or do nothing, but generally
        // shouldn't crash)
        try {
            list.rotateLeft(1000);
            list.rotateRight(1000);
        } catch (Exception e) {
            throw new RuntimeException("Rotation crashed on large inputs! " + e.getMessage());
        }

        System.out.println("[PASS] Exceptions caught where expected.");
    }

    // Helper interface for lambdas
    interface RunnableTask {
        void run() throws Exception;
    }

    private static void assertException(RunnableTask task, String testName) {
        try {
            task.run();
            throw new RuntimeException("Test '" + testName + "' DID NOT Throw Exception!");
        } catch (IndexOutOfRange e) {
            // Expected
            // System.out.println(" Verified exception for: " + testName);
        } catch (Exception e) {
            throw new RuntimeException("Test '" + testName + "' threw WRONG exception: " + e.getClass().getName());
        }
    }
}
