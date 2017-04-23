package matrices;

import java.util.Scanner;

public class LUDecomposition {
	public static void main(String args[]) {
		System.out.println("Enter the dimension of the matrix:");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		double[][] mat = new double[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				mat[i][j] = sc.nextDouble();

		if (n == 2) {
			double[][] l = new double[n][n];
			l[0][0] = l[1][1] = 1;
			l[0][1] = 0;

			double[][] u = new double[n][n];
			u[1][0] = 0;

			u[0][0] = mat[0][0];
			u[0][1] = mat[0][1];

			l[1][0] = mat[1][0] / mat[0][0];
			u[1][1] = mat[1][1] - (l[1][0] * u[0][1]); // mat[2][2]-(mat[2][1]*mat[1][2]/mat[1][1]);

			System.out.println("The L Component is:");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					System.out.print(" " + l[i][j]);
				System.out.println();
			}
			System.out.println("The U Component is:");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					System.out.print(" " + u[i][j]);
				System.out.println();
			}

		}
		if (n == 3) {
			double[][] l = new double[n][n];
			l[0][0] = l[1][1] = l[2][2] = 1;
			l[0][1] = l[0][2] = l[1][2] = 0;

			double[][] u = new double[n][n];
			u[1][0] = u[2][0] = u[2][1] = 0;

			u[0][0] = mat[0][0];
			u[0][1] = mat[0][1];
			u[0][2] = mat[0][2];

			l[1][0] = mat[1][0] / mat[0][0];
			u[1][1] = mat[1][1] - (l[1][0] * u[0][1]); // mat[2][2]-(mat[2][1]*mat[1][2]/mat[1][1]);
			u[1][2] = mat[1][2] - (l[1][0] * u[0][2]);

			l[2][0] = mat[2][0] / u[0][0];
			l[2][1] = (mat[2][1] - l[2][1] * u[0][1]) / u[1][1];
			u[2][2] = mat[2][2] - (l[2][0] * u[0][2]) - (l[2][1] * u[1][2]);

			System.out.println("The L Component is:");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					System.out.print(" " + l[i][j]);
				System.out.println();
			}
			System.out.println("The U Component is:");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					System.out.print(" " + u[i][j]);
				System.out.println();
			}
		}
		sc.close();
	}
	
	
	public Matrix LU(){
		Matrix result = new Matrix(r,c);
		Matrix tmp=new Matrix(r,c*2);
		tmp.print();
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++){
				tmp.set(i,j,get(i,j));
			}
		}
		tmp.print();
		for(int i=0;i<r;i++){
			for(int j=c;j<2*c;j++){
				if(i==j-c)
					tmp.set(i, j, 1);
				else
					tmp.set(i, j,0);
			}
		}
		tmp.print();
		for(int k=0;k<r-1;k++){
			for(int i=k+1;i<r;i++){
				int g=gcd(tmp.get(k,k),tmp.get(i,k));
				int l=g/tmp.get(k,k);
				int r=g/tmp.get(i,k);
				for(int j=0;j<c*2;j++)
					tmp.set(i, j, tmp.get(i, j)*r-tmp.get(k, j)*l);
			}

		}
		tmp.print();
		return result;
	}

}

