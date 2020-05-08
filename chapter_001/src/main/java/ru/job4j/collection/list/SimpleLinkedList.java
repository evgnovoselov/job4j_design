package ru.job4j.collection.list;

/**
 * Простой связный список.
 *
 * @author Evgeny Novoselov.
 */
public class SimpleLinkedList<E> {
    private Node<E> head;
    private int index = 0;
    private int modCount = 0;

    public void add(E value) {

    }

    public E get(int index) {
        return null;
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
