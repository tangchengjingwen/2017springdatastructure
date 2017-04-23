package dataStructure;


public class DoubleLinkedList {
	 class Link {
		 private int data;
		 public Link previous;
		 public Link next;
		 
		 public Link(int data) {
			 Link previous = null;
			 Link next = null;
		 }
		 
		 public Link(Link previous, int data, Link next) {
			 this.previous = previous;
			 this.data = data;
			 this.next = next;
		 }
	}
	 
	 private Link head;
	 private int size;
	 
	 public  DoubleLinkedList() {
		 head = null;
		 size = 0;
	 }
	 
	 public void addStart (int data) {
		 if(head == null)
			 head = new Link(null, data, null);
		 else {
			 Link newLink = new Link(null, data, head);
			 head.previous = newLink;
			 head = newLink;
		 }
		 size++;
	 }
	 
	 public void addEnd (int data) {
		 if(head == null) 
			 head = new Link(null, data, null);
		 else {
			 Link current = head;
			 while(current.next != null) {
				 current = current.next;
			 }
			 Link  newLink = new Link(current, data, null);
			 current.next = newLink;
		 }
		 size++;
	 }
	 
	 public void removeStart() {
		 if(head == null)
			 return;
		 head = head.next;
		 head.previous = null;
		 size--;
	 }
	 
	 public void removeEnd() {
		 if(head == null)
			 return;
		 if(head.next == null) {
			 head =null;
			 size--;
			 return;
		 }
		 Link current =head;
		 while(current.next.next != null) {
			 current = current.next;
		 }
		 current.next = null;
		 size--;
	 }
	 
	 public void insert(int data, int index) {
		 if(head == null)
			 return;
		 if(index < 1 || index > size)
			 return;
		 
		 Link current = head;
		 int i = 1;
		 while (i < index) {
			 current = current.next;
			 i++;
		 }
		 if(current.previous == null) {
			 Link newLink = new Link(null, data, current);
			 current.previous = newLink;
			 head = newLink;
		 }
		 else{
			 Link newLink = new Link(current.previous, data, current);
			 current.previous.next = newLink;
			 current.previous = newLink;
		 }
		 size++;
	 }
	 
	 public void removeAt(int index) {
		 if(head == null)
			 return;
		 if(index < 1 || index > size)
			 return;
		 
		 Link current = head;
		 int i = 1;
		 while(i < index) {
			 current = current.next;
			 i++;
		 }
		 if(current.next == null){
			 current.previous.next = null;
		 }
		 else if(current.previous == null) {
			 current = current.next;
			 current.previous = null;
			 head = current;
		 }
		 else {
			 current.previous.next = current.next;
			 current.next.previous = current.previous;
		 }
		 size--;
	 }
	 
	 public String toString() {
			StringBuilder b = new StringBuilder();//size*6  ); // preallocate size, non-thread-safe
			b.append('[');
			for (Link p = head; p != null; p = p.next) { // O(n)
				b.append(p.data).append(","); //O(n)
			}
			b.append(']');
	    return b.toString();
		}
	 
	 public int size() {
		 return size;
	 }
	 

	 public boolean isEmpty() {
		 return head == null;
	 }
	 
	 public static void main(String[] args){
		 DoubleLinkedList dll = new DoubleLinkedList();
		 for (int i = 0; i < 10; i++)
			dll.addStart(i);
	   	 System.out.println(dll);
		 for (int i = 0; i < 10; i++)
		   dll.addEnd(i);
		 dll.removeStart();
		 System.out.println(dll);
		 dll.removeEnd();
		 System.out.println(dll);
		 dll.insert(190, 2);
		 System.out.println(dll);
    	 dll.removeAt(2);
		 System.out.println(dll);
	 }	 
}
