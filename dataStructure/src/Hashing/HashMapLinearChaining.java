package Hashing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class HashMapLinearChaining {
	private static class Node {
		private String key;
		private int value;
		private Node next;

		public Node(String key, int value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}

	private Node[] table;
	private int used;
	private int[] hist;

	private final int hash(String word) {
		int sum = word.length();
		for (int i = 0; i < word.length(); i++)
			sum = (sum << 13) + (sum << 3) + word.charAt(i) + (sum >> 17);
		return sum & (table.length - 1);
	}

	private void grow() {
		Node[] temp = table;
		table = new Node[temp.length *2];
		for (int i = 0; i < temp.length; i++) {
			Node e = temp[i];
			if(e != null) {
				do {
					Node next = e.next;
					int j = hash(e.key);
					e.next = table[j];
					table[j] = e;
					e = next;
				} while(e != null);
			}
		}
	}

	public HashMapLinearChaining(int initialSize) {
		table = new Node[initialSize];
		used = 0;
		hist = new int[15];
	}

	public void load(String filename) throws FileNotFoundException {
		FileReader r = new FileReader(filename);
		Scanner s = new Scanner(r);
		int count = 0;
		while (s.hasNext()) {
			String word = s.next();
			add(word, count++);
		}
		s.close();
	}

	public void printHist() {
		for (int i = 0; i < 15; i++)
			System.out.println(i + "\t" + hist[i]);
	}

	public void add(String word, int v) {
		if (2 * used >= table.length)
			grow();
		int pos = hash(word);
		int count = 0;
		if (table[pos] == null) {
			table[pos] = new Node(word,v);
			if (count > 14)
				count = 14;
			used++;
			hist[count]++;
		}
		else {
			Node entry = table[pos];
			while(entry.next != null ) {
				entry = entry.next;
				count++;
			}
			entry.next = new  Node(word, v);
			used++;
			if (count > 14)
				count = 14;
			hist[count]++;
		}
	}
	
	public boolean contains(String word) {
		int pos = hash(word);
		while(table[pos] != null) {
			if(table[pos].key.equals(word)) {
				return true;
			}
			table[pos] = table[pos].next;
		}
		return false;
	}

	public static void main(String[] args) throws FileNotFoundException {
		HashMapLinearChaining m = new HashMapLinearChaining(1000);
		m.load("dict.txt");
		m.printHist();
		System.out.println(m.contains("aa"));
		System.out.println(m.contains("aaronitic"));
		System.out.println(m.contains("tangchengjingwen"));
	}

}
