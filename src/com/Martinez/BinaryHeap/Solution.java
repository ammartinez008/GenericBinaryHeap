package com.Martinez.BinaryHeap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	public static BinaryHeap heap;
	public static Scanner s;
	
	public static void main(String[] args) throws Exception {
		s = new Scanner(System.in);
		Solution solution = new Solution();

		String heapType = s.nextLine();
		if(heapType.equals("max-heap")) 
			 solution.initialize(true);
		else
			solution.initialize(false);
		
		 solution.run();
	}

	// create new heap
	private void initialize(Boolean isMaxHeap) {
		heap = new BinaryHeap(isMaxHeap);
	}
	
	private void run() throws IOException {
		int num;

		while(s.hasNextInt()) {
			num = s.nextInt();
			heap.insert(num);
		}
		
		while(heap.size() > 1) {
			num = heap.remove();
			System.out.println(num);
		}
	}

	
	/*******************
	 * Not sure if its possible to send multiple classes
	 * in hackerrank, so i created an inner classes
	 *
	 **********************/
	public class BinaryHeap {

		// creates a min-heap
		private ArrayList<Integer> heap;
		private Boolean isMaxHeap;
		
		public BinaryHeap(Boolean isMaxHeap) {
			this.heap = new ArrayList<Integer>();
			this.heap.add(0);
			this.isMaxHeap = isMaxHeap;
		}
		
		//returns the size of heap
		private int size() {
			if(heap == null)
				return 0;
			
			return heap.size() - 1;
		}
	
		// returns root element
		// does not remove the element
		private int peek() {
			if(heap == null)
				return 0;
			return heap.get(1);
		}
		
		private void insert(int element) {
			heap.add(element);
			if(size() == 0) 
				return;			
			
			//place element at the end of heap
			// find proper spot and insert
			percolateUp(heap.size() - 1, element);
			System.out.println("Heap after insert: " + heap.toString());
		}
		
	
		//properly places element in the heap
		private void percolateUp(int childIndex, int element) {
			int parent = getParent(childIndex);
			int parentIndex = getParentIndex(childIndex);
			
			//check if at root of heap
			if(parentIndex == 0) {
				return;
			}
			
			if(isMaxHeap && element > parent) {
				heap.set(parentIndex, element);
				heap.set(childIndex, parent);
				percolateUp(parentIndex, element);
			}
			else if(!isMaxHeap && element < parent) {
				heap.set(parentIndex, element);
				heap.set(childIndex, parent);
				percolateUp(parentIndex, element);
			}
		}
		
		private int getParentIndex(int childIndex) {
			int parentIndex = childIndex / 2;
			if(parentIndex < 1) 
				return 0;
			
			return parentIndex;
		}
		
		private int getParent(int currentIndex) {
			int parentIndex = getParentIndex(currentIndex);
			
			if(parentIndex < 1) {
				return 0;
			}
			
			return heap.get(parentIndex);
		}
		
		//removes and returns root element
		private int remove() {
			if(heap.size() <= 1)
				return 0;
			
			int head = heap.get(1);
			int tempHead = heap.remove(heap.size() - 1);
			
			if(heap.size() > 1) {
				heap.set(1, tempHead);
				percolateDown(1);
			}
			System.out.println("removed " + head + " heap: " + heap.toString());
			return head;
		}
		
		private void percolateDown(int index) {
			if(isMaxHeap)
				percolateDownMax(index);
			else
				percolateDownMin(index);
		}
		
		private void percolateDownMax(int index) {
			int leftChild = getLeftChild(index);
			int rightChild = getRightChild(index);
			
			//reached the bottom of the heap
			if(leftChild == 0 && rightChild == 0) {
				return;
			}
			//check if the leftChild is empty
			else if(leftChild == 0 && rightChild > heap.get(index)) {
				swap(index, (index*2 + 1));
				percolateDownMax((index*2) + 1);
			}
			
			//check if the right child is empty
			else if(rightChild == 0 && leftChild > heap.get(index)) {
				swap(index, index*2);
				percolateDownMax(index*2);
			}
			else {
				if(leftChild < rightChild) {
					swap(index, (index*2 + 1));
					percolateDownMax((index*2) + 1);
				}
				else {
					swap(index, index*2);
					percolateDownMax(index*2);
				}
			}
			
		}
		
		private void percolateDownMin(int index) {
			int leftChild = getLeftChild(index);
			int rightChild = getRightChild(index);
			
			//reached the bottom of the heap
			if(leftChild == 0 && rightChild == 0) {
				return;
			}
			//check if the leftChild is empty
			else if(leftChild == 0 && rightChild < heap.get(index)) {
				swap(index, (index*2 + 1));
				percolateDownMin((index*2) + 1);
			}
			
			//check if the right child is empty
			else if(rightChild == 0 && leftChild < heap.get(index)) {
				swap(index, index*2);
				percolateDownMin(index*2);
			}
			else {
				if(leftChild > rightChild) {
					swap(index, (index*2 + 1));
					percolateDownMin((index*2) + 1);
				}
				else {
					swap(index, index*2);
					percolateDownMin(index*2);
				}
				
			}
		}
		
		private int getLeftChild(int parentIndex) {
			if(parentIndex * 2 >= size())
				return 0;
			
			return heap.get(parentIndex * 2);
		}
		
		private int getRightChild(int parentIndex) {
			if((parentIndex * 2) + 1 >= size())
				return 0;
			
			return heap.get((parentIndex * 2) + 1);
		}
		
		
		//swap the position of two elements in the heap
		private void swap(int index1, int index2) {
			int holder = heap.get(index1);
			
			heap.set(index1, heap.get(index2));
			heap.set(index2, holder);
		}
	}
}
