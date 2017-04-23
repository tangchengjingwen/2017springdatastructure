package backtracking;

public class MagicSquare1 {
	private int rowSum;
	private int[] m;
	private int n2;
	public MagicSquare1(int n) {
		rowSum = n * (n*n+1) / 2;
		m = new int[n*n];
		n2 = n*n;
		//		for (int i = 0; i < m.length; i++)
		//		m[i] = i+1;
	}
	public void generate() {
		for (int a = 1; a <= n2; a++) {  
			int bmax = rowSum - a - 1;
			int bmin = rowSum - a - 9;            //suppose a= 1  rowMIn -1 = 14  c = 9  14-9=5
			for (int b = bmin; b <= bmax; b++) {
				if (a == b)
					continue;
				int c = rowSum - a - b;
        if (c < 1 || c > rowSum)
					continue;
				for (int d = 1; d<= n2; d++) {
					if (d == a || d == b || d == c

				}
				
		for (int c = 1; c < n2; c++)
			m[k] = c;
		generate(k-1);

	}
	public void generate(int k) {
		if (k == 0)
			doit();
		
		for (int c = 1; c < n2; c++)
			m[k] = c;
		generate(k-1);

	}
	

	
}
