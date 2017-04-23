package Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TrieDictionary {
	private static class Node {
		boolean isWord;
		Node[] next = new Node[26];

		Node() {
			for (int i = 0; i < 26; i++)
				next[i] = null;
			isWord = false;
		}

		public void addChar(char c, Node current) {
			int pos = c - 97;
			if (current.next[pos] != null)
				return;
			current.next[pos] = new Node();
		}
	}

	private Node root;

	public TrieDictionary() {
		root = new Node();
	}

	public void addWord(String s) {
		Node current = root;
		for (int i = 0; i < s.length(); i++) {
			current.addChar(s.charAt(i), current);
			current = current.next[s.charAt(i) - 97];
		}
		current.isWord = true;
	}

	public boolean isWord(String s) {
		Node current = root;
		for (int i = 0; i < s.length(); i++) {
			if (current.next[s.charAt(i) - 97] == null)
				return false;
			current = current.next[s.charAt(i) - 97];
		}
		if (current.isWord)
			return true;
		else
			return false;
	}

	public static void main(String[] args) throws FileNotFoundException {
		TrieDictionary dict = new TrieDictionary();
		File dictFile = new File("dict.txt");
		Scanner c = new Scanner(dictFile);
		while (c.hasNext()) {
			String s = null;
			s = c.next();
			dict.addWord(s);
		}
		c.close();

		File txt = new File("hw5.txt");
		Scanner c2 = new Scanner(txt);
		while (c2.hasNext()) {
			String s2 = c2.next();
			if (dict.isWord(s2))
				System.out.println(true);
			else
				System.out.println(false);
		}
		c2.close();
	}
}
