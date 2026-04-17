package edu.iastate.cs2280.hw3;

import java.util.ListIterator;

public class StoutListMain {
    static void main () {
        // base case
        StoutList<Character> james = new StoutList<Character>();
        james.add('A');
        james.add('B');
        james.add('C');
        james.add('E');
        james.add(3, 'D');
        System.out.println(james.toStringInternal());

        james.add('V');
        System.out.println(james.toStringInternal());

        james.add('W');
        System.out.println(james.toStringInternal());

        james.add(2, 'X');
        System.out.println(james.toStringInternal());

        james.add(2, 'Y');
        System.out.println(james.toStringInternal());

        james.add(2, 'Z');
        System.out.println(james.toStringInternal());

        james.remove(9);
        System.out.println(james.toStringInternal());

        james.remove(3);
        System.out.println(james.toStringInternal());

        james.remove(3);
        System.out.println(james.toStringInternal());

        james.remove(5);
        System.out.println(james.toStringInternal());

        james.remove(3);
        System.out.println(james.toStringInternal());

        james.sort();
        System.out.println(james.toStringInternal());

        james.sortReverse();
        System.out.println(james.toStringInternal());

    }
}