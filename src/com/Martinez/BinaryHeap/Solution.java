package com.Martinez.BinaryHeap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Solution {
	public static BinaryHeap heap;
	public static Scanner s;
	
	public static void main(String[] args) throws Exception {
		s = new Scanner(System.in);
		Solution solution = new Solution();
		solution.getHeapType(s);
		//solution.getType(s);
		
		 solution.runTests();
	}
	
	private void getHeapType(Scanner s) {
		String heapType = s.nextLine();
		if(heapType.toLowerCase().equals("max-heap")) 
			initialize(true);
		else
			initialize(false);
	}
	
	// create new heap
	private void initialize(Boolean isMaxHeap) {
		heap = new BinaryHeap(isMaxHeap);
	}
	
	private void getType(Scanner s) throws IOException {
		if(s.hasNextLong()) {
			runLong();
		}
		if(s.hasNextInt()) {
			runInt();
		} 
		else if(s.hasNextByte()) {
			runByte();
		}
		else if(s.hasNextDouble()) {
			runDouble();
		}
		else if(s.hasNextFloat()) {
			runFloat();
		}
		else if(s.hasNextLine()) {
			runString();
		}
	
	}
	
	private void runLong() throws IOException {
		long num;

		while(s.hasNextLong()) {
			num = s.nextLong();
			heap.insert(num);
		}
		
		while(heap.size() >= 1) {
			num = (long) heap.remove();
			System.out.println(num);
		}
	}
	
	private void runFloat() throws IOException {
		float num;

		while(s.hasNextFloat()) {
			num = s.nextFloat();
			heap.insert(num);
		}
		
		while(heap.size() >= 1) {
			num = (float) heap.remove();
			System.out.println(num);
		}
	}
	
	private void runDouble() throws IOException {
		double num;

		while(s.hasNextDouble()) {
			num = s.nextDouble();
			heap.insert(num);
		}
		
		while(heap.size() >= 1) {
			num = (double) heap.remove();
			System.out.println(num);
		}
	}
	
	private void runByte() throws IOException {
		Byte num;

		while(s.hasNextByte()) {
			num = s.nextByte();
			heap.insert(num);
		}
		
		while(heap.size() >= 1) {
			num = (Byte) heap.remove();
			System.out.println(num.toString());
		}
	}
	
	private void runInt() throws IOException {
		int num;

		while(s.hasNextInt()) {
			num = s.nextInt();
			heap.insert(num);
		}
		
		while(heap.size() >= 1) {
			num = (int) heap.remove();
			System.out.println(num);
		}
	}
	
	private void runString() throws IOException {
		Comparable num;

		while(s.hasNext()) {
			num = s.nextLine();
			heap.insert(num);
		}
		
		while(heap.size() >= 1) {
			num = heap.remove();
			System.out.println(num);
		}
	}
	
	private void runTests() {
		Random rand = new Random();
		int[] data = {84, 72, 66 ,66, 24, 66, 58, 66};
		
		for(int i = 0; i < 7; i ++) {
			int num = data[i];
			heap.insert(num);
		}
		System.out.println(heap.heap.toString());
		
		
		while(heap.size() >= 1) {
			int num = (int) heap.remove();
			System.out.println(num);
		}
		
	}
	
	/*******************
	 * Not sure if its possible to send multiple classes
	 * in hackerrank, so i created an inner classes
	 *
	 **********************/
	public class BinaryHeap<T extends Comparable<T>>{

		// creates a min-heap
		private ArrayList<T> heap;
		private Boolean isMaxHeap;
		
		public BinaryHeap(Boolean isMaxHeap) {
			this.heap = new ArrayList<T>();
			this.heap.add(null);
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
		private T peek() {
			if(heap == null)
				return null;
			
			return heap.get(1);
		}
		
		private void insert(T element) {
			if(element == null)
				return;
			
			heap.add(element);
			if(size() == 0) 
				return;			
			
			//place element at the end of heap
			// find proper spot and insert
			percolateUp(heap.size() - 1, element);
		}
		
	
		//properly places element in the heap
		private void percolateUp(int childIndex, T element) {
			T parent = getParent(childIndex);
			int parentIndex = getParentIndex(childIndex);
			
			//check if at root of heap
			if(parentIndex == 0 || parent == null) {
				return;
			}
			
			if(isMaxHeap && element.compareTo(parent) > 0) {
				heap.set(parentIndex, element);
				heap.set(childIndex, parent);
				percolateUp(parentIndex, element);
			}
			else if(!isMaxHeap && element.compareTo(parent) < 0) {
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
		
		private T getParent(int currentIndex) {
			int parentIndex = getParentIndex(currentIndex);
			
			if(parentIndex < 1) {
				return null;
			}
			
			return heap.get(parentIndex);
		}
		
		//removes and returns root element
		private T remove() {
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
		
		private void percolateDown(int index) {
			if(isMaxHeap)
				percolateD(index);
			else
				percolateDownMin(index);
		}
		
		
		private void percolateD(int index) {

			while(true) {
				T leftChild = getLeftChild(index);
				T rightChild = getRightChild(index);
				
				//reached the bottom of the heap
				if(leftChild == null && rightChild == null) {
					return;
				}
				
				if(leftChild == null){
					if(rightChild.compareTo(heap.get(index)) > 0){
						swap(index, (index *2) + 1);
					}
					else
						return;
				}
				else if(rightChild == null) {
					if(leftChild.compareTo(heap.get(index)) > 0) {
						swap(index, (index *2));
					}
					else 
						return;
				}
							
				else if(leftChild.compareTo(rightChild) > 0 && leftChild.compareTo(heap.get(index)) > 0) {
					swap(index, index*2);
					index *= 2;
				}
				else if(leftChild.compareTo(rightChild) < 0 && rightChild.compareTo(heap.get(index)) > 0){
					swap(index, index*2 +1);
					index = (index * 2) + 1;
				}
				else if(leftChild.compareTo(rightChild) == 0 && leftChild.compareTo(heap.get(index)) > 0) {
					swap(index, index*2);
					index *= 2;
				}
				else
					return;
			}
		}
		
		private void percolateDownMin(int index) {
			while(true) {
				T leftChild = getLeftChild(index);
				T rightChild = getRightChild(index);
				
				//reached the bottom of the heap
				if(leftChild == null && rightChild == null) {
					return;
				}
				
				if(leftChild == null){
					if(rightChild.compareTo(heap.get(index)) < 0){
						swap(index, (index *2) + 1);
					}
					else
						return;
				}
				else if(rightChild == null) {
					if(leftChild.compareTo(heap.get(index)) < 0) {
						swap(index, (index *2));
					}
					else 
						return;
				}
							
				else if(leftChild.compareTo(rightChild) < 0 && leftChild.compareTo(heap.get(index)) < 0) {
					swap(index, index*2);
					index *= 2;
				}
				else if(leftChild.compareTo(rightChild) > 0 && rightChild.compareTo(heap.get(index)) < 0){
					swap(index, index*2 +1);
					index = (index * 2) + 1;
				}
				else if(leftChild.compareTo(rightChild) == 0 && leftChild.compareTo(heap.get(index)) < 0) {
					swap(index, index*2);
					index *= 2;
				}
				else
					return;
			}
		}
		
		private T getLeftChild(int parentIndex) {
			if(parentIndex * 2 > size()) {
				return null;
			}
			return heap.get(parentIndex * 2);
		}
		
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
}
