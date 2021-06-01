package ru.job4j.io.find;

/**
 * Класс ищет файл в заданном каталоге и подкаталогах.
 * Ключи
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 */
public class Find {
    public static void main(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Error argument count.");
        }
    }
}
