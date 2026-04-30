package edu.iastate.cs2280.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static edu.iastate.cs2280.hw4.MsgTree.*;

/**
 * @author Ben Goeders
 */

public class Main {
    static void main(String[] args) {
        String fileName;
        try { // attempt to grab filename from command line
            fileName = args[0];
        } catch (ArrayIndexOutOfBoundsException e) { // if there is no argument given on the command line
            System.out.println("Please enter a file name on the command line (e.g. java main.java filename.arch)");
            return;
        }

        Scanner sc;

        try { // attempt to use the filename to open and scan an existing file
            File f = new File(fileName);
            sc = new Scanner(f);
        } catch (FileNotFoundException e) { // if that file does not exist for the given directory
            System.out.println("File " + fileName + " not found. Try again.");
            return;
        }

        String line = sc.nextLine();
        String encodeLine = "";

        while (line.charAt(0) != '0' && line.charAt(0) != '1') { // while the line read in is not part of the binary string
            encodeLine += line + '\n'; // handle newline special character
            line = sc.nextLine();
        }
        String binaryLine = line; // the next line will be the binary string

        sc.close();

        MsgTree m = new MsgTree(encodeLine); // build the tree based on the encoding string
        System.out.println("\nCharacter    Code\n----------------------------");
        printCodes(m, ""); // find characters in the tree and their binary codes from m
        System.out.println("-----------------------------\n");
        decode(m, binaryLine); // use the binary string to decode the MsgTree
    }
}
