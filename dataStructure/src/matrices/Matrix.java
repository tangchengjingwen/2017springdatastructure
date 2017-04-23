package matrices;

import java.util.*;
import java.io.*;

public class Matrix implements Cloneable {
	private int rows, cols;
	private double m[];
	
	public Matrix() {
		rows = cols = 0;
		m = null;
	}
	public Matrix(int r, int c) {
		rows = r;
		cols = c;
		m = new double[r*c];
	}
	public Matrix clone() {
		Matrix temp = new Matrix(rows, cols);
		for (int i = 0; i < rows*cols; i++)
			temp.m[i] = this.m[i];
		return temp;
	}
  public double get(int i, int j) {
		return m[i*cols+j];
	}
	
  public void set(int i, int j, double v) {
		m[i*cols+j] = v;
	}
	public Matrix add(Matrix b) {
		if (this.rows != b.rows || this.cols != b.cols)
			throw new RuntimeException("Bad size for add");
		Matrix ans = new Matrix(rows, cols);

		for (int i = 0; i < rows * cols; i++)
			ans.m[i] = m[i] + b.m[i];
		return ans;
	}

	public Matrix mult(Matrix b) { //O(rows*cols*b.cols)
		if (cols != b.rows)
			throw new RuntimeException("Bad size");
		Matrix ans = new Matrix(rows, b.cols);  //O(a.rows*b.cols)
		for (int k = 0; k < b.cols; k++) { //O(b.cols)
			for (int j = 0; j < rows; j++) { //O(this.rows)
				double dot = 0;
				for (int i = 0; i < cols; i++) // O(a.cols)
					dot = dot + this.get(j, i) * b.get(i, k); 
				ans.set(j, k, dot);
			}
		}
		return ans;
	}
	public String toString() {
		StringBuilder b = new StringBuilder(rows * cols * 5);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++)
				b.append(this.get(i,j)).append(' ');
			b.append('\n');
		}
		return b.toString();
	}
	public void read(Scanner s) {
		rows = s.nextInt();
		cols = s.nextInt();
		m = new double[rows*cols];
		for (int i = 0; i < rows*cols; i++)
			this.m[i] = s.nextDouble();
	}
	// print out the solution to n variables using Gaussian Partial Pivoting
	void solve() {
		
	}


	public static void main(String[] args ) throws Exception {
		Matrix a = new Matrix(3,4);
		a.set(0,0, 5.5);
		Matrix b = new Matrix(3,4);
		b.set(0,1, 2.5);
		Matrix c = a.add(b);
		System.out.println(c);
		
		Scanner s = new Scanner(new FileReader("solve"));
		c.read(s);
    c.solve();
	}


}