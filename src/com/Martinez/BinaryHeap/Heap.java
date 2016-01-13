package com.Martinez.BinaryHeap;

/**
 * Created by alx on 1/11/16.
 */
public interface Heap<T extends Comparable<T>> {
    T peek();
    int size();
    void insert(T element);
    T remove();

}
