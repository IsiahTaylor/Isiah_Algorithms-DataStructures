package edu.metrostate.ics340.p2.it8343;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

	static class HNode implements TreeNode<Character>, Comparable<HNode> {

		private int count;
		private HNode leftChild;
		private HNode rightChild;
		private Character value;

		@Override
		public int compareTo(HNode o) {

			return Integer.compare(count, o.count);
		}

		public int getCount() {
			return count;
		}

		@Override
		public HNode getLeftChild() {
			// TODO Auto-generated method stub
			return leftChild;
		}

		@Override
		public HNode getRightChild() {
			// TODO Auto-generated method stub
			return rightChild;
		}

		@Override
		public Character getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		public void setCount(int input) {
			count = input;
		}

		public void setValue(Character input) {
			value = input;
		}

	}

	public static Huffman build(String filePath) {

		/*
		 * Returns a Huffman coder based on the frequency of characters contained in the
		 * specified file. Precondition: filepath must exist and be accessible.
		 */
		Huffman huff = new Huffman();
		ArrayList<Character> charArray = calculateStats(filePath);

		huff.createHeap(charArray);
		huff.getEncodingMap();
		return huff;
	}

	private static ArrayList<Character> calculateStats(String filePath) {
		// Looks for the file path that is given and prints out the file for now
		File file = new File(filePath);
		char[] txtFile = new char[(int) file.length()];
		int i = 0;

		try (FileReader fr = new FileReader(file)) {
			int content;
			while ((content = fr.read()) != -1) {
				txtFile[i++] = (char) content;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// counting the frequency and putting the result in the array
		ArrayList<Character> charArray = new ArrayList<Character>();
		ArrayList<Integer> freqArray = new ArrayList<Integer>();

		for (i = 0; txtFile.length > i; i++) {
			if (charArray.contains(txtFile[i])) {
				freqArray.set((charArray.indexOf(txtFile[i])), freqArray.get(charArray.indexOf(txtFile[i])) + 1);
			} else {
				charArray.add(txtFile[i]);
				freqArray.add(1);
			}
		}
		return charArray;
	}

	// Now to create the min-max heap
	private static PriorityQueue<HNode> createQueue(ArrayList<Character> charArray) {
		int i;
		PriorityQueue<HNode> q = new PriorityQueue<HNode>();

		for (i = 0; i < charArray.size(); i++) {

			// creating a Huffman node object
			// and add it to the priority queue.
			HNode node = new HNode();

			// Need to add the char and integer to the node information
			// Add char
			// Add Int
			node.setCount(charArray.get(i));
			node.setValue(charArray.get(i));

			// add functions adds
			// the huffman node to the queue.
			q.add(node);
		}
		return q;
	}

	// This node holds encoding tree
	private HNode decodingRoot = null;

	// Map that is used by two methods
	private Map<Character, String> map;

	private Huffman() {
		map = new HashMap<>();
	}

	/*
	 * Create the "Heap". Assign the smallest two values in the que to the left and
	 * right of the current root then move that node into the que then keep going
	 * until there is only one left
	 */
	private void createHeap( ArrayList<Character> charArray) {
		PriorityQueue<HNode> q = createQueue(charArray);

		while (q.size() > 1) {
			HNode left = q.poll();
			HNode right = q.poll();

			HNode c = new HNode();
			c.setCount(left.getCount() + right.getCount());
			c.setValue('-');

			c.leftChild = left;
			c.rightChild = right;

			decodingRoot = c;
			q.add(c);
		}
	}

	String decode(String code) {
		String output = "";
		char[] cha = code.toCharArray();
		/*
		 * code) Returns the decoded text of the given code. Precondition: all codes
		 * must be recognized by the decoder otherwise method throws an
		 * IllegalArgumentException that displays the unrecognized code.
		 */
		HNode cursor = decodingRoot;
		int i = 0;

		while (cha.length > i) {
			if (cha[i] == '1') {
				cursor = cursor.rightChild;
				i++;
			}

			else {
				cursor = cursor.leftChild;
				i++;
			}
			if (cursor.leftChild == null && cursor.rightChild == null) {
				output = output + cursor.value;
				cursor = decodingRoot;
			}
		}
		return output;
	}

	String encode(String text) {
		String output = "";
		char[] cha = text.toCharArray();
		/*
		 * Returns an encoded string of ‘0’s and ‘1’s with the Huffman encoding of the
		 * given text Precondition: all symbols of the given text must have an encoding
		 * from the reference file, otherwise method throws an IllegalArgumenException
		 * that displays the unrecognized character.
		 */
		// How to display charecter?
		for (int i = 0; i < cha.length; i++) {
			if (map.containsKey(cha[i])) {
				output = output + map.get(cha[i]);
			} else {
				// throw new "IllegalArgumenException";
			}

		}
		return output;
	}

	TreeNode<Character> getDecodingTree() {
		/*
		 * Returns the root of the decoding tree for this Huffman coder. Note: the
		 * TreeNode::getValue method should return the Character value of the node
		 */
		return decodingRoot;

	}

	Map<Character, String> getEncodingMap() {
		/* Returns a map of symbols and their Huffman code */
		mapBuilder(decodingRoot, "");
		return map;

	}

	private void mapBuilder(HNode node, String s) {
		if (node.leftChild == null && node.rightChild == null) {
			map.put(node.value, s);
			return;
		} else {
			mapBuilder(node.leftChild, s + "0");
			mapBuilder(node.rightChild, s + "1");
		}
	}

	void setCodeNode(HNode input) {
		decodingRoot = input;
	}
}
