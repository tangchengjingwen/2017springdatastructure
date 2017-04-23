package Hashing;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class HashingLinearChaining2 {
	private static class Node {
		private String key;
		private Node next;

		Node(String key) {
			this.key = key;
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
		table = new Node[temp.length * 2];
		for (int i = 0; i < temp.length; i++) {
			Node e = temp[i];
			if(e != null) {
				temp[i] = null;
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

	public HashingLinearChaining2(int initialSize) {
		table = new Node[initialSize];
		used = 0;
		hist = new int[15];
	}

	public void load(String filename) throws FileNotFoundException {
		FileReader r = new FileReader(filename);
		Scanner s = new Scanner(r);
		while (s.hasNext()) {
			String word = s.next();
			add(word);
		}
		s.close();
	}

	public void printHist() {
		for (int i = 0; i < 15; i++)
			System.out.println(i + "\t" + hist[i]);
	}

	public void add(String word) {
		if (3 * used >= table.length)
			grow();
		int pos = hash(word);
		int count = 0;
		if (table[pos] == null) {
			table[pos] = new Node(word);
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
			entry.next = new  Node(word);
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
		HashingLinearChaining2 m = new HashingLinearChaining2(1000);
		m.load("dict.txt");
		m.printHist();
		System.out.println(m.contains("aa"));
	}

}

