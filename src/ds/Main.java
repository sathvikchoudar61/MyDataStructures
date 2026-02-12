package ds;

import ds.AppExceptions.IndexOutOfRange;

public class Main {

    public static void main(String[] args) {
        try {
            test_list();
            test_stack();
            test_queue();
            test_hashmap();
            test_hashset();
            test_treemap();
            test_treeset();
            test_nested();
            stress_test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ComparableList<T extends Comparable<T>> extends MyList<T> implements Comparable<ComparableList<T>> {
        @Override
        public int compareTo(ComparableList<T> o) {
            return Integer.compare(this.size(), o.size());
        }
    }

    private static void test_list() throws IndexOutOfRange {
        MyList<Integer> l = new MyList<>();
        for (int i = 0; i < 20; i++)
            l.add(i);
        check(l.size() == 20, "size mismatch");
        check(l.get(0) == 0, "index 0 mismatch");

        for (int i = 20; i < 100; i++)
            l.add(i);
        check(l.size() == 100, "resize failed");

        l.add(5, 999);
        check(l.get(5) == 999, "insert failed");
        check(l.size() == 101, "insert size fail");

        l.remove(5);
        check(l.get(5) == 5, "remove failed");
        check(l.contains(50), "contains failed");

        l.reverse();
        check(l.get(0) == 99, "reverse fail");
        l.reverse();
        System.out.println("list passed");
    }

    private static void test_stack() {
        MyStack<String> s = new MyStack<>();
        for (int i = 0; i < 50; i++)
            s.push("item " + i);
        check(s.size() == 50, "stack size fail");

        String p = s.pop();
        check(p.equals("item 49"), "pop fail");

        MyStack<Integer> empty = new MyStack<>();
        check(empty.isEmpty(), "empty check fail");
        System.out.println("stack passed");
    }

    private static void test_queue() {
        MyQueue<Double> q = new MyQueue<>();
        for (int i = 0; i < 50; i++)
            q.add((double) i);
        check(q.size() == 50, "queue size fail");

        Double d = q.poll();
        check(d == 0.0, "poll fail");

        for (int i = 0; i < 1000; i++)
            q.add((double) i);
        while (!q.isEmpty())
            q.poll();
        check(q.size() == 0, "drain fail");
        System.out.println("queue passed");
    }

    private static void test_hashmap() throws IndexOutOfRange {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        check(map.size() == 2, "map size fail");
        check(map.get("two") == 2, "get fail");

        map.put("two", 22);
        check(map.get("two") == 22, "update fail");

        for (int i = 0; i < 100; i++)
            map.put("k" + i, i);
        check(map.size() == 102, "resize fail");

        map.remove("one");
        check(!map.containsKey("one"), "remove fail");
        System.out.println("hashmap passed");
    }

    private static void test_hashset() throws IndexOutOfRange {
        MyHashSet<String> set = new MyHashSet<>();
        set.add("a");
        set.add("b");
        set.add("a");
        check(set.size() == 2, "duplicate check fail");
        check(set.contains("b"), "contains fail");

        for (int i = 0; i < 100; i++)
            set.add("v" + i);
        check(set.contains("v99"), "mass add fail");
        System.out.println("hashset passed");
    }

    private static void test_treemap() throws IndexOutOfRange {
        MyTreeMap<Integer, String> tm = new MyTreeMap<>();
        tm.put(10, "ten");
        tm.put(5, "five");
        tm.put(20, "twenty");

        check(tm.size() == 3, "treemap size fail");
        check(tm.peekFirstKey() == 5, "min key fail");
        check(tm.get(5).equals("five"), "get fail");

        tm.remove(5);
        check(!tm.containsKey(5), "remove fail");
        System.out.println("treemap passed");
    }

    private static void test_treeset() throws IndexOutOfRange {
        MyTreeSet<Integer> ts = new MyTreeSet<>();
        ts.add(50);
        ts.add(25);
        ts.add(75);

        check(ts.contains(25), "treeset contains fail");
        check(ts.peekFirst() == 25, "min fail");
        check(ts.getCeil(28) == 50, "ceil fail");
        System.out.println("treeset passed");
    }

    private static void test_nested() throws IndexOutOfRange {
        MyList<ComparableList<Integer>> lol = new MyList<>();
        for (int i = 0; i < 5; i++) {
            ComparableList<Integer> sub = new ComparableList<>();
            for (int j = 0; j < 5; j++)
                sub.add(j * i);
            lol.add(sub);
        }
        check(lol.get(2).get(3) == 6, "nested list fail");

        MyHashMap<String, MyHashSet<String>> adj = new MyHashMap<>();
        MyHashSet<String> fa = new MyHashSet<>();
        fa.add("bob");
        adj.put("alice", fa);
        check(adj.get("alice").contains("bob"), "nested map fail");

        MyStack<MyQueue<Integer>> soq = new MyStack<>();
        MyQueue<Integer> q1 = new MyQueue<>();
        q1.add(1);
        soq.push(q1);
        check(soq.pop().poll() == 1, "nested stack fail");
        System.out.println("nested passed");
    }

    private static void stress_test() throws IndexOutOfRange {
        int lim = 100000;
        long t = System.currentTimeMillis();
        MyList<Integer> l = new MyList<>();
        for (int i = 0; i < lim; i++)
            l.add(i);
        System.out.println("list stress: " + (System.currentTimeMillis() - t) + "ms");

        t = System.currentTimeMillis();
        MyHashMap<Integer, Integer> m = new MyHashMap<>();
        for (int i = 0; i < lim; i++)
            m.put(i, i * 2);
        System.out.println("map stress: " + (System.currentTimeMillis() - t) + "ms");

        t = System.currentTimeMillis();
        MyTreeSet<Integer> ts = new MyTreeSet<>();
        for (int i = 0; i < 20000; i++)
            ts.add(i);
        System.out.println("tree stress: " + (System.currentTimeMillis() - t) + "ms");
    }

    private static void check(boolean cond, String msg) {
        if (!cond)
            throw new RuntimeException(msg);
    }
}
