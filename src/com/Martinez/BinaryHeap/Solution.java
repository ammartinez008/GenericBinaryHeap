package com.Martinez.BinaryHeap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Solution {
	public static BinaryHeap heap;
	public static Scanner scanner;
	
	public static void main(String[] args) throws Exception {
		//initialize scanner to read in from stdin
		scanner = new Scanner(System.in);
		//run heap
		Solution solution = new Solution();
		solution.getHeapType(scanner);
		//solution.getType(scanner);
		solution.runTests();
	}
	
	/*****************************************
	 *  reads first line from stdin
	 *  determines if it is a max or min heap
	 * @param scanner - scanner from stdin
	 ******************************************/
	private void getHeapType(Scanner scanner) {
		String heapType = scanner.nextLine();
		if(heapType.toLowerCase().equals("max-heap")) 
			initialize(true);
		else
			initialize(false);
	}
	
	/**********************************************
	 * creates a new heap
	 * @param isMaxHeap - boolean passed to
	 * 	constructor to determine if its a max-heap
	 **********************************************/
	private void initialize(Boolean isMaxHeap) {
		heap = new BinaryHeap(isMaxHeap);
	}
	
	/********************************************
	 * peek first line of stdin
	 * determine what type of comparable we are dealing with
	 * read the following lines and build heap accordingly 
	 * @param s
	 * @throws IOException
	 ********************************************/
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
	
	/*******************************
	 * build heap with long values
	 * @throws IOException
	 ******************************/
	private void runLong() throws IOException {
		long num;

		while(scanner.hasNextLong()) {
			num = scanner.nextLong();
			heap.insert(num);
		}
		
		while(heap.size() >= 1) {
			num = (long) heap.remove();
			System.out.println(num);
		}
	}
	
	/*******************************
	 * build heap with float values
	 * @throws IOException
	 ******************************/
	private void runFloat() throws IOException {
		float num;

		while(scanner.hasNextFloat()) {
			num = scanner.nextFloat();
			heap.insert(num);
		}
		
		while(heap.size() >= 1) {
			num = (float) heap.remove();
			System.out.println(num);
		}
	}
	
	/*******************************
	 * build heap with double values
	 * @throws IOException
	 ******************************/
	private void runDouble() throws IOException {
		double num;

		while(scanner.hasNextDouble()) {
			num = scanner.nextDouble();
			heap.insert(num);
		}
		
		while(heap.size() >= 1) {
			num = (double) heap.remove();
			System.out.println(num);
		}
	}
	
	/*******************************
	 * build heap with Byte values
	 * @throws IOException
	 ******************************/
	private void runByte() throws IOException {
		Byte num;

		while(scanner.hasNextByte()) {
			num = scanner.nextByte();
			heap.insert(num);
		}
		
		while(heap.size() >= 1) {
			num = (Byte) heap.remove();
			System.out.println(num.toString());
		}
	}
	
	/*******************************
	 * build heap with int values
	 * @throws IOException
	 ******************************/
	private void runInt() throws IOException {
		int num;

		while(scanner.hasNextInt()) {
			num = scanner.nextInt();
			heap.insert(num);
		}
		
		while(heap.size() >= 1) {
			num = (int) heap.remove();
			System.out.println(num);
		}
	}
	
	/*******************************
	 * build heap with String values
	 * @throws IOException
	 ******************************/
	private void runString() throws IOException {
		Comparable num;

		while(scanner.hasNext()) {
			num = scanner.nextLine();
			heap.insert(num);
		}
		
		while(heap.size() >= 1) {
			num = heap.remove();
			System.out.println(num);
		}
	}
	
	/*******************************
	 * used for debugging
	 * @throws IOException
	 ******************************/
	private void runTests() {
		Random rand = new Random();
		int[] data = {84, 72, 66 ,66, 24, 66, 58, 66};
		
		for(int i = 0; i < 30; i ++) {
			int num = rand.nextInt(100 - 1) + 1;
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
	 * BinaryHeap can take in any Comparable obj
	 **********************/
	public class BinaryHeap<T extends Comparable<T>>{

		// instance variables
		private ArrayList<T> heap;
		private Boolean isMaxHeap;
		
		//constructor
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
		
		/*********************************
		 * returns root element
		 * does not remove the element
		 * @return - root element
		 *********************************/
		private T peek() {
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
				
				// check if we should move up a level for max heap
				if(isMaxHeap && element.compareTo(parent) > 0) {
					heap.set(parentIndex, element);
					heap.set(childIndex, parent);
					childIndex = parentIndex;
				}
				
				//check if we should move up a level for min heap
				else if(!isMaxHeap && element.compareTo(parent) < 0) {
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
		
		/*************************************
		 * calls appropriate percolate down
		 * depending on if heap is max or min
		 * @param index - current level of node 
		 * to be adjusted
		 **************************************/
		private void percolateDown(int index) {
			if(isMaxHeap)
				percolateDownMax(index);
			else
				percolateDownMin(index);
		}
		
		/*************************************
		 * - percolate down for max heap
		 * adjusts heap after a remove call
		 * temporary root element gets sent down
		 * to appropriate level
		 ***************************************/
		private void percolateDownMax(int index) {

			while(true) {
				T leftChild = getLeftChild(index);
				T rightChild = getRightChild(index);
				
				//reached the bottom of the heap
				if(leftChild == null && rightChild == null) {
					return;
				}
				
				//dont think this is ever possible
				//but check if we only have a right child
				if(leftChild == null){
					if(rightChild.compareTo(heap.get(index)) > 0){
						swap(index, (index *2) + 1);
					}
					else
						return;
				}
				
				//check if we only have a left child
				else if(rightChild == null) {
					if(leftChild.compareTo(heap.get(index)) > 0) {
						swap(index, (index *2));
					}
					else 
						return;
				}
				//we have two children, compare them and adjust
				else if(leftChild.compareTo(rightChild) > 0 && leftChild.compareTo(heap.get(index)) > 0) {
					swap(index, index*2);
					index *= 2;
				}
				else if(leftChild.compareTo(rightChild) < 0 && rightChild.compareTo(heap.get(index)) > 0){
					swap(index, index*2 +1);
					index = (index * 2) + 1;
				}
				//both children are equal, just move up one and adjust 
				else if(leftChild.compareTo(rightChild) == 0 && leftChild.compareTo(heap.get(index)) > 0) {
					swap(index, index*2);
					index *= 2;
				}
				// return, we have reached the appropriate level for element
				else
					return;
			}
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
}
