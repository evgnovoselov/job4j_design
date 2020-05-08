package ru.job4j.collection.list;

import java.util.Objects;

/**
 * Простой связный список.
 *
 * @author Evgeny Novoselov.
 */
public class SimpleLinkedList<E> {
    private Node<E> head;
    private int size = 0;
    private int modCount = 0;

    public void add(E value) {
        modCount++;
        size++;
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<E> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = new Node<>(value, null);
        }
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> resultNode = head;
        for (int i = 0; i < index; i++) {
            resultNode = resultNode.next;
        }
        return resultNode.value;
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
