package backtracking;

public class Permute {
	private int[] a;

	private void swap(int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public Permute(int n) {
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
			// System.out.print("Swapping: " + c + "," + N);
			swap(c, N - 1); // swap(0, 7)
			generate(N - 1);
			// System.out.print("Swapping: " + c + "," + N);
			swap(c, N - 1);
		}
	}

	public void doit() {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		Permute p = new Permute(4);
	}
}