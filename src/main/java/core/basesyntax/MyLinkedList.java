package core.basesyntax;

import java.util.List;
import java.util.Objects;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private static final int IS_EMPTY = 0;
    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T value) {
        if (size == IS_EMPTY) {
            Node<T> node = new Node<>(null, value, null);
            head = node;
            tail = node;
            size++;
        } else {
            Node<T> node = new Node<>(null, value, null);
            tail.next = node;
            node.prev = tail;
            tail = node;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (size == IS_EMPTY) {
            Node<T> node = new Node<>(null, value, null);
            head = node;
            tail = node;
            size++;
        } else if (index == 0) {
            Node<T> node = new Node<>(null, value, null);
            head.prev = node;
            node.next = head;
            head = node;
            size++;
        } else if (index == size) {
            Node<T> node = new Node<>(null, value, null);
            tail.next = node;
            node.prev = tail;
            tail = node;
            size++;
        } else {
            Node<T> node = new Node<>(null, value, null);
            Node<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            node.prev = currentNode.prev;
            currentNode.prev.next = node;
            currentNode.prev = node;
            node.next = currentNode;
            size++;
        }

    }

    @Override
    public void addAll(List<T> list) {
        for (T value : list) {
            add(value);
        }
    }

    @Override
    public T get(int index) {
        indexValidation(index);
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.value;
    }

    @Override
    public T set(T value, int index) {
        indexValidation(index);
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        T oldValue = currentNode.value;
        currentNode.value = value;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        if (size == 1) {
            final T removedValue = head.value;
            head = null;
            tail = null;
            size--;
            return removedValue;
        } else if (index == 0) {
            final T removedValue = head.value;
            head = head.next;
            head.prev = null;
            size--;
            return removedValue;
        } else if (index == (size - 1)) {
            final T removedValue = tail.value;
            tail = tail.prev;
            tail.next = null;
            size--;
            return removedValue;
        } else {
            Node<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            final T removedValue = currentNode.value;
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
            size--;
            return removedValue;
        }
    }

    @Override
    public boolean remove(T object) {
        Node<T> currentNode = head;
        while (currentNode != tail.next) {
            if (Objects.equals(currentNode.value, object)) {
                break;
            }
            currentNode = currentNode.next;
        }
        if (currentNode == null) {
            return false;
        }
        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return true;
        } else if (currentNode == head) {
            head = head.next;
            head.prev = null;
            size--;
            return true;
        } else if (currentNode == tail) {
            tail = tail.prev;
            tail.next = null;
            size--;
            return true;
        } else {
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
            size--;
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexValidation(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        Node(Node<T> prev, T value, Node<T> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

    }

}
