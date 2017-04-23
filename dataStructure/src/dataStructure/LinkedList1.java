package dataStructure;

public class LinkedList1 {
	private static class Node {
		int val;
	    Node next;
	}
	
	private Node head;
 	public LinkedList1() { // O(1)
 		head = null;
	}

	public void addEnd(int v) {
		if (head == null) {
			head = new Node();
			head.val = v;
			head.next = null;
			return;
		}
		Node p;
		for (p = head; p.next != null; p = p.next)  //O(n)
			;
		Node temp = new Node();
		temp.val = v;
		temp.next = null;
		p.next = temp;
	}
	
	public void addStart(int v) { //O(1)
		Node temp = new Node();
		temp.val = v;
		temp.next = head;
		head = temp;
	}
	
	public void insert(int i, int v) {
		Node p = head;
        while (i > 0) {
			if (p == null)
				return;
			p = p.next;
		    i--;
		}
        Node temp = new Node();
		temp.val = v;
		temp.next = p.next; // remember the ones after p!
		p.next = temp;
	}
	
	public void removeEnd() {
		if (head == null)
			return;
		if (head.next == null) {
			head = null;                //		return;
		}
		Node p;
		for (p = head; p.next.next != null; p = p.next)
			;
		p.next = null;
	}
	
	public void removeStart() {
		if (head == null)
			return;
		head = head.next;
	}
	//	public void remove(int i) {}
	//	public int get(int i) {  }
	//	public void set(int i, int v) {}
	public String toString() {
		//		"[1,2,3,4,5,6,7,]"

		//		StringBuffer b = new StringBuffer();//size*6  );
		StringBuilder b = new StringBuilder();//size*6  ); // preallocate size, non-thread-safe
		b.append('[');
		for (Node p = head; p != null; p = p.next) { // O(n)
			b.append(p.val).append(","); //O(n)
		}
		b.append(']');
        return b.toString();
	}

	public int size() { //O(n)
		int count = 0;
		for (Node p = head; p != null; p = p.next)
			count++;
		return count;
	}

	public int get(int i) { // O(n)
		Node p = head;
		for ( ; i > 0; i--, p = p.next)
			if (p == null)
				throw new IndexOutOfBoundsException("LinkedList index " + i);
        return p.val;
	}


	public static void main(String[]args) {
		LinkedList1 a = new LinkedList1();
		for (int i = 0; i < 10; i++)
			a.addStart(i);
		System.out.println(a);
		for (int i = 0; i < 10; i++)
			a.addEnd(i);
		System.out.println(a);		
	}
}	