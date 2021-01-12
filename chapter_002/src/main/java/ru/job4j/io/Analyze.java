package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Analyze {
    public void unavailable(String source, String target) {
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("files/unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
