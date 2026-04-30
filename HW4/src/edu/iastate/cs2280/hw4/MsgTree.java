package edu.iastate.cs2280.hw4;

/**
 * @author Ben Goeders
 */

public class MsgTree {

    public char payload; // value of a given node
    public MsgTree left; // left node in the MsgTree
    public MsgTree right; // right node in the MsgTree
    private static int charIdx = 0; // used to keep track of the position in string to build the tree

    /**
     * Recursively builds a message tree based
     * on the encoding string read from a file
     *
     * @param key Encoding string read in from file
     */
    public MsgTree(String key) {
        if (charIdx >= key.length()) { return; }

        char ch = key.charAt(charIdx++);
        this.payload = ch;

        if (ch == '^') {
            this.left = new MsgTree(key);
            this.right = new MsgTree(key);
        }
    }

    /**
     * Builds a single node of the MsgTree,
     * has no right or left
     *
     * @param payload char value of node
     */
    public MsgTree(char payload) {
        this.payload = payload;
        this.right = null;
        this.left = null;
    }

    /**
     * Decodes the message tree based on the binary
     * path string from the file. A 0 from the string
     * represents left and 1 represents right
     *
     * @param tree MsgTree built from encoding string
     * @param msg binary path string from file
     */
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

    /**
     * Searches MsgTree and finds the binary path to each character.
     * 0 represents left, 1 represents right
     *
     * @param tree MsgTree built from encoding string
     * @param code empty string to build path for each character
     */
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
