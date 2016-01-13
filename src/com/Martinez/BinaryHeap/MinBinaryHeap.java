package com.Martinez.BinaryHeap;

import java.util.ArrayList;

/**
 * Created by alx on 1/11/16.
 */
public class MinBinaryHeap<T extends Comparable<T>> implements Heap<T> {
    // instance variables
    private ArrayList<T> heap;
    private final HeapType heapType;

    //constructor
    public MinBinaryHeap() {
        this.heap = new ArrayList<T>();
        this.heap.add(null);
        this.heapType = HeapType.MIN;
    }

    //returns the size of heap
    @Override
    public int size() {
        if(heap == null)
            return 0;

        return heap.size() - 1;
    }

    /*********************************
     * returns root element
     * does not remove the element
     * @return - root element
     *********************************/
    @Override
    public T peek() {
        if(heap == null)
            return null;

        return heap.get(1);
    }

    /**********************************
     * inserts element at end of heap
     * percolates up to find appropriate
     * spot in heap
     * @param element - element to be inserted
     ***********************************/
    @Override
    public void insert(T element) {
        if(element == null)
            return;

        heap.add(element);
        if(size() == 0)
            return;

        //place element at the end of heap
        // find proper spot and insert
        percolateUp(heap.size() - 1, element);
    }


    /****************************************
     * places inserted element in proper place
     * @param childIndex - current index of element
     * @param element - element value
     ******************************************/
    private void percolateUp(int childIndex, T element) {
        T parent;
        int parentIndex;

        while(true) {
            parent = getParent(childIndex);
            parentIndex = getParentIndex(childIndex);

            //check if at root of heap
            if(parentIndex == 0 || parent == null) {
                return;
            }

            //check if we should move up a level for min heap
            if(element.compareTo(parent) < 0) {
                heap.set(parentIndex, element);
                heap.set(childIndex, parent);
                childIndex = parentIndex;
            }
            else
                return;
        }

    }

    /***************************************
     * gets parent index
     * @param childIndex - index where child is located
     * @return int value of parent index
     *****************************************/
    private int getParentIndex(int childIndex) {
        int parentIndex = childIndex / 2;
        if(parentIndex < 1)
            return 0;

        return parentIndex;
    }

    /********************************
     * get parent value
     * @param currentIndex - child index
     * @return parent value
     *********************************/
    private T getParent(int currentIndex) {
        int parentIndex = getParentIndex(currentIndex);

        if(parentIndex < 1) {
            return null;
        }

        return heap.get(parentIndex);
    }

    /***********************************
     * removes root element
     * fixes tree order by percolating down
     *
     * @return - root element
     *************************************/
    @Override
    public T remove() {
        if(heap.size() < 1)
            return null;

        T head = heap.get(1);
        T tempHead = heap.remove(heap.size() - 1);

        if(heap.size() > 1) {
            heap.set(1, tempHead);
            percolateDown(1);
        }

        return head;
    }

    /*************************************
     * calls appropriate percolate down
     * depending on if heap is max or min
     * @param index - current level of node
     * to be adjusted
     **************************************/
    private void percolateDown(int index) {
        percolateDownMin(index);
    }

    /*************************************
     * - percolate down for min heap
     * adjusts heap after a remove call
     * temporary root element gets sent down
     * to appropriate level
     ***************************************/
    private void percolateDownMin(int index) {
        while(true) {
            T leftChild = getLeftChild(index);
            T rightChild = getRightChild(index);

            //reached the bottom of the heap
            if(leftChild == null && rightChild == null) {
                return;
            }

            //check if we only have a right child
            // dont think this is possible
            if(leftChild == null){
                if(rightChild.compareTo(heap.get(index)) < 0){
                    swap(index, (index *2) + 1);
                }
                else
                    return;
            }

            //check if we only have a left child
            else if(rightChild == null) {
                if(leftChild.compareTo(heap.get(index)) < 0) {
                    swap(index, (index *2));
                }
                else
                    return;
            }

            // we have two children, so we compare them both and see which one to move up
            else if(leftChild.compareTo(rightChild) < 0 && leftChild.compareTo(heap.get(index)) < 0) {
                swap(index, index*2);
                index *= 2;
            }
            else if(leftChild.compareTo(rightChild) > 0 && rightChild.compareTo(heap.get(index)) < 0){
                swap(index, index*2 +1);
                index = (index * 2) + 1;
            }
            // both children are equal, just move one (left child) and percolate down
            else if(leftChild.compareTo(rightChild) == 0 && leftChild.compareTo(heap.get(index)) < 0) {
                swap(index, index*2);
                index *= 2;
            }
            //return, we have reached the appropriate level for node
            else
                return;
        }
    }

    //gets left child of current node
    private T getLeftChild(int parentIndex) {
        if(parentIndex * 2 > size()) {
            return null;
        }
        return heap.get(parentIndex * 2);
    }

    //gets right child of current node
    private T getRightChild(int parentIndex) {
        if((parentIndex * 2) + 1 > size()) {
            return null;
        }
        return heap.get((parentIndex * 2) + 1);
    }


    //swap the position of two elements in the heap
    private void swap(int index1, int index2) {
        T holder = heap.get(index1);

        heap.set(index1, heap.get(index2));
        heap.set(index2, holder);
    }
}
