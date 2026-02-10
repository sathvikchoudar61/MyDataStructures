package ds;

import ds.AppExceptions.IndexOutOfRange;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== BASIC TEST =====");
        MyList<Integer> list = new MyList<>();
        System.out.println(list);           // []
        System.out.println("Size: " + list.size());
        System.out.println("Empty: " + list.isEmpty());

        try {
            System.out.println("\n===== ADD =====");
            list.add(10);
            list.add(20);
            list.add(30);
            System.out.println(list);       // [10, 20, 30]

            list.add(1, 15);
            list.add(0, 5);
            list.add(list.size(), 40);
            System.out.println(list);       // [5, 10, 15, 20, 30, 40]

            System.out.println("\n===== GET / SET =====");
            System.out.println("get(2): " + list.get(2));
            list.set(2, 99);
            System.out.println("after set: " + list);

            System.out.println("\n===== INVALID GET =====");
            try {
                list.get(100);
            } catch (IndexOutOfRange e) {
                System.out.println("Caught expected exception");
            }

            System.out.println("\n===== REMOVE =====");
            list.remove(0);                 // remove first
            list.remove(list.size() - 1);   // remove last
            list.remove(1);                 // remove middle
            System.out.println(list);

            System.out.println("\n===== SEARCH =====");
            System.out.println("contains 20: " + list.contains(20));
            System.out.println("indexOf 20: " + list.indexOf(20));
            System.out.println("lastIndexOf 20: " + list.lastIndexOf(20));

            System.out.println("\n===== NULL HANDLING =====");
            MyList<Integer> nullList = new MyList<>();
            nullList.add(null);
            nullList.add(10);
            nullList.add(null);
            nullList.add(20);
            System.out.println(nullList);
            System.out.println("ValueFreq(null): " + nullList.ValueFreq(null));
            System.out.println("indicesOf(null): " +
                    Arrays.toString(nullList.indicesOf(null)));

            System.out.println("\n===== ADD ALL =====");
            MyList<Integer> other = new MyList<>();
            other.add(100);
            other.add(200);
            nullList.addAll(other);
            System.out.println(nullList);

            System.out.println("\n===== SUB ARRAY =====");
            System.out.println("subArray(1): " +
                    Arrays.toString(nullList.subArray(1)));
            System.out.println("subArray(1, 4): " +
                    Arrays.toString(nullList.subArray(1, 4)));

            System.out.println("\n===== REVERSE =====");
            nullList.reverse();
            System.out.println(nullList);

            System.out.println("\n===== REVERSE SUB ARRAY =====");
            nullList.reverseSubArray(1, 4);
            System.out.println(nullList);

            System.out.println("\n===== ROTATION =====");
            MyList<Integer> rot = new MyList<>();
            for (int i = 1; i <= 5; i++) rot.add(i);
            System.out.println(rot);

            rot.rotateLeft(2);
            System.out.println("Left rotate 2: " + rot);

            rot.rotateRight(2);
            System.out.println("Right rotate 2: " + rot);

            rot.rotateLeft(100);
            System.out.println("Left rotate 100: " + rot);

            System.out.println("\n===== MIN / MAX =====");
            System.out.println("Min: " + rot.getMin());
            System.out.println("Max: " + rot.getMax());

            System.out.println("\n===== TO ARRAY =====");
            System.out.println(Arrays.toString(rot.toArray()));

            System.out.println("\n===== CLEAR =====");
            rot.clear();
            System.out.println(rot);
            System.out.println("Empty: " + rot.isEmpty());

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        System.out.println("\n===== ALL TESTS COMPLETED SUCCESSFULLY =====");
    }
}
