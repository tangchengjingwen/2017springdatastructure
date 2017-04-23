package dataStructure;

public class LinkedListWithlterator {
	private static class Node { // LinkedList.Node
    int val;
	Node next;
	Node(int v, Node n) { val = v; next = n; }
	}
	private Node head;
	public LinkedListWithlterator() {
		head = null;
	}
	
	public void addStart(int v) { // O(1)
		head = new Node(v, head);
	}
	public void removeStart() { //O(1)
    head = head.next;
	}

	public void addEnd(int v) {  //O(n)
		if (head == null) { //O(1)
			head = new Node(v, null);
			return;
		}
		Node p;
		for (p = head; p.next != null; p = p.next) //O(n)
			;
		p.next = new Node(v, null);
	}
  public void removeEnd() {
		if (head == null) { //O(1)
			return;
		}
		if (head.next == null) {
			head = null;
		}
		Node  p;
		Node q;
		for (p = head, q = p.next; q.next != null; p = q, q = q.next) //O(n)
			;

		p.next = null;
	}

	public int get(int i) {
		Node p = head;
		for (int j = 0; j < i; j++)
			p = p.next;
    return p.val;			
	}

	public String toString() {
		// make sure this isn't O(n^2)
		StringBuilder b = new StringBuilder();
		
		for (Node p = head; p != null; p = p.next)
			b.append(p.val).append(' ');
		return b.toString();
	}


	public static class Iterator { // LinkedList.Iterator
		private	Node current;
		public Iterator(LinkedListWithlterator a) {
			current = a.head;
		}
		
		public boolean hasNext() {
			return current != null;
		}

		public Iterator next() {
			current = current . next;
			return this;
		}

		public int getValue() {
			return current.val;
		}

	}
	public static void main(String[] args) {
		LinkedListWithlterator a = new LinkedListWithlterator();
		for (int i = 0; i < 10; i++)
			a.addEnd(i);
		System.out.println(a);
           
//		for (LinkedListWithlterator i = a; i.hasNext(); i.next())
//			System.out.print(i.getValue());


	}
	
}
