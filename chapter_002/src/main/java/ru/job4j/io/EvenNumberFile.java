package ru.job4j.io;

import java.io.FileInputStream;

/**
 * Смотрим четное и не четное число в файле.
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] lines = text.toString().split(System.lineSeparator());
        for (String line : lines) {
            System.out.print(line);
            if (Integer.parseInt(line) % 2 == 0) {
                System.out.println(" - Even");
            } else {
                System.out.println(" - Uneven");
            }
        }
    }
}
