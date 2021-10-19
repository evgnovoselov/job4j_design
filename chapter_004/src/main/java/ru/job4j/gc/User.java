package ru.job4j.gc;

import java.util.Objects;

public class User {
    private String name;
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
        User user = new User("Evgeny", 32);
        User user1 = new User("Julia", 30);
        User user2 = new User("Alexander", 30);
        System.out.println(user);
        System.out.println(user1);
        System.out.println(user2);
        for (int i = 0; i < 2_000; i++) {
            new User("User" + i, i);
        }
    }
}
