package backtracking;

public class Heaps {
	private int[] a;

	private void swap(int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public Heaps(int n) {
		a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = i + 1;
		this.generate(n);
	}

	public void generate(int N) {
		// System.out.println("generate(" + N + ")");
		if (N == 0)
			doit();
		for (int c = 0; c < N; c++) {
			generate(N - 1);
			// System.out.print("Swapping: " + c + "," + N);
			swap(N % 2 == 1 ? 0 : c, N - 1);
		}
	}

	public void doit() {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		Heaps p = new Heaps(4);
	}
}
