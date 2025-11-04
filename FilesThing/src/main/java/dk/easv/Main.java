package dk.easv;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.print("Hello \norbert World!\r\n");
        System.out.print("\tJeppe");

        String file = Main.class.getResource("cat.txt").getFile();
        /*
        System.out.println("\t" + file);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while (reader.ready()) {
            System.out.println(reader.readLine());
        }
*/
        List<String> strings = Files.lines(Paths.get(file)).toList();
        for(String s : strings) {
            System.out.println(s);
        }

        for (int i = 0; i < 255; i++) {
            System.out.println("Ascii: " + i  + "  char: " + (char)i);
            //Thread.sleep(2000);
        }


    }
}