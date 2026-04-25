package edu.iastate.cs2280.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static edu.iastate.cs2280.hw4.MsgTree.*;

/**
 * @author Ben Goeders
 */

public class Main {
    public static void main(String[] args) {
        String fileName;
        try {
            fileName = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a file name on the command line (e.g. java main.java filename.arch)");
            return;
        }

        Scanner sc;

        try {
            File f = new File(fileName);
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found. Try again.");
            return;
        }

        String line = sc.nextLine();
        String encodeLine = "";

        while (line.charAt(0) != '0' && line.charAt(0) != '1') {
            encodeLine += line + '\n';
            line = sc.nextLine();
        }
        String binaryLine = line;

        sc.close();

        MsgTree m = new MsgTree(encodeLine);
        System.out.println("\nCharacter    Code\n----------------------------");
        printCodes(m, "");
        System.out.println("-----------------------------\n");
        decode(m, binaryLine);

    }
}
