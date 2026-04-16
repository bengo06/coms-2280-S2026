package edu.iastate.cs2280.hw3;

import java.util.ListIterator;

public class StoutListMain {
    static void main () {
        StoutList<Integer> james = new StoutList<Integer>();
        System.out.println(james.toStringInternal());
        james.add(4);
        System.out.println(james.toStringInternal());
        james.add(5);
        System.out.println(james.toStringInternal());
        james.add(6);
        System.out.println(james.toStringInternal());
        james.add(7);
        System.out.println(james.toStringInternal());
        james.add(1);
        System.out.println(james.toStringInternal());

        System.out.println(james.contains(0));
        System.out.println(james.contains(1));

        System.out.println(james);

        ListIterator<Integer> jamesI = james.listIterator();

        System.out.println(jamesI.hasNext());
        System.out.println(jamesI.nextIndex());
        System.out.println(jamesI.next());

        System.out.println(james.toStringInternal(jamesI));

        System.out.println(jamesI.hasPrevious());
        System.out.println(jamesI.previousIndex());
        System.out.println(jamesI.previous());

        System.out.println(james.toStringInternal(jamesI));

        while (jamesI.hasNext()) {
            jamesI.next();
            System.out.println(james.toStringInternal(jamesI));
        }

        jamesI.previous();
        System.out.println(james.toStringInternal(jamesI));

        jamesI.set(0);
        System.out.println(james.toStringInternal(jamesI));

        jamesI.previous();
        System.out.println(james.toStringInternal(jamesI));

        jamesI.set(0);
        System.out.println(james.toStringInternal(jamesI));

        jamesI.next();
        System.out.println(james.toStringInternal(jamesI));

        jamesI.set(1);
        System.out.println(james.toStringInternal(jamesI));

        System.out.println(james.get(2));

        james.add(5, 12);
        System.out.println(james.toStringInternal());


    }
}