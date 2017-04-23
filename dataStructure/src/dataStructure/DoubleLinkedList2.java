package dataStructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DoubleLinkedList2 {
	private static class Node { // LinkedList.Node
     int val;
	 Node next;
	 Node prev;
	}
	
	private Node head, tail;
	
	public DoubleLinkedList2() {
		head = null;
		tail = null;
	}
	
	public void addStart(int v) {
		Node temp = new Node();
		if(head == null) {
			temp.val = v;
			temp.next = null;
			temp.prev = null;
			head = temp;
			tail = temp;
		}
		else {
			temp.val = v;
			temp.next = head;
			temp.prev = null;
			head = temp;
		}
	}


	public void addEnd(int v) {
		Node temp = new Node();
		if(tail == null) {
			temp.val = v;
			temp.next = null;
			temp.prev = null;
			head = temp;
			tail = temp;
		}
		else {
			temp.val = v;
			temp.next = null;
			temp.prev = tail;
			tail.next = temp;
			tail = temp;
		}
	}
	public void insert(int i, int v) {
		Node p = head;
		while(i > 0) {
			if(p ==null)
				return;
			p = p.next;
			i--;
		}
		Node temp = new Node();
		temp.val = v;
		temp.next = p.next;
		temp.prev = p;
		p.next = temp;
		temp.next.prev = temp;
	}
	
	public void removeEnd() {
		if(tail == null)
			return;
		tail = tail.prev;
		tail.next = null;
	}
	public void removeStart() {
		if(head ==null)
			return;
		head = head.next;
		head.prev = null;
	}


	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("[");
		for(Node p = head; p !=null; p = p.next)
			b.append(p.val).append(",");
		b.append("]");
		return b.toString();
	}

	public int size() {
		int count = 0;
		for (Node p = head; p != null; p = p.next) //O(n)
			count++;
		return count;
	}

	public int get(int i) {
		Node temp = head;
		for(int j = 0; j < i; j++)
			temp = temp.next;
		return temp.val;
	}

	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("HW4b.txt");
		String command = null,para = null;
		String[] str ;
		DoubleLinkedList2 a = new DoubleLinkedList2();
		int start,step,end,num;
		Scanner s = null;
		s = new Scanner(file);
		while (s.hasNextLine()) {
			command = s.next();
			if(command.equals("OUTPUT")){
			   System.out.println(a);
			   }
				   
			if(command.equals("ADD_FRONT")){
			   para = s.next();
			   str = para.split("\\:");
			   start = Integer.parseInt(str[0]);
			   step = Integer.parseInt(str[1]);
			   end = Integer.parseInt(str[2]);
			   for(int m=start;m<=end;m+=step)
			     a.addStart(m);
			    }
					
		    if(command.equals("ADD_BACK")){
		   	   para = s.next();
			   str = para.split("\\:");
			   start = Integer.parseInt(str[0]);
			   step = Integer.parseInt(str[1]);
			   end = Integer.parseInt(str[2]);

			   for(int m=start;m<=end;m+=step)
				   a.addEnd(m);
			  }
					
		    if(command.equals("REMOVE_FRONT")){
				para = s.next();
				num = Integer.parseInt(para);
				for(int j= 0;j<num;j++)	
					a.removeStart();
				}
			   
		     if(command.equals("REMOVE_BACK")){
				para = s.next();
				num = Integer.parseInt(para);
				for(int j= 0;j<num;j++)	
				 a.removeEnd();
				}
			}
		s.close();
	}
}	