package ru.job4j.gc;

import java.util.Objects;

public class User {
    /*
    Заголовок: 16 byte (32bit = 8 byte, 64bit = 16 byte)
    Поля int: 4 byte * 3 = 12 byte
    Ссылочная переменная на объект массива: 8 byte (32bit = 4 byte, 64bit = 8 byte)
    Итого, без new char[]: 36 byte (в размер не входит информация о массиве new char[] где хранятся данные)
    */
    private String name;

    /*
    Поле int: 4 byte
    */
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return String.format("User{name='%s', age='%s'}", name, age);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %s%n", this);
    }

    public static void main(String[] args) {
        /*
        Ниже экземпляр класса User занимает памяти:
        Заголовок: 16 byte
        Поле String {
            Поля String без new char[] расчитал в объявлении класса: 36 byte
            Поле new char[6] {
                Заголовок: 16 byte + 4 byte на длину массива == 20 byte
                Примитивы char: 2 byte * 6 == 12 byte
                Выравнивание для кратности 16: 0 byte
                Итого: 32 byte
            }: 32 byte
        }: 36 byte + 32 byte = 68 byte
        Поле int: 4 byte
        Выравнивание для кратности 16: 8 byte
        Итого: 96 byte
         */
        User user = new User("Evgeny", 32);

        /*
        Ниже экземпляр класса User занимает памяти:
        Заголовок: 16 byte
        Поле String {
            Поля String без new char[] расчитал в объявлении класса: 36 byte
            Поле new char[5] {
                Заголовок: 16 byte + 4 byte на длину массива == 20 byte
                Примитивы char: 2 byte * 5 == 10 byte
                Выравнивание для кратности 16: 2 byte
                Итого: 32 byte
            }: 32 byte
        }: 36 byte + 32 byte = 68 byte
        Поле int: 4 byte
        Выравнивание для кратности 16: 8 byte
        Итого: 96 byte
         */
        User user1 = new User("Julia", 30);

        /*
        Ниже экземпляр класса User занимает памяти:
        Заголовок: 16 byte
        Поле String {
            Поля String без new char[] расчитал в объявлении класса: 36 byte
            Поле new char[9] {
                Заголовок: 16 byte + 4 byte на длину массива == 20 byte
                Примитивы char: 2 byte * 9 == 18 byte
                Выравнивание для кратности 16: 10 byte
                Итого: 48 byte
            }: 48 byte
        }: 36 byte + 48 byte = 84 byte
        Поле int: 4 byte
        Выравнивание для кратности 16: 8 byte
        Итого: 112 byte
         */
        User user2 = new User("Alexander", 30);
        System.out.println(user);
        System.out.println(user1);
        System.out.println(user2);
    }
}
