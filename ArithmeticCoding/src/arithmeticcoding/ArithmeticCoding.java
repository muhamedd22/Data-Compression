package arithmeticcoding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class ArithmeticCoding {

    public static Vector<Node> myNodes = new Vector<>();
    public static String text;
    public static double lower;
    public static double upper;
    public static double compressedValue;

    public static void readFile(String STR) throws IOException {
        if (STR.equals("compress")) {
            File file = new File("compress.txt");
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
            text = br.readLine();
        } 
        else if (STR.equals("decompress")) {
            File file = new File("compressed value.txt");
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
            String str;
            str = br.readLine();
            compressedValue = Double.valueOf(str);

        }
    }

    public static void writeFile(String STR) throws IOException {
        if (STR.equals("compress")) {
            String fileName = "compressed value.txt";
            String s = Double.toString(compressedValue);
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(s);
            writer.close();
        } else if (STR.equals("decompress")) {
            String fileName = "text.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(text);
            writer.close();
        }
    }

    public static void writeprobability() throws IOException {
        String fileName = "probability.txt";
        String s = "";
        for (int i = 0; i < myNodes.size(); i++) {
            s += myNodes.get(i).symbol + "  " + Double.toString(myNodes.get(i).freq);
            s += '\n';
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(s);
        writer.close();

    }

    public static void printProbibility() {
        System.out.println("Probibilities : ");
        for (int i = 0; i < myNodes.size(); i++) {
            System.out.println(myNodes.get(i).symbol + "  " + Double.toString(myNodes.get(i).freq));
        }
    }

    public static void bubbleSort() {
        for (int i = 0; i < myNodes.size() - 1; i++) {
            for (int j = 0; j < myNodes.size() - i - 1; j++) {
                if (myNodes.get(j).symbol.charAt(0) > myNodes.get(j + 1).symbol.charAt(0)) {
                    String symbol = myNodes.get(j).symbol;
                    myNodes.get(j).symbol = myNodes.get(j + 1).symbol;
                    myNodes.get(j + 1).symbol = symbol;

                    double freq = myNodes.get(j).freq;
                    myNodes.get(j).freq = myNodes.get(j + 1).freq;
                    myNodes.get(j + 1).freq = freq;

                }
            }
        }
    }

    public static void getInput() {
        Vector<String> myChar = new Vector<>();
        double counter = 0.0;
        boolean bool = false;
        for (int i = 0; i < text.length(); i++) {
            String c = text.charAt(i) + "";
            for (int j = 0; j < myChar.size(); j++) {
                if (myChar.get(j).equals(c)) {
                    bool = true;
                }
            }
            if (!bool) {
                myChar.add(c);
                for (int k = 0; k < text.length(); k++) {
                    if (c.equals(text.charAt(k) + "")) {
                        counter++;
                    }
                }
                Node node = new Node();
                node.freq = counter / text.length();
                node.symbol = c;

                myNodes.add(node);
            }
            counter = 0;
            bool = false;
        }
    }

    public static void setRange() {
        myNodes.get(0).lower = 0;
        myNodes.get(0).upper = myNodes.get(0).freq;

        for (int i = 1; i < myNodes.size(); i++) {
            myNodes.get(i).lower = myNodes.get(i - 1).upper;
            myNodes.get(i).upper = myNodes.get(i - 1).upper + myNodes.get(i).freq;
        }
    }

    public static Node search(String key) {
        for (int i = 0; i < myNodes.size(); i++) {
            if (myNodes.get(i).symbol.equals(key)) {
                return myNodes.get(i);
            }
        }
        return null;
    }

    public static void compress() throws IOException {
        getInput();
        bubbleSort();
        setRange();
        writeprobability();
        Node found = search(text.charAt(0) + "");
        lower = found.lower;
        upper = found.upper;
        for (int i = 1; i < text.length(); i++) {
            String symbol = text.charAt(i) + "";
            found = search(symbol);
            if (found != null) {
                double range = upper - lower;
                upper = lower + found.upper * range;
                lower = lower + found.lower * range;
            }
        }// end for 
        compressedValue = (upper + lower) / 2.0;
    }
    

    public static void decompress(int numberOfSymbols) throws IOException {
        text = "";
        for (int i = 0; i < numberOfSymbols; i++) {
            for (int j = 0; j < myNodes.size(); j++) {
                if (compressedValue > myNodes.get(j).lower && compressedValue < myNodes.get(j).upper) {
                    text += myNodes.get(j).symbol;
                    compressedValue = (compressedValue - myNodes.get(j).lower) / (myNodes.get(j).upper - myNodes.get(j).lower);
                    break;
                }
            }
        }
    }

    public static void setprobability(String str) {
        String[] vec = str.split(" ");
        for (int i = 0; i < vec.length; i += 2) {
            Node node = new Node();
            node.freq = Double.valueOf(vec[i + 1]);
            node.symbol = vec[i];
            myNodes.add(node);
        }
        bubbleSort();
        setRange();
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Now Compression...........");
        readFile("compress");
        compress();
        System.out.println("Text : " + text);
        System.out.println("Compressed Value : " + compressedValue);
        printProbibility();
        writeFile("compress");

        System.out.println();

        System.out.println("Now Decompression.........");
        readFile("decompress");
        decompress(text.length());
        writeFile("decompress");
        System.out.println("Text : " + text);

    }
}

