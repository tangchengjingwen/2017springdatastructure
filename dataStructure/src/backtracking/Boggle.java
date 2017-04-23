package backtracking;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;


/**
 * 
 * @author chengjingwen tang
 * just make sure the boggle file do not contain spaceline or space, otherwise it can not work!!!!
 *
 */
public class Boggle {
	private TrieDictionary dict ;
	private int size , count;
	private char[][] box ;
	private boolean[][] used ;
	HashMap<String, Integer> hp;
	public Boggle(int size) {
		dict = new TrieDictionary();
	    this.size = size;
	    box = new char[size][size];
	    used = new boolean[size][size];
	    hp = new HashMap<String, Integer>();
	}
	
	private void save(String s) {
		if (hp.containsKey(s) == true)
			return;
		hp.put(s, count++);
	}
	
	private  void boggle(int i, int j, String str) {
		str = str + box[i][j];
		used[i][j] = true;
		
		if(str.length() >= 3 && dict.isWord(str))
			save(str);
		
		if(dict.isPrefix(str)) {
			if (i - 1 >= 0 && j - 1 >= 0 && used[i - 1][j - 1] == false)
			{
				boggle(i - 1, j - 1, str);
				used[i - 1][j - 1] = false;
			}
			if (i - 1 >= 0 && used[i - 1][j] == false) {
				boggle(i - 1, j, str);
				used[i - 1][j] = false;
			}
			if (i - 1 >= 0 && j + 1 < size && used[i - 1][j + 1] == false) {
				boggle(i - 1, j + 1, str);
				used[i - 1][j + 1] = false;
			}
			if (j - 1 >= 0 && used[i][j - 1] == false) {
				boggle(i, j - 1, str);
				used[i][j - 1] = false;
			}
			if (j + 1 < size && used[i][j + 1] == false) {
				boggle(i, j + 1,str);
				used[i][j + 1] = false;
			}
			if (i + 1 < size && j - 1 >= 0 && used[i + 1][j - 1] == false){
				boggle(i + 1, j - 1,str);
				used[i + 1][j - 1] = false;
			}
			if (i + 1 <size && used[i + 1][j] == false){
				boggle(i + 1, j,str);
				used[i + 1][j] = false;
			}
			if (i + 1 < size && j + 1 < size && used[i + 1][j + 1] == false){
				boggle(i + 1, j + 1,str);
				used[i + 1][j + 1] = false;
			}
		}
		else return;
		return;
	}
	
	public void reset()
	{
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				used[i][j] = false;
	}
	
	public static void main(String[] args) throws Exception {
		Boggle boggle = new Boggle(4);
		File dictFile = new File("dict.txt");
		Scanner s  = new Scanner(dictFile);
		while(s.hasNext()) {
			String str = null;
			str = s.next();
			boggle.dict.addWord(str);
		}
		s.close();
		
		File file = new File("boggle");
		Scanner reader = new Scanner(file);
		String line = reader.nextLine();
		int row = 0;
		while(reader.hasNext()){
		    line = reader.nextLine();
			for (int i = 0; i < line.length(); i++){
				boggle.box[row][i] = (char) (line.charAt(i) + 32) ;
			}
			row++;
		}
		reader.close();
	    
		for (int i = 0; i < boggle.size; i++)
			for (int j = 0; j < boggle.size; j++)
			{
				// for every letter, reset the used[], and do boggle game.
				boggle.reset();
				boggle.boggle(i, j,"");
			}
		
         System.out.println(boggle.hp.keySet()); 
        System.out.println("there are " + boggle.count + "word");
		
	}

}

class TrieDictionary {
	private static  class Node {
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

	public boolean isPrefix(String s) {
		Node current = root;
		for (int i = 0; i < s.length(); i++) {
			if (current.next[s.charAt(i) - 97] == null)
				return false;
			current = current.next[s.charAt(i) - 97];
		}
		if (current != null)
			return true;
		else
			return false;
	}
	
	
}
