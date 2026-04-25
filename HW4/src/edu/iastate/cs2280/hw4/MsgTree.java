package edu.iastate.cs2280.hw4;

/**
 * @author Ben Goeders
 */

public class MsgTree {

    public char payload;
    public MsgTree left;
    public MsgTree right;
    private static int charIdx = 0;

    public MsgTree(String key) {
        if (charIdx >= key.length()) { return; }

        char ch = key.charAt(charIdx++);
        this.payload = ch;

        if (ch == '^') {
            this.left = new MsgTree(key);
            this.right = new MsgTree(key);
        }
    }

    public MsgTree(char payload) {
        this.payload = payload;
        this.right = null;
        this.left = null;
    }

    public static void decode(MsgTree tree, String msg) {
        MsgTree treeCopy = tree;
        String decoded = "";

        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '0') {
                treeCopy = treeCopy.left;
            }

            if (msg.charAt(i) == '1') {
                treeCopy = treeCopy.right;
            }

            if (treeCopy.payload != '^') {
                decoded += treeCopy.payload;
                treeCopy = tree;
            }
        }

        System.out.println("Message:\n" + decoded + "\n");
    }

    public static void printCodes(MsgTree tree, String code) {
        if (tree == null) { return; }

        if (tree.payload != '^') {
            String pl = Character.toString(tree.payload);
            if (tree.payload == '\n') {
                System.out.println("   \\n        " + code);
            }
            else if (tree.payload == ' ') {
                System.out.println("   space     " + code);
            }
            else {
                System.out.println("   " + tree.payload + "         " + code);
            }
        }

        printCodes(tree.left, code + '0');
        printCodes(tree.right, code + '1');


    }
}
