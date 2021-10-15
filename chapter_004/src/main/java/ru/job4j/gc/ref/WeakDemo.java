package ru.job4j.gc.ref;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Демонстрация работы слабых ссылок.
 */
public class WeakDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("example1");
        example1();
        System.out.println("example2");
        example2();
        System.out.println("safe");
        safe();
    }

    public static void example1() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Object removed! example1");
            }
        };
        WeakReference<Object> weakReference = new WeakReference<>(object);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(weakReference.get());
    }

    public static void example2() throws InterruptedException {
        List<WeakReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objects.add(new WeakReference<>(new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed! example2");
                }
            }));
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Данный метод демонстрирует правильное получение объекта по мягкой сылкой.
     */
    private static void safe() {
        List<WeakReference<Object>> someData = new ArrayList<>();
        someData.add(new WeakReference<>(new Object()));
        Object strong = someData.get(0).get();
        if (strong != null) {
            System.out.println("safe method, strong != null");
            // do something
        } else {
            System.out.println("safe method, strong == null");
            // do something
        }
        // work with strong
    }
}
