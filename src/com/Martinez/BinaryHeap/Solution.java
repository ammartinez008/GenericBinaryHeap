package com.Martinez.BinaryHeap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Solution {
	public static Heap heap;
	public static Scanner scanner;
	
	public static void main(String[] args) throws Exception {
		Solution solution = new Solution();
		MaxBinaryHeap heap = new MaxBinaryHeap();
		solution.runStringTest(heap);

	}
	



	/*******************************
	 * used for debugging
	 * @throws IOException
	 ******************************/
	private void runTests(Heap binaryHeap) {
		Random rand = new Random();
		int[] data = {84, 72, 66 ,66, 24, 66, 58, 66};
		
		for(int i = 0; i < 30; i ++) {
			int num = rand.nextInt(100 - 1) + 1;
			//int num = data[i];
			binaryHeap.insert(num);
		}
		
		while(binaryHeap.size() > 1) {
			Object num = binaryHeap.remove();
			System.out.println(num);
		}
		
	}

	/*******************************
	 * used for debugging
	 * @throws IOException
	 ******************************/
	private void runCharTest(Heap binaryHeap) {
		Random rand = new Random();
		char[] data = {'a','d','b','j','f'};

		for(int i = 0; i < data.length; i ++) {
			//int num = rand.nextInt(100 - 1) + 1;
			char character = data[i];
			binaryHeap.insert(character);
		}

		while(binaryHeap.size() > 1) {
			Object num = binaryHeap.remove();
			System.out.println(num);
		}

	}

	private void runStringTest(Heap binaryHeap) {
		String[] data = {"first","second", "third", "fourth", "fifth", "six", "seven", "eigth"};

		for(int i = 0; i < data.length; i ++) {
			String word = data[i];
			binaryHeap.insert(word);
		}

		while(binaryHeap.size() > 1) {
			Object num = binaryHeap.remove();
			System.out.println(num);
		}
	}

}
