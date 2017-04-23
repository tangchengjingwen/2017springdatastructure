package Tree;

public class BinaryTree {
	private static class Node {
		private int val;
		Node left , right;
		Node(int v) { val = v; left = null; right = null; }
		
		public void inorder(StringBuilder b) {
			if (left != null)
				left.inorder(b);
			b.append(val);
			if (right != null)
				right.inorder(b);
		}
		
		public void preorder(StringBuilder b) {
			b.append(val);
			if(left != null)
				left.preorder(b);
			if(right != null)
				right.preorder(b);
		}
		
		public void postorder(StringBuilder b) {
			 if(left != null)
				 left.postorder(b);
			 if(right != null)
				 right.postorder(b);
			 b.append(val);
		}
		
		public void addRecursive(int v) {
            if (v > val) {
                right.addRecursive(v);
            } else {
                left.addRecursive(v);
            }
        }
	}
	
	private Node root;
	
	public BinaryTree() {
		root = null;
	}
	
	public void add(int v) {
		if(root == null) {
			root = new Node(v);
			return;
		}
		Node p =root;
		while(true) {
			if(v > p.val) {
				if(p.right == null) {
					p.right = new Node(v);
					return;
				}
				p = p.right;
			} 
			else {
				if (p.left == null){
					p.left = new Node(v);
					return;
				}
				p = p.left;
			}
		}
	}
	
	public void inorder(StringBuilder b) {
		if(root == null)
			return;
		root.inorder(b);
	}
	
	public void preorder(StringBuilder b) {
		if(root == null)
			return;
		root.preorder(b);
	}
	
	public void postorder(StringBuilder b) {
		if(root == null)
			return;
		root.postorder(b);
	}
	
	 public void addRecursive(int v) {
		    if (root == null) {
		      root = new Node(v);
		      return;
		    }
		        root.addRecursive(v);
		    }
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		root.inorder(b);
		root.preorder(b);
		root.postorder(b);
		return b.toString();
	}
	
	public static void main (String[] args) {
		BinaryTree b = new BinaryTree();
		b.add(5);
		b.add(3);
		b.add(9);
		b.add(1);
		b.add(4);
		b.add(2);
		b.add(8);
		b.add(11);
		System.out.println(b);
		
	}
}